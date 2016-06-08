package view.utility;

import java.util.Optional;

import controller.utility.Pair;

public class Memento {
    
    private final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> state;
    
    public Memento(final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> status) {
        this.state = status;
    }
    
    public Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> getState() {
        return this.state;
    }
}
