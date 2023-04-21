package com.miniBankend.model;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UserModelTest {

    @Test
    public void testUserModel() {
        UserModel userModel = new UserModel();
        userModel.setName("John Doe");
        userModel.setEmail("john.doe@example.com");
        userModel.setPassword("password");

        assertEquals("John Doe", userModel.getName());
        assertEquals("john.doe@example.com", userModel.getEmail());
        assertEquals("password", userModel.getPassword());
    }
}
