package com.mazurenko.springsecuritybasic.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthorityUtils {

    public static String authoritiesToString(Collection<? extends GrantedAuthority> authoritiesCollection) {
        List<String> authorities = authoritiesCollection.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return String.join(",", authorities);
    }
}
