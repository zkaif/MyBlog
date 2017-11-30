package top.u8u.storage.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.u8u.base.utils.JavaUtil;
import top.u8u.base.vo.Message;
import top.u8u.db.domain.Content;
import top.u8u.storage.service.IStroageService;
import top.u8u.storage.service.impl.StroageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by dim on 17-10-14.
 */
@RestController
@RequestMapping("stroage")
public class StroageController {
    @Autowired
    private IStroageService stroageService;
    private static Log log = LogFactory.getLog(StroageServiceImpl.class);

    @RequestMapping("download/{id}/{fileName}.{suffix}")
    public void download(HttpServletResponse response, @PathVariable("id") Long id,
                         @PathVariable("fileName") String fileName,@PathVariable("suffix") String suffix) {
        Content content = stroageService.getContentById(id);
        if (content == null) {
            log.warn("content is null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (!content.isUriFlag()|| StringUtils.isEmpty(content.getOssKey())) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if(!StringUtils.isEmpty(suffix)){
            fileName = fileName + "." + suffix;
        }
        if (!(content.getFileName()).equals(fileName)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = stroageService.getObjectByContent(content);
            if (inputStream == null) {
                log.warn("inputStream is null");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            outputStream = response.getOutputStream();
            int len = JavaUtil.inToOut(inputStream, outputStream);
            response.setContentLength(len);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Message update(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Message message = new Message();
        if (file==null){
            return message;
        }
        String[] strings = file.getOriginalFilename().split("\\.");
        String suffix="";
        if (strings.length>0){
            suffix="."+strings[strings.length-1];
        }
        try {
            Content content = stroageService.saveObjectToContent(file.getInputStream(),"stroage",suffix);
            boolean b = stroageService.downloadContent(content);
            if (!b){
                message.setCode(Message.Code.ERROR.ordinal());
                return message;
            }
            message.add(content.getUri());
            return message;
        } catch (IOException e) {
            message.setCode(Message.Code.ERROR.ordinal());
            return message;
        }
    }
}
