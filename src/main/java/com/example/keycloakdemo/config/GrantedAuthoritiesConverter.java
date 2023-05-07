package com.example.keycloakdemo.config;

import com.example.keycloakdemo.constant.ClaimNames;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class GrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        if (jwt.getClaim(ClaimNames.REALM_ACCESS) instanceof Map<?, ?> realmAccessMap) {
            if (realmAccessMap.get(ClaimNames.ROLES) instanceof Collection<?> roles) {
                return roles.stream()
                        .map(o -> (String) o)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());
            }
        }

        return Collections.emptyList();
    }
}
