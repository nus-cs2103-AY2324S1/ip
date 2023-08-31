import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    public static void display_list(ArrayList<Task> tasklist) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");

        System.out .println(" Here are the tasks in your list:");
        int x = 1;
        for (Task task : tasklist) {
            System.out.println(x + "." + task.getStatusIcon());
            x++;
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
    public static void mark(ArrayList<Task> tasklist, int index) {
        tasklist.get(index).markAsDone();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" Nice! I've marked this task as done:\n" + "   " + tasklist.get(index).getStatusIcon());
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void unmark(ArrayList<Task> tasklist, int index) {
        tasklist.get(index).unmark();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" OK, I've marked this task as not done yet:\n" + "   " + tasklist.get(index).getStatusIcon());
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void delete(ArrayList<Task> tasklist, int index) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" Noted. I've removed this task:\n" + "   " + tasklist.get(index).getStatusIcon());
        tasklist.remove(index);
        System.out.println(" Now you have " + tasklist.size() + " tasks in the list.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void todo(ArrayList<Task> tasklist, String description) {
        tasklist.add(new Todo(description));
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void deadline(ArrayList<Task> tasklist, String description, LocalDateTime by) {
        tasklist.add(new Deadline(description, by));
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void event(ArrayList<Task> tasklist, String description, LocalDateTime from, LocalTime to) {
        tasklist.add(new Event(description, from, to));
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + tasklist.size() +" tasks in the list.");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void saveTasksToFile(ArrayList<Task> tasklist) {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task : tasklist) {
                // Convert task to string format and write to the file
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTasksFromFile(ArrayList<Task> tasklist) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create tasks, then add to the list
                Task task = Task.parseFromString(line);
                tasklist.add(task);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");

        ArrayList<Task> tasklist = new ArrayList<Task>();
        //When the chatbot starts we load the tasks from the file
        loadTasksFromFile(tasklist);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        // Hard-coded file path relative to project root
        String filePath = "./data/duke.txt";

        while (!str.equals("bye")) {
            String[] input = str.split(" ", 2);

            try {
                if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
                    throw new DukeException("OOPS!!! The description of a " + str + " cannot be empty.");
                } else if (str.equals("mark") || str.equals("unmark") || str.equals("delete")) {
                    throw new DukeException("OOPS!!! Please specify which task you want to mark, unmark or delete .");
                }

                else if (input[0].equals("list")){
                    display_list(tasklist);
                    str = sc.nextLine();
                } else if (input[0].equals("mark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    mark(tasklist, index);
                    str = sc.nextLine();
                } else if (input[0].equals("unmark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    unmark(tasklist, index);;
                    str = sc.nextLine();
                } else if (input[0].equals("todo")) {
                    String description = input[1];
                    todo(tasklist, description);
                    str = sc.nextLine();
                } else if (input[0].equals("deadline")) {
                    String[] full_desc = input[1].split(" /by ");
                    String description = full_desc[0];
                    LocalDateTime by = LocalDateTime.parse(full_desc[1]);
                    deadline(tasklist, description, by);
                    str = sc.nextLine();
                } else if (input[0].equals("event")) {
                    String[] full_desc = input[1].split(" /from | /to ");
                    String description = full_desc[0];
                    LocalDateTime from = LocalDateTime.parse(full_desc[1]);
                    LocalTime to = LocalTime.parse(full_desc[2]);
                    event(tasklist, description, from, to);
                    str = sc.nextLine();
                } else if (input[0].equals("delete")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    delete(tasklist, index);
                    str = sc.nextLine();
                }

                else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(e.getMessage());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            }
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");

        //Before you exit, save the changes to the file
        saveTasksToFile(tasklist);
    }
}
