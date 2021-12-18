package com.game.Battleship.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.Battleship.helpers.Validator;
import com.game.Battleship.models.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardControllerTests {

    Board[] boardsTest = new Board[]{new Board(2)};
    @Autowired
    private MockMvc mockMvc;
    private WebApplicationContext wac;

    @MockBean
    private HumanPlayer mockHumanPlayer;

    @MockBean
    private ComputerPlayer mockComputerPlayer;

    @MockBean
    private Validator mockValidator;

    @SpyBean
    private Game spyGame;

    @Test
    public void testGetRequestToBoardEndpointReturnsAnArrayWithBoards() throws Exception {

        Board[] boardsTest = new Board[]{new Board(1)};
        Mockito.when(spyGame.getAllBoards()).thenReturn(List.of(boardsTest));
        String boardsToJson = new ObjectMapper().writeValueAsString(boardsTest);
        this.mockMvc.perform(get("/boards"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(boardsToJson));
    }

    @Test
    public void testPatchRequestToBoardPlaceEndpointWillReturnMessagesToConfirmThatShipsAreAdded() throws Exception {

        Board testBoard = new Board(2);
        Ship ship = new Ship(Cell.Type.DESTROYER, 2);
        Mockito.when(spyGame.getHumanPlayer()).thenReturn(mockHumanPlayer);
        Mockito.when(spyGame.getHumanPlayer().getBoard()).thenReturn(testBoard);
        Mockito.when(spyGame.updateBoardWithShips(ship, 0)).thenReturn("The ship is placed!");

        String changesToJSON = "{\"row\":0,\"column\":0,\"shipType\":\"DESTROYER\", \"shipSize\": 2, \"isHorizontal\":true}";
        MockHttpServletRequestBuilder patch =
                MockMvcRequestBuilders.patch("/boards/place")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch)
                .andExpect(status().isOk())
                .andExpect(content().string("The ship is placed!"))
                .andDo(print());
    }

    @Test
    public void testPatchRequestToBoardPlaceEndpointReturnsMessageWhenShipsWillOverlap() throws Exception {


        Board testBoard = new Board(2);
        Ship ship1 = new Ship(Cell.Type.DESTROYER, 2);
        testBoard.addShip(ship1, 0);
        Mockito.when(spyGame.getHumanPlayer()).thenReturn(mockHumanPlayer);
        Mockito.when(spyGame.getHumanPlayer().getBoard()).thenReturn(testBoard);
        String changesToJSON = "{\"row\":0,\"column\":0,\"shipType\":\"SUBMARINE\", \"shipSize\": 3, \"isHorizontal\":true}";
        MockHttpServletRequestBuilder patch2 =
                MockMvcRequestBuilders.patch("/boards/place")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch2)
                .andExpect(status().isOk())
                .andExpect(content().string("The ship cannot overlap!"))
                .andDo(print());

    }

    @Test
    public void testPatchRequestToBoardHitEndpointWhenStrikeWillReturnMessageThatItIsAMissed() throws Exception {

        Board testBoard = new Board(2);
        Mockito.when(spyGame.getComputerPlayer()).thenReturn(mockComputerPlayer);
        Mockito.when(spyGame.getComputerPlayer().getBoard()).thenReturn(testBoard);
        Mockito.when(spyGame.updateBoardWithStrike(1)).thenReturn("You've missed!");

        String changesToJSON = "{\"row\":0,\"column\":1}";
        MockHttpServletRequestBuilder patch =
                MockMvcRequestBuilders.patch("/boards/hit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch)
                .andExpect(content().string("You've missed!"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testPatchRequestToBoardHitEndpointWhenStrikeWillReturnMessageThatItIsAHitAndTheNameOfTheShip() throws Exception {

        Board testBoard = new Board(2);
        Ship ship1 = new Ship(Cell.Type.DESTROYER, 2);
        testBoard.addShip(ship1, 0);
        Cell cell = new Cell(Cell.Type.DESTROYER, Cell.Value.HIT);
        testBoard.getBoardField().set(0,cell);
        Mockito.when(spyGame.getComputerPlayer()).thenReturn(mockComputerPlayer);
        Mockito.when(spyGame.getComputerPlayer().getBoard()).thenReturn(testBoard);
        Mockito.when(spyGame.updateBoardWithStrike(1)).thenReturn("The DESTROYER has been hit!");

        String changesToJSON = "{\"row\":0,\"column\":1}";
        MockHttpServletRequestBuilder patch =
                MockMvcRequestBuilders.patch("/boards/hit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch)
                .andExpect(content().string("The DESTROYER has been hit!"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testPatchRequestToBoardHitEndpointWhenTheSpotHasAlreadyBeenHitReturnMessageToConfirm() throws Exception {

        Board testBoard = new Board(2);
        Cell cell = new Cell(Cell.Type.WATER, Cell.Value.MISSED);
        testBoard.getBoardField().set(0,cell);
        Mockito.when(spyGame.getComputerPlayer()).thenReturn(mockComputerPlayer);
        Mockito.when(spyGame.getComputerPlayer().getBoard()).thenReturn(testBoard);
        String changesToJSON1 = "{\"row\":0,\"column\":0}";
        MockHttpServletRequestBuilder patch2 =
                MockMvcRequestBuilders.patch("/boards/hit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON1);
        this.mockMvc.perform(patch2)
                .andExpect(content().string("The spot cannot be hit twice!"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}








