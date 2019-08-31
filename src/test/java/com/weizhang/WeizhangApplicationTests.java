package com.weizhang;

import com.weizhang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeizhangApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        System.out.println("Run JUnit");
        for (int i = 100; i < 105; i++){
            userService.insertMiddleData(i, i);
        }
    }

}
