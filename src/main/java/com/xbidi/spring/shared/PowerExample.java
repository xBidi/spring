package com.xbidi.spring.shared;

import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/** @author diegotobalina created on 14/08/2020 */
public class PowerExample {

  public ExampleMatcher getMatcher() {
    return getExampleMatcher(ExampleMatcher.matching());
  }

  public ExampleMatcher getMatcher(Class<?> clazz, List<String> wantedFields) {
    String[] notWantedFields = getNotWantedFields(clazz, wantedFields);
    return getExampleMatcher(ExampleMatcher.matching().withIgnorePaths(notWantedFields));
  }

  private ExampleMatcher getExampleMatcher(ExampleMatcher matching) {
    return matching
        .withIgnoreNullValues()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
  }

  private String[] getNotWantedFields(Class<?> clazz, List<String> wantedFields) {
    List<String> response = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      String fieldName = field.getName();
      if (!wantedFields.contains(fieldName)) response.add(fieldName);
    }
    String[] strings = response.toArray(new String[response.size()]);
    if (strings.length == 0) return new String[] {"_"};
    return strings;
  }
}
