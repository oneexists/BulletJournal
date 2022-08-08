package com.bujo.services;

import java.util.Optional;

import com.bujo.data.models.AppUserModel;

public interface AppUserModelDao {
	Optional<AppUserModel> selectApplicationUserByUsername(String username);
}
