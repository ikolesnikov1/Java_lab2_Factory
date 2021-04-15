package Executor;

import Exceptoins.WrongID;
import Exceptoins.WrongInput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    public static void parseInput(WorkflowExecutor executor) throws IOException, WrongInput, WrongID {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(executor.getFilename())));
        Map<String, WorkflowExecutor.Pair<String, ArrayList<String>>> association = new HashMap<>();

        while (reader.ready()) {
            String currentLine = reader.readLine();

            String[] keyWords = currentLine.split(" +");

            String firstWord = keyWords[0];
            if (firstWord.equals("csed")) {
                break;
            } else if (!firstWord.equals("desc")) {
                String mustBeEqualitySymbol = keyWords[1];
                if (!mustBeEqualitySymbol.equals("=")) {
                    throw new WrongInput();
                }

                ArrayList <String> args = new ArrayList<>(Arrays.asList(keyWords).subList(3, keyWords.length));
                String command = keyWords[2];

                association.put(firstWord, new WorkflowExecutor.Pair<>(command, args));
            }
        }

        if (reader.ready()) {
            String[] sequence = reader.readLine().split(" +-> +");
            for (String current : sequence) {
                if (association.containsKey(current)) {
                    executor.getSequence().add(association.get(current));
                } else {
                    throw new WrongID();
                }
            }
        }
    }
}