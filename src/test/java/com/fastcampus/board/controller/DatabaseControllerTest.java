package com.fastcampus.board.controller;

import com.fastcampus.board.model.Products;
import com.fastcampus.board.repository.DBTestRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.FileReader;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class DatabaseControllerTest {

    @Autowired
    private DBTestRepository dbTestRepository;

    private MockMvc mockMvc;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PW = "Tkdrud1dnjf!";

    @BeforeEach
    void before(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .alwaysDo(print())
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @Rollback(value = false)
    void testDb() throws Exception {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Crawling_Data\\0.json"));
        int category = 0;

        System.out.println("   >>>   " + jsonObject);

//        for(int i = 0; i < 321; i++){
//            if(i >= 0 && i < 3){
//                category = 0;
//            } else if (i >= 3 && i < 6) {
//
//            }
//
//            jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Crawling_Data\\" + i + ".json"));
//        }


        Products products = new Products(null, 0,0, jsonObject.toString());

        System.out.println("products Entity  >>>  " + products.toString());

        dbTestRepository.save(products);
    }
}