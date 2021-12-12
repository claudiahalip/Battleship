package com.game.Battleship.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private List<Board> boardList;

    public Game() {

        this.humanPlayer = new HumanPlayer();
        this.computerPlayer = new ComputerPlayer();
        this.boardList = new ArrayList<>(Arrays.asList(
                this.humanPlayer.getBoard(),
                this.computerPlayer.getBoard()));
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public ComputerPlayer getComputerPlayer() {
        return computerPlayer;
    }

    public List<Board> getAllBoards() {
        return boardList;
    }

    public String updateBoardWithStrike(Integer index) {

        Cell cell = computerPlayer.getBoard().getBoardField().get(index);
        if (cell.getValue().equals(Cell.Value.WATER)) {
            cell.setValue(Cell.Value.MISSED);
        } else if (cell.getValue().equals(Cell.Value.SHIP)) {
            cell.setValue(Cell.Value.HIT);
        }
        computerPlayer.getBoard().getBoardField().set(index, cell);
        changeShipStatus(cell);
        String message = messageToSentAfterUpdateBoard(cell);
        return message;
    }

    public String updateBoardWithShips(Ship ship, Integer index) {

        System.out.println("++++++++inside update with ships");
        humanPlayer.getBoard().addShip(ship, index);
        return "The ship is placed!";
    }

    public Integer convertCoordinatesToIndex(Board board, Integer row, Integer column) {

        Integer index = row * board.getSize() + column;
        return index;
    }

    private void changeShipStatus(Cell cell){

        if (isShipSunk(cell, computerPlayer.getBoard()) ){
            Ship ship = getShipFromFleet(cell.getType(), computerPlayer.getFleet());
            ship.setSunk(true);
        }
    }

    private String messageToSentAfterUpdateBoard(Cell cell){

        String message = null;
        if(isAMissed(cell)){
            message =  "You've missed!";
        }
        else {
            if(isGameOver(computerPlayer.getBoard())) {
                message = "The game is over!";
            }
            else
                if (isShipSunk(cell, computerPlayer.getBoard())) {
                    message = "The " + cell.getType() + " has sunk";
                } else  {
                    message = "The " + cell.getType() + " has been hit!";
                }
        }
        return message;
    }

    private Boolean isGameOver(Board board) {

        for (Ship ship : computerPlayer.getFleet()) {
            return ship.getSunk();
        }
        return true;
    }

    private Boolean isAMissed(Cell cell) {

        return (cell.getValue().equals(Cell.Value.MISSED));
    }

    private Boolean isShipTypeSunk(Board board, Enum type, Integer size) {

        Integer submarineCellsCount = Math.toIntExact(board.getBoardField().stream().
                filter(cell -> cell.getType().equals(type) && cell.getValue().equals(Cell.Value.HIT))
                .count());
        return submarineCellsCount == size;
    }

    private Boolean isShipSunk(Cell cell, Board board){

        switch (cell.getType()) {
            case CARRIER:
                return isShipTypeSunk(board, Cell.Type.CARRIER, 5);
            case BATTLESHIP:
                return isShipTypeSunk(board, Cell.Type.BATTLESHIP, 4);
            case CRUISER:
                return  isShipTypeSunk(board, Cell.Type.CRUISER, 3);
            case SUBMARINE:
                return isShipTypeSunk(board, Cell.Type.SUBMARINE, 3);
            case DESTROYER:
                return isShipTypeSunk(board, Cell.Type.DESTROYER, 2);
            default: return false;
        }
    }

    private Ship getShipFromFleet(Enum type, List<Ship> fleet){

        Ship shipFromComputerFleet = null;
        for (Ship ship : fleet) {
            if (ship.getType().equals(type)){
                shipFromComputerFleet = ship;
            }
        }
        return shipFromComputerFleet;
    }
}
