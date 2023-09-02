package phi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the task list of the chatbot
 */
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

    /**
     * Marks a task as done and returns the corresponding response message
     * Contains error handling for incorrect user inputs
     *
     * @param input User-inputted string to be parsed
     * @return      String response
     */
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

    /**
     * Marks a task as undone and returns the corresponding response message
     * Contains error handling for incorrect user inputs
     *
     * @param input User-inputted string to be parsed
     * @return      String response
     */
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

    /**
     * Deletes a task from the list and returns the corresponding response message
     * Contains error handling for incorrect user inputs
     *
     * @param input User-inputted string to be parsed
     * @return      String response
     */
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

    /**
     * Prints the current task list. If list is empty, returns a corresponding response message
     *
     * @return String representation of task list
     */
    public String printList() {
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

    /**
     * Adds a task into the list from input given by the stored tasklist.txt file
     *
     * @param input each individual line of input in the .txt file
     */
    public void addFromTxt(String input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\|");
        // parameter handling
        String taskType = sc.next();
        boolean isComplete = Boolean.parseBoolean(sc.next());
        String taskMsg = sc.next();

        switch (taskType) {
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

    /**
     * Returns the entire tasklist in output format, to be written by a Storage object.
     *
     * @return A String containing the entire task list, formatted in output style.
     */
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
