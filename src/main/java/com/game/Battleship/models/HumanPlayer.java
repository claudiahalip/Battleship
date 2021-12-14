package com.game.Battleship.models;

public class HumanPlayer {

    private Board board;

    public HumanPlayer(){

        this.board = new Board();
    }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }
}
