package TaskList;
import Task.*;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task taskDesc) {
        if (taskList.size() < 100) {
           taskList.add(taskDesc);
        } else {
            System.out.println(" 100/100 Task limit reached.");
            System.out.println(" Pay to upgrade your account.");
        }
    }

    public void displayTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No task present in list");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    public void doneAndDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            taskList.get(taskNum).isCompleted();
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public void notDoneNotDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            taskList.get(taskNum).isNotCompleted();
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public boolean isMarked(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            return taskList.get(taskNum).checkIsDone();
        } else {
            System.out.println("Error: No such Task Number");
            return false;
        }
    }

    public Task deleteTask(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            Task removedTask = taskList.remove(taskNum);
            return removedTask;
        } else {
            System.out.println("Error: No such Task Number");
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void loadTasks(TaskList loadedTasks) {
        taskList.clear();
        taskList.addAll(loadedTasks.getTasks());
    }


}
