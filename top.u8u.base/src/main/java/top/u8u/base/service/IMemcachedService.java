package top.u8u.base.service;

/**
 * Created by dim on 17-10-2.
 */
public interface IMemcachedService {
    boolean set(String key, Object value);

    boolean set(String key, Object value, int timeout);

    Object get(String key);

    boolean delete(String key);

    void flushAll();
}
