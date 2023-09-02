import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import enums.Command;

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

            Command command = Command.commandToValueMap(args.get(0));
            switch (Objects.requireNonNull(command)) {
                case BYE:
                    talk = false;
                    break;
                case LIST:
                    taskList.listAllTasks();
                    break;
                case MARK:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskDone(args.get(1));
                    break;
                case UNMARK:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskUndone(args.get(1));
                    break;
                case DELETE:
                    if (!taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.deleteTask(args.get(1));
                    break;
                case TODO:
                    taskList.addTask(new TodoTask(args.get(1)));
                    break;
                case DEADLINE:
                    taskList.addTask(new DeadlineTask(args.get(1), args.get(2)));
                    break;
                case EVENT:
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