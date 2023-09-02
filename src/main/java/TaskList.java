import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public int numberOfCompletedTasks = 0;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        Task removedTask = taskList.remove(index);
        if (removedTask.isComplete()) numberOfCompletedTasks--;
    }

    public void clearAll() {
        taskList.clear();
    }

    public int getNumberOfTask() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public boolean isAllComplete() {
        return taskList.size() == numberOfCompletedTasks;
    }

    public String list() {
        if (taskList.size() == 0) {
            return "list looks empty to me!";
        } else {
            StringBuilder displayList = new StringBuilder();
            if (isAllComplete()) {
                displayList.append("Wow! You are ALL COMPLETE!!!!\n")
                        .append("TIME TO PLAY WITH MEEEEE :DDDD\n");
            } else if (taskList.size() > 10) {
                displayList.append("Seems like you have a lot of things to do...\n")
                        .append("Remember to play with me after :D\n");
            } else {
                displayList.append("Here are the things you told me to keep track of:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    public String markDone(int index) {
        Task task = get(index);
        if (task.isComplete()) {
            return "That was done already...\n" +
                    "are you sure you wanted to mark that?\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks++;
            return "Yay! One step closer to playing with me!\n"
                    + task.toString();
        }
    }

    public String markNotDone(int index) {
        Task task = get(index);
        if (!task.isComplete()) {
            return "Don't worry it's still not done\n" +
                    "What are you doing? Let's get it done now!\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks--;
            return "Oh no... what happened :(\n"
                    + task.toString();
        }
    }
}
