package com.alpha.enums;

public enum TagEnum {
    TODO("T"), DEADLINE("D"), EVENT("E"), EMPTY(" ");

    private final String tag;

    TagEnum(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }
}
