package view;

public interface ITable {

    
    //probabilmente inutile come MyTable
    public void searchBy(/*tipo di richerca*/, /*elemento della ricerca*/);
    
    public void reset();
    
    public void editMode(boolean flag);
    
}
