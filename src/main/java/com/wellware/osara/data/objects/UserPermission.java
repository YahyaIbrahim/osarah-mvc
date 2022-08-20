package com.wellware.osara.data.objects;

import lombok.Data;

public enum UserPermission {
    MENU_READ("menu:read"),
    MENU_WRITE("menu:write"),
    CATEGORIES_READ("categories:read"),
    CATEGORIES_WRITE("categories:write"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    RATE_READ("rate:read"),
    RATE_WRITE("rate:write"),
    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write");


    private final String permission;


    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
