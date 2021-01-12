package search.engine.strategies;

import search.engine.EngineStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NoneStrategy implements EngineStrategy {

    @Override
    public List<String> search(String[] searchWords, TreeMap<String, List<Integer>> invertedIndex, List<String> data) {
        Set<Integer> differanceSet = IntStream.range(0, data.size()).boxed().collect(Collectors.toCollection(TreeSet::new));
        for (String word : searchWords) {
            if (invertedIndex.containsKey(word)) {
                List<Integer> indexes = invertedIndex.get(word);
                differanceSet.removeAll(indexes);
            }
        }
        List<String> intersectionList = new ArrayList<>();
        differanceSet.forEach(index -> intersectionList.add(data.get(index)));
        return intersectionList;
    }
}
