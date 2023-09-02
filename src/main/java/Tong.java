import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class Tong {
    public static void main(String[] args) {
        try {
            File directory = new File("./data");
            if (! directory.exists()){
                directory.mkdir();
            }

            String fileName = "./data/tong.txt";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner input = new Scanner(System.in);
            String currentLine;
            ToDoList list = new ToDoList();

            printHorizontalLine(35);
            System.out.println("Hello! I'm TONG.");
            System.out.println("Say something. Say anything.");
            System.out.println("Sometimes you say 'bye'.");
            printHorizontalLine(35);

            currentLine = input.nextLine();

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
                        writeToFile(fileName, list.toString());
                        break;
                    case "unmark":
                        order = Integer.parseInt(split[1]);
                        list.unmarkTask(order);
                        writeToFile(fileName, list.toString());
                        break;
                    case "todo":
                        ToDo todo = new ToDo(trimCommand(currentLine));
                        list.addTask(todo);
                        writeToFile(fileName, list.toString());
                        break;
                    case "deadline":
                        String[] dSplit = trimCommand(currentLine).split(" /by ");
                        String deadlineName = dSplit[0];
                        String by = dSplit[1];
                        String[] bySplit = by.split(" ");
                        String date = bySplit[0];
                        String time = bySplit[1];
                        Deadline deadline = new Deadline(deadlineName, date, time);
                        list.addTask(deadline);
                        writeToFile(fileName, list.toString());
                        break;
                    case "event":
                        String[] eSplit = trimCommand(currentLine).split(" /from ");
                        String eventName = eSplit[0];
                        String[] fromAndTo = eSplit[1].split(" /to ");
                        String from = fromAndTo[0];
                        String[] fromSplit = from.split(" ");
                        String fromDate = fromSplit[0];
                        String fromTime = fromSplit[1];
                        String to = fromAndTo[1];
                        String[] toSplit = to.split(" ");
                        String toDate = toSplit[0];
                        String toTime = toSplit[1];
                        Event event = new Event(eventName, fromDate, fromTime, toDate, toTime);
                        list.addTask(event);
                        writeToFile(fileName, list.toString());
                        break;
                    case "delete":
                        order = Integer.parseInt(split[1]);
                        list.deleteTask(order);
                        writeToFile(fileName, list.toString());
                    default:
                        System.out.println(currentLine + " " + currentLine + " " + currentLine);
                        System.out.println("Sorry, I don't understand :)");
                }

                currentLine = input.nextLine();
            }

            System.out.println("Bye.");
            printHorizontalLine(35);
            input.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
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

    private static void writeToFile(String filePath, String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }
}
