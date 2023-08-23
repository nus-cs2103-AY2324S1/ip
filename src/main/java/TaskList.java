import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int i) {
        if (i <= 0 || i > taskList.size()) {
            throw new IllegalArgumentException("this task does not exist, genius..");
        } else {
            return taskList.get(i - 1);
        }
    }

    public void doTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(5));
            getTask(number).markDone();
            System.out.println("ALRIGHT NICE I'll mark this as completed :)");
            System.out.println(getTask(number));
        }
        catch (NumberFormatException n) {
            System.out.println("OI open ur eyes and give a proper input ITS \"mark\" AND A NUMBER");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void undoTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(7));
            getTask(number).markUndone();
            System.out.println("Oh nooo I will mark this undone then :(");
            System.out.println(getTask(number));
        }
        catch (NumberFormatException n) {
            System.out.println("OI open ur eyes and give a proper input ITS \"unmark\" AND A NUMBER");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added:\n" + task.toString());
    }

    public void printList(String input) {
        if (!input.equals("list")) {
            throw new IllegalArgumentException("Nice job did you mean \"list\" coz what you gave wasn't an accepted input");
        } else {
            for (Task t : taskList) {
                System.out.printf("%d.%s%n", taskList.indexOf(t) + 1, t.toString());
            }
        }
    }
}
