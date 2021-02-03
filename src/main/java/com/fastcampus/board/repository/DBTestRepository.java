package com.fastcampus.board.repository;

import com.fastcampus.board.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBTestRepository extends JpaRepository<Products, Long> {

}
