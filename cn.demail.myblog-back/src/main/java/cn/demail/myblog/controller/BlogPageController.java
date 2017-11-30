package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.utils.JavaUtil;
import top.u8u.base.vo.Message;
import top.u8u.db.domain.BlogType;
import top.u8u.db.domain.Content;
import top.u8u.storage.service.IStroageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by dim on 17-10-15.
 */
@RestController
public class BlogPageController {
    @Autowired
    private IStroageService stroageService;
    @Autowired
    private IModuleService moduleService;

    private static Log log = LogFactory.getLog(BlogPageController.class);

    @RequestMapping("blog/{id}.html")
    public void blogPage(HttpServletResponse response, @PathVariable("id") Long id) {
        Content content = stroageService.getContentById(id);
        if (content == null) {
            log.warn("content is null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (!content.isUriFlag() || StringUtils.isEmpty(content.getOssKey())) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String[] urls = (content.getOssKey()).split("/");
        if (urls.length <= 0 || StringUtils.isEmpty(urls[0]) || !urls[0].equals("blogHtml")) {
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

    @RequestMapping("sys/flushAll")
    public Message flushAll(){
        Message message = new Message();
        moduleService.flushAll();
        return message;
    }
}
