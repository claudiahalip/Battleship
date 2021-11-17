package com.game.Battleship.controllers;

import com.game.Battleship.models.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("/board")
    public Board getBoard(){

        Board board = new Board();
        return board;
    }

}
