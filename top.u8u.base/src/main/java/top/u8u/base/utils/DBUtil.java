package top.u8u.base.utils;

/**
 * Created by dim on 17-9-11.
 */
public class DBUtil {
    public static boolean idLegal(Long id){
        if (id<=0){
            return false;
        }
        return true;
    }
}
