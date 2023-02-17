package com.example.custom.entity.listener;

import com.example.custom.entity.AuthorEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

public class AuthorEntityListener {
	@PrePersist
	void onPrePersist(AuthorEntity authorEntity) {
		if (authorEntity.getId() == null)
			authorEntity.setId(UUID.randomUUID().toString());
	}
	
	@PreUpdate
	void onPreUpdate(AuthorEntity authorEntity) {
	}
}
