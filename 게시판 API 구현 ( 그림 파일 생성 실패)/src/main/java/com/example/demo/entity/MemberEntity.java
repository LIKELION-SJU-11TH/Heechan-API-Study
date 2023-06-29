package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(name="name")
    private String name;

    /**일대다 관계에서 참조 당하는 엔티티에 사용*/
    @OneToMany(mappedBy = "memberId", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ArticleEntity> articleEntities=new ArrayList<>();

    @Builder
    public MemberEntity(long memberId) {
        this.memberId = memberId;
    }
}
