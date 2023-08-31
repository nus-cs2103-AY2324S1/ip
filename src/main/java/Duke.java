import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.File;
public class Duke {
    public static Integer count = 0;
    static List<Task> inputs;

    static {
        try {
            inputs = readFromFile();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be found: " + e.getMessage());
        }
    }

    public static void saveToFile() {
        try {
            File f = new File("./data/duke.txt");

            FileWriter fw = new FileWriter(f);
            for (Task t : inputs) {
                fw.write(t.store() + "\n");
            }
            System.out.println(inputs);
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving to the file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File("./data/duke.txt");

        if(!f.exists()){
            System.out.println("File does not exist");
            return taskList;
        } else {
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()){
                String[] type = scanner.nextLine().substring(4).split(" ");
                String[] description = scanner.nextLine().substring(4).split("\\|");

                if(type[0].equals("todo")){
                    Todo todo = new Todo(description[1]);
                    taskList.add(todo);
                } else if (type[0].equals("deadline")){
                    Deadline deadline = new Deadline(description[1], description[2]);
                    taskList.add(deadline);
                } else if (type[0].equals("event")){
                    String date[] = scanner.nextLine().substring(4).split("-");
                    Event event = new Event(description[1], description[2], date[1]);
                    taskList.add(event);
                } else {
                    System.out.println ("Invalid Input");
                }
            }
        }
        return taskList;
    }


    public static void main(String[] args) {
        System.out.println("Hello! I'm ChatBot.\n" +
                "What can I do for you?\n" );

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println((i + 1) + ". " + inputs.get(i));
                    }
                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + inputs.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + inputs.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.split(" ", 2).length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task curr = new Todo(input.split(" ")[1]);
                    count++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task: \n" +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list \n");
                } else if (input.startsWith("event")) {
                    String description = input.split("/")[0].split(" ", 2)[1].stripLeading();
                    String date = input.split("/")[1].substring(5);
                    String time = input.split("/to")[1];
                    Task curr = new Event(description, date, time);
                    count++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task:\n" +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list\n");
                } else if (input.startsWith("deadline")) {
                    String description = input.split("/by")[0].split(" ", 2)[1].stripLeading();
                    String date = input.split("/by")[1];
                    Task curr = new Deadline(description, date);
                    count++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task:\n" +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list\n");
                } else if (input.startsWith("delete")){
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    inputs.remove(index);
                    count --;

                    System.out.println("Noted. I've removed this task:\n" +
                            "Now you have " + count + " tasks in the list.");
                }else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }
        saveToFile();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
