package view.utility;

import controller.utility.Pair;

public class Memento {
    
    private final Pair<Object, Pair<Integer, Integer>> state;
    
    public Memento(final Pair<Object, Pair<Integer, Integer>> status) {
        this.state = status;
    }
    
    public Pair<Object, Pair<Integer, Integer>> getState() {
        return this.state;
    }
}
