package com.bnta.word_guesser.models;

public class Game {

    private String word;
    private int guesses;
    private boolean complete;

    public Game(){

    }

    public Game(String word){
        this.word = word;
        this.guesses = 0;
        this.complete = false;
    }

    public String getWord(){
        return this.word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public int getGuesses(){
        return this.guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public boolean isComplete(){
        return this.complete;
    }

    public void setComplete (boolean isComplete){
        this.complete = isComplete;
    }
}
