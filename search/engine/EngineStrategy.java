package search.engine;

import java.util.List;
import java.util.TreeMap;

public interface EngineStrategy {
    List<String> search(String[] searchWord, TreeMap<String, List<Integer>> invertedIndex, List<String> data);
}
