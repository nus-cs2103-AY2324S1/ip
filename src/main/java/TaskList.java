import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myList = new ArrayList<>();
    int numOfItems;
    public TaskList() {
        myList = new ArrayList<>();
        numOfItems = 0;
    }
    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
        numOfItems = myList.size();
    }
    public void add(Task task) {
        myList.add(task);
    }
//    public void add(String task) throws MaxException {
//        // Parse command based on type of task (Todo, Deadline, Event)
//        if (task.startsWith("todo")) {
//
//        } else if (task.startsWith("deadline")) {
//
//        } else if (task.startsWith("event")) {
//
//        }
//
//        // Visual feedback from chatbot
//        Ui ui = new Ui();
//        ui.showAdd(this);
//        // Increment pointer
//        numOfItems++;
//    }
    public ArrayList<Task> getList() {
        return myList;
    }
    public void delete(int taskNumber) throws MaxException {
        if (taskNumber > myList.size()) {
            throw new MaxException("     Seems like that number is out of range. Check again!");
        }
        Task toDelete = myList.get(taskNumber - 1);
        myList.remove(toDelete);
        numOfItems--;
    }
    public void mark(int taskNumber) throws MaxException {
        if (taskNumber > myList.size()) {
            throw new MaxException("     Seems like that number is out of range. Check again!");
        }
        myList.get(taskNumber - 1).mark();
    }
    public void unmark(int taskNumber) throws MaxException {
        if (taskNumber > myList.size()) {
            throw new MaxException("     Seems like that number is out of range. Check again!");
        }
        myList.get(taskNumber - 1).unmark();
    }
}
