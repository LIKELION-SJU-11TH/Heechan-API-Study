package com.example.demo.dto;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArticlesSaveDto {
    private MemberEntity memberEntity;
    private String title;
    private String content;
    private List<MultipartFile> files;

    @Builder
    public ArticlesSaveDto(MemberEntity memberEntity, String title, String content) {
        this.memberEntity = memberEntity;
        this.title = title;
        this.content = content;

    }

    public ArticleEntity toEntity(){
        return ArticleEntity.builder()
                .memberId(memberEntity)
                .title(title)
                .content(content)
                .build();

    }
}
