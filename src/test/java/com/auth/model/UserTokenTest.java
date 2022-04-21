package com.auth.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
@SpringBootTest
 public class UserTokenTest {

    @InjectMocks
    UserToken token;

    @Test
   public  void getterSetterTest() {
        token.setAuthToken("token");

        token.setUsername("name");
        token.setRole("admin");

        assertEquals("token", token.getAuthToken());
        assertEquals("name", token.getUsername());
        assertEquals("admin", token.getRole());
    }

    @Test
   public  void noArgsConstructorTest() {
        token = new UserToken();
        assertNotNull(token);
    }

    @Test
    public void allArgsContructorTest() {
        token = new UserToken("name", "token", "admin");
        assertNotNull(token);
        assertEquals("token", token.getAuthToken());
        assertEquals("name", token.getUsername());
        assertEquals("admin", token.getRole());
    }

}
