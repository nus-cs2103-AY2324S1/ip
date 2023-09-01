package phi;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private Task getTask(int i) {
        if (i <= 0 || i > taskList.size()) {
            throw new IllegalArgumentException("this task does not exist, genius..");
        } else {
            return taskList.get(i - 1);
        }
    }

    public void doTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(5));
            Task t = getTask(number);
            System.out.println("ALRIGHT NICE I'll mark this as completed :)");
            t.markDone();
            System.out.println(t);
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
            Task t = getTask(number);
            System.out.println("Oh nooo I will mark this undone then :(");
            t.markUndone();
            System.out.println(t);
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

    public void deleteTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(7));
            Task t = getTask(number);
            System.out.printf("Alright say bye bye to task %d!%n", number);
            System.out.println(t);
            this.taskList.remove(t);
            System.out.printf("There's %d task(s) in the list now.%n", taskList.size());
        }
        catch (NumberFormatException n) {
            System.out.println("Ugh to delete stuff, you have to input \"delete\" and the number...");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printList(String input) {
        if (!input.equals("list")) {
            throw new IllegalArgumentException("Nice job did you mean \"list\" coz what you gave wasn't an accepted input");
        } else {
            if (taskList.isEmpty()) {
                System.out.println("You do know that to SHOW a list there has to be stuff INSIDE the (currently empty) list right?");
            } else {
                for (Task t : taskList) {
                    System.out.printf("%d.%s%n", taskList.indexOf(t) + 1, t.toString());
                }
            }
        }
    }

    public void addFromSc(String input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\|");
        // parameter handling
        String taskType = sc.next();
        boolean isComplete = Boolean.parseBoolean(sc.next());
        String taskMsg = sc.next();

        switch (taskType) {
            // todo task
            case "T":
                this.taskList.add(new ToDo(taskMsg, isComplete));
                break;
            // deadline task
            case "D":
                String deadline = sc.next();
                this.taskList.add(new Deadline(taskMsg, isComplete, deadline));
                break;
            // event task
            case "E":
                String start = sc.next();
                String end = sc.next();
                this.taskList.add(new Event(taskMsg, isComplete, start, end));
                break;
            default:
                System.out.println("something went wrong...");
        }
    }

    public String outputList() {
        StringBuilder output = new StringBuilder();
        for (Task t : this.taskList) {
            output.append(t.outputFormat()).append("\n");
        }
        return output.toString();
    }

    public int listSize() {
        return this.taskList.size();
    }

}
