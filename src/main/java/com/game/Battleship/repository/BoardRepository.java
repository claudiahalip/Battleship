package com.game.Battleship.repository;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BoardRepository {

    private List<Board>  boardList = new ArrayList<>(Arrays.asList(
        new Board("currentPlayer"),
        new Board("AIPlayer")
    ));

    public List<Board> allBoards(){
        return boardList;
    }

    public Board updateBoard(Board board, Integer index, String newValue){

            Cell cell = board.getBoardField().get(index);

            if (cell.getValue() == null && newValue.equals("Ship")) {
                cell.setValue("Ship");
            } else if (cell.getValue() == null && newValue.equals("Strike")) {
                cell.setValue("Missed");
            } else if (cell.getValue().equals("Ship") && newValue.equals("Strike")) {
                cell.setValue("Hit");
            }

            board.getBoardField().set(index, cell);
            return board;

    }

    public Board findByName(String name){
        Board findBoardByName = null;
        for(Board board: boardList){
            if(board.getName().equals(name)){
                findBoardByName = board;

            }
        }
        return findBoardByName;

    }

}
