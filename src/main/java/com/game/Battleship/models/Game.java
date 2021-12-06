package com.game.Battleship.models;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Game {

    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    List<Board> boardList;

    public Game(){

        this.humanPlayer = new HumanPlayer();
        this.computerPlayer = new ComputerPlayer();
        this.boardList = new ArrayList<>(Arrays.asList(
                this.humanPlayer.getBoard(),
                this.computerPlayer.getBoard()));
    }

    public HumanPlayer getHumanPlayer() { return humanPlayer; }

    public ComputerPlayer getComputerPlayer(){ return computerPlayer; }

    public List<Board> getAllBoards(){
        return boardList;
    }

    public Board updateBoardWithStrike( Integer row, Integer column){

        Integer index = convertCoordinatesToIndex(computerPlayer.getBoard(), row, column);
        Cell cell = computerPlayer.getBoard().getBoardField().get(index);
        if (cell.getValue() == Cell.Value.WATER ) {
            cell.setValue(Cell.Value.MISSED);
        } else if (cell.getValue() == Cell.Value.SHIP ) {
            cell.setValue(Cell.Value.HIT);
        }
        computerPlayer.getBoard().getBoardField().set(index, cell);
        return computerPlayer.getBoard();
    }

    public Board updateBoardWithShips(Ship ship, Integer row, Integer column){

        Integer index = convertCoordinatesToIndex(humanPlayer.getBoard(), row, column);
        humanPlayer.getBoard().addShip(ship, index);
        return humanPlayer.getBoard();
    }

    public Integer convertCoordinatesToIndex(Board board, Integer row, Integer column){

        Integer index = row * board.getSize() + column;
        return index;
    }
}
