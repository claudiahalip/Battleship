package com.game.Battleship.controllers;
import org.junit.jupiter.api.Assertions;
import com.game.Battleship.models.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BoardControllerTests {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void boardsEndpointContains2Boards() {

        List<Object> response = template.getForObject("/boards", List.class);
        Assertions.assertEquals(2, response.size());

    }

    @Test
    public void boardsEndpointHasABoardObject() {

        ResponseEntity<Board[]> response = template.getForEntity("/boards", Board[].class);
        Assert.isInstanceOf(Board.class, Arrays.stream(response.getBody()).findFirst().get());

    }

    @Test
    public void boardsEndpointNameGetRequestReturnStatusOK() {

        ResponseEntity<String> response = template.getForEntity("/boards/currentPlayer", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void boardsEndpointNameHasABoardObjectWithANameCurrentPlayer() {

        Board response = template.getForObject("/boards/currentPlayer", Board.class);
        Assertions.assertEquals("currentPlayer", response.getName());

    }

    @Test
    public void boardsEndpointNameHasABoard() {

        Board response = template.getForObject("/boards/currentPlayer", Board.class);
        Assertions.assertEquals("currentPlayer", response.getName());

    }

}




