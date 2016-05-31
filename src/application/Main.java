package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Controller;
import view.IView;
import view.ViewImpl;

public final class Main {
    
    private Main() {
        
    }
    
    public static void main(final String... args) {
        try {
            Controller.getController().readConfiguration();
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error:", e);
        }
        final IView view = new ViewImpl();
        view.addData(0, null);
        Controller.getController().setView(view);
    }
}
