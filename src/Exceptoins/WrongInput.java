package Exceptoins;

public class WrongInput extends Exception {
    public WrongInput() {
        super();
    }

    public WrongInput(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
