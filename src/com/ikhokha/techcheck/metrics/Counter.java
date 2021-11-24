package com.ikhokha.techcheck.metrics;

import java.util.HashMap;
import java.util.Map;

public class Counter {
    protected Map<String, Integer> counterMap = new HashMap<>();
    
    public void setCounter(String key) {
        counterMap.putIfAbsent(key, 0);
		counterMap.put(key, counterMap.get(key) + 1);
    }

    public Map<String, Integer> getCounterMap() {
        return counterMap;
    }

    public void keepCounter(String comment,  String command) {

    }
}
