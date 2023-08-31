import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Phi {
    // scanner object for user input
    static Scanner sc = new Scanner(System.in);
    // arrayList to store input text given
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        readFromFile();
        greeting();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.startsWith("list")) {
                    taskList.printList(input);
                } else if (input.startsWith("mark")) {
                    taskList.doTask(input);
                } else if (input.startsWith("unmark")) {
                    taskList.undoTask(input);
                } else if (input.startsWith("todo")) {
                    taskList.addTask(ToDo.newToDo(input));
                } else if (input.startsWith("deadline")) {
                    taskList.addTask(Deadline.newDeadline(input));
                } else if (input.startsWith("event")) {
                    taskList.addTask(Event.newEvent(input));
                } else if (input.startsWith("delete")) {
                    taskList.deleteTask(input);
                } else {
                    System.out.println("SIKE I can't process that! Try again or say \"bye\" to exit");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            writeToFile();
            System.out.println();
            input = sc.nextLine();
        }

        sc.close();
        writeToFile();
        goodbye();
    }

    public static void greeting() {
        String logo = " ___ _  _ ___\n" +
                      "| _ \\ || |_ _|\n" +
                      "|  _/ __ || | \n" +
                      "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction)\nWhat can I do for you?\n";

        System.out.println(logo + greetingMsg);
    }

    public static void readFromFile() {
        File txtFile = new File("./data/tasklist.txt");
        try {
            System.out.println("Attempting to read from text file...");
            Scanner txtScanner = new Scanner(txtFile);
            while (txtScanner.hasNextLine()) {
                taskList.addFromSc(txtScanner.nextLine());
            }
            txtScanner.close();
            System.out.println("Text file has been scanned!\n" + taskList.taskList.size() + " items in the list");
        } catch (FileNotFoundException e) {
            System.out.println("I can't find it :( Creating new .txt file");
            try {
                txtFile.createNewFile();
                System.out.println("New .txt file created :)");
            } catch (IOException i) {
                System.out.println(i.getMessage());
            }
        }
    }

    public static void writeToFile() {
        try {
            File txtFile = new File("./data/tasklist.txt");
            FileWriter output = new FileWriter(txtFile);
            output.write(taskList.outputList());
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }

}