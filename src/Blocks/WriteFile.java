package Blocks;

import Exceptoins.WrongParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

import static Blocks.Block.InOutParameter.OUT;

public class WriteFile implements Block {
    private static final Logger logger = LogManager.getLogger(WriteFile.class.getName());
    @Override
    public void execute(ArrayList<String> text, ArrayList<String> currentArguments) throws WrongParameters, FileNotFoundException {
        logger.trace("Starting execution!");
        if (currentArguments.size() != 1) {
            throw new WrongParameters();
        }

        String filename = currentArguments.get(0);
        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filename)));

        for (String line : text) {
            writer.write(line + '\n');
        }

        writer.close();

        for (int i = 0; i < text.size(); ++i) {
            text.removeAll(text);
        }

        logger.trace("Execution completed successfully!");
    }

    @Override
    public InOutParameter getParamOfBlock() {
        return OUT;
    }
}