package com.bnta.word_guesser.controllers;

import com.bnta.word_guesser.models.Game;
import com.bnta.word_guesser.models.Guess;
import com.bnta.word_guesser.models.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // is now preconfigured to handle REST requests and helps with serialisation JAVA -> JSON vice versa
@RequestMapping(value = "/games") //
public class GameController {

//    @GetMapping("/games") // localhost:8080/games GET
//    public Game newGame(){
//        return new Game("hello"); // Spring will serialize it to JSON
//    }
    private Game game;
    private String currentWord;
    private ArrayList<String> guessedLetters;

    @PostMapping // localhost:8080/games Reply
    public ResponseEntity<Reply> newGame(){ // we use this to move data not to do CRUD functions

        this.game = new Game("hello");
        this.currentWord = "*****";
        this.guessedLetters = new ArrayList<>();

        Reply reply = new Reply(currentWord,"New game Started", false); // Spring will serialize it to JSON
        return new ResponseEntity<>(reply, HttpStatus.OK);// takes in object you want to send back, status code
    }

    @GetMapping // give us the current word
    public ResponseEntity<Reply> getGameStatus(){
        Reply reply = new Reply(currentWord, "Game is in progress", false);
        return new ResponseEntity<>(reply,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Reply> handleGuess(@RequestBody Guess guess){ // will use annotation on guess parameter
        Reply reply;

        // check if game started
        if(game == null){
            reply = new Reply(
                    currentWord,
                    "Game has not been started",
                    false
            );
            return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
        }
        // check if a letter is already guessed or not
        if(guessedLetters.contains(guess.getLetter())){
            reply =new Reply(
                    currentWord,
                    String.format(
                            "Already guessed the letter %s",
                            guess.getLetter()),
                    false
            );
            return new ResponseEntity<>(reply,HttpStatus.OK);
        }

        guessedLetters.add(guess.getLetter());

        // check for incorrect guess
        if(!game.getWord().contains(guess.getLetter())){
            reply = new Reply(
                    currentWord,
                    String.format(
                            "%s is not in the word.",
                            guess.getLetter()),
                    false);
            return new ResponseEntity<>(reply,HttpStatus.OK);
        }
        // if none of the above, then process correct guess

        String runningSolution = game.getWord(); // original words get word

        for(Character letter : game.getWord().toCharArray()){ // .toCharArray gives access to all individual letters
            if(!guessedLetters.contains(letter.toString())){
                runningSolution = runningSolution.replace(letter, '*');

            }
        }

        currentWord = runningSolution;

        reply = new Reply(currentWord,String.format("%s is in the word ", guess.getLetter()),true);
        return new ResponseEntity<>(reply,HttpStatus.OK);

        //      flip * to correct letters
        //      add a letter to guessed letters
        //      check if we won the game

    }


}
