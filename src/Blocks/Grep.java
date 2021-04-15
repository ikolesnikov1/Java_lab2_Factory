package Blocks;

import Exceptoins.WrongParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static Blocks.Block.InOutParameter.IN_OUT;

public class Grep implements Block {
    private static final Logger logger = LogManager.getLogger(Grep.class.getName());
    @Override
    public void execute(ArrayList<String> text, ArrayList<String> currentArguments) throws WrongParameters, FileNotFoundException {
        logger.trace("Starting execution!");
        if (currentArguments.size() != 1) {
            throw new WrongParameters();
        }

        ArrayList <String> selected = new ArrayList<>();
        String wordToFind = currentArguments.get(0);

        for (String line : text) {
            if (line.contains(wordToFind)) {
                selected.add(line);
            }
        }

        text.removeAll(text);
        text.addAll(selected);

        logger.trace("Execution completed successfully!");
    }

    @Override
    public InOutParameter getParamOfBlock() {
        return IN_OUT;
    }
}