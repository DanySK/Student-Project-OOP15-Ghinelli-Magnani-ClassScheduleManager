package view;

public class GenericViewImpl implements GenericView {
    
    private final GenericSearchView searchView = new GenericSearchViewImpl();

    @Override
    public void setViewType(final int value) {
        if (value == 0) {
           this.searchView.createBaseFrame(); 
        }
        /*aggiungere caso della modifica degli orari e inserimento(dovrebbe essere comune)*/
    }

}
