package com.lanxin.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//无参构造方法
@AllArgsConstructor//所有参构造方法
public class LanxinResult {
    private Integer code;

    private String msg;

    private Object data;

    private Integer sum;

    public LanxinResult(Integer code, String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static LanxinResult ok(Object data){

        return new LanxinResult(200,"操作成功",data);
    }

    public static LanxinResult ok(Object data,Integer sum){

        return new LanxinResult(200,"操作成功",data,sum);
    }

}
