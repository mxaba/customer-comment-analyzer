package com.ikhokha.techcheck.metrics;

import java.net.URL;

public class Command extends Counter {
    public void keepCounter(String comment,  String command, String key) {
        String[] splittedComment = comment.split(" ");
        if(splittedComment.length != 0){
            command = command.toLowerCase();
            if(command.equals("spam")){
                for (String word : splittedComment) {
                    if(isURLValid(word)){
                        setCounter(key);
                    }
                }
            }else if(command.equals("15")){
                if(splittedComment.length < 15){
                    setCounter(key);
                }
            } else{
                for (String word : splittedComment) {
                    if(word.toLowerCase().contains(command)){
                        setCounter(key);
                    }
                }
            }
        }
    }

    static boolean isURLValid(String currentWord){
        try {
            var url = new URL(currentWord);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
