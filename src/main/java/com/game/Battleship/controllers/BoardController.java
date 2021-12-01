package com.game.Battleship.controllers;
import com.game.Battleship.models.Board;
import com.game.Battleship.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/boards")
    public List<Board> getAllBoard(){

        return boardRepository.allBoards();

    }

    @RequestMapping("boards/{name}")
    public Board getBoard(@PathVariable("name") String name){
        return boardRepository.findByName(name);
    }

    @PatchMapping(value = "/boards/{name}")
    public Board updateBoard(@PathVariable String name,
                             @RequestBody Map<String, Object> changes) {

        Board board = boardRepository.findByName(name);
        Integer index = (Integer) changes.get("index");
        String newValue = (String) changes.get("newValue");

        return boardRepository.updateBoard(board, index, newValue);
    }

 }
