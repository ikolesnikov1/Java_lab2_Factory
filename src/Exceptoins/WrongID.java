package Exceptoins;

public class WrongID extends Exception {
    public WrongID() {
        super();
    }

    public WrongID(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}