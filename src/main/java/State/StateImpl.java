package State;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;

import java.util.HashMap;
import java.util.Set;


public class StateImpl implements State {

    String message;
    HashMap<String, State> transitions;
    String[] posSym;
    private UniformIntegerDistribution dist;

    public StateImpl(String message, String[] posSym) {
        transitions = new HashMap<String, State>();
        this.message = message;
        this.posSym = posSym;
        if(posSym != null) {
            dist = new UniformIntegerDistribution(0, posSym.length - 1);
        }
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String generateSymbol() {
        return posSym[dist.sample()];
    }

    @Override
    public State setTransition(String regEx, State nextState) {
        this.transitions.put(regEx, nextState);
        return this;
    }

    @Override
    public State getState(String in) {
        Set<String> set = this.transitions.keySet();
        for (String s : set) {
            if (in.matches(s)) {
                return this.transitions.get(s);
            }
        }
        return null;
    }
}
