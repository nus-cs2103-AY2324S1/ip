package tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(0);
    }

    public void inputHandler(String input) {

        if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark")) {
            markTaskDone(input);
        } else if (input.startsWith("unmark")) {
            unmarkTaskDone(input);
        } else {
            addTask(input);
        }

    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:\n");

        if (taskList.size() == 0) {
            System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\");
        }

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i) + "\n");
        }
    }

    public void markTaskDone(String input) {

        // retrieve the index input
        int index = input.indexOf(" ");
        String numStr = input.substring(index + 1);
        int number = Integer.parseInt(numStr);
        System.out.println(number);
        taskList.get(number - 1).setIsDone();

        System.out.println("Yay! You have completed this task:\n" +
                taskList.get(number - 1) + "\n");
    }

    public void unmarkTaskDone(String input) {
        // retrieve the index input
        int index = input.indexOf(" ");
        String numStr = input.substring(index + 1);
        int number = Integer.parseInt(numStr);
        taskList.get(number - 1).setIsNotDone();

        System.out.println("Ok... Guess you're not actually done with this:\n" +
                taskList.get(number - 1) + "\n");
    }

    public void addTask(String input) {
        String[] parts = input.split(" ", 2);

        if (parts[0].equals("todo")) {

            Task todo = new ToDo(parts[1]);
            this.taskList.add(todo);

            System.out.println("added new task:\n" + todo);
            System.out.println("you now have " + taskList.size() + " tasks in your list." + "\n");

        } else if (parts[0].equals("deadline")) {

            String[] arr = parts[1].split("/");
            Task deadline = new Deadline(arr[0], arr[1]);
            this.taskList.add(deadline);

            System.out.println("added new task:\n" + deadline);
            System.out.println("you now have " + taskList.size() + " tasks in your list." + "\n");

        } else if (parts[0].equals("event")) {

            String[] arr = parts[1].split("/");
            Task event = new Event(arr[0], arr[1], arr[2]);
            this.taskList.add(event);

            System.out.println("added new task:\n" + event);
            System.out.println("you now have " + taskList.size() + " tasks in your list." + "\n");

        } else {
            System.out.println("invalid!");
            return;
        }
    }
}
