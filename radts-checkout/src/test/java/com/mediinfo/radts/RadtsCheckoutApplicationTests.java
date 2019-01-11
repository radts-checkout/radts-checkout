package com.mediinfo.radts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RadtsCheckoutApplicationTests {
    @Value("${server.port}")
    public String port ;

    @Test
    public void contextLoads() {
    }

    @Test
    public void readYmlTest() {
        System.err.println(port);

    }
}
