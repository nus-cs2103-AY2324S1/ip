package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.MissingDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.exceptions.IncorrectCommandFormatException;

/**
 * The TaskListStorage class stores an arraylist of tasks.
 * It contains functionality to manipulate this list (ie, marking, unmarking,
 * adding, deleting tasks)
 * It also handles saving/loading the list of tasks to/from a file.
 * 
 * Notes: Combining Task and Storage is a solution adopted from the discussion here https://github.com/nus-cs2103-AY2324S1/forum/issues/30
 */
public class TaskListStorage {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private static final String TASK_FILEPATH = "." + File.separator + "data" + File.separator + "tasks.txt";
    private final File file;

    public TaskListStorage() {
        this.file = new File(TASK_FILEPATH);
        
        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (java.io.IOException e) {
                String errorString = "Error creating file! Make sure you have the correct permissions.\nOtherwise tasks will not be saved.\n"
                        + e.getMessage();
                Messages.printInLine(errorString);
            }
        }

        try {
            this.loadFromFile();
        } catch (FileNotFoundException | IncorrectCommandFormatException | MissingDescriptionException e) {
            Messages.printInLine(e.getMessage());
        }
    }

    private void loadFromFile() throws FileNotFoundException, IncorrectCommandFormatException, MissingDescriptionException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] taskInfo = line.split(" \\| ");
            String taskType = taskInfo[0];
            boolean isDone = taskInfo[1].equals("1");
            String taskDescription = taskInfo[2];
            switch (taskType) {
            case "T":
                taskList.add(new Todo(taskDescription, isDone));
                break;
            case "D":
                taskList.add(new Deadline(taskDescription, taskInfo[3], isDone));
                break;
            case "E":
                taskList.add(new Event(taskDescription, taskInfo[3], taskInfo[4], isDone));
                break;
            default:
                break;
            }
        }
        sc.close();
    }

    private void writeTaskListToFile(ArrayList<Task> taskList, String filepath) {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (java.io.IOException e) {
                String errorString = "Error creating file! Make sure you have the correct permissions.\nOtherwise tasks will not be saved.\n"
                        + e.getMessage();
                Messages.printInLine(errorString);
            }
        }

        try {
            java.io.FileWriter fw = new java.io.FileWriter(filepath);
            for (Task task : taskList) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            Messages.printInLine(e.getMessage());
        }
    }

    public void printList() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        Messages.printInLine(s);
    }

    public void markAsDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= taskList.size() || index < 0) {
            Messages.printInLine("Invalid index!");
            return;
        }
        taskList.get(index).markAsDone();
        writeTaskListToFile(taskList, TASK_FILEPATH);
        String outputString = "Nice! I've marked this task as done:\n" + Messages.TAB
                + taskList.get(index).toString();
        Messages.printInLine(outputString);
    }

    public void markAsUndone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= taskList.size() || index < 0) {
            Messages.printInLine("Invalid index!");
            return;
        }
        taskList.get(index).markAsUndone();
        writeTaskListToFile(taskList, TASK_FILEPATH);
        String outputString = "Ok! I've marked this task as not done yet:\n" + Messages.TAB
                + taskList.get(index).toString();
        Messages.printInLine(outputString);
    }

    // public void addTodo(String input) throws MissingDescriptionException {
    //     taskList.add(new Todo(Commands.extractTaskDescription(input)));
    //     writeTaskListToFile(taskList, TASK_FILEPATH);
    //     String outputString = "Got it. I've added this task:\n" + Messages.TAB + taskList.get(taskList.size() - 1)
    //             + "\nNow you have " + taskList.size() + " tasks in the list.";
    //     Messages.printInLine(outputString);
    // }

    // public void addDeadline(String input) throws MissingDescriptionException, IncorrectCommandFormatException {
    //     Deadline deadline = new Deadline(Commands.extractTaskDescription(input),
    //             Commands.extractDeadline(input));
    //     taskList.add(deadline);
    //     writeTaskListToFile(taskList, TASK_FILEPATH);
    //     String outputString = "Got it. I've added this task:\n" + Messages.TAB + taskList.get(taskList.size() - 1)
    //             + "\nNow you have " + taskList.size() + " tasks in the list.";
    //     Messages.printInLine(outputString);
    // }

    // public void addEvent(String input) throws MissingDescriptionException, IncorrectCommandFormatException {
    //     Event event = new Event(Commands.extractTaskDescription(input),
    //             Commands.extractEventFrom(input), Commands.extractEventTo(input));
    //     taskList.add(event);
    //     writeTaskListToFile(taskList, TASK_FILEPATH);
    //     String outputString = "Got it. I've added this task:\n" + Messages.TAB + taskList.get(taskList.size() - 1)
    //             + "\nNow you have " + taskList.size() + " tasks in the list.";
    //     Messages.printInLine(outputString);
    // }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= taskList.size() || index < 0) {
            Messages.printInLine("Invalid index!");
            return;
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        writeTaskListToFile(taskList, TASK_FILEPATH);
        String outpuString = "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        Messages.printInLine(outpuString);
    }
}
