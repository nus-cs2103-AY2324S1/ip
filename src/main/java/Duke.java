import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import Tasks.*;
import enums.Command;
import enums.DukeDateFormats;
import storage.TaskFileHandler;

public class Duke {

    public static int CHAT_WIDTH = 80;

    public static void main(String[] args) {
        hello_world();
        converse();
    }

    public static void hello_world() {
        System.out.println("Hello! I'm Jing Sheng");
        System.out.println("What can I do for you?");
        print_divider_line();
    }

    public static void converse() {
        TaskList taskList = new TaskList(TaskFileHandler.readFromFile());
        Scanner scanner = new Scanner(System.in);
        String message;
        LocalDate startDate;
        LocalDate endDate;

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
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    taskList.listAllTasks();
                    break;
                case MARK:
                    if (taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskDone(args.get(1));
                    break;
                case UNMARK:
                    if (taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.markTaskUndone(args.get(1));
                    break;
                case DELETE:
                    if (taskList.validateTaskIndex(args.get(1))) {
                        break;
                    }
                    taskList.deleteTask(args.get(1));
                    break;
                case TODO:
                    taskList.addTask(new TodoTask(args.get(1)));
                    break;
                case DEADLINE:
                    endDate = LocalDate.parse(args.get(2), DukeDateFormats.DATE_ONLY.getFormatter());
                    taskList.addTask(new DeadlineTask(args.get(1), endDate));
                    break;
                case EVENT:
                    startDate = LocalDate.parse(args.get(2), DukeDateFormats.DATE_ONLY.getFormatter());
                    endDate = LocalDate.parse(args.get(3), DukeDateFormats.DATE_ONLY.getFormatter());
                    taskList.addTask(new EventTask(args.get(1), startDate, endDate));
                    break;
                case NULLCOMMAND:
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            print_divider_line();
        }

        TaskFileHandler.saveToFile(taskList);
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

    public static void print_divider_line() {
        System.out.println("═".repeat(CHAT_WIDTH));
    }
}