package com.fuzamei.common.enums.auth;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/8.
 */
@Getter
public enum AuthEnum {

    //TODO ################################CORE AUTHES(核心权限，不可分配)###############################################
    A1(1001L, "查询子账号信息", "/contractchain/api/staffAccountManagement/queryStaffAccount"),
    A2(1002L, "创建子账号", "/contractchain/api/staffAccountManagement/insertStaff"),
    A3(1003L, "停用或启用子账号", "/contractchain/api/staffAccountManagement/freezeOrNot"),

    A4(1004L, "个人用户认证", "/contractchain/api/authenticate/personAuthenticate"),
    A5(1005L, "律师用户认证", "/contractchain/api/authenticate/lawyerAuthenticate"),
    A6(1006L, "企业认证", "/contractchain/api/authenticate/enterpriseAuthenticate"),
    A7(1007L, "金融主体认证", "/contractchain/api/authenticate/financialAuthenticate"),

    //TODO ################################BUSINESS AUTHES(业务权限，可分配)###############################################


    //TODO ################################COMMON AUTHES(共有权限)######################################
    C1(3001L, "查询操作记录", "/contractchain/api/operation/queryOperationHistory"),

    C2(3002L, "查询省信息", "/contractchain/api/cnArea/queryProvinces"),
    C3(3003L, "查询市信息", "/contractchain/api/cnArea/queryCities"),
    C4(3004L, "查询区信息", "/contractchain/api/cnArea/queryDistricts"),

    C5(3005L, "查询用户信息是否存在", "/contractchain/api/account/findAccountIsRight"),

    C6(3006L, "上传签章", "/contractchain/api/seal/sealUpload"),
    C7(3007L, "查询签章", "/contractchain/api/seal/sealQuery"),
    C8(3008L, "删除签章", "/contractchain/api/seal/sealDelete"),

    C9(3009L, "向原手机号发送验证码", "/contractchain/api/security/sendToOriginal"),
    C10(3010L, "向用户输入的手机号发送验证码", "/contractchain/api/security/sendCode"),
    C11(3011L, "绑定手机号码", "/contractchain/api/security/bindingPhone"),
    C12(3012L, "验证原手机", "/contractchain/api/security/verifyOriginal"),
    C13(3013L, "校验新手机号码，并更新手机号码", "/contractchain/api/security/verifyAndModify"),
    C14(3014L, "旧邮箱发送验证码", "/contractchain/api/security/sendOldEmailCode"),
    C15(3015L, "新邮箱发送验证码", "/contractchain/api/security/sendNewEmailCode"),
    C16(3016L, "绑定邮箱", "/contractchain/api/security/setEmail"),
    C17(3017L, "验证原邮箱", "/contractchain/api/security/checkOldEmail"),
    C18(3018L, "修改成新邮箱", "/contractchain/api/security/changeToNewEmail"),
    C19(3019L, "修改密码", "/contractchain/api/security/changePwd"),

    C20(3020L, "根据用户ID查询用户的基本信息", "/contractchain/api/homepage/findHomePageData"),
    C21(3021L, "获取主页合同动态数据", "/contractchain/api/homepage/findHomePageContract"),

    C22(3022L, "pdf上传合同文件", "/contractchain/api/upload/uploadPdfFile"),
    C23(3023L, "img上传合同文件", "/contractchain/api/upload/uploadImgFile"),

    C24(3024L, "更新阅读状态", "/contractchain/api/contract/updateReadStatus"),
    C25(3025L, "获取合同管理页", "/contractchain/api/contract/findContractList"),
    C26(3026L, "合同管理查看详情", "/contractchain/api/contract/findContractView"),
    C27(3027L, "删除合同", "/contractchain/api/contract/delContract"),
    C28(3028L, "合同发起方创建合同", "/contractchain/api/contract/createContract"),
    C29(3029L, "合同发起方签署合同", "/contractchain/api/contract/signContract"),
    C30(3030L, "签署合同", "/contractchain/api/contract/sign"),
    C31(3031L, "发送短信验证码", "/contractchain/api/contract/sendSM"),


    C32(3032L, "向银行预留手机号发送短信验证码", "/contractchain/api/authenticate/sendCode"),
    C33(3033L, "返回认证信息", "/contractchain/api/authenticate/queryAuthentication"),

    C34(3034L, "根据hash查询PDF的图片信息", "/contractchain/api/upload/findImagesByHash"),
    ;

    private Long authorizationId;
    private String authorizationName;
    private String authorizationUrl;

    AuthEnum(Long authorizationId, String authorizationName, String authorizationUrl) {
        this.authorizationId = authorizationId;
        this.authorizationName = authorizationName;
        this.authorizationUrl = authorizationUrl;
    }

    public static final List<Long> COMMON_AUTHES = Arrays.asList(new Long[]{C1.getAuthorizationId(), C2.getAuthorizationId(), C3.getAuthorizationId(),
            C4.getAuthorizationId(), C5.getAuthorizationId(), C6.getAuthorizationId(), C7.getAuthorizationId(), C8.getAuthorizationId(), C9.getAuthorizationId(),
            C10.getAuthorizationId(), C11.getAuthorizationId(), C12.getAuthorizationId(), C13.getAuthorizationId(), C14.getAuthorizationId(), C15.getAuthorizationId(),
            C16.getAuthorizationId(), C17.getAuthorizationId(), C18.getAuthorizationId(), C19.getAuthorizationId(), C20.getAuthorizationId(), C21.getAuthorizationId(),
            C22.getAuthorizationId(), C23.getAuthorizationId(), C24.getAuthorizationId(), C25.getAuthorizationId(), C26.getAuthorizationId(), C27.getAuthorizationId(),
            C28.getAuthorizationId(), C29.getAuthorizationId(), C30.getAuthorizationId(), C31.getAuthorizationId(), C32.getAuthorizationId(), C33.getAuthorizationId(),
            C34.getAuthorizationId()
    });

    public static final List<Long> PERSON = new ArrayList<>(COMMON_AUTHES);
    public static final List<Long> LAWYER = new ArrayList<>(COMMON_AUTHES);
    public static final List<Long> ENTERPRISE = new ArrayList<>(COMMON_AUTHES);
    public static final List<Long> FINANCIAL = new ArrayList<>(COMMON_AUTHES);
    public static final List<Long> STAFF = new ArrayList<>(COMMON_AUTHES);

    static {
        PERSON.add(A4.getAuthorizationId());

        LAWYER.add(A5.getAuthorizationId());


        ENTERPRISE.add(A1.getAuthorizationId());
        ENTERPRISE.add(A2.getAuthorizationId());
        ENTERPRISE.add(A3.getAuthorizationId());
        ENTERPRISE.add(A6.getAuthorizationId());

        FINANCIAL.add(A1.getAuthorizationId());
        FINANCIAL.add(A2.getAuthorizationId());
        FINANCIAL.add(A3.getAuthorizationId());
        FINANCIAL.add(A7.getAuthorizationId());
    }


}
