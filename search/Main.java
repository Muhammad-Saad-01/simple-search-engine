package search;

import search.engine.SearchEngine;
import search.engine.strategies.AllStrategy;
import search.engine.strategies.AnyStrategy;
import search.engine.EngineStrategy;
import search.engine.strategies.NoneStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static List<String> people;
    static Scanner reader = new Scanner(System.in);
    private static final HashMap<String, EngineStrategy> strategyOptions = new HashMap<>() {{
        put("ANY", new AnyStrategy());
        put("ALL", new AllStrategy());
        put("NONE", new NoneStrategy());

    }};

    public static void main(String[] args) {
        HashMap<String, String> params = convertArgsToKeyValuePair(args);
        String fileName = params.get("data");
        try {
            people = readFileAsListOfStrings(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMenu();
        int option = Integer.parseInt(reader.nextLine());
        while (true) {
            if (option == 0) {
                System.out.println("Bye!");
                break;
            } else if (option == 1) {
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String strategyOption = reader.nextLine();
                search(strategyOptions.get(strategyOption));

            } else if (option == 2) {
                printPeople();
            } else {
                System.out.println("Incorrect option! Try again.");
            }
            printMenu();
            option = Integer.parseInt(reader.nextLine());
        }


    }


    private static void printPeople() {
        System.out.println("=== List of people ===");
        for (String person : people) {
            System.out.println(person);
        }
    }


    public static void printMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }


    public static void search(EngineStrategy strategyOption) {
        String[] searchWord;
        System.out.println("Enter a name or email to search all suitable people.");
        reader = new Scanner(System.in);
        searchWord = reader.nextLine().toLowerCase().split(" ");
        SearchEngine searchEngine = new SearchEngine(people, strategyOption);
        List<String> foundList = searchEngine.search(searchWord);
        if (!foundList.isEmpty()) {
            System.out.println(foundList.size() + " persons found: ");
            foundList.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }

    }


    private static HashMap<String, String> convertArgsToKeyValuePair(String[] args) {

        HashMap<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i].substring(2);
            String value = args[i + 1];
            params.put(key, value);
        }

        return params;
    }

    public static List<String> readFileAsListOfStrings(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}
