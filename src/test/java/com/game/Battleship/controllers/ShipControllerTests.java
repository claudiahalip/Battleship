package com.game.Battleship.controllers;
import com.game.Battleship.models.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ShipControllerTests {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void shipEndpointContains5ShipObjects(){

        List<Object> response = template.getForObject("/ships", List.class);
        Assertions.assertEquals(5, response.size());

    }

    @Test
    public void shipEndpointContainsShipObjects(){

        ResponseEntity<Ship[]> response = template.getForEntity("/ships", Ship[].class);
        Assert.isInstanceOf(Ship.class, Arrays.stream(response.getBody()).findAny().get());

    }

}
