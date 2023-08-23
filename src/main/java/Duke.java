import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    protected static String divider = "   _______________________________________ \n";
    protected static String greeting = "   Hello! I'm Sana \n   What can I do for you? \n";
    protected static String bye = "   Bye. Hope to see you again soon! \n";

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String command = myObj.next();
            String userInput = myObj.nextLine();

            if (command.equals("bye")) {
                System.out.println(divider + bye + divider);
                break;
            }

            switch (command) {
            case "list":
                System.out.println(list(tasksList));
                break;
            case "mark":
                System.out.println(mark(tasksList, userInput));
                break;
            case "unmark":
                System.out.println(unmark(tasksList, userInput));
                break;
            case "todo":
                try {
                    System.out.println(todo(tasksList, userInput));
                } catch (DukeException e) {
                    System.out.println(divider + "    " + e.getMessage() + "\n" + divider);
                }
                break;
            case "deadline":
                try {
                    System.out.println(deadline(tasksList, userInput));
                } catch (DukeException e) {
                    System.out.println(divider + "    " + e.getMessage() + "\n" + divider);
                }
                break;
            case "event":
                try {
                    System.out.println(event(tasksList, userInput));
                } catch (DukeException e) {
                    System.out.println(divider + "    " + e.getMessage() + "\n" + divider);
                }
                break;
            default:
                try {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(divider + "    " + e.getMessage() + "\n" + divider);
                }
            }
        }
    }

    public static String list(ArrayList<Task> tasksList) {
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            int id = i + 1;
            task.append("   " + id + "." + tasksList.get(i).toString() + "\n");
        }
        return(divider + "   Here are the tasks in your list: \n" + task
                + divider);
    }

    public static String mark(ArrayList<Task> tasksList, String userInput) {
        int taskId = userInput.charAt(1) - '0';
        tasksList.get(taskId - 1).markAsDone();
        return(divider + "   Nice! I've marked this task as done: \n     "
                + tasksList.get(taskId - 1).toString() + "\n" + divider);
    }

    public static String unmark(ArrayList<Task> tasksList, String userInput) {
        int taskId = userInput.charAt(1) - '0';
        tasksList.get(taskId - 1).markAsNotDone();
        return(divider + "   OK, I've marked this task as not done yet: \n     "
                + tasksList.get(taskId - 1).toString() + "\n" + divider);
    }

    public static String todo(ArrayList<Task> tasksList, String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo(userInput);
        tasksList.add(newTodo);
        return(divider + "   Got it. I've added this task: \n" + "    " + newTodo.toString() + "\n"
                + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list \n" + divider);
    }

    public static String deadline(ArrayList<Task> tasksList, String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("OOPS!!! Incomplete task description.\n    Make sure you follow the format " +
                    "'deadline [name of task] /by [deadline]'");
        }

        int lastDescId = userInput.indexOf('/');
        if (lastDescId == -1) {
            throw new DukeException("OOPS!! The deadline cannot be empty. \n    Make sure you follow the format " +
                    "'deadline [name of task] /by [deadline]'");
        }
        String desc = userInput.substring(0, lastDescId - 1);
        String by = userInput.substring(lastDescId + 4);

        Task newDeadline = new Deadline(desc, by);
        tasksList.add(newDeadline);
        return(divider + "   Got it. I've added this task: \n" + "    " + newDeadline.toString() + "\n"
                + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list \n" + divider);
    }

    public static String event(ArrayList<Task> tasksList, String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("OOPS!!! Incomplete command. Make sure you follow the format " +
                    "'event [name of task] /from [from] /to [to]'");
        }

        int lastDescId = userInput.indexOf('/');
        Task newEvent = getEvent(userInput, lastDescId);
        tasksList.add(newEvent);
        return(divider + "   Got it. I've added this task: \n" + "    " + newEvent.toString() + "\n"
                + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list \n" + divider);
    }

    private static Task getEvent(String userInput, int lastDescId) throws DukeException {
        if (lastDescId == -1) {
            throw new DukeException("OOPS!! The 'from' field cannot be empty. \n    Make sure you follow the format " +
                    "'deadline [name of task] /from [from] /to [to]'");
        }
        String desc = userInput.substring(0, lastDescId - 1);

        int lastFromId = userInput.indexOf('/', lastDescId + 1);
        if (lastFromId == -1) {
            throw new DukeException("OOPS!! The 'to' field cannot be empty. \n    Make sure you follow the format " +
                    "'deadline [name of task] /from [from] /to [to]'");
        }
        String from = userInput.substring(lastDescId + 6, lastFromId - 1);
        String to = userInput.substring(lastFromId + 4);

        Task newEvent = new Event(desc, from, to);
        return newEvent;
    }
}
