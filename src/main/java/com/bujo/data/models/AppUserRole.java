package com.bujo.data.models;

import static com.bujo.data.models.AppUserPermission.*;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRole {
	USER(Sets.newHashSet()), 
	MANAGER(Sets.newHashSet(BOOK_READ, USER_READ)),
	ADMIN(Sets.newHashSet(BOOK_READ, BOOK_WRITE, USER_READ, USER_WRITE));
	
	private final Set<AppUserPermission> permissions;
	
	AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
