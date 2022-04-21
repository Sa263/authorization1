package com.auth.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

 public class UserEntityTest {

    @InjectMocks
    UserEntity user;

    @Test
   public void getterSetterTest() {
        user = new UserEntity();

        user.setPassword("pass");
        user.setUsername("admin");
        user.setRole("admin");

        assertEquals("pass", user.getPassword());
        assertEquals("admin", user.getUsername());
        assertEquals("admin", user.getRole());
    }

    @Test
   public  void noArgsConstructorTest() {
        user = new UserEntity();
        assertNotNull(user);
    }

    @Test
   public  void allArgsCOnstructor() {
        user = new UserEntity(1,"admin", "pass", "admin");
        assertEquals("pass", user.getPassword());
        assertEquals("admin", user.getUsername());
        assertEquals("admin", user.getRole());
    }

}
