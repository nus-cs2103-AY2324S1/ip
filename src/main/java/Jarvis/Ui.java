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

    public static void printGreeting() {
        System.out.println(line);
        System.out.println(greeting + name + "!");
        System.out.println(question);
        System.out.println(line);
    }

    public static void printBye() {
        System.out.println(line);
        System.out.println(signOff);
        System.out.println(line);
    }

    public static void printMark(Task currentTask) {
        System.out.println(line);
        System.out.println(markInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    public static void printUncheck(Task currentTask) {
        System.out.println(line);
        System.out.println(uncheckInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    public static void printListOfCommands(ArrayList<String> validCommands, String inputCommand, Exception e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println("    This is the list of valid commands:");
        for (int i = 0; i < validCommands.size(); i++) {
            int numbering = i + 1;
            System.out.println("    " + numbering + ". " + validCommands.get(i));
        }
        System.out.println(line);
    }

    public static void printWrongFormat(String command, Exception e) {
        if (command.equals("mark")) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println("    The following is the correct format:");
            System.out.println("        mark <task number>");
            System.out.println(line);
        } else if (command.equals("uncheck")) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println("    The following is the correct format:");
            System.out.println("        uncheck <task number>");
            System.out.println(line);
        } else if (command.equals("todo")) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println("    The following is the correct format:");
            System.out.println("        todo <description>");
            System.out.println(line);
        } else if (command.equals("deadline")) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println("    The following is the correct format:");
            System.out.println("        deadline <description> /by <date or time>");
            System.out.println(line);
        } else if (command.equals("event")) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println("    The following is the correct format:");
            System.out.println("        event <description> /from <date or time> /to <date or time>");
            System.out.println(line);
        }

    }

    public static void printTask(TaskList tasks, Task currentTask) {
        System.out.println(line);
        System.out.println(taskInforming);
        System.out.println(currentTask.toString());
        System.out.println("You have now " + tasks.countTask() + " tasks in the list Sir.");
        System.out.println(line);
    }

    public static void printDelete(TaskList tasks, Task currentTask) {
        System.out.println(line);
        System.out.println(deleteInforming);
        System.out.println(currentTask.toString());
        System.out.println("You have now " + tasks.countTask() + " tasks in the list Sir.");
        System.out.println(line);
    }

    public static void printTaskList(TaskList tasks) {
        System.out.println(line);
        System.out.println(listInforming);
        for (int i = 0; i < tasks.countTask() ; i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = tasks.getTask(i);
            System.out.println(count + "." + currentTask.toString());
        }
        System.out.println(line);
    }

    public static void printFoundTask(ArrayList<Task> foundTasks) {
        System.out.println(line);
        System.out.println(findInforming);
        for (int i = 0; i < foundTasks.size() ; i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = foundTasks.get(i);
            System.out.println(count + "." + currentTask.toString());
        }
        System.out.println(line);
    }

}
