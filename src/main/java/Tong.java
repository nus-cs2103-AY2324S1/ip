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
            String content;
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
                    content = String.join(" ", Arrays.copyOfRange(split, 1, split.length));
                    ToDo todo = new ToDo(content);
                    list.addTask(todo);
                    break;
                case "deadline":
                    content = String.join(" ", Arrays.copyOfRange(split, 1, split.length));
                    String[] dSplit = content.split(" /by ");
                    String deadlineName = dSplit[0];
                    String by = dSplit[1];
                    Deadline deadline = new Deadline(deadlineName, by);
                    list.addTask(deadline);
                    break;
                case "event":
                    content = String.join(" ", Arrays.copyOfRange(split, 1, split.length));
                    String[] eSplit = content.split(" /from ");
                    String eventName = eSplit[0];
                    String[] fromAndTo = eSplit[1].split(" /to ");
                    String from = fromAndTo[0];
                    String to = fromAndTo[1];
                    Event event = new Event(eventName, from, to);
                    list.addTask(event);
                    break;
                case "delete":
                    order = Integer.parseInt(split[1]);
                    list.deleteTask(order);
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
}
