package com.game.Battleship.controllers;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import  org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BoardControllerTests {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void boardEndpointContainsSizeAttribute(){

        Board response = template.getForObject("/board", Board.class);
        Assertions.assertEquals(10, response.getSize());

    }

    @Test
    public void boardFieldSizeIsEqualWithBoardSizeToThePowerOf2(){

        Board response = template.getForObject("/board", Board.class);
        Assertions.assertEquals(100, response.getBoardField().size());

    }

    @Test
    public void boardFieldContainsCellObjects(){

        Board response = template.getForObject("/board", Board.class);
        Assert.isInstanceOf(Cell.class, response.getBoardField().get(0));

    }

}
