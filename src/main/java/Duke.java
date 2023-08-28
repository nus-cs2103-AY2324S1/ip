import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
public class Duke {
    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }


    public static void introduction() {
        String name = "BOB";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void conclusion() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();
    }



    public static void toDo() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            File theDir = new File("./src/main/data");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            File myObj = new File("./src/main/data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String type = data.substring(0,1);
                switch (type) {
                    case "T":
                        Todo.readData(list, data);
                        break;
                    case "D":
                        Deadline.readData(list, data);
                        break;
                    case "E":
                        Event.readData(list, data);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            File myObj = new File("./src/main/data/duke.txt");
        }

        Scanner sc = new Scanner(System.in);
        Pattern listRegex = Pattern.compile("^" + Commands.LIST, Pattern.CASE_INSENSITIVE);
        Pattern markRegex = Pattern.compile("^" + Commands.MARK, Pattern.CASE_INSENSITIVE);
        Pattern unmarkRegex = Pattern.compile("^" + Commands.UNMARK, Pattern.CASE_INSENSITIVE);
        Pattern deadlineRegex = Pattern.compile("^" + Commands.DEADLINE, Pattern.CASE_INSENSITIVE);
        Pattern todoRegex = Pattern.compile("^" + Commands.TODO, Pattern.CASE_INSENSITIVE);
        Pattern eventRegex = Pattern.compile("^" + Commands.EVENT, Pattern.CASE_INSENSITIVE);
        Pattern deleteRegex = Pattern.compile("^" + Commands.DELETE, Pattern.CASE_INSENSITIVE);
        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                return;
            } else if (listRegex.matcher(command).find()) {
                Task.printList(list);
            } else if (markRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(5)) - 1;
                Task currTask = list.get(curr);
                currTask.markDone();
            } else if (unmarkRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(7)) - 1;
                Task currTask = list.get(curr);
                currTask.markUndone();
            } else if (deadlineRegex.matcher(command).find()) {
                Deadline.addDeadline(list, command);
            } else if (todoRegex.matcher(command).find()) {
                Todo.addTodo(list, command);
            } else if (eventRegex.matcher(command).find()) {
                Event.addEvent(list, command);
            } else if (deleteRegex.matcher(command).find()) {
                Task.deleteTask(list, command);
            } else {
                try {
                    throw new DukeException("Invalid Response");
                } catch (DukeException e) {
                    printHorizontalLine();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printHorizontalLine();
                }
            }
        }
    }

    public static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!\n");
            myWriter.write("Files in Java might be tricky, but it is fun enough!\n");
            myWriter.write("Files in Java might be tricky, but it is fun enough!\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        introduction();

        toDo();


        conclusion();



    }
}
