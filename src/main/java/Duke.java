import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ----------------------------------------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static Boolean programRunning = true;

    private static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    private static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }
    
    private static void greet() {
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)");
        messages.add("What do you want to do today?");
        Duke.respond(messages);
    }

    private static void exit() {
        Duke.respond("Bye. Hope to see you again soon!");
    }

    private static void listTasks(ArrayList<Task> tasks) throws DukeException{
        ArrayList<String> output = new ArrayList<String>();

        for (int i = 0; i < tasks.size(); i++) {
            String taskOutput = String.format("%d. %s", i + 1, tasks.get(i));
            output.add(taskOutput);
        }

        if (output.size() == 0) {
            Duke.respond("There are no tasks in your list.");
            return;
        }

        Duke.respond(output);
    }

    private static void markTaskAsDone(String args) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"mark 1\" to mark the first task as done.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            Task targetTask = tasks.get(taskIndex);
            targetTask.markAsDone();

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Nice! I've marked this task as done:");
            messages.add(String.format("  %s",targetTask.toString()));

            Duke.respond(messages);
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void markTaskAsUndone(String args) throws DukeException{
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"unmark 1\" to mark the first task as not done.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            Task targetTask = tasks.get(taskIndex);
            targetTask.markAsUndone();

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("OK, I've marked this task as not done yet:");
            messages.add(String.format("  %s",targetTask.toString()));

            Duke.respond(messages);
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void deleteTask(String args) throws DukeException{
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"delete 1\" to delete the first task.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            String targetTaskInfo = tasks.get(taskIndex).toString();
            tasks.remove(taskIndex);

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Noted. I've removed this task:");
            messages.add(String.format("  %s", targetTaskInfo));
            messages.add(String.format("Now you have %d tasks in the list.", tasks.size()));

            Duke.respond(messages);
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void respondWithAddedTask(Task newTask) {
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Got it. I've added this task:");
        messages.add(String.format("  %s", newTask.toString()));
        messages.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        Duke.respond(messages);
    }

    private static void addTodo(String description) throws DukeException{
        if (description.trim().equals("")) {
            throw new DukeException("No description specified. Please specify a description.");
        }

        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        Duke.respondWithAddedTask(newTodo);
    }

    private static void addDeadline(String args) throws DukeException {
        String[] splitArguments = args.split("/");

        String description = splitArguments[0];
        String dueDateTime = null;

        for (int i = 1; i < splitArguments.length; i++) {
            if (splitArguments[i].startsWith("by ")) {
                dueDateTime = splitArguments[i].substring(3).trim();
            }
        }

        if (description.equals("")) {
            throw new DukeException("No description specified. Please specify a description.");
        }

        if (dueDateTime == null || dueDateTime.equals("")) {
            throw new DukeException("No due date/time specified. Please specify a due date/time");
        }

        Task newDeadline = new Deadline(description, dueDateTime);
        tasks.add(newDeadline);
        Duke.respondWithAddedTask(newDeadline);
    }

    private static void addEvent(String args) throws DukeException{
        String[] splitArguments = args.split("/");

        String description = splitArguments[0].trim();
        String startDatetime = null;
        String endDatetime = null;
        
        for (int i = 1; i < splitArguments.length; i++) {
            if (splitArguments[i].startsWith("from ")) {
                startDatetime = splitArguments[i].substring(5).trim();
            }

            if (splitArguments[i].startsWith("to ")) {
                endDatetime = splitArguments[i].substring(3).trim();
            }
        }

        if (description.equals("")) {
            throw new DukeException("No description specified. Please specify a description.");
        }
        
        if (startDatetime == null || startDatetime.equals("")) {
            throw new DukeException("No start date/time specified. Please specify a start date/time.");
        }

        if (endDatetime == null|| endDatetime.equals("")) {
            throw new DukeException("No end date/time specified. Please specify an end date/time.");
        }

        Task newEvent = new Event(description, startDatetime, endDatetime);
        tasks.add(newEvent);
        Duke.respondWithAddedTask(newEvent);
    }

    private static void loadTasks() throws DukeException{
        try {
            File storedTasks = new File("./data/duke.txt");
            if (storedTasks.exists()) {
                 Scanner scanner = new Scanner(storedTasks);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    // TODO: Parse task data and add to tasks
                }
                scanner.close();
            } else {
                storedTasks.getParentFile().mkdirs();
                storedTasks.createNewFile();
            }
        } catch (Exception e) { 
            throw new DukeException(String.format("Unable to load tasks from file as %s", e.getMessage()));
        }
    }

    public static void main(String[] args) {
        try {
            Duke.loadTasks();
        } catch (DukeException e) {
            Duke.respond(e);
        }

        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        while (programRunning) {
            String input = scanner.nextLine();
            System.out.println();

            if (input.trim().equals("")) {
                continue;
            }

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String arguments = splitInput.length > 1 ? splitInput[1] : "";

            try {
                switch(command) {
                case "bye":
                    programRunning = false;
                    break;
                case "list":
                    Duke.listTasks(tasks);
                    break;
                case "mark":
                    Duke.markTaskAsDone(arguments);
                    break;
                case "unmark":
                    Duke.markTaskAsUndone(arguments);
                    break;
                case "todo":
                    Duke.addTodo(arguments);
                    break;
                case "deadline":
                    Duke.addDeadline(arguments);
                    break;
                case "event":
                    Duke.addEvent(arguments);
                    break;
                case "delete": 
                    Duke.deleteTask(arguments);
                    break;
                default:
                    throw new DukeException(String.format("Command \"%s\" does not exist!"
                                                          + "Please type another command.", command));
                }
            } catch (DukeException e) {
                Duke.respond(e);
                continue;
            }
        }

        scanner.close();
        Duke.exit();
    }
}
