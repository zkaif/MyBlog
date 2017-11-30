package top.u8u.base.service.impl;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.util.StringUtils;
import top.u8u.base.service.IMemcachedService;

import java.util.concurrent.TimeoutException;

/**
 * Created by dim on 17-10-2.
 */
public class MemcachedServiceXmemcachedImpl implements IMemcachedService {
    private MemcachedClient memcachedClient;

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public boolean set(String key, Object value) {
        return set(key, value, 0);
    }

    @Override
    public boolean set(String key, Object value, int timeout) {
        if (StringUtils.isEmpty(key) || value == null || timeout < 0) {
            return false;
        }
        try {
            return memcachedClient.set(key, timeout, value);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            return memcachedClient.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        try {
            return memcachedClient.delete(key);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void flushAll() {
        try {
            memcachedClient.flushAll();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }
}
