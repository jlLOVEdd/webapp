package com.wdd.test.comm.util;

import com.wdd.test.comm.log.SysLog;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 10:37 2018/3/16
 */
@Component
public class JedisUtils {

   @Resource
    private RedisTemplate redisTemplate;

    private JedisUtils() {
    }

    public static final JedisUtils getInstance() {
        return JedisUtilHolder.INSTANCE;
    }

    private static class JedisUtilHolder {
        private static final JedisUtils INSTANCE = new JedisUtils();
    }

    /**
     * 根据key value 缓存到redis，设置过期时间
     *
     * @param key   key
     * @param value value
     * @param time  过期时间,单位秒
     * @return
     */
    public boolean cacheValue(String key, String value, long time) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, value);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            SysLog.debug("缓存[{}]成功，value[{}]", key, value);
            return true;
        } catch (Exception e) {
            SysLog.error("缓存[{}]失败, value[{}]", key, value, e);
        }
        return false;
    }

    /**
     * 根据key value 缓存到redis，不设置过期时间
     *
     * @param key   key
     * @param value value
     * @return
     */
    public boolean cacheValue(String key, String value) {
        return cacheValue(key, value, -1);
    }

    /**
     * 根据MAP设置 缓存到redis，
     * @param map
     * @return
     */
    public boolean cacheValue(Map<String,Object> map) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            valueOps.multiSet(map);
            SysLog.debug("缓存{}成功", JsonUtils.toJSONString(map));
            return true;
        } catch (Exception e) {
            SysLog.error("缓存{}失败,", JsonUtils.toJSONString(map), e);
        }
        return false;
    }

    /**
     * 检查key是否存在
     *
     * @param key 可以
     * @return
     */
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            SysLog.error("判断缓存存在失败key[{}, ", key, e);
        }
        return false;
    }

    /**
     * 根据Key获取Value
     *
     * @param key key
     * @return
     */
    public Optional<String> getValue(String key) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return Optional.ofNullable(valueOps.get(key));
        } catch (Exception e) {
            SysLog.error("获取缓存失败key[{}]", key, e);
        }
        return Optional.empty();
    }

    /**
     * 根据keys 删除 value
     *
     * @param keys key
     * @return
     */
    public boolean remove(List<String> keys) {
        try {
            redisTemplate.delete(keys);
            SysLog.debug("删除缓存成功key{}", keys);
            return true;
        } catch (Exception e) {
            SysLog.error("删除缓存失败key{}", keys, e);
        }
        return false;
    }
    /**
     * 根据keys 删除 value
     *
     * @param key key
     * @return
     */
    public boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            SysLog.debug("删除缓存成功key[{}]", key);
            return true;
        } catch (Exception e) {
            SysLog.error("删除缓存失败key[{}]", key, e);
        }
        return false;
    }

    /**
     * 根据key 、 对象缓存，不设置过期时间
     *
     * @param key 可以
     * @param obj 对象
     * @return
     */
    public boolean cacheValue(String key, Object obj) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, obj);
            SysLog.debug("缓存[{}]成功, value[{}]", key, JsonUtils.toJSONString(obj));
            return true;
        } catch (Throwable t) {
            SysLog.error("缓存[{}]失败, value[{}]", key, JsonUtils.toJSONString(obj));
        }
        return false;
    }

    /**
     * 根据key获取对象
     *
     * @param key 可以
     * @return
     */
    public Optional<Object> getObjValue(String key) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            return Optional.ofNullable(valueOps.get(key));
        } catch (Throwable e) {
            SysLog.error("获取list缓存失败key[{}]", key, e);
        }
        return Optional.empty();
    }

    /**
     * 根据key* 模糊查询value 列表
     *
     * @param pattern
     * @return
     */
    public List<Object> getObjList(String pattern) {
        return getObjList(redisTemplate.keys(pattern));
    }

    /**
     * 根据Set<key> 获取List<String>
     *
     * @param keySet
     * @return
     */
    public List<Object> getObjList(Set<String> keySet) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            return valueOps.multiGet(keySet);
        } catch (Exception e) {
            SysLog.error("获取list缓存失败key[{}]", keySet, e);
        }
        return null;
    }

    public boolean cacheZSetValue(String key, Object obj, Integer score) {
        try {
            ZSetOperations<String, Object> valueOps = redisTemplate.opsForZSet();
            valueOps.add(key, obj, score == null ? 1 : score);
            SysLog.debug("缓存[{}]成功, value[{}]", key, JsonUtils.toJSONString(obj));
            return true;
        } catch (Throwable t) {
            SysLog.error("缓存[{}]失败, value[{}]", key, JsonUtils.toJSONString(obj));
        }
        return false;
    }

    public boolean cacheZSetValue(String key, Set<ZSetOperations.TypedTuple<Object>> tuples) {
        try {
            ZSetOperations<String, Object> valueOps = redisTemplate.opsForZSet();
            valueOps.add(key,tuples);
            SysLog.debug("缓存[{}]成功, value[{}]", key, JsonUtils.toJSONString(tuples));
            return true;
        } catch (Throwable t) {
            SysLog.error("缓存[{}]失败, value[{}]", key, JsonUtils.toJSONString(tuples));
        }
        return false;
    }

    public Optional<Set<Object>> getZSetValue(String key) {
        try {
            ZSetOperations<String, Object> valueOps = redisTemplate.opsForZSet();

            SysLog.debug("get zset [{}]成功 ", key);
            return Optional.ofNullable(valueOps.range(key, 0, -1));
        } catch (Throwable t) {
            SysLog.error("get zset[{}]失败, ", key);
        }
        return Optional.empty();
    }

    public boolean removeZSetValue(String key, Object obj) {
        try {
            ZSetOperations<String, Object> valueOps = redisTemplate.opsForZSet();
            valueOps.remove(key, obj);
            SysLog.debug("删除[{}]成功, value[{}]", key, JsonUtils.toJSONString(obj));
            return true;
        } catch (Throwable t) {
            SysLog.error("删除[{}]失败, value[{}]", key, JsonUtils.toJSONString(obj));
        }
        return false;
    }

    /**
     * 根据 pattern 获取keys
     * @param pattern
     * @return
     */
    public Optional<Set<String>> getKeysByPattern(String pattern) {
        try {
            return Optional.of(redisTemplate.keys(pattern));
        } catch (Throwable t) {
            SysLog.error("根据 pattern:{} 获取keys 失败",pattern,t);
        }
        return Optional.empty();
    }

    /**
     * 根据keys获取values
     * @param keys
     * @return
     */
    public Optional<List<Object>> getValueByKeys(Set<String> keys) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            return Optional.of(valueOps.multiGet(keys));
        } catch (Throwable t) {
            SysLog.error("根据keys:{}获取value失败", keys,t);
        }
        return  Optional.empty();
    }
}
