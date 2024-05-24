package com.example.courseprogram.service;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.openhtmltopdf.extend.FSSupplier;
import com.openhtmltopdf.extend.impl.FSDefaultCacheStore;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {
    @Autowired
    private ResourceLoader resourceLoader;

    private FSDefaultCacheStore fSDefaultCacheStore = new FSDefaultCacheStore();
    /**
     * 将HTML的字符串转换成PDF文件，返回前端的PDF文件二进制数据流数据流
     * @param htmlContent  HTML 字符流
     * @return PDF数据流兑现
     */
    public ResponseEntity<StreamingResponseBody> getPdfDataFromHtml(String htmlContent) {
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, null);
            builder.useFastMode();
            builder.useCacheStore(PdfRendererBuilder.CacheStore.PDF_FONT_METRICS, fSDefaultCacheStore);
            Resource resource = resourceLoader.getResource("classpath:font/SourceHanSansSC-Regular.ttf");
            InputStream fontInput = resource.getInputStream();
            builder.useFont(new FSSupplier<InputStream>() {
                @Override
                public InputStream supply() {
                    return fontInput;
                }
            }, "SourceHanSansSC");
            StreamingResponseBody stream = outputStream -> {
                builder.toStream(outputStream);
                builder.run();
            };

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(stream);

        }
        catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }
    }

    public static void generateTempPDF() throws Exception {
        PdfReader reader = null;
        PdfStamper ps = null;
        OutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            String fileName = "d:/test/form.pdf";//模板绝对路径
            reader = new PdfReader(fileName);
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);

            // 使用中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);

            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data());//渲染

            //必须要调用这个，否则文档不会生成的
            ps.setFormFlattening(true);
            if(ps != null){
                ps.close();
            }
            //生成pdf路径存放的路径
            fos = new FileOutputStream("d:/test/formout.pdf");
            fos.write(bos.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                fos.flush();
                fos.close();
            }
            if (bos != null){
                bos.close();
            }
            if(reader != null){
                reader.close();
            }
        }
    }

    /**
     * 填充模板中的数据
     */
    public static void fillData(AcroFields fields, Map<String, String> data) {
        try {
            for (String key : data.keySet()) {
                String value = data.get(key);
                // 为字段赋值,注意字段名称是区分大小写的
                fields.setField(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充数据源
     * 其中data存放的key值与pdf模板中的文本域值相对应
     */
    public static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        //key要与模板中的别名一一对应
        data.put("num", "2001");
        data.put("name", "张三");
        data.put("gender", "男");
        data.put("dept", "软件学院");
        return data;
    }
}
