package State;

public interface State {

    State setTransition(String regEx, State nextState);

    State getState(String in);

    String getMessage();

    String generateSymbol();
}
