package view.utility;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    
    private List<Memento> mementoList = new ArrayList<>();

    public void add(final Memento state) {
       this.mementoList.add(state);
    }

    public Memento get(final int index) {
       return this.mementoList.get(index);
    }
    
    public int mementoListSize() {
        return this.mementoList.size();
    }
    
    public void removeUsedMemento(final int index) {
        this.mementoList.remove(index);
    }
    
    public void cleanMementoList() {
        this.mementoList = new ArrayList<>();
    }
}
