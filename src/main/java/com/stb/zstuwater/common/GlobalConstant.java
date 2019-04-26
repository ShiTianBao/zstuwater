package com.stb.zstuwater.common;


public interface GlobalConstant {
    int ID_MENU_FATHER = 0;
    int PROJECT_STATE_WAITE_FIRST_CHECK = 10;
    int PROJECT_STATE_NOT_SUBMIT = 0;

    String SESSION_ATTR_KEY = "ZSTUUSER";
    String SESSION_VERIFY_KEY = "VERIFYTEXT";

    int CODE_PARAM_MISS = 1;

    int CODE_USER_NOT_FOUND = 101;
    int CODE_USER_PASSWORD_WRONG = 102;
    int CODE_USER_NOT_LOGIN = 103;

    String PENDING = "待配送";
    String BEING = "配送中";
    String FINISHED = "已送达";

    String STAFF_STATE_FREE = "空闲";
    String STAFF_STATE_BUSY = "配送中";

}
