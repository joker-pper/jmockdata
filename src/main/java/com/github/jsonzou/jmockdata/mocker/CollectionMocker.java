package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;
import com.github.jsonzou.jmockdata.util.ClassUtils;
import com.github.jsonzou.jmockdata.util.RandomUtils;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 模拟Collection
 */
public class CollectionMocker implements Mocker<Object> {

  private Class clazz;

  private Type genericType;

  CollectionMocker(Class clazz, Type genericType) {
    this.clazz = clazz;
    this.genericType = genericType;
  }

  @Override
  public Object mock(DataConfig mockConfig) {
    int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
    Collection<Object> result;
    if (List.class.isAssignableFrom(clazz)) {
      result = new ArrayList<>(size);
    } else {
      result = new HashSet<>(size);
    }
    BaseMocker baseMocker = new BaseMocker(genericType);
    for (int index = 0; index < size; index++) {
      result.add(baseMocker.mock(mockConfig));
    }

    if (Arrays.asList(Collection.class, List.class, ArrayList.class, Set.class, HashSet.class).contains(clazz)) {
      return result;
    }

    Collection<Object> collection = ClassUtils.newInstance(clazz);
    collection.addAll(result);

    return collection;
  }

}
