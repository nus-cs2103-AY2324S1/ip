import java.util.Scanner;

public class Duke {

    private static final String NAME_ART =
            "               _                _               _                      \n" +
            "           _  /\\ \\            / /\\          _  /\\ \\               \n" +
            "          /\\_\\\\ \\ \\          / /  \\        /\\_\\\\ \\ \\        \n" +
            "         / / / \\ \\ \\        / / /\\ \\      / / / \\ \\ \\          \n" +
            "        / / /   \\ \\ \\      / / /\\ \\ \\    / / /   \\ \\ \\        \n" +
            "        \\ \\ \\____\\ \\ \\    /_/ /  \\ \\ \\   \\ \\ \\____\\ \\ \\ \n" +
            "         \\ \\________\\ \\   \\ \\ \\   \\ \\ \\   \\ \\________\\ \\ \n" +
            "          \\/________/\\ \\   \\ \\ \\   \\ \\ \\   \\/________/\\ \\  \n" +
            "                    \\ \\ \\   \\ \\ \\___\\ \\ \\            \\ \\ \\ \n" +
            "                     \\ \\_\\   \\ \\/____\\ \\ \\            \\ \\_\\ \n" +
            "                      \\/_/    \\_________\\/             \\/_/";
    private static final String NAME = "404";
    private static final String INDENT = "     ";
    private static final int STORE_SIZE = 100;
    private Task[] tasks = new Task[STORE_SIZE];
    private int task_pointer = 0;

    public static void main(String[] args) {
        Duke robot404 = new Duke();
        robot404.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                         INDENT, NAME, INDENT);
        System.out.println(NAME_ART);
        printLine();
        System.out.println(greeting);
        printLine();
        System.out.println();

        String[] commands = new String[4];
        // Commands: [command, task_description, end_date(deadline)/from_date(event), to_date(event)]
        boolean exit = false;
        while (!exit) {
            String text = sc.nextLine();
            printLine();
            try {
                exit = executeCommand(text, commands);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printLine();
            System.out.println();
        }
        sc.close();
    }

    private boolean executeCommand(String text, String[] commands) throws DukeException {
        processCommand(text, commands);
        switch(commands[0]) {
            case "bye":
                System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
                return true;

            case "list":
                listTask();
                break;

            case "mark":
            case "unmark":
                int task_num = Integer.parseInt(commands[1]);
                if (task_num > task_pointer || task_num < 1) {
                    String message = String.format("%sOOPS!!! There is no task %d to %s", INDENT, task_num, commands[0]);
                    listTask();
                    throw new DukeException(message);
                }
                markTask(task_num - 1, commands[0].equals("mark"));
                break;

            case "todo":
                addTask(new Todo(commands[1]));
                break;

            case "deadline":
                addTask(new Deadline(commands[1], commands[2]));
                break;

            case "event":
                addTask(new Event(commands[1], commands[2], commands[3]));
                break;
        }
        return false;
    }

    private void processCommand(String text, String[] commands) throws DukeException {
        String[] split = text.split(" ");
        if (text.isEmpty() || split.length == 0) {
            String message = String.format("%sOOPS!!! You have not entered anything!%n", INDENT);
            throw new DukeException(message);
        }

        commands[0] = split[0].toLowerCase();
        String unknownMessage = String.format("%sOOPS!!! I'm sorry, but I don't know what that means.", INDENT);
        if (split.length == 1) {
            String message = String.format("%sOOPS!!! The description of a %s cannot be empty.%n%s", INDENT, commands[0], INDENT);
            switch (commands[0]){
                case "bye":
                case "list":
                    return;

                case "todo":
                    throw new TodoException(message);

                case "deadline":
                    throw new DeadlineException(message);

                case "event":
                    throw new EventException(message);

                case "mark":
                case "unmark":
                    throw new MarkException(message);

                default:
                    throw new DukeException(unknownMessage);
            }
        }

        String temp = text.substring(split[0].length() + 1);
        String message = String.format("%sOOPS!!! The command for %s task is invalid.%n%s", INDENT, commands[0], INDENT);
        switch (commands[0]) {
            case "bye":
                if (temp.equals(NAME)) {
                    break;
                }
            case "list":
                String errMessage = String.format("%sOOPS!!! The command for %s is invalid.\n" +
                        "%sPlease enter in the form: %s", INDENT, commands[0], INDENT, commands[0]);
                throw new DukeException(errMessage);

            case "todo":
                commands[1] = temp;
                break;

            case "mark":
            case "unmark":
                try {
                    Integer.parseInt(temp);
                    commands[1] = temp;
                } catch (NumberFormatException e) {
                    throw new MarkException(message);
                }
                break;

            case "deadline":
                String[] deadlineTask = temp.split(" /by ");
                if (deadlineTask.length != 2) {
                    throw new DeadlineException(message);
                }
                commands[1] = deadlineTask[0];
                commands[2] = deadlineTask[1];
                break;

            case "event":
                String[] eventTask = temp.split(" /from ");
                if (eventTask.length != 2) {
                    throw new EventException(message);
                }
                String[] dates = eventTask[1].split(" /to ");
                if (dates.length != 2) {
                    throw new EventException(message);
                }
                commands[1] = eventTask[0];
                commands[2] = dates[0];
                commands[3] = dates[1];
                break;

            default:
                throw new DukeException(unknownMessage);
        }
    }

    private static void printLine() {
        String line = "    ____________________________________________________________\n" +
                      "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }

    private void addTask(Task task) {
        tasks[task_pointer] = task;
        task_pointer++;
        System.out.printf("%sGot it. I've added this task:%n" +
                          "%s  %s%n" +
                          "%sNow you have %d tasks in the list.%n",
                          INDENT, INDENT, task, INDENT, task_pointer);
    }

    private void listTask() {
        if (task_pointer == 0) {
            System.out.printf("%sOOPS!!! There is nothing in the list, yet!%n", INDENT);
            return;
        } else {
            System.out.printf("%sHere are the tasks in your list:%n", INDENT);
        }

        for (int i = 0; i < task_pointer; i++) {
            if (tasks[i] == null) {
                break;
            } else {
                String out = String.format("%s%d.%s", INDENT, i + 1, tasks[i]);
                System.out.println(out);
            }
        }
    }

    private void markTask(int index, boolean mark) {
        String task;
        String message;
        if (mark) {
            message = "Nice! I've marked this task as done:";
            task = tasks[index].markAsDone();
        } else {
            message = "OK, I've marked this task as not done yet:";
            task = tasks[index].markAsNotDone();
        }
        System.out.printf("%s%s%n%s  %s%n", INDENT, message, INDENT, task);
    }
}
