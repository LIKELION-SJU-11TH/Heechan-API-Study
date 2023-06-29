package com.example.demo.entity;

import com.example.demo.dto.ArticleUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="articles")
public class ArticleEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articlesId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = MemberEntity.class)
    @JoinColumn(name="member_id",updatable = false)
    private  MemberEntity memberId;

    @OneToMany(mappedBy="article")
    private List<ImageEntity> image = new ArrayList<>();


    @Builder
    public ArticleEntity(String title, String content, MemberEntity memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
    public void addImage(ImageEntity imageEntity){
        image.add(imageEntity);
        if(imageEntity.getArticle() != this){
            imageEntity.setArticle(this);
        }
    }

    public void update(ArticleUpdateDto articleUpdateDto){
        this.title=articleUpdateDto.getTitle();
        this.content=articleUpdateDto.getContent();
    }
}
