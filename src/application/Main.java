package application;

import view.IView;
import view.IViewImpl;

public final class Main {
    
    private Main() {
        
    }
    
    public static void main(final String... args) {
        final IView view = new IViewImpl();
        view.addData(null);
    }
}
