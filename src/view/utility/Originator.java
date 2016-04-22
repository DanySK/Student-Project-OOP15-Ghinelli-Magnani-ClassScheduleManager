package view.utility;

public class Originator {

    private String state;
    
    public void setState(final String status) {
        this.state = status;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(final Memento memento) {
        this.state = memento.getState();
    }
}