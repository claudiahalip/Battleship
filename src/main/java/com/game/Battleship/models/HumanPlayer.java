package com.game.Battleship.models;

import java.util.List;

public class HumanPlayer {

    private Board board;
    private Fleet fleet;

    public HumanPlayer(){

        this.board = new Board();
    }

    public Board getBoard() { return board; }

    public List<Ship> getFleet() { return fleet.getFleet();}
}
