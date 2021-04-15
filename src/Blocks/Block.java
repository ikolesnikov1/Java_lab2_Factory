package Blocks;

import Exceptoins.WrongParameters;

import java.io.IOException;
import java.util.ArrayList;

public interface Block {
    enum InOutParameter {
        DEFAULT,

        IN,
        OUT,
        IN_OUT
    }

    void execute(ArrayList<String> text, ArrayList<String> currentArguments) throws WrongParameters, IOException;

    InOutParameter getParamOfBlock();
}