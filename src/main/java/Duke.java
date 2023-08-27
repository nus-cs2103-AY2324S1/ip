import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static String PATH = "./data/duke.txt";
    public static void greet(){
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }
    public static void printList(int size, List<Task> ls){
        for(int i = 0; i < size;i++) {
            int j = i + 1;
            System.out.println(j + "." + ls.get(i));
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void writeToFile_exceptionThrown(String filePath, String textToAdd) {
        try {
            writeToFile(filePath, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void printMarkUndone(String task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    private static void printMarkDone(String str) {
        System.out.println("Nice! I've marked this task as done:\n" + str);
    }

    private static void printRemoveTask(String str, int size) {
        System.out.println("Noted. I've removed this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    private static void printAddTask(String str, int size) {
        System.out.println("Got it. I've added this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        List<Task> ls = new ArrayList<>();



        while (true) {
            if (cmd.equals("bye")) {
                sc.close();
                bye();
                System.exit(0);
            } else if (cmd.equals("list")) {
                int size = ls.size();
                printList(size, ls);
                cmd = sc.nextLine();
            } else if (cmd.contains("unmark")) {
                //int index = sc.nextInt();
                int index = Integer.parseInt(cmd.substring(7, 8));
                Task task = ls.get(index-1);
                ls.remove(index-1);
                task.unmark();
                ls.add(index-1, task);
                printMarkUndone(task.toString());
                writeToFile_exceptionThrown(PATH, task.saveToFileString());
                cmd = sc.nextLine();
            } else if (cmd.contains("mark")) {
                int index = Integer.parseInt(cmd.substring(5, 6));
                Task task = ls.get(index - 1);
                ls.remove(index-1);
                task.markAsDone();
                ls.add(index - 1, task);
                printMarkDone(task.toString());
                writeToFile_exceptionThrown(PATH, task.saveToFileString());
                cmd = sc.nextLine();
            } else if (cmd.contains("delete")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                Task task = ls.get(index - 1);
                ls.remove(index - 1);
                int size = ls.size();
                printRemoveTask(task.toString(), size);
                cmd = sc.nextLine();
            } else if (cmd.contains("deadline")) {
                String[] parts = cmd.split("/by");
                if (parts.length == 2) {
                    String description = parts[0].replace("deadline", "").trim(); // Remove "deadline"
                    String deadline = parts[1].trim();
                    LocalDate d1 = LocalDate.parse(deadline);
                    String formattedDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Task task = new Deadline(description, formattedDate);
                    ls.add(task);
                    int size = ls.size();
                    printAddTask(task.toString(), size);
                    writeToFile_exceptionThrown(PATH, task.saveToFileString());
                } else {
                    DukeException exp = new DukeException("deadline");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else if (cmd.contains("todo")){
                String[] parts = cmd.split(" ", 2);
                if (parts.length == 2) {
                    String desc = parts[1].trim();
                    Task task = new ToDo(desc);
                    ls.add(task);
                    int size = ls.size();
                    printAddTask(task.toString(), size);
                    writeToFile_exceptionThrown(PATH, task.saveToFileString());
                } else {
                    DukeException exp = new DukeException("todo");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else if (cmd.contains("event")) {
                String[] parts = cmd.split("/from");
                if (parts.length == 2) {
                    String desc = parts[0].replace("event", "").trim();
                    String rest = parts[1].trim();
                    String[] restParts = rest.split("/to");
                        String from = restParts[0].trim();
                        String till = restParts[1].trim();
                        Task task = new Event(desc, from, till);
                        ls.add(task);
                        int size = ls.size();
                        printAddTask(task.toString(), size);
                        writeToFile_exceptionThrown(PATH, task.saveToFileString());
                } else {
                    DukeException exp = new DukeException("event");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else {
                DukeException exp = new DukeException("");
                System.out.println(exp.nothing());
                cmd = sc.nextLine();
            }
        }
    }
}
