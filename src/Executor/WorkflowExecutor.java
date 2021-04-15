package Executor;

import java.io.IOException;
import java.util.ArrayList;

import Blocks.Block;
import Exceptoins.WrongInput;
import Exceptoins.WrongID;
import Exceptoins.WrongParameters;

import Factory.BlockFactory;
import org.apache.log4j.*;

public class WorkflowExecutor {
    private static final Logger logger = LogManager.getLogger(WorkflowExecutor.class);

    Block.InOutParameter previousState = Block.InOutParameter.DEFAULT;

    static class Pair <C extends String, A extends ArrayList<String>> {
        C command;
        A arguments;

        public Pair(C first, A second) {
            this.setCommand(first);
            this.setArguments(second);
        }

        public C getCommand() {
            return command;
        }

        public void setCommand(C command) {
            this.command = command;
        }

        public A getArguments() {
            return arguments;
        }

        public void setArguments(A arguments) {
            this.arguments = arguments;
        }
    }

    private ArrayList <String> text;
    private final String filename;

    private ArrayList <Pair <String, ArrayList <String>>> workflowSequence;
    private int indexinSequence = 0;

    public WorkflowExecutor(String inputFile) {
        logger.trace("Creating new workflow environment...");

        this.filename = inputFile;
        workflowSequence = new ArrayList<>();
        text = new ArrayList<>();

        logger.trace("Workflow environment created successfully!");
    }


    public ArrayList<String> getText() {
        return text;
    }

    ArrayList<Pair<String, ArrayList<String>>> getSequence() {
        return workflowSequence;
    }

    String getFilename() {
        return filename;
    }


    private boolean allSequenceRead() {
        return this.indexinSequence == workflowSequence.size();
    }

    private String getCurrentKey() {
        return workflowSequence.get(indexinSequence).getCommand();
    }

    private ArrayList <String> getCurrentArguments() {
        return workflowSequence.get(indexinSequence).getArguments();
    }

    public void start() {
        BasicConfigurator.configure();
        try {
            logger.trace("Start parsing raw data...");
            FileParser.parseInput(this);
            logger.trace("Parsing completed successfully!");

            while (!allSequenceRead()) {
                Block newBlock = BlockFactory.getInstance().getBlock(getCurrentKey());
                logger.trace("Instance of " + newBlock.getClass().getSimpleName() + " has created.");

                checkCurrentBlock(newBlock);
                newBlock.execute(text, getCurrentArguments());
                indexinSequence++;
            }
            logger.trace("SUCCESS! All sequence completed without errors! This session will be terminated.\n");

        } catch (IOException e) {
            logger.error("An error has occured during reading file or creating buffer!");
            logger.error("Process will be terminated.");
        } catch (WrongInput e) {
            logger.error("Raw data has wrong format!");
            logger.error("Process will be terminated.");
        } catch (WrongID e) {
            logger.error("Workflow sequence contains undefined ID!");
            logger.error("Process will be terminated.");
        } catch (WrongParameters e) {
            logger.error("In created block passed wrong number of arguments!");
            logger.error("Process will be terminated.");
        } catch (Exception e) {
            logger.error("An error has occured during creating of block!");
            logger.error("Process will be terminated.");
        }
    }

    private void checkCurrentBlock(Block b) throws WrongInput {
        switch (b.getParamOfBlock()) {
            case IN -> {
                if (indexinSequence != 0 || previousState != Block.InOutParameter.DEFAULT) {
                    throw new WrongInput();
                }
            }
            case OUT -> {
                if (indexinSequence != workflowSequence.size() || previousState == Block.InOutParameter.OUT) {
                    throw new WrongInput();
                }
            }
            case IN_OUT -> {
                if (previousState == Block.InOutParameter.OUT) {
                    throw new WrongInput();
                }
            }
        }
    }
}
