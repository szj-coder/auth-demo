package com.example.authdemo.learn.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapModifyLearn {

    /**
     * HashMap
     * 更新，成功且立即生效
     * 删除，迭代失败，删除成功
     * 新增，迭代失败，新增成功
     */
    @Test
    public void hashMap() {
        HashMap<String, String> map = new HashMap<>();
        initMap(map);

        // 可以更新
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
            map.put("2", "3");
        }
        Assertions.assertEquals("{1=1, 2=3}", map.toString());

        // 不可以删除
        initMap(map);
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                map.remove("2");
            }
            Assertions.fail();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        Assertions.assertEquals("{1=1}", map.toString());

        // 新增
        initMap(map);
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                map.put("3", "3");
            }
            Assertions.fail();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        System.out.println(map);
    }

    /**
     * ConcurrentHashMap
     * 更新，成功且立即生效
     * 删除，删除成功，迭代成功，原始队列，删除成功
     * 新增，新增成功，迭代失败，新增后，新增成功
     */
    @Test
    public void concurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        initMap(map);

        // 可以更新
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
            map.put("2", "3");
        }
        Assertions.assertEquals("{1=1, 2=3}", map.toString());

        // 不可以删除
        initMap(map);
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                map.remove("2");
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        Assertions.assertEquals("{1=1}", map.toString());

        // 新增
        initMap(map);
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                map.put("3", "3");
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        Assertions.assertEquals("{1=1, 2=2, 3=3}", map.toString());
    }

    public void initMap(Map<String, String> map) {
        map.clear();
        map.put("1", "1");
        map.put("2", "2");
        System.out.println("init map:"+ map);
    }
}
