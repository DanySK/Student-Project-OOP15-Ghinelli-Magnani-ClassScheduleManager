package view.utility;

import controller.utility.Pair;

public class Originator {

    private Pair<Object, Pair<Integer, Integer>> state;
    
    public void setState(final Pair<Object, Pair<Integer, Integer>> status) {
        this.state = status;
    }

    public Pair<Object, Pair<Integer, Integer>> getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(final Memento memento) {
        this.state = memento.getState();
    }
}