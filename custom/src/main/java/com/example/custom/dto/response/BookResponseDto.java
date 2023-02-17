package com.example.custom.dto.response;

public class BookResponseDto {

    private String id;
    private String title;
    private AuthorResponseDto author;
    private String volume;

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

    public AuthorResponseDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponseDto author) {
        this.author = author;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
