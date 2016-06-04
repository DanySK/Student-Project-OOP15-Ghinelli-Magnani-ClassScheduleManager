package application;

import controller.Controller;
import view.ViewImpl;

public final class Main {
    
    private Main() {
        
    }
    
    public static void main(final String... args) {
        Controller.getController().setView(new ViewImpl());
    }
}
