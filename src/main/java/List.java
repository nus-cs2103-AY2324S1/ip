import java.util.ArrayList;

public class List {

    private int taskNumber;

    private ArrayList<Task> storagePile;

    public List() {
        taskNumber = 1;
        storagePile = new ArrayList<>();
    }

    public void input(String item) {
        storagePile.add(new Task(item));
        taskNumber++;
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

    public void checkItem(int x) {
        storagePile.get(x-1).markDone();
    }

    public void notDoneItem(int x) {
        storagePile.get(x-1).markUndone();
    }



    public String showThisTask(int x) {
        return storagePile.get(x-1).toString();
    }
}
