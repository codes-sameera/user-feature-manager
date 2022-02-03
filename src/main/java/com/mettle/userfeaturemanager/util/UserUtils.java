package com.mettle.userfeaturemanager.util;

import com.mettle.userfeaturemanager.model.MyUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@UtilityClass
public class UserUtils {
    public boolean isAdmin(UsernamePasswordAuthenticationToken principal) {
        return ((MyUserDetails) principal.getPrincipal())
                .getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }
}
