package cheems;

import java.util.ArrayList;

public interface Storable {
    public void saveNewTask(String a);
    public ArrayList<String[]> loadData();
    public void updateWholeDatabase(String a);
}
