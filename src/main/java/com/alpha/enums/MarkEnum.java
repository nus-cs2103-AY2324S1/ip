package com.alpha.enums;

public enum MarkEnum {

  DONE("X"), NOTDONE(" ");

  private String mark;

  MarkEnum(String mark) {
    this.mark = mark;
  }

  public String getMark() {
    return this.mark;
  }
}
