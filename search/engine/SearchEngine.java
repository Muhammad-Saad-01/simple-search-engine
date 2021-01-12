package search.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SearchEngine {
    List<String> data;
    TreeMap<String, List<Integer>> invertedIndex;
    private final EngineStrategy engineStrategy;

    public SearchEngine(List<String> dataAsListOfStrings, EngineStrategy engineStrategy) {
        this.data = dataAsListOfStrings;
        this.engineStrategy = engineStrategy;
        invertedIndex = getInverterIndex();
    }

    public List<String> search(String... searchWord) {
        return this.engineStrategy.search(searchWord, invertedIndex, data);
    }

    TreeMap<String, List<Integer>> getInverterIndex() {
        TreeMap<String, List<Integer>> inverterIndex = new TreeMap<>();
        int index = 0;
        List<Integer> indexes;
        for (String lineOfData : data) {
            for (String word : lineOfData.split(" ")) {
                if (inverterIndex.containsKey(word.toLowerCase())) {
                    indexes = inverterIndex.get(word.toLowerCase());
                } else {
                    indexes = new ArrayList<>();
                }
                indexes.add(index);
                inverterIndex.put(word.toLowerCase(), indexes);
            }
            index++;
        }
        return inverterIndex;
    }

}


