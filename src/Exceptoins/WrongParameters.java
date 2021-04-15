package Exceptoins;

public class WrongParameters extends Exception {
    public WrongParameters() {
        super();
    }

    public WrongParameters(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}