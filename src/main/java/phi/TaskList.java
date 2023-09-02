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

    public String doTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(5));
            Task t = getTask(number);
            t.markDone();
            return "ALRIGHT NICE I'll mark this as completed :)\n" + t;
        }
        catch (NumberFormatException | StringIndexOutOfBoundsException n) {
            return "OI open ur eyes and give a proper input ITS \"mark\" AND A NUMBER";
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String undoTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(7));
            Task t = getTask(number);
            t.markUndone();
            return "Oh nooo I will mark this undone then :(\n" + t;
        }
        catch (NumberFormatException n) {
            return "OI open ur eyes and give a proper input ITS \"unmark\" AND A NUMBER";
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String addTask(Task task) {
        this.taskList.add(task);
        return "Added:\n" + task;
    }

    public String deleteTask(String input) {
        try {
            int number = Integer.parseInt(input.substring(7));
            Task t = getTask(number);
            System.out.printf("Alright say bye bye to task %d!%n", number);
            System.out.println(t);
            this.taskList.remove(t);
            return String.format("There's %d task(s) in the list now.%n", taskList.size());
        }
        catch (NumberFormatException n) {
            return "Ugh to delete stuff, you have to input \"delete\" and the number...";
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return "Something went wrong, please try again :/";
    }

    public String printList(String input) {
        if (!input.equals("list")) {
            throw new IllegalArgumentException("Nice job did you mean \"list\" coz what you gave wasn't an accepted input");
        } else {
            if (taskList.isEmpty()) {
                return "You do know that to SHOW a list there has to be stuff INSIDE the (currently empty) list right?";
            } else {
                StringBuilder output = new StringBuilder();
                for (Task t : taskList) {
                    output.append(String.format("%d.%s%n", taskList.indexOf(t) + 1, t.toString()));
                }
                return output.toString();
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

    public String findTasks(String input) {
        if (input.equals("find")) {
            throw new IllegalArgumentException("You gotta put something for me to find...");
        }
        String findText = input.substring(5);
        StringBuilder output = new StringBuilder("Here's all the tasks that match your keyword!\n");
        for (Task t : this.taskList) {
            if (t.getMsg().contains(findText)) {
                output.append(t).append("\n");
            }
        }
        return output.toString();
    }
}
