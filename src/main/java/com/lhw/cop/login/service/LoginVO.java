package com.lhw.cop.login.service;

public class LoginVO {

    /**
     * 사용자아이디
     */
    private String userId;

    /**
     * 이메일
     */
    private String email;

    /**
     * 이름
     * 별명
     */
    private String userNm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
}
