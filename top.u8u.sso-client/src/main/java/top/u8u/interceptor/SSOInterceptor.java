package top.u8u.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.u8u.base.service.IMemcachedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import top.u8u.vo.UserInfo;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dim on 17-10-3.
 */
public class SSOInterceptor extends HandlerInterceptorAdapter {
    private static Log log = LogFactory.getLog(SSOInterceptor.class);

    private IMemcachedService memcachedService;

    private String sso_anon_uri="";

    public IMemcachedService getMemcachedService() {
        return memcachedService;
    }

    public void setMemcachedService(IMemcachedService memcachedService) {
        this.memcachedService = memcachedService;
    }

    public String getSso_anon_uri() {
        return sso_anon_uri;
    }

    public void setSso_anon_uri(String sso_anon_uri) {
        this.sso_anon_uri = sso_anon_uri;
        this.anonUris = Arrays.asList(sso_anon_uri.split(";"));
    }

    private List<String> anonUris;

    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        String serviceKey = request.getParameter("serviceKey");
        String sId = request.getParameter("SID");
        //允许匿名的资源
        if (isAnonRequest(servletPath)) {
            return true;
        }
        //服务器间调用
        if (isServiceRequest(serviceKey)) {
            return true;
        }
        //登录用户调用
        if (isUsersRequest(sId)) {
            return true;
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\"code\":\"1\",\"des\":\"no login!!!\",\"mgs\":{},\"body\":[\"\"]}";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonStr);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    private boolean isAnonRequest(String uri) {
        for (String path : anonUris) {
            boolean b = matcher.match(path, uri);
            if (b) {
                log.debug("accept url = " + uri + ", matcher anno pattern = " + path);
                return true;
            }
        }
        return false;
    }

    private boolean isServiceRequest(String serviceKey) {
//        String serviceKeyNew = (String) memcachedService.get("serviceKeyNew");
//        if (serviceKeyNew.equals(serviceKey)){
//            return true;
//        }
//        String serviceKeyOld = (String) memcachedService.get("serviceKeyOld");
//        if (serviceKeyOld.equals(serviceKey)){
//            return true;
//        }
        return false;
    }

    private boolean isUsersRequest(String sId) {
        Object o = memcachedService.get(sId);
        if (o != null && o instanceof UserInfo) {
            UserInfo userInfo = (UserInfo) o;
            if (sId.equals(userInfo.getsId())) {
                return true;
            }
        }
        return false;
    }

}
