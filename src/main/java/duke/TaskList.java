package duke;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> userData;

    public TaskList(ArrayList<Task> userData) {
        this.userData = userData;
    }

    public void addTask(Task newTask) {
        this.userData.add(newTask);
    }

    public void deleteTask(int referenceIndex) {
        this.userData.remove(referenceIndex);
    }
}
