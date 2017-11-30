package top.u8u.storage.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import top.u8u.db.domain.Content;
import top.u8u.storage.service.IStroageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by dim on 17-10-14.
 */
public abstract class StroageServiceAliImpl implements IStroageService{
    @Autowired
    protected OSSClient ossClient;
    @Value("${oss.bucket.name}")
    private static String OSS_BUCKET_NAME = "";

    @Override
    public Content saveStringToContent(String contentStr, String path, String suffix) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(contentStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
        if (byteArrayInputStream==null)
            return null;
        Content content = saveObjectToContent(byteArrayInputStream,path,suffix);
        try {
            byteArrayInputStream.close();
        } catch (IOException e) {
            return null;
        }
        return content;
    }

        @Override
    public Content saveObjectToContent(InputStream inputStream, String path, String suffix){
        String[] paths = path.split("/");
        StringBuilder builder = new StringBuilder();
        for (String p:paths) {
            if (StringUtils.isEmpty(p))
                continue;
            builder.append(p);
            builder.append("/");
        }
        path = builder.toString();
        builder = new StringBuilder();
        builder.append(UUID.randomUUID().toString());
        builder.append(suffix);
        String fileName = builder.toString();
        String key = path+fileName;
        Content content = new Content();
        content.setcTime(new Date());
        content.setOssKey(key);
        content.setFileName(fileName);
        content.setUriFlag(false);
        try {
            ossClient.putObject(OSS_BUCKET_NAME, key, inputStream);
        }catch (Exception e){
            return null;
        }
        return content;
    }
    @Override
    public InputStream getObjectByContent(Content content){
        OSSObject ossObject;
        try {
            ossObject = ossClient.getObject(OSS_BUCKET_NAME, content.getOssKey());
        }catch (Exception e){
            return null;
        }
        if(ossObject==null){
            return null;
        }
        return ossObject.getObjectContent();
    }

    public Content updateStringToContent(String contentStr,Content content){
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(contentStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
        if (byteArrayInputStream==null)
            return null;
        content = updateObjectToContent(byteArrayInputStream,content);
        try {
            byteArrayInputStream.close();
        } catch (IOException e) {
            return null;
        }
        return content;
    }
    public Content updateObjectToContent(InputStream inputStream,Content content){
        OSSObject ossObject;
        try {
            ossClient.putObject(OSS_BUCKET_NAME, content.getOssKey(),inputStream);
        }catch (Exception e){
            return null;
        }
        return content;
    }
}
