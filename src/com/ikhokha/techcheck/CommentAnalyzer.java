package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.ikhokha.techcheck.metrics.Command;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
		Command commandPassed = new Command();		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				commandPassed.keepCounter(line, "MOVER", "MOVER_MENTIONS");
				commandPassed.keepCounter(line, "SHAKER", "SHAKER_MENTIONS");
				commandPassed.keepCounter(line, "15", "SHORTER_THAN_15");
				commandPassed.keepCounter(line, "?", "QUESTIONS");
				commandPassed.keepCounter(line, "SPaM", "SPAM");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		return commandPassed.getCounterMap();
	}
}
