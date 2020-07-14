package services.ConnectionBD;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;




class ConnectionBDTest {
ConnectionBD connectionBD = new ConnectionBD();


    @Test
    public void testMockDBConnection(){
       try {
           assertTimeout(Duration.ofSeconds(2),()->connectionBD.getConnection());
       }catch (Throwable e){
           fail(e.getMessage());
       }


    }
}