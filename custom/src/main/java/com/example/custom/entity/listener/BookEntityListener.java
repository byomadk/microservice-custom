package com.example.custom.entity.listener;

import com.example.custom.entity.BookEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

public class BookEntityListener {
	@PrePersist
	void onPrePersist(BookEntity bookEntity) {
		if (bookEntity.getId() == null)
			bookEntity.setId(UUID.randomUUID().toString());
	}
	
	@PreUpdate
	void onPreUpdate(BookEntity bookEntity) {
	}
}
