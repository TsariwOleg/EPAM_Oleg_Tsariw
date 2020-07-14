package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignIn_EntityTest {
    SignIn_Entity signInEntity = new SignIn_Entity();

    int integer=20;
    String string="string";

    @Test
    void testId() {
        signInEntity.setId(integer);
        assertEquals(signInEntity.getId(),integer);
    }

    @Test
    void testNSP() {
        signInEntity.setNSP(string);
        assertEquals(signInEntity.getNSP(),string);
    }

    @Test
    void testLogin() {
        signInEntity.setLogin(string+"1");
        assertEquals(signInEntity.getLogin(),string+"1");
    }

    @Test
    void testPassword() {
        signInEntity.setPassword(string+"2");
        assertEquals(signInEntity.getPassword(),string+"2");
    }
}