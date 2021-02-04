package com.fastcampus.board.controller;

import com.fastcampus.board.Converter.StringRevisor;
import com.fastcampus.board.exception.ResourceNotFoundException;
import com.fastcampus.board.model.Products;
import com.fastcampus.board.repository.DBTestRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.FileReader;
import java.util.Arrays;

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
        JSONObject jsonObject = new JSONObject();

//        int categoryIdx = 0;
//        int[] categoryNumList = {
//                0, 1, 2, 3, 4, 5, 6,
//                10,11,12,13,14,15,16,
//                20,21,22,23,24,25,26,27,28,
//                30,31,32,33,34,35,36,
//                40,41,42,43,44,45,
//                50,51,52,53,54,55,56,57,
//                60,61,62,63,64,65,
//                70,71,72,73,74,
//                80,81,82,83,
//                90,91,92,93,94,95,
//                100,101,102,103,104,105,106,
//                110,111,112,113,114,115,
//                120,121,122,123,124,
//                130,131,132,133,134,135,
//                140,141,142,
//                150,151,152,153,154,155,156,157,158,
//                160,161,162,163,164,165,166};
//
//        System.out.println(categoryNumList.length); // ==> i < 106
//
//        int cnt = 0;
//        for(int i = 0; i < 321; i++){//321
//            if(categoryNumList[categoryIdx] == 132){
//                if(cnt / 2 > 0){
//                    cnt = 0;
//                    categoryIdx++;
//                }
//            } else if(categoryNumList[categoryIdx] == 166){
//                if(cnt / 1 > 0){
//                    cnt = 0;
//                    categoryIdx++;
//                }
//            } else {
//                if(cnt / 3 > 0){//124까지 3개씩
//                    cnt = 0;
//                    categoryIdx++;
//                }
//            }
//
//            System.out.println("i   >>>  " + i);
//            System.out.println("categoryIdx   >>>  " + categoryIdx);
//            System.out.println("categoryNumList[categoryIdx]   >>>  " + categoryNumList[categoryIdx]);
//            System.out.println("cnt   >>>  " + cnt);
//
//
//
//            jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Crawling_Data\\" + i + ".json"));
//            Products products = new Products(null, categoryNumList[categoryIdx], 0, jsonObject.toString());
//            dbTestRepository.save(products);
//            cnt++;
//        }


        Products testProduct = dbTestRepository.findById((long) 1).orElseThrow(
                () -> new ResourceNotFoundException()
        );
        System.out.println("product >>> " + testProduct);

        String str = testProduct.getData();
        StringRevisor sr = new StringRevisor();
        System.out.println("StringRevisor >>> " + sr.StringRevise(str));
    }
}