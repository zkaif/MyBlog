import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import top.u8u.base.utils.JavaUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dim on 17-11-4.
 */
public class Test {
    private static String URL_HEAD="https://www.zhoukaifan.com/blog/";

    private static String DD_SAVE="http://data.zz.baidu.com/urls?site=www.zhoukaifan.com&token=ZVqX9R45BTN7UK3o";
    private static String DD_UPDATE="http://data.zz.baidu.com/update?site=www.zhoukaifan.com&token=ZVqX9R45BTN7UK3o";
    private static String DD_DEL="http://data.zz.baidu.com/del?site=www.zhoukaifan.com&token=ZVqX9R45BTN7UK3o";


    public static void main(String[] args){
        PathMatcher matcher = new AntPathMatcher();
        boolean s = matcher.match("/stroage/download/**","/stroage/download/926742477514244098/9c14881c-0a1d-4fc0-9011-9a1b8bf632c6.png");
        return;
    }

    public static String publishBlogContents(List<String> urls) {
        StringBuilder content = new StringBuilder();
        for (String url : urls) {
            content.append(URL_HEAD).append(url).append("\n");
        }
        return content.toString();
    }
}
