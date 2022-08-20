package com.wellware.osara.data.objects;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.wellware.osara.data.objects.UserPermission.*;

@Getter
public enum Roles {
    USER(Sets.newHashSet(MENU_READ, CATEGORIES_READ, RATE_READ)),
    ADMIN(Sets.newHashSet(
            MENU_READ, MENU_WRITE,
            CATEGORIES_READ, CATEGORIES_WRITE,
            ORDER_READ, ORDER_WRITE,
            RATE_READ, RATE_WRITE,
            RESERVATION_READ, RESERVATION_WRITE)),
    CUSTOMER(Sets.newHashSet(MENU_READ, CATEGORIES_READ, RATE_READ));

    private final Set<UserPermission> permissions;

    Roles(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
