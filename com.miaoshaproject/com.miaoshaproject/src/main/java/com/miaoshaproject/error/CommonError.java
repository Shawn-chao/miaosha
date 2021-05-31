package com.miaoshaproject.error;

/**
 * @ClassName CommonErroe
 * @Description TODO
 * @date 2021/5/18 20:59
 * @Version 1.0
 */
public interface CommonError {

    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);


}
