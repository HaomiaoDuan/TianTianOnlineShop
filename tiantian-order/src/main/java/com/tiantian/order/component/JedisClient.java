package com.tiantian.order.component;

public interface JedisClient {

    /**
     * 仿照redis的部分命令的接口
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);
    public String get(String key);
    public Long hset(String key, String item, String value);
    public String hget(String key, String item);
    public Long incr(String key);
    public Long decr(String key);
    public Long expire(String key, int second);
    public Long ttl(String key);
    public Long hdel(String key,String item);
}
