package com.github.jsonzou.jmockdata;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author joker-pper 2024年05月15日 下午13:37:34
 */
public class ListDataTest {

    @Test
    public void testBase() {

        checkBase(new TypeReference<Collection<String>>() {
        }, Collection.class);

        checkBase(new TypeReference<List<String>>() {
        }, List.class);

        checkBase(new TypeReference<Set<String>>() {
        }, Set.class);

        checkBase(new TypeReference<ArrayList<String>>() {
        }, ArrayList.class);

        //模拟数组集合
        checkBase(new TypeReference<ArrayList<Integer[]>>() {
        }, ArrayList.class);

        //模拟集合数组
        checkBase(new TypeReference<ArrayList<List<Integer>[]>>() {
        }, ArrayList.class);

        checkBase(new TypeReference<LinkedList<String>>() {
        }, LinkedList.class);

        //模拟数组集合
        checkBase(new TypeReference<LinkedList<Integer[]>>() {
        }, LinkedList.class);

        //模拟集合数组
        checkBase(new TypeReference<LinkedList<List<Integer>[]>>() {
        }, LinkedList.class);

        checkBase(new TypeReference<HashSet<String>>() {
        }, HashSet.class);

        //模拟数组集合
        checkBase(new TypeReference<HashSet<Integer[]>>() {
        }, HashSet.class);

        //模拟集合数组
        checkBase(new TypeReference<HashSet<List<Integer>[]>>() {
        }, HashSet.class);


        checkBase(new TypeReference<LinkedHashSet<String>>() {
        }, LinkedHashSet.class);


        //模拟数组集合
        checkBase(new TypeReference<LinkedHashSet<Integer[]>>() {
        }, LinkedHashSet.class);

        //模拟集合数组
        checkBase(new TypeReference<LinkedHashSet<List<Integer>[]>>() {
        }, LinkedHashSet.class);


    }


    static <T> void checkBase(TypeReference<T> typeReference, Class<?> fromSuperClass) {
        T mockData = JMockData.mock(typeReference);
        Assert.assertNotNull(mockData);
        System.out.println(String.format("mock data class is %s", mockData.getClass().getName()));
        System.out.println(JSON.toJSONString(mockData));
        Assert.assertTrue(String.format("expected data from %s, but actual data from %s", fromSuperClass.getName(), mockData.getClass().getName()), fromSuperClass.isAssignableFrom(mockData.getClass()));
    }


}
