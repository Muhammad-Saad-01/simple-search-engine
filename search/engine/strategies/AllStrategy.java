package search.engine.strategies;

import search.engine.EngineStrategy;

import java.util.*;

public class AllStrategy implements EngineStrategy {

    @Override
    public List<String> search(String[] searchWords, TreeMap<String, List<Integer>> invertedIndex, List<String> data) {
        Set<Integer> unionSet = new TreeSet<>();
        for (String word : searchWords)
            if (invertedIndex.containsKey(word)) {
                List<Integer> indexes = invertedIndex.get(word);
                unionSet.addAll(indexes);
            }

        for (String word : searchWords)
            if (invertedIndex.containsKey(word)) {
                List<Integer> indexes = invertedIndex.get(word);
                unionSet.retainAll(indexes);
            }
        List<String> intersectionList = new ArrayList<>();
        unionSet.forEach(index -> intersectionList.add(data.get(index)));
        return intersectionList;
    }
}
