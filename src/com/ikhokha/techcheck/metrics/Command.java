package com.ikhokha.techcheck.metrics;

public class Command extends Counter {
    public void keepCounter(String comment,  String command, String key) {
        String[] splittedComment = comment.split(" ");
        if(splittedComment.length != 0){
            for (String word : splittedComment) {
                if(word.toLowerCase().contains(command.toLowerCase())){
                    setCounter(key);
                }
            }
        }
    }
}
