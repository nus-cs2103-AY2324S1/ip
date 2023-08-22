import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> dukeList;

    public DukeList() {
        dukeList = new ArrayList<>(100);
    }

    public void addToList(String input) {
        //Instantiate a new task
        Task newTask = new Task(input);
        dukeList.add(newTask);
        System.out.println("added: " + input);
    }

    public void displayList() {
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = dukeList.get(i);
            System.out.println(num + ". " + currTask.returnDescription());
        }
    }

    public void setTaskAsDone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.returnDescription());
    }

    public void setTaskAsUndone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.returnDescription());
    }
}
