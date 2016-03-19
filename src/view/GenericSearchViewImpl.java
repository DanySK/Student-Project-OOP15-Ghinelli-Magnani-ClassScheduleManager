package view;

public class GenericSearchViewImpl implements GenericSearchView {

    @Override
    public void createBaseFrame() {
        new ScheletonGUI();
    }

    @Override
    public void setSearchType(SearchView viewType) {
        /*il parametro è un oggetto che ha un metodo che torna le lezioni da far visualizzare*/
    }

}
