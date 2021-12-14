package com.game.Battleship.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import com.game.Battleship.models.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BoardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRequestToBoardEndpoint() throws Exception {

        Game game = new Game();
        //to transform an Object into Json I've added : implementation 'com.fasterxml.jackson.core:jackson-databind:2.10.0' in build.gradle file
        String allBoardsToJason = new ObjectMapper().writeValueAsString(game.getAllBoards());
        this.mockMvc.perform(get("/boards"))
                .andExpect(content().string(allBoardsToJason))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testPatchRequestToBoardPlaceEndpoint() throws Exception {

        Board board = new Board();
        board.getBoardField().get(0).setType(Cell.Type.DESTROYER);
        board.getBoardField().get(0).setValue(Cell.Value.SHIP);
        board.getBoardField().get(1).setType(Cell.Type.DESTROYER);
        board.getBoardField().get(1).setValue(Cell.Value.SHIP);
        String boardToJson = new ObjectMapper().writeValueAsString(board);
        String changesToJSON = "{\"row\":0,\"column\":0,\"shipType\":\"DESTROYER\", \"shipSize\": 2, \"isHorizontal\":true}";
        MockHttpServletRequestBuilder patch =
                MockMvcRequestBuilders.patch("/boards/place")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch)
                .andExpect(status().isOk())
                .andExpect(content().string(boardToJson))
                .andDo(print());
    }

    @Test
    public void testPatchRequestToBoardHitEndpoint() throws Exception {

        Board board = new Board();
        board.getBoardField().get(0).setValue(Cell.Value.MISSED);
        String boardToJson = new ObjectMapper().writeValueAsString(board);
        String changesToJSON = "{\"row\":0,\"column\":0}";
        MockHttpServletRequestBuilder patch =
                MockMvcRequestBuilders.patch("/boards/hit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(changesToJSON);
        this.mockMvc.perform(patch)
                .andExpect(content().string(boardToJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
}








