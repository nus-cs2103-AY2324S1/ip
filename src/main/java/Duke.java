import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

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
    private List<Task> tasks = new ArrayList<>();

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

        boolean exit = false;
        while (!exit) {
            String text = sc.nextLine();
            printLine();
            try {
                exit = executeCommand(text);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printLine();
            System.out.println();
        }
        sc.close();
    }

    private boolean executeCommand(String text) throws DukeException {
        String[] split = text.split(" ");
        if (text.isEmpty() || split.length == 0) {
            String message = String.format("%sOOPS!!! You have not entered anything!%n", INDENT);
            throw new DukeException(message);
        }

        Command com;
        try {
            com = Command.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            String str = String.format("%sOOPS!!! I'm sorry, but I don't know what that means.", INDENT);
            throw new DukeException(str);
        }

        if (split.length == 1) {
            String message = String.format("%sOOPS!!! The description of a %s cannot be empty.%n%s",
                    INDENT, com.getCommand(), INDENT);
            switch (com){
                case BYE:
                    System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
                    return true;

                case LIST:
                    listTask();
                    return false;

                case TODO:
                    throw new TodoException(message);

                case DEADLINE:
                    throw new DeadlineException(message);

                case EVENT:
                    throw new EventException(message);

                case MARK:
                case UNMARK:
                case DELETE:
                    throw new ManipulateException(message, com.getCommand());
            }
        }

        String rest = text.substring(split[0].length() + 1);
        String message = String.format("%sOOPS!!! The command for %s task is invalid.%n%s",
                INDENT, com.getCommand(), INDENT);
        switch (com) {
            case BYE:
                if (rest.equals(NAME)) {
                    System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
                    return true;
                }
            case LIST:
                String errMessage = String.format("%sOOPS!!! The command for %s is invalid.\n" +
                        "%sEnter in the form: \"%s\"",
                        INDENT, com.getCommand(), INDENT, com.getCommand());
                throw new DukeException(errMessage);

            case MARK:
            case UNMARK:
            case DELETE:
                int task_num;
                try {
                    if (!rest.equals("all")) {
                        task_num = Integer.parseInt(rest);
                    } else {
                        if (tasks.isEmpty()) {
                            String str = String.format("%sOOPS!!! There are no tasks to %s.",
                                    INDENT, com.getCommand());
                            throw new DukeException(str);
                        }
                        if (com.equals(Command.DELETE)) {
                            tasks.clear();
                        } else {
                            tasks.forEach(t -> t.mark(com.equals(Command.MARK)));
                        }
                        System.out.printf("%sNoted. I will %s all tasks.%n", INDENT, com.getCommand());
                        break;
                    }
                } catch (NumberFormatException e) {
                    throw new ManipulateException(message, com.getCommand());
                }

                if (task_num > tasks.size() || task_num < 1) {
                    String str = String.format("%sOOPS!!! There is no task %d to %s",
                            INDENT, task_num, com.getCommand());
                    listTask();
                    throw new DukeException(str);
                }

                if (com.equals(Command.DELETE)) {
                    deleteTask(task_num - 1);
                } else {
                    markTask(task_num - 1, com.equals(Command.MARK));
                }
                break;

            case TODO:
                addTask(new Todo(rest));
                break;

            case DEADLINE:
                String[] deadlineTask = rest.split(" /by ");
                if (deadlineTask.length != 2) {
                    throw new DeadlineException(message);
                }
                addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
                break;

            case EVENT:
                String[] eventTask = rest.split(" /from ");
                if (eventTask.length != 2) {
                    throw new EventException(message);
                }
                String[] dates = eventTask[1].split(" /to ");
                if (dates.length != 2) {
                    throw new EventException(message);
                }
                addTask(new Event(eventTask[0], dates[0], dates[1]));
                break;
        }
        return false;
    }

    private static void printLine() {
        String line = "    ____________________________________________________________\n" +
                      "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.printf("%sGot it. I've added this task:%n" +
                          "%s  %s%n" +
                          "%sNow you have %d tasks in the list.%n",
                          INDENT, INDENT, task, INDENT, tasks.size());
    }

    private void listTask() {
        if (tasks.isEmpty()) {
            System.out.printf("%sOOPS!!! There is nothing in the list, yet!%n", INDENT);
            return;
        } else {
            System.out.printf("%sHere are the tasks in your list:%n", INDENT);
        }
        IntStream.range(0, tasks.size())
                 .forEach(i ->
                         System.out.printf("%s%d.%s%n", INDENT, i + 1, tasks.get(i)));
    }

    private void markTask(int index, boolean mark) {
        String task = tasks.get(index).mark(mark);
        String message = mark ? "Nice! I've marked this task as done:"
                              : "OK, I've marked this task as not done yet:";
        System.out.printf("%s%s%n%s  %s%n", INDENT, message, INDENT, task);
    }

    private void deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        System.out.printf("%sNoted. I've removed this task:%n" +
                          "%s  %s%n" +
                          "%sNow you have %d tasks in the list.%n",
                INDENT, INDENT, removedTask, INDENT, tasks.size());
    }
}
