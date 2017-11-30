package top.u8u.base.utils;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by dim on 17-9-28.
 */
public class HtmlUtil {
    public static String createHtml(String html, Map<String,String> data){
        if (StringUtils.isEmpty(html)){
            return null;
        }
        for(String key : data.keySet()){
            if (StringUtils.isEmpty(data.get(key))){
                continue;
            }
            html = html.replace("$["+key+"]",data.get(key));
        }
        return html;
    }
}
