package Blocks;

import Exceptoins.WrongParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

import static Blocks.Block.InOutParameter.IN_OUT;

public class Sort implements Block {
    private static final Logger logger = LogManager.getLogger(Sort.class.getName());
    @Override
    public void execute(ArrayList<String> text, ArrayList<String> currentArguments) throws WrongParameters, FileNotFoundException {
        logger.trace("Starting execution!");
        if (currentArguments.size() != 0) {
            throw new WrongParameters();
        }

        text.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        logger.trace("Execution completed successfully!");
    }

    @Override
    public InOutParameter getParamOfBlock() {
        return IN_OUT;
    }
}