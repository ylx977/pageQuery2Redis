package com.fuzamei.common.enums.http.msg;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/6.
 */
@Getter
public enum HintMsg {

    SUCCESS("200","成功"),
    
    ;

    private String code;
    private String message;

    private HintMsg(String code,String message){
        this.code = code;
        this.message = message;
    }


}
