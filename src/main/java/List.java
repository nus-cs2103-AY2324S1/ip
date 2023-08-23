import java.util.ArrayList;

public class List {

    ArrayList<String> storagePile;

    public List() {
        storagePile = new ArrayList<String>();
    }

    public void input(String item) {
        storagePile.add(item);
    }

    public String toString() {
        int leng = storagePile.size();
        String listed = "";
        for (int i = 1; i <= leng; i++) {
            listed += String.format("%s - %s",
                    i, storagePile.get(i-1)) +" \n" ;
        }
        return listed;
    }
}
