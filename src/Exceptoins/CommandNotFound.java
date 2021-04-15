package Exceptoins;

public class CommandNotFound extends Exception {
    public CommandNotFound() {
        super();
    }

    public CommandNotFound(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}