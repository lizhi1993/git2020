package com.lanxin.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error extends RuntimeException{

    private Integer code;
    private  String msg;

}
