import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> dukeList;

    public DukeList() {
        dukeList = new ArrayList<>(100);
    }

    //General function to add task to list
    public void printAddList(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask.toString());
        System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
    }

    public void addToDo(String description) {
        ToDo newToDo = new ToDo(description);
        dukeList.add(newToDo);
        printAddList(newToDo);
    }

    public void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        dukeList.add(newDeadline);
        printAddList(newDeadline);
    }

    public void addEvent(String description, String from, String to) {
        Event newEvent = new Event(description, from, to);
        dukeList.add(newEvent);
        printAddList(newEvent);
    }

    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = dukeList.get(i);
            System.out.println(num + ". " + currTask.toString());
        }
    }

    public void setTaskAsDone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    public void setTaskAsUndone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }
}
