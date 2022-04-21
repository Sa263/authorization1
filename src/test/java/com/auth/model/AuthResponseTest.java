package com.auth.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
@SpringBootTest
 public class AuthResponseTest {

    @InjectMocks
    AuthResponse response;

    @Test
   public  void getterSetterTest() {
        response.setUsername("sanchit");
        response.setRole("admin");
        response.setValid(true);

        assertEquals("sanchit", response.getUsername());
        assertEquals("admin", response.getRole());
        assertTrue(response.isValid());
    }

    @Test
   public  void noArgsConstructorTest() {
        response = new AuthResponse();
        assertNotNull(response);
    }

    @Test
   public  void allArgsConstructorTest() {
        response = new AuthResponse("name", "admin", true);
        assertNotNull(response);
        assertEquals("name", response.getUsername());
        assertEquals("admin", response.getRole());
        assertTrue(response.isValid());
    }

}
