package com.example.demo.service;

import com.example.demo.component.FileHandler;
import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleListDto;
import com.example.demo.dto.ArticleUpdateDto;
import com.example.demo.dto.ArticlesSaveDto;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.entity.ImageEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileHandler fileHandler;
    private final ImageRepository imageRepository;
    /** SaveDto가지고 데이터 베이스 테이블에 저장해야함*/
    public Long createArticle(ArticlesSaveDto articlesSaveDto,List<MultipartFile> files)
    throws Exception{
        ArticleEntity articleEntity=ArticleEntity.builder()
                .content(articlesSaveDto.getContent())
                .title(articlesSaveDto.getTitle())
                .memberId(articlesSaveDto.getMemberEntity())
                .build();

        List<ImageEntity> imageList =fileHandler.parseFileInfo(files);

        if(!imageList.isEmpty()){
            for(ImageEntity image:imageList){
                articleEntity.addImage(imageRepository.save(image));
            }
        }
        return articleRepository.save(articleEntity).getArticlesId();
    }

    /** 모든 게시글을 읽어오는 과정으로 해당 entity를 dto로 변환해서 list로 만드는 과정임*/
    public List<ArticleListDto> read(){
        return articleRepository.findAll().stream().
                map(ArticleListDto::new)
                .collect(Collectors.toList());
    }

    /**게시글 하나 읽어오기*/
    public ArticleDto readOne(Long id){
        ArticleEntity articleEntity=articleRepository.findById(id).orElseThrow();
        return new ArticleDto(articleEntity);
    }


    /** 업데이트 방식은 @Transactional을 붙이면 entity로 불러온 정보를 바꾼다면 해당 트랜잭션이 끝날때 바뀐
     * 정보를 데이터베이스에도 업데이트 해줌 */
    @Transactional
    public ArticleDto update(Long id, ArticleUpdateDto articleUpdateDto){
        ArticleEntity articleEntity=articleRepository.findById(id).orElseThrow();
        articleEntity.update(articleUpdateDto);

        return new ArticleDto(articleEntity);
    }

    /** 마찬가지로 @Transactional를 붙여주면 삭제시 트랜잭션 반영시 바뀐 Entity로 반영한다*/
    @Transactional
    public void delete(Long id){
        ArticleEntity articleEntity=articleRepository.findById(id).orElseThrow();
        articleRepository.delete(articleEntity);
    }
}
