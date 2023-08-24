import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public void addTask(Task task) {
        listOfTasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index) {
        Task removedTask = listOfTasks.remove(index);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void listOutTasks() {
        if (listOfTasks.isEmpty()) {
            System.out.println("There is currently no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println((i + 1) + "." + listOfTasks.get(i));
            }
        }
    }

    public void markAsDone(int i) {
        if (i >= 0 && i < listOfTasks.size()) {
            listOfTasks.get(i).markDone();
            System.out.println("Nice! I've marked this task as done:\n " + listOfTasks.get(i));
        } else {
            System.out.println("This is an invalid task index, please check. ");
        }
    }

    public void markAsUndone(int i) {
        if (i >= 0 && i < listOfTasks.size()) {
            listOfTasks.get(i).markUndone();
            System.out.println("OK, I've marked this task as not done yet:\n " + listOfTasks.get(i));
        } else {
            System.out.println("This is an invalid task index, please check. ");
        }
    }


}
