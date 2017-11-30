package top.u8u.storage.service;

import top.u8u.base.vo.Page;
import top.u8u.db.domain.Content;

import java.io.InputStream;
import java.util.List;

/**
 * Created by dim on 17-10-14.
 */
public interface IStroageService {
    boolean saveAndUpdateContent(Content content);
    boolean deleteContentByIds(List<Long> ids);
    Content getContentById(long id);
    Page<Content> getContentList(Content content);

    Content saveStringToContent(String contentStr, String path, String suffix);
    Content saveObjectToContent(InputStream inputStream, String path, String suffix);

    Content updateStringToContent(String contentStr,Content content);
    Content updateObjectToContent(InputStream inputStream,Content content);

    InputStream getObjectByContent(Content content);
    String getObjectStrByContent(Content content);


    boolean downloadContent(Content content);
    boolean downloadContent(Content content,String uri);
}
