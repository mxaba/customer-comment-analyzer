package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	private static final int numberOfThreads = 2;
	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();

		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		ArrayList<Future<Map<String, Integer>>> futureResultList = new ArrayList<>();

		for (File commentFile : commentFiles) {
			futureResultList.add(executor.submit(new CommentAnalyzer(commentFile)));
		}
		futureResultList.forEach(result->{
				try {
					addReportResults(result.get(), totalResults);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
		});

		executor.shutdown();
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (var entry : source.entrySet()) {
			// System.out.println(target.containsKey(entry.getKey()));
			if (target.containsKey(entry.getKey())){
				target.put(entry.getKey(), entry.getValue() + target.get(entry.getKey()));
			} else {
				target.put(entry.getKey(), entry.getValue());
			}
		}
	}

}
