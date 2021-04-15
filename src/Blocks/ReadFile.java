package Blocks;

import Exceptoins.WrongParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

import static Blocks.Block.InOutParameter.IN;

public class ReadFile implements Block {
    private static final Logger logger = LogManager.getLogger(ReadFile.class.getName());
    @Override
    public void execute(ArrayList<String> text, ArrayList<String> currentArguments) throws WrongParameters, IOException {
        logger.trace("Starting execution!");
        if (currentArguments.size() != 1) {
            throw new WrongParameters();
        }

        String filename = currentArguments.get(0);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename)));

        while (reader.ready()) {
            text.add(reader.readLine());
        }

        reader.close();

        logger.trace("Execution completed successfully!");
    }

    @Override
    public InOutParameter getParamOfBlock() {
        return IN;
    }
}