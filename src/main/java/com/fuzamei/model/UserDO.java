package com.fuzamei.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Data
@Builder
public class UserDO {

    private Long id;
    private String username;
    private String password;
    private String address;
    private Long ctime;
    private Long utime;
    private Long price;

}
