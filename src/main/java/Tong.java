import java.util.Scanner;
import java.util.Arrays;

public class Tong {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String currentLine;
        ToDoList list = new ToDoList();

        printHorizontalLine(35);
        System.out.println("Hello! I'm TONG.");
        System.out.println("Say something. Say anything.");
        System.out.println("Sometimes you say 'bye'.");
        printHorizontalLine(35);

        currentLine = input.nextLine();

//        while (!currentLine.equalsIgnoreCase("bye")) {
//            if (currentLine.equalsIgnoreCase("list")) {
//                System.out.println(list);
//            } else if (currentLine.contains("unmark")) {
//                String[] split = currentLine.split(" ");
//                int order = Integer.parseInt(split[1]);
//                list.unmarkTask(order);
//            } else if (currentLine.contains("mark")) {
//                String[] split = currentLine.split(" ");
//                int order = Integer.parseInt(split[1]);
//                list.markTask(order);
//            } else {
//                Task task = new Task(currentLine);
//                list.addTask(task);
//            }
//
//            currentLine = input.nextLine();
//        }

        while (!currentLine.equalsIgnoreCase("bye")) {
            String[] split = currentLine.split(" ");
            String command = split[0];
            int order;

            switch (command) {
                case "list":
                    System.out.println(list);
                    break;
                case "mark":
                    order = Integer.parseInt(split[1]);
                    list.markTask(order);
                    break;
                case "unmark":
                    order = Integer.parseInt(split[1]);
                    list.unmarkTask(order);
                    break;
                case "todo":
                    try {
                        ToDo todo = new ToDo(trimCommand(currentLine));
                        list.addTask(todo);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] dSplit = trimCommand(currentLine).split(" /by ");
                        String deadlineName = dSplit[0];
                        String by = dSplit[1];
                        Deadline deadline = new Deadline(deadlineName, by);
                        list.addTask(deadline);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;
                case "event":
                    try {
                        String[] eSplit = trimCommand(currentLine).split(" /from ");
                        String eventName = eSplit[0];
                        String[] fromAndTo = eSplit[1].split(" /to ");
                        String from = fromAndTo[0];
                        String to = fromAndTo[1];
                        Event event = new Event(eventName, from, to);
                        list.addTask(event);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    break;
                case "delete":
                    order = Integer.parseInt(split[1]);
                    list.deleteTask(order);
                default:
                    System.out.println(currentLine + " " + currentLine + " " + currentLine);
                    System.out.println("Sorry, I don't understand :)");
            }

            currentLine = input.nextLine();
        }

        System.out.println("Bye.");
        printHorizontalLine(35);
        input.close();
    }

    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static String trimCommand(String description) throws EmptyDescriptionException {
        String[] split = description.split(" ");

        if (split.length == 1) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }

        return String.join(" ", Arrays.copyOfRange(split, 1, split.length));
    }
}
