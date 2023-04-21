package com.miniBankend.model;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginModelTest {
    @Test
    public void testLoginModel() {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail("john.doe@example.com");
        loginModel.setPassword("password");

        assertEquals("john.doe@example.com", loginModel.getEmail());
        assertEquals("password", loginModel.getPassword());
    }
}
