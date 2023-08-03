package com.bnta.word_guesser.models;

public class Reply { //DTO

    private String wordState; // "*****/ when guessed would be like / " **ll*
    private String message;
    private boolean correct;

    public Reply(){

    }

    public Reply(String wordState, String message, boolean correct){
        this.wordState = wordState;
        this.message = message;
        this.correct = correct;
    }

    public String getWordState(){
        return this.wordState;
    }

    public void setWordState(String wordState){
        this.wordState = wordState;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
