package com.example.demo.dto;

import com.example.demo.entity.ArticleEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String member;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ArticleDto(ArticleEntity articleEntity) {
        this.id = articleEntity.getArticlesId();
        this.title = articleEntity.getTitle();
        this.member = articleEntity.getMemberId().getName();
        this.content = articleEntity.getContent();
        this.createdAt = articleEntity.getCreatedAt();
        this.modifiedAt=articleEntity.getModifiedAt();
    }
}
