package com.example.demo.dto;

import com.example.demo.entity.ArticleEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleListDto {
    private Long id;
    private String member;
    private String title;
    private LocalDateTime createdAt;

    public ArticleListDto(ArticleEntity articleEntity) {
        this.id = articleEntity.getArticlesId() ;
        this.member = articleEntity.getMemberId().getName();
        this.title =articleEntity.getTitle();
        this.createdAt = articleEntity.getCreatedAt();
    }
}
