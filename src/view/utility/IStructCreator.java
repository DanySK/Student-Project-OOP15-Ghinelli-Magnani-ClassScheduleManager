package view.utility;

import java.util.List;

public interface IStructCreator { // non dovrebbe servire più
    
    List<List<Object>> getStruct(final int searchType, List<Object> list);

}
