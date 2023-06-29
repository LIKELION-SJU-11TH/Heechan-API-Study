package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateDto {
    private String title;
    private String content;


    public ArticleUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
