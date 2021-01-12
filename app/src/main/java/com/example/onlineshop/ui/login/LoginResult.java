package com.example.onlineshop.ui.login;

import androidx.annotation.Nullable;

import com.example.onlineshop.data.model.UserVO;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private UserVO success;
    @Nullable
    private String error;

    LoginResult(@Nullable String error) {
        this.error = error;
    }


    LoginResult(@Nullable UserVO success) {
        this.success = success;
    }

    @Nullable
    UserVO getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }
}
