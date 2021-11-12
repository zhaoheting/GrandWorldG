package com.example.GrandWorldG.enums;

/**
 * Roles of login user.
 *
 * @author Hobbs.Heting.Zhao
 * @since 2021/11/12
 */
public enum UserRoleEnum {
    ADMIN(1), USER(2);
    private int value;

    UserRoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
