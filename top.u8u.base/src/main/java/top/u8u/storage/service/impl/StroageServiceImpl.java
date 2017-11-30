package top.u8u.storage.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.u8u.base.utils.JavaUtil;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.Content;
import top.u8u.storage.dao.IContentDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dim on 17-10-14.
 */
@Service("stroageService")
public class StroageServiceImpl extends StroageServiceAliImpl {
    @Autowired
    private IContentDao contentDao;
    private static Log log = LogFactory.getLog(StroageServiceImpl.class);


    @Transactional(readOnly = false)
    @Override
    public boolean saveAndUpdateContent(Content content) {
        if (content == null) {
            log.warn("content is null");
            return false;
        }
        return contentDao.saveOrUpdate(content);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteContentByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            log.warn("ids is null");
            return false;
        }
        return contentDao.deletesById(ids);
    }

    @Transactional(readOnly = true)
    @Override
    public Content getContentById(long id) {
        if (id <= 0) {
            log.warn("id is < 0");
            return null;
        }
        return contentDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Content> getContentList(Content content) {
        if (content == null) {
            content = new Content();
        }
        if (content.getPageD() <= 0) {
            content.setPageD(1);
        }
        if (content.getCountD() <= 0) {
            content.setCountD(Integer.MAX_VALUE);
        }
        return contentDao.getAllPage(content);
    }


    @Override
    public Content saveObjectToContent(InputStream inputStream, String path, String suffix) {
        if (inputStream == null) {
            log.warn("inputStream is null");
            return null;
        }
        if (path == null) {
            path = "";
        }
        if (suffix == null) {
            suffix = "";
        }
        Content content = super.saveObjectToContent(inputStream, path, suffix);
        if (content==null){
            return content;
        }
        if (!this.saveAndUpdateContent(content)){
            return null;
        }
        return content;
    }

    @Override
    public InputStream getObjectByContent(Content content) {
        if (content == null || StringUtils.isEmpty(content.getOssKey())) {
            return null;
        }
        return super.getObjectByContent(content);
    }

    @Override
    public String getObjectStrByContent(Content content) {
        if (content == null || StringUtils.isEmpty(content.getOssKey())) {
            return null;
        }
        InputStream inputStream = super.getObjectByContent(content);
        String contentStr = JavaUtil.InputStreamToString(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentStr;
    }


    @Override
    public Content saveStringToContent(String contentStr, String path, String suffix){
        if (contentStr == null) {
            log.warn("contentStr is null");
            contentStr = "";
        }
        if (path == null) {
            path = "";
        }
        if (suffix == null) {
            suffix = "";
        }
        Content content = super.saveStringToContent(contentStr, path, suffix);
        if (content==null){
            return content;
        }
        if (!this.saveAndUpdateContent(content)){
            return null;
        }
        return content;
    }

    @Override
    public boolean downloadContent(Content content){
        return downloadContent(content,null);
    }

    @Override
    public boolean downloadContent(Content content,String uri){
        if (content==null){
            log.warn("content is null");
            return false;
        }
        content.setUriFlag(true);
        if (StringUtils.isEmpty(uri)) {
            uri = "stroage/download/" + content.getId() + "/" + content.getFileName();
        }
        content.setUri(uri);
        boolean b = contentDao.update(content);
        if (!b){
            content.setUriFlag(false);
            content.setUri(null);
        }
        return b;
    }

    public Content updateStringToContent(String contentStr,Content content){
        if (contentStr == null) {
            log.warn("contentStr is null");
            contentStr = "";
        }
        if (content == null) {
            log.warn("content is null");
            return null;
        }
        content = super.updateStringToContent(contentStr,content);
//        if (content==null){
//            return content;
//        }
//        if (!contentDao.update(content)){
//            return null;
//        }
        return content;
    }
    public Content updateObjectToContent(InputStream inputStream,Content content){
        if (inputStream == null) {
            log.warn("inputStream is null");
            return null;
        }
        if (content == null) {
            log.warn("content is null");
            return null;
        }
        content = super.updateObjectToContent(inputStream, content);
//        if (content==null){
//            return content;
//        }
//        if (!this.update(content)){
//            return null;
//        }
        return content;
    }
}
