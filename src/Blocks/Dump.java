package Blocks;

import Exceptoins.WrongParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static Blocks.Block.InOutParameter.IN_OUT;

public class Dump implements Block {
    private static final Logger logger = LogManager.getLogger(Dump.class.getName());

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

        logger.trace("Execution completed successfully!");
    }

    @Override
    public InOutParameter getParamOfBlock() {
        return IN_OUT;
    }
}