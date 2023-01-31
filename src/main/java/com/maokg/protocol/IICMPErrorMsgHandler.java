package com.maokg.protocol;

public interface IICMPErrorMsgHandler {
    public  boolean handleICMPErrorMsg(int type, int code, byte[] data);
}
