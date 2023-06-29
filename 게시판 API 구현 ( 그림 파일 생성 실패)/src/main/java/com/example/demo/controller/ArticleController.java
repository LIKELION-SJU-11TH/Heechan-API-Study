package com.example.demo.controller;



import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleListDto;
import com.example.demo.dto.ArticleUpdateDto;
import com.example.demo.dto.ArticlesSaveDto;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;
    //게시글 생성
    /**기본적으로 @RequestBody는 body로 전달받은 JSON형태의 데이터를 파싱해준다.
     * 반면 Content-Type이 multipart/form-data로 전달되어 올 때는 Exception을 발생시킨다.
     * 파일 및 이미지의 경우는 multipart/form-data로 요청이와서 @RequestBody를 바꿔줘야함
     * multipart/form-data를 전달받는 방법에는 @RequestPart, @RequestParam이 있음
     *
     * create는 게시글을 생성함과 동시에 이미지 파일도 추가하는 API임
     *
     * */

    @PostMapping ("/create")
    public Long createArticles(
            @RequestPart(value = "requestDto") ArticlesSaveDto articlesSaveDto,
            @RequestPart(value="image", required=false) List<MultipartFile> files
    )throws Exception{
        return articleService.createArticle(articlesSaveDto,files);
    }

    @GetMapping("/read")
    public List<ArticleListDto> readArticle(){
        return articleService.read();
    }

    @GetMapping("/read-one/{id}")
    public ArticleDto readOneArticle(@PathVariable Long id){
        return articleService.readOne(id);
    }


    @PatchMapping("/update/{id}")
    public ArticleDto updateArticle(@PathVariable Long id , @RequestBody ArticleUpdateDto articleUpdateDto) {
        return articleService.update(id,articleUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        articleService.delete(id);
    }


}
