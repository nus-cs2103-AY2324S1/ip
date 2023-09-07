package Jarvis;

import java.util.ArrayList;

public class Ui {
    private static final String name = "Jarvis";
    private static final String line = "____________________________________________________________";
    private static final String greeting = "Good day Sir! I'm ";
    private static final String question = "How can I help you today Sir?";
    private static final String signOff = "Good bye Sir!";
    private static final String listInforming = "Here are the tasks in your list Sir:";
    private static final String markInforming = "Roger that Sir, I've marked this task as done:";
    private static final String uncheckInforming = "Noted Sir, I've marked this task as NOT done yet:";
    private static final String taskInforming = "As you please Sir, I've added the task:";
    private static final String deleteInforming = "Alright Sir, I've removed this task";
    private static final String findInforming = "Here are the matching tasks in your list Sir:";

    public static String printGreeting() {
        return greeting + name + "!" + "\n" + question;
    }

    public static String getByeMessage() {
        return signOff;
    }

    public static String getMarkMessage(Task currentTask) {
        return markInforming + "\n" + currentTask.toString();
    }

    public static String getUncheckMessage(Task currentTask) {
        return uncheckInforming + "\n" + currentTask.toString();
    }

    public static String getListOfCommands(ArrayList<String> validCommands, Exception e) {
        String returnString = e.getMessage() + "\n";
        for (int i = 0; i < validCommands.size(); i++) { // listing out the current task
            int numbering = i + 1;
            returnString = returnString + "    " + numbering + ". " + validCommands.get(i) + "\n";
        }
        return returnString;
    }

    public static String getWrongFormatMessage(String command, Exception e) {
        if (command.equals("mark")) {
            return e.getMessage() + "\n" +
                    "    The following is the correct format:" + "\n"
                    + "        mark <task number>";
        } else if (command.equals("uncheck")) {
            return e.getMessage() + "\n" +
                    "    The following is the correct format:" + "\n"
                    + "        uncheck <task number>";
        } else if (command.equals("todo")) {
            return e.getMessage() + "\n" +
                    "    The following is the correct format:" + "\n"
                    + "        todo <description>";
        } else if (command.equals("deadline")) {
            return e.getMessage() + "\n" +
                    "    The following is the correct format:" + "\n"
                    + "        deadline <description> /by <date or time>";
        } else {
            return e.getMessage() + "\n" +
                    "    The following is the correct format:" + "\n"
                    + "        event <description> /from <date or time> /to <date or time>";
        }
    }

    public static String getTaskMessage(TaskList tasks, Task currentTask) {
        return taskInforming + "\n" + "You have now " + tasks.countTask() + " tasks in the list Sir.";
    }

    public static String getDeleteMessage(TaskList tasks, Task currentTask) {
        return deleteInforming + "\n" + "You have now " + tasks.countTask() + " tasks in the list Sir.";
    }

    public static String getTaskList(TaskList tasks) {
        String returnString = listInforming + "\n";
        for (int i = 0; i < tasks.countTask(); i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = tasks.getTask(i);
            returnString = returnString + count + "." + currentTask.toString() + "\n";
        }
        return returnString;
    }

    public static String getFoundTaskMessage(ArrayList<Task> foundTasks) {
        String returnString = findInforming + "\n";
        for (int i = 0; i < foundTasks.size(); i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = foundTasks.get(i);
            returnString = returnString + count + "." + currentTask.toString() + "\n";
        }
        return returnString;
    }

    public static String respond(String message) {
        return message;
    }
}
