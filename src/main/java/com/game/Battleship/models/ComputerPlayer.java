package com.game.Battleship.models;

public class ComputerPlayer {

    private Board board;

    public ComputerPlayer(){

        this.board = new Board();
    }

    public Board getBoard(){ return this.board; }

    public void setBoard(Board board){ this.board = board; }
}
