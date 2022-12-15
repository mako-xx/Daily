package com.travelthree.daily;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootTest
@Import(BCryptPasswordEncoder.class)
class DailyApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Executor executor = Executors.newSingleThreadExecutor();

    @Test
    void contextLoads() {
        executor.execute(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
