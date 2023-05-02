package com.example.demo;

import redis.clients.jedis.Jedis;

/**
 * @Author yushi
 * @Description TODO
 * @Date 2023/5/1 15:41
 */
public class RedisTest {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println(jedis.ping());
    }
}
