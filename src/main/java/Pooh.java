import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pooh {
    protected static final String horizontalLine = "      _______________________________________________________________________________";

    public static void welcomeMsg() {
        String logo = "      .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "      | .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "      | |   ______     | || |     ____     | || |     ____     | || |  ____  ____  | |\n" +
                "      | |  |_   __ \\   | || |   .'    `.   | || |   .'    `.   | || | |_   ||   _| | |\n" +
                "      | |    | |__) |  | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |__| |   | |\n" +
                "      | |    |  ___/   | || |  | |    | |  | || |  | |    | |  | || |   |  __  |   | |\n" +
                "      | |   _| |_      | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  | |_  | |\n" +
                "      | |  |_____|     | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                "      | |              | || |              | || |              | || |              | |\n" +
                "      | '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                "       '----------------'  '----------------'  '----------------'  '----------------'";

        String greetings = "      Hi there! Good to see you! I'm Pooh!\n      What can I do for you?";
        System.out.println(logo);
        System.out.println(horizontalLine);
        System.out.println(greetings);
        System.out.println(horizontalLine);
    }

    public static void exitMsg() {
        String byeMessage = "      How lucky I am to have something that makes saying goodbye so hard. Bye!";
        System.out.println(horizontalLine);
        System.out.println(byeMessage);
        System.out.println(horizontalLine);
    }

    public static void generalRespond(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printTasksMsg(List<Task> taskList) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format("      %d. ", i + 1) + taskList.get(i) + "\n";
            todoListString.append(task);
        }
        System.out.println(horizontalLine);
        System.out.println("      Here are the tasks in your list:");
        System.out.println(todoListString.toString().stripTrailing());
        System.out.println(horizontalLine);
    }

    public static void taskDoneMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println("      Nice! I've marked this task as done:\n      " + task);
        System.out.println(horizontalLine);
    }

    public static void taskUndoneMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println("      OK, I've marked this task as not done yet:\n      " + task);
        System.out.println(horizontalLine);
    }

    public static void addTask(List<Task> taskList, String userAction, String cmd) throws EmptyTaskDescriptorsException {
        if (cmd.split(" ", 2).length == 1) {
            throw new EmptyTaskDescriptorsException();
        }

        String userArgs = cmd.split(" ", 2)[1];
        Task task;
        if (userAction.equalsIgnoreCase("todo")) {
            task = new Todo(userArgs);
        } else if (userAction.equalsIgnoreCase("deadline")) {
            String[] specificArgs = userArgs.split(" /by ", 2);
            String description = specificArgs[0];
            String deadlineTime = specificArgs[1];
            task = new Deadline(description, deadlineTime);
        } else {
            String[] specificArgs = userArgs.split(" /from ", 2);
            String description = specificArgs[0];
            String[] eventTimes = specificArgs[1].split(" /to ");
            String eventStartTime = eventTimes[0];
            String eventEndTime = eventTimes[1];
            task = new Event(description, eventStartTime, eventEndTime);
        }
        taskList.add(task);
        String addTaskMessage = String.format("      Got it. I've added this task:\n          %s\n      Now you have %d tasks in the list", task.toString(), taskList.size());
        generalRespond(addTaskMessage);
    }

    public static void main(String[] args) {
        welcomeMsg();
        List<Task> todoList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            try {
                String userCmd = userInput.nextLine();
                String userAction = userCmd.split(" ")[0];
                if (userAction.equalsIgnoreCase("bye")) {
                    exitMsg();
                    userInput.close();
                    System.exit(0);
                } else if (userAction.equalsIgnoreCase("list")) {
                    printTasksMsg(todoList);
                } else if (userAction.equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(userCmd.split(" ")[1]) - 1;
                    if (index < 0 || index >= todoList.size()) {
                        throw new InvalidTaskException();
                    }
                    Task task = todoList.get(index);
                    task.markAsDone();
                    taskDoneMsg(task);
                } else if (userAction.equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(userCmd.split(" ")[1]) - 1;
                    if (index < 0 || index >= todoList.size()) {
                        throw new InvalidTaskException();
                    }
                    Task task = todoList.get(index);
                    task.markAsUndone();
                    taskUndoneMsg(task);
                } else if (userAction.equalsIgnoreCase("todo") || userAction.equalsIgnoreCase("event") || userAction.equalsIgnoreCase("deadline")) {
                    try {
                        addTask(todoList, userAction, userCmd);
                    } catch (EmptyTaskDescriptorsException ex) {
                        generalRespond(ex.toString());
                    }
                } else {
                    throw new UnrecognizedCommandException();
                }
            } catch (UnrecognizedCommandException ex) {
                generalRespond(ex.toString());
            } catch (InvalidTaskException ex) {
                generalRespond(ex.toString());
            }
        }
    }
}