import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static int CHAT_WIDTH = 80;

    public static void main(String[] args) {
        hello_world();
        converse();
        goodbye_world();
    }

    public static void hello_world() {
        System.out.println("Hello! I'm Jing Sheng");
        System.out.println("What can I do for you?");
        print_divider_line();
    }

    public static void converse() {
        final String END_COMMAND = "bye";
        final String LIST_COMMAND = "list";
        final String MARK_COMMAND = "mark";
        final String UNMARK_COMMAND = "unmark";
        final String TODO_NEW_TASK_COMMAND = "todo";
        final String DEADLINE_NEW_TASK_COMMAND = "deadline";
        final String EVENT_NEW_TASK_COMMAND = "event";
        final String DELETE_TASK_COMMAND = "delete";

        TaskList taskList = new TaskList(100);

        Scanner scanner = new Scanner(System.in);

        String message;
        boolean talk = true;
        while (talk) {
            System.out.print("You:");
            message = scanner.nextLine();
            ArrayList<String> args = getArgs(message);
            print_divider_line();
            System.out.println("Bot:");
            switch (args.get(0)) {
                case END_COMMAND:
                    talk = false;
                    break;
                case LIST_COMMAND:
                    taskList.listAllTasks();
                    break;
                case MARK_COMMAND:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskDone(args.get(1));
                    break;
                case UNMARK_COMMAND:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskUndone(args.get(1));
                    break;
                case DELETE_TASK_COMMAND:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.deleteTask(args.get(1));
                    break;
                case TODO_NEW_TASK_COMMAND:
                    taskList.addTask(new TodoTask(args.get(1)));
                    break;
                case DEADLINE_NEW_TASK_COMMAND:
                    taskList.addTask(new DeadlineTask(args.get(1), args.get(2)));
                    break;
                case EVENT_NEW_TASK_COMMAND:
                    taskList.addTask(new EventTask(args.get(1), args.get(2), args.get(3)));
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            print_divider_line();
        }
    }

    public static ArrayList<String> getArgs(String text) {
        ArrayList<String> result = new ArrayList<>();
        String[] words = text.split("\\s+");
        String mainCommand = words[0];
        StringBuilder subCommand = new StringBuilder();

        result.add(mainCommand);
        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.startsWith("/")) {
                result.add(subCommand.toString().trim());
                subCommand = new StringBuilder();
            } else {
                subCommand.append(" ").append(currentWord);
            }
        }

        result.add(subCommand.toString().trim());
        return result;
    }


    public static void goodbye_world() {
        System.out.println("Bye. Hope to see you again soon!");
        print_divider_line();
    }

    public static void print_divider_line() {
        System.out.println("═".repeat(CHAT_WIDTH));
    }
}