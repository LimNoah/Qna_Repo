package com.fastcampus.board.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@TypeDef(name="json", typeClass = JsonBinaryType.class)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int category;
    private int cnt;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String data;
}
