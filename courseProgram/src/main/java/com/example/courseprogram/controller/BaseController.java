package com.example.courseprogram.controller;

import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.service.PdfService;
import com.example.courseprogram.utils.ComDataUtil;
import com.example.courseprogram.utils.CommonMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * BaseController 主要时为前台框架的基本数据管理提供的Web请求服务
 */
// origins： 允许可访问的域列表
// maxAge:准备响应前的缓存持续的最大时间（以秒为单位）。
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/base")
@Slf4j
public class BaseController {
    @Value("${web.upload-path}"+"photo\\")    //环境配置变量获取
    private String photoFolder;  //服务器端数据存储
    @Autowired
    private UserRepository userRepository;  //用户数据操作自动注入
    @Autowired
    PdfService pdfService;

//    /**
//     * 获取所有角色信息的列表
//     * 前台请求参数 无
//     * 返回前端存储角色信息的OptionItem的List
//     *
//     * @return
//     */
//
//    @PostMapping("/getRoleOptionItemList")
//    @PreAuthorize("hasRole('ADMIN')")
//    public OptionItemList getRoleOptionItemList(@Valid @RequestBody DataRequest dataRequest) {
//        List<UserType> uList = userTypeRepository.findAll();
//        OptionItem item;
//        List<OptionItem> itemList = new ArrayList();
//        for (UserType ut : uList) {
//            itemList.add(new OptionItem(ut.getId(), null, ut.getName().name()));
//        }
//        return new OptionItemList(0, itemList);
//    }

//    /**
//     * 获取某个用户类型 userTypeId 菜单树 信息
//     * 前台请求参数 无
//     * 返回前端某个用户类型 userTypeId 菜单树对象MyTreeNode（这个是一个递归的树节点对象）
//     *
//     * @return
//     */
//
//    @PostMapping("/getMenuTreeNodeList")
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<MyTreeNode> getMenuTreeNodeList(@Valid @RequestBody DataRequest dataRequest) {
//        return baseService.getMenuTreeNodeList();
//    }


    /**
     * 获取服务器端的图片文件的数据
     * 前台请求参数  文件路径
     * 返回前端图片数据的二进制数据留
     *
     * @return
     */
    @PostMapping("/getFileByteData")
    public ResponseEntity<StreamingResponseBody> getFileByteData(@Valid @RequestBody DataRequest dataRequest) {
        String fileName = dataRequest.getString("fileName");
        try {
            File file = new File(photoFolder + fileName);
            int len = (int) file.length();
            byte data[] = new byte[len];
            FileInputStream in = new FileInputStream(file);
            in.read(data);
            in.close();
            MediaType mType = new MediaType(MediaType.APPLICATION_OCTET_STREAM);
            StreamingResponseBody stream = outputStream -> {
                outputStream.write(data);
            };
            return ResponseEntity.ok()
                    .contentType(mType)
                    .body(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }


    /**
     * 上传文件服务
     * 前台请求参数  uploader 信息  remoteFile 服务器文件路径  fileName 前端上传的文件名
     * 返回前端 正常操作信心和异常操作信息
     *
     * @return
     */
    @PostMapping(path = "/uploadPhoto")
    public DataResponse uploadPhoto(@RequestBody byte[] barr,
                                    @RequestParam(name = "uploader") String uploader,
                                    @RequestParam(name = "remoteFile") String remoteFile,
                                    @RequestParam(name = "fileName") String fileName) {
        try {
            OutputStream os = new FileOutputStream(new File(photoFolder + remoteFile));
            os.write(barr);
            os.close();
            return DataResponse.ok();
        } catch (Exception e) {
            return DataResponse.failure(402,"错误:"+e.getMessage());
        }
    }

    /**
     * 上传Html字符串流， 用于生成html字符流和PDF数据流的源HTML， 保存的内存MAP中
     * 前台请求参数  html 前端传过来的 html字符串
     * 返回前端 字符串保存的MAP的key,用于下载html，PDF的主键
     *
     * @return
     */

    @PostMapping("/uploadHtmlString")
    public DataResponse uploadHtmlString(@Valid @RequestBody DataRequest dataRequest) {
        String str = dataRequest.getString("html");
        String html = new String(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8)));
        System.out.println(html);
        int htmlCount = ComDataUtil.getInstance().addHtmlString(html);
        return DataResponse.success(htmlCount);
    }

    /**
     * 获取Html页面数据，
     * 前台请求参数  htmlCount 获取原始的前端传送后端保存的Html的主键
     * 返回前端 html页面， 前端WebPage可以直接访问的页面
     *
     * @return
     */

    @GetMapping("/getHtmlPage")
    public ResponseEntity<StreamingResponseBody> htmlGetBaseHtmlPage(HttpServletRequest request) {
        String htmlCountStr = request.getParameter("htmlCount");
        Integer htmlCount = Integer.parseInt(htmlCountStr);
        String html = ComDataUtil.getInstance().getHtmlString(htmlCount);
        MediaType mType = new MediaType(MediaType.TEXT_HTML, StandardCharsets.UTF_8);
        try {
            byte data[] = html.getBytes();
            StreamingResponseBody stream = outputStream -> {
                outputStream.write(data);
            };
            return ResponseEntity.ok()
                    .contentType(mType)
                    .body(stream);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 获取PDF 数据，用于前端PDFView显示，
     * 前台请求参数  htmlCount 获取原始的前端传送后端保存的Html的主键
     * 返回前端 PDF数据的二进制数据流， 系统将html转换为PDF格式数据
     *
     * @return
     */

    @PostMapping("/getPdfData")
    public ResponseEntity<StreamingResponseBody> getPdfData(@Valid @RequestBody DataRequest dataRequest) {
        Integer htmlCount = dataRequest.getInteger("htmlCount");
        String content = ComDataUtil.getInstance().getHtmlString(htmlCount);
        String head = "<!DOCTYPE html><html><head><style>html { font-family: \"SourceHanSansSC\", \"Open Sans\";}</style><meta charset='UTF-8' /><title>Insert title here</title></head><body>";
        content = head + content + "</body></html>";
        content = CommonMethod.removeErrorString(content, "&nbsp;", "style=\"font-family: &quot;&quot;;\"");
        return pdfService.getPdfDataFromHtml(content);
    }


    //  Web 请求
    @PostMapping("/getPhotoImageStr")
    public DataResponse getPhotoImageStr(@Valid @RequestBody DataRequest dataRequest) {
        String fileName = dataRequest.getString("fileName");
        String str = "";
        try {
            File file = new File(photoFolder + fileName);
            int len = (int) file.length();
            byte data[] = new byte[len];
            FileInputStream in = new FileInputStream(file);
            in.read(data);
            in.close();
            String imgStr = "data:image/png;base64,";
            String s = new String(Base64.getEncoder().encode(data));
            imgStr = imgStr + s;
            return CommonMethod.getReturnData(imgStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonMethod.getReturnMessageError("下载错误！");
    }

    @PostMapping(value = "/uploadPhotoWeb", consumes = {"multipart/form-data"})
    public DataResponse uploadPhotoWeb(@RequestParam Map pars, @RequestParam("file") MultipartFile file) {
        try {
            String remoteFile = CommonMethod.getString(pars, "remoteFile");
            InputStream in = file.getInputStream();
            int size = (int) file.getSize();
            byte[] data = new byte[size];
            in.read(data);
            in.close();
            OutputStream os = new FileOutputStream(new File(photoFolder + remoteFile));
            os.write(data);
            os.close();
            return CommonMethod.getReturnMessageOK();
        } catch (Exception e) {

        }
        return CommonMethod.getReturnMessageOK();
    }

}
