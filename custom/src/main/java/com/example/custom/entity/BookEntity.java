package com.example.custom.entity;

import com.example.custom.entity.listener.BookEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
@EntityListeners(BookEntityListener.class)
public class BookEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
	private AuthorEntity author;

	@Column(name = "VOLUME", nullable = false)
	private Integer volume;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
