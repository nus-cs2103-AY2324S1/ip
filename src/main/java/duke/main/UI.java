package duke.main;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;

public class UI {

    public String printLine() {
        return "______________________________________";
    }

    public String greetUser(String chatBotName) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n"
                + printLine() + "\n"
                + "Hello! I'm " + chatBotName + "! SUIIII!!!\n"
                + "What can I do for you?";
    }

    public String displayList(ArrayList<Task> list, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < numOfTasks; i++) {
            sb.append((i + 1) + ". " + list.get(i)).append("\n");
        }
        sb.append(printLine());
        return sb.toString();
    }

    public String displayError(DukeException e) {
        return printLine() + "\n" + e.getMessage() + "\n" + printLine();
    }

    public String exit() {
        return printLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + printLine();
    }

    public String addTask(String taskName, int numOfTasks) {
        String message = numOfTasks != 1
                ? "Now you have " + numOfTasks + " tasks in your list, just like how I have 5 Ballon d'Ors."
                : "Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.";
        return printLine() + "\n"
                + "Got it. I've added the task:\n" + taskName + "\n"
                + message + "\n"
                + printLine();
    }

    public String deleteTask(String taskName, int numOfTasks) {
        return printLine() + "\n"
                + "Removed task:\n" + taskName + "\n"
                + "Now you have " + numOfTasks + " tasks in your list.\n"
                + printLine();
    }

    public String markTask(String taskName) {
        return printLine() + "\n"
                + "SIUUU! I've marked this task as done. We will make Saudi League number 1.\n [X] " + taskName + "\n"
                + printLine();
    }

    public String unMarkTask(String taskName) {
        return printLine() + "\n"
                + "OK, I've marked this task as not done.\n [ ] " + taskName + "\n"
                + printLine();
    }

    protected String displayFilteredList(ArrayList<Task> filteredList, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        if (numOfTasks == 0) {
            sb.append("There are no tasks matching your search :(");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < numOfTasks; i++) {
                sb.append((i + 1) + ". " + filteredList.get(i)).append("\n");
            }
        }
        sb.append(printLine());
        return sb.toString();
    }
}
