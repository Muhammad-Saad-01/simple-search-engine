package search.engine.strategies;

import search.engine.EngineStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AnyStrategy implements EngineStrategy {

    @Override
    public List<String> search(String[] searchWords, TreeMap<String, List<Integer>> invertedIndex, List<String> data) {
        List<String> intersectionList = new ArrayList<>();
        for (String word : searchWords) {
            if (invertedIndex.containsKey(word)) {
                List<Integer> indexes = invertedIndex.get(word);
                indexes.forEach(index -> intersectionList.add(data.get(index)));
            }
        }
        return intersectionList;
    }
}
