package view.utility;

import java.util.Optional;

import controller.utility.Pair;

public class Originator {

    private Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> state;
    
    public void setState(final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> status) {
        this.state = status;
    }

    public Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(final Memento memento) {
        this.state = memento.getState();
    }
}