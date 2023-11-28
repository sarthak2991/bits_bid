package com.bits.bids.utils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {
  private CollectionUtils() {}

  public static List<String> convertCsvToList(String csv) {
    if (StringUtils.isNullOrEmpty(csv)) {
      return new ArrayList<>();
    }
    String[] split = csv.split(",");
    ArrayList<String> objects = new ArrayList<>();
      Collections.addAll(objects, split);
    return objects;
  }

  public static List<Integer> convertCsvToIntList(String csv) {
    return convertCsvToList(csv.trim().replace(" ", "")).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  public static Set<String> convertCsvToSet(String csv) {
    return Arrays.asList(csv.split(",")).stream().collect(Collectors.toSet());
  }

  public static Set<Integer> convertCsvToIntSet(String csv) {
    return Arrays.asList(csv.split(",")).stream().map(Integer::parseInt)
        .collect(Collectors.toSet());
  }

  public static String convertLongSetToCsv(Set<Long> set) {
    StringBuilder csv = new StringBuilder();
    set.forEach(num -> csv.append(num).append(","));
    if (csv.length() > 0) {
      csv.setLength(csv.length() - 1);
    }
    return csv.toString();
  }

  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }

  public static List<Long> convertCsvToLongList(String csv) {
    return convertCsvToList(csv.trim().replace(" ", "")).stream().map(Long::parseLong)
        .collect(Collectors.toList());
  }

  public static String convertLongListToCsv(List<Long> list) {
    StringBuilder csv = new StringBuilder();
    list.forEach(num -> csv.append(num).append(","));
    if (csv.length() > 0) {
      csv.setLength(csv.length() - 1);
    }
    return csv.toString();
  }
}
