package view.utility;

public class Memento {
    
    private final String state;
    
    public Memento(final String status) {
        this.state = status;
    }
    
    public String getState() {
        return this.state;
    }
}
