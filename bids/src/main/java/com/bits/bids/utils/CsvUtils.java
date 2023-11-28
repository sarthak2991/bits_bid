package com.bits.bids.utils;

public class CsvUtils {
  private StringBuilder csv;

  public CsvUtils() {
    this.csv = new StringBuilder();
  }

  public <T> CsvUtils addElement(T element) {
    this.csv.append(element);
    return this;
  }

  public String getCsv() {
    return this.csv.toString();
  }

}
