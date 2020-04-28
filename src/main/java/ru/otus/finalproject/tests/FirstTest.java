package ru.otus.finalproject.tests;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import ru.otus.finalproject.FinalProjectApplication;
import ru.otus.finalproject.config.BaseWebDrivingTest;
import ru.otus.finalproject.config.Config;

@SpringBootTest(classes = FinalProjectApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class FirstTest extends BaseWebDrivingTest {
    @Test()
    public void test(){
        driver.get(config.getUrl());
    }
}
