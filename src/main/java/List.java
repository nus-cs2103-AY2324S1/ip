import java.util.ArrayList;

public class List {

    private ArrayList<Task> storagePile;

    public List() {
        storagePile = new ArrayList<>();
    }

    public void input(String item) {
        String firstWord = item.split(" ")[0];
        String task = item.split(" ", 2)[1];
        if (firstWord.equals("todo")) {
            storagePile.add(new ToDoTask(task));
        } else if (firstWord.equals("deadline")) {
            storagePile.add(new DeadlineTask(task));
        } else {
            storagePile.add(new EventTask(task));
        }
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

    public int numOfItems() {
        return storagePile.size();
    }
}
