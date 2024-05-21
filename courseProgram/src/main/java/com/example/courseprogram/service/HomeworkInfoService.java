package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.CourseRepository;
import com.example.courseprogram.repository.HomeworkInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkInfoService {

    @Autowired
    HomeworkInfoRepository homeworkInfoRepository;
    @Autowired
    CourseRepository courseRepository;

    @Value("${web.upload-path}"+"HomeworkInfo")
    String path;

    //检查信息是否完整
    public boolean checkInfo(HomeworkInfo homeworkInfo){
        return DataUtil.checkInfo(homeworkInfo);
    }

    public DataResponse upload(byte[] file,String fileName){
        if (!(file.length==0)) {
            String allowedTypes = "application/pdf, image/jpeg, image/png,application/zip,application/rar"; // 允许的文件类型
//            if (!Arrays.asList(allowedTypes.split(", ")).contains(file.getContentType())) {
//                return DataResponse.failure(402,"文件类型不允许");
//            }

            if (file.length > 209715200) { // 200MB
                return DataResponse.failure(402,"文件过大(最大200MB)");
            }
            try {
                String directoryPath=path+ "\\"+LocalDate.now();
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    // 目录不存在，创建目录及其所有必需的父目录
                    boolean isDirectoryCreated = directory.mkdirs();
                    if (!isDirectoryCreated) {
                        System.out.println("目录创建失败: " + directoryPath);
                    }
                }

                Path dirPath = Paths.get(directoryPath);

                // 文件处理逻辑，例如保存到服务器上的某个目录
                // 这里可以使用文件输出流来保存文件
                FileOutputStream fos = new FileOutputStream(dirPath +"\\"+ fileName);
                fos.write(file);
                fos.close();
                File nowFile = new File(dirPath +"\\"+ fileName);

                return DataResponse.okM("success:"+dirPath +"\\"+fileName);
            } catch (Exception e) {
                return DataResponse.failure(402,"File upload failed: " + e.getMessage());
            }
        } else {
            return DataResponse.failure(401,"请上传一个文件!");
        }

    }

    public ResponseEntity<byte[]> download(String url){
        try {
            HttpResponse<byte[]> response;
            url=url.replace((char) 32, (char) 92);
            Path filePath = Paths.get(url);
            Resource resource = new UrlResource(filePath.toUri());
            byte[] fileContent=Files.readAllBytes(filePath);
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok(fileContent);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //增加或修改
    public DataResponse addOrUpdHomeworkInfo(HomeworkInfo homeworkInfo){

        Course c=homeworkInfo.getCourse();
        if(c==null||c.getNumber()==null)return DataResponse.failure(401,"信息不完整");
        Optional<Course> opCourse=courseRepository.findCourseByNumber(c.getNumber());
        if(opCourse.isPresent()){
            c=opCourse.get();
            homeworkInfo.setCourse(c);
        }
        else{
            return DataResponse.failure(404,"未找到该课程！");
        }

        if(!checkInfo(homeworkInfo)){
            return DataResponse.failure(401,"信息不完整");
        }
        homeworkInfoRepository.saveAndFlush(homeworkInfo);
        return DataResponse.okM("添加成功");
    }

    //根据id删除一条
    public DataResponse deleteById(Integer id){
        if(id==null){
            return DataResponse.error(401,"信息不完整");
        }
        else if(!homeworkInfoRepository.existsById(id)){
            return DataResponse.error(404,"未找到这条作业信息");
        }
        else {
            homeworkInfoRepository.deleteByHomeworkInfoId(id);
            return DataResponse.okM("删除成功");
        }
    }

    //根据课程id查找
    public DataResponse findByCourseId(Integer id){
        List<HomeworkInfo> list=homeworkInfoRepository.findHomeworksInfoByCourse_CourseId(id);
        if(list.isEmpty())return DataResponse.error(404,"未找到课程对应的作业信息");
        else return DataResponse.success(list);
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(homeworkInfoRepository.findAll());
    }
}
