package com.ll.board.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private T data;


    public boolean isFail() {
        return resultCode.startsWith("F-");
    }
    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }
}