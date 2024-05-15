package com.github.jsonzou.jmockdata;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author joker-pper 2024年05月15日 上午11:51:01
 */
public class MapDataTest {


    @Test
    public void testBeanMapEntity() {
        BeanMapEntity mockData = JMockData.mock(BeanMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData.getMap(), Integer.class, String.class);
    }


    @Test
    public void testBeanHashMapEntity() {
        BeanHashMapEntity mockData = JMockData.mock(BeanHashMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData.getMap(), Integer.class, String.class);
    }

    @Test
    public void testBeanLinkedHashMapEntity() {
        //验证LinkedHashMap接收
        BeanLinkedHashMapEntity mockData = JMockData.mock(BeanLinkedHashMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData.getMap(), Integer.class, String.class);
    }

    @Test
    public void testBeanExtendHashMapEntity() {
        BeanExtendHashMapEntity mockData = JMockData.mock(BeanExtendHashMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }

    @Test
    public void testBeanExtendLinkedHashMapEntity() {
        BeanExtendLinkedHashMapEntity mockData = JMockData.mock(BeanExtendLinkedHashMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }

    @Test
    public void testBeanExtendLinkedHashMapSuperEntity() {
        //验证下继承多次?
        BeanExtendLinkedHashMapSuperExtendOneEntity mockData = JMockData.mock(BeanExtendLinkedHashMapSuperExtendOneEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);

        BeanExtendLinkedHashMapSuperExtendTwoEntity mockData2 = JMockData.mock(BeanExtendLinkedHashMapSuperExtendTwoEntity.class);

        System.out.println(JSON.toJSONString(mockData2, true));
        Assert.assertNotNull(mockData2);
        checkMockData(mockData2, Integer.class, String.class);


        BeanExtendLinkedHashMapSuperExtendThreeEntity mockData3 = JMockData.mock(BeanExtendLinkedHashMapSuperExtendThreeEntity.class);

        System.out.println(JSON.toJSONString(mockData3, true));
        Assert.assertNotNull(mockData3);
        checkMockData(mockData3, Integer.class, String.class);

    }

    @Test
    public void testBeanExtendCrudeHashMapEntity() {
        //验证未指定具体类型时，按照最简单的来 key和value都是string类型
        BeanExtendCrudeHashMapEntity mockData = JMockData.mock(BeanExtendCrudeHashMapEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, String.class, String.class);
    }

    @Test
    public void testBeanExtendDefineGenericEntity() {
        //验证继承自泛型类 （继承泛型 LinkedHashMap<A, B> ）
        BeanExtendDefineGenericEntity mockData = JMockData.mock(BeanExtendDefineGenericEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }

    @Test
    public void testBeanExtendDefineGenericSuperEntity() {
        //验证继承前一个测试方法的类 （该父类 继承自泛型类 （继承泛型 LinkedHashMap<A, B> ））
        BeanExtendDefineGenericSuperEntity mockData = JMockData.mock(BeanExtendDefineGenericSuperEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }

    @Test
    public void testBeanExtendDefineGenericAgainEntity() {
        //验证继承自泛型类 比BeanExtendDefineGenericEntity多一次
        BeanExtendDefineGenericAgainEntity mockData = JMockData.mock(BeanExtendDefineGenericAgainEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }

    @Test
    public void testBeanExtendDefineGenericAgainSuperEntity() {
        //验证继承前一个测试方法的类
        BeanExtendDefineGenericAgainSuperEntity mockData = JMockData.mock(BeanExtendDefineGenericAgainSuperEntity.class);

        System.out.println(JSON.toJSONString(mockData, true));
        Assert.assertNotNull(mockData);
        checkMockData(mockData, Integer.class, String.class);
    }


    @Ignore
    @Test
    public void testBeanDefinePropertyGenericEntity() {
        //属性中存在泛型如果来自类上会被擦除 TODO
        try {
            BeanDefinePropertyGenericEntity<Integer, String> mockData = JMockData.mock(new TypeReference<BeanDefinePropertyGenericEntity<Integer, String>>() {
            });

            System.out.println(JSON.toJSONString(mockData, true));
            Assert.assertNotNull(mockData);
            checkMockData(mockData.getMap(), Integer.class, String.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testBeanExtendDefinePropertyGenericEntity() {
        //属性中存在泛型，继承
        try {
            BeanExtendDefinePropertyGenericEntity mockData = JMockData.mock(BeanExtendDefinePropertyGenericEntity.class);

            System.out.println(JSON.toJSONString(mockData, true));
            Assert.assertNotNull(mockData);
            checkMockData(mockData.getMap(), Integer.class, String.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testBeanExtendDefinePropertyGenericSuperEntity() {
        //属性中存在泛型，再次继承
        try {
            BeanExtendDefinePropertyGenericSuperEntity mockData = JMockData.mock(BeanExtendDefinePropertyGenericSuperEntity.class);

            System.out.println(JSON.toJSONString(mockData, true));
            Assert.assertNotNull(mockData);
            checkMockData(mockData.getMap(), Integer.class, String.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查mock结果 （数据不为空，取值的key和value类型符合）
     *
     * @param mockData
     * @param keyClass
     * @param valueClass
     */
    void checkMockData(Map mockData, Class<?> keyClass, Class<?> valueClass) {

        //数据不为空
        Assert.assertFalse(mockData.isEmpty());

        String mockDataClazz = mockData.getClass().getName();
        //验证key和value的类型符合预期
        Object key = mockData.keySet().iterator().next();
        Assert.assertNotNull(mockDataClazz + " key must be not null", key);
        Object value = mockData.get(key);
        Assert.assertNotNull(mockDataClazz + "value must be not null", value);

        Assert.assertEquals(String.format("%s keyClass not expected", mockDataClazz), keyClass, key.getClass());
        Assert.assertEquals(String.format("%s valueClass not expected", mockDataClazz), valueClass, value.getClass());
    }

    @Data
    public static class BeanMapEntity {
        private Map<Integer, String> map;
    }


    @Data
    public static class BeanHashMapEntity {
        private HashMap<Integer, String> map;
    }

    @Data
    public static class BeanLinkedHashMapEntity {
        private LinkedHashMap<Integer, String> map;
    }


    @Data
    public static class BeanExtendHashMapEntity extends HashMap<Integer, String> {
    }


    @Data
    public static class BeanExtendLinkedHashMapEntity extends LinkedHashMap<Integer, String> {
    }


    @Data
    public static class BeanExtendCrudeHashMapEntity extends HashMap {
    }


    @Data
    public static class BeanExtendLinkedHashMapSuperExtendOneEntity extends BeanExtendLinkedHashMapEntity {
    }


    @Data
    public static class BeanExtendLinkedHashMapSuperExtendTwoEntity extends BeanExtendLinkedHashMapSuperExtendOneEntity {
    }

    @Data
    public static class BeanExtendLinkedHashMapSuperExtendThreeEntity extends BeanExtendLinkedHashMapSuperExtendTwoEntity {
    }


    @Data
    public static class BeanDefineGenericEntity<A, B> extends LinkedHashMap<A, B> {
    }


    @Data
    public static class BeanExtendDefineGenericEntity extends BeanDefineGenericEntity<Integer, String> {
    }


    @Data
    public static class BeanExtendDefineGenericSuperEntity extends BeanExtendDefineGenericEntity {
    }


    @Data
    public static class BeanDefineGenericAgainEntity<H, C> extends BeanDefineGenericEntity<H, C> {
    }


    @Data
    public static class BeanExtendDefineGenericAgainEntity extends BeanDefineGenericAgainEntity<Integer, String> {
    }


    @Data
    public static class BeanExtendDefineGenericAgainSuperEntity extends BeanExtendDefineGenericAgainEntity {
    }


    @Data
    public static class BeanDefinePropertyGenericEntity<A, B> {
        private LinkedHashMap<A, B> map;

    }

    @Data
    public static class BeanExtendDefinePropertyGenericEntity extends BeanDefinePropertyGenericEntity<Integer, String> {

    }

    @Data
    public static class BeanExtendDefinePropertyGenericSuperEntity extends BeanExtendDefinePropertyGenericEntity {

    }




}
