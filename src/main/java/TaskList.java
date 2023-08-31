import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task taskDesc) {
        if (taskList.size() < 100) {
           taskList.add(taskDesc);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + taskDesc);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
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
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + taskList.get(taskNum));
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public void notDoneNotDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            taskList.get(taskNum).isNotCompleted();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + taskList.get(taskNum));
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public boolean isMarked(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            return taskList.get(taskNum).isDone;
        } else {
            System.out.println("Error: No such Task Number");
            return false;
        }
    }

    public void deleteTask(int taskNum) {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            Task removedTask = taskList.remove(taskNum);
            System.out.println("Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Error: No such Task Number");
        }
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public void loadTasks(TaskList loadedTasks) {
        taskList.clear();
        taskList.addAll(loadedTasks.getTasks());
    }


}
