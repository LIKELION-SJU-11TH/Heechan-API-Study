package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="image")
@Getter
@NoArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name="url")
    private String url;

    @Column(name="image_name")
    private String origFileName;

    @ManyToOne
    @JoinColumn(name="articles_id")
    private ArticleEntity article;

    @Builder
    public ImageEntity(String url, String origFileName) {
        this.url = url;
        this.origFileName = origFileName;
    }


    public void setArticle(ArticleEntity articleEntity){
        article=articleEntity;
        if(!articleEntity.getImage().contains(this)){
            articleEntity.getImage().add(this);
        }
    }
}
