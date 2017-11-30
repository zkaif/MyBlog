package top.u8u.base.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by dim on 17-9-2.
 */
public class JavaUtil {
    private static final int TIME_OUT = 60000;
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid.replace("-", "");
        return uuid;
    }

    public static boolean listIsEmpty(List list) {
        if (null == list || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String readFile(String targetURL) {
        InputStream in = null;
        try {
            in = new FileInputStream(targetURL);
            return JavaUtil.InputStreamToString(in);
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String InputStreamToString(InputStream in) {
        try {
            int len = 0;
            byte[] data = new byte[2048];
            StringBuilder stringBuilder = new StringBuilder();
            while ((len = in.read(data)) != -1) {
                stringBuilder.append(new String(Arrays.copyOfRange(data, 0, len),"UTF-8"));
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean saveFile(String html, String targetURL) {
        File file = new File(targetURL);
        if (!file.isFile()) {
            return false;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            out.write(html.getBytes("UTF-8"));
            out.flush();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean deleteFile(String targetURL) {
        File file = new File(targetURL);
        if (file.isFile()) {
            return file.delete();
        }
        return false;
    }

    public static int inToOut(InputStream inputStream, OutputStream outputStream) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[2048];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        outputStream.flush();
        return byteCount;
    }

    public static String md5(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(password.getBytes("UTF-8"));
            String hexString = "";
            for (byte b : bs) {
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String postForText(String url, Map<String, String> headers, String content, String charset) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        String text = "";
        Set<String> keys = null;
        String value = null;
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIME_OUT)
                .setConnectionRequestTimeout(10000).setSocketTimeout(TIME_OUT).build();
        post.setConfig(requestConfig);
        if (null != headers && headers.size() > 0) {
            keys = headers.keySet();
            for (String key : keys) {
                if (StringUtils.isEmpty(key)) {
                    continue;
                }
                value = headers.get(key);
                if (!StringUtils.isEmpty(value)) {
                    post.setHeader(key, value);
                }
            }
        }
        HttpResponse response = null;
        try {
            if (!StringUtils.isEmpty(content)) {
                StringEntity entity = new StringEntity(content, charset);
                post.setEntity(entity);
            }
//            if (isHttps(url)) {
//                response = httpsClient.execute(post);
//            } else {
//                response = httpClient.execute(post);
//            }
            response = httpClient.execute(post);
            post.completed();

            if (response.getStatusLine().getStatusCode() == 200) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                text = new String(out.toByteArray(), "UTF-8");
                out.close();
            }
            return text;

        } catch (Exception e) {
        } finally {
            post.releaseConnection();
            if (null != response) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
        return text;
    }
}
