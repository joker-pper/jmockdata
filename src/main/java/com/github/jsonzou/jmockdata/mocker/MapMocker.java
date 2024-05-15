package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.MockException;
import com.github.jsonzou.jmockdata.Mocker;
import com.github.jsonzou.jmockdata.util.ClassUtils;
import com.github.jsonzou.jmockdata.util.RandomUtils;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟Map
 */
public class MapMocker implements Mocker<Object> {
  private Class clazz;
  private Type[] types;

  MapMocker(Class clazz, Type[] types) {
    this.clazz = clazz;

    if (types.length == 0) {
      //当前类继承自Map  -- 取对应的实际类型
      types = ClassUtils.getSuperclassGenericActualTypeArguments(clazz);
      if (types == null) {
        throw new MockException("NOT found " + clazz.getName() + " generic actual type arguments");
      }
    }

    this.types = types;
  }

  @Override
  public Object mock(DataConfig mockConfig) {
    int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
    Map<Object, Object> result = new HashMap<>(size);

    Type keyType;
    Type valueType;

    if (types.length == 2 && types[0] instanceof TypeVariable && types[1] instanceof TypeVariable) {
      //没有实际类型值时，按照最简单的来 （若field的泛型来自类上声明时，若有具体类型，不应进入这里）
      keyType = String.class;
      valueType = String.class;
    } else {
      keyType = types[0];
      valueType = types[1];
    }

    BaseMocker keyMocker = new BaseMocker(keyType);
    BaseMocker valueMocker = new BaseMocker(valueType);
    for (int index = 0; index < size; index++) {
      result.put(keyMocker.mock(mockConfig), valueMocker.mock(mockConfig));
    }

    if (clazz == Map.class || clazz == HashMap.class) {
      //仅在class为Map或HashMap时可直接返回
      return result;
    }

    //通过newInstance，再进行putAll，避免赋值时因类型不一致而失败
    Map<Object, Object> objectMap = ClassUtils.newInstance(clazz);
    objectMap.putAll(result);
    return objectMap;
  }

}
