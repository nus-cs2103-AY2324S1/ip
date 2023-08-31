import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Ballsorter {

    public static void main(String[] args) {

        String line = "____________________________________________________________";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(line);

        //check for storage
        File tmpDir = new File("data/Ballsorter.txt");
        try {
            if (tmpDir.createNewFile()) {
                //do nothing
            } else {
                Scanner sc = new Scanner(tmpDir);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    int stat = Integer.parseInt(input.substring(2, 3));
                    StringBuilder desc = new StringBuilder();
                    StringBuilder start = new StringBuilder();
                    Task curr;
                    if (input.charAt(0) == 'T') {
                        curr = new Todo(input.substring(4));
                    } else if (input.charAt(0) == 'D') {
                        int i = 4;
                        while (input.charAt(i) != '|') {
                            desc.append(input.charAt(i));
                            i++;
                        }
                        i++;
                        curr = new Deadline(desc.toString(), input.substring(i));
                    } else {
                        int i = 4;
                        while (input.charAt(i) != '|') {
                            desc.append(input.charAt(i));
                            i++;
                        }
                        i++;
                        while (input.charAt(i) != '|') {
                            start.append(input.charAt(i));
                            i++;
                        }
                        i++;
                        curr = new Event(desc.toString(), start.toString(), input.substring(i));
                    }
                    if (stat == 1) {
                        curr.markDone();
                    }
                    tasks.add(curr);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String input = sc.nextLine();

            if (input.equals("bye")) {

                break;

            } else if (input.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                if (tasks.size() == 0) {
                    System.out.println("You do not have any tasks yet ☹");
                }

                for (int i = 0; i < tasks.size(); i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + tasks.get(i).toString());
                }
                System.out.println(line);

            } else if (input.startsWith("mark")) {

                int target = Integer.parseInt(input.substring(5)) - 1;

                if (target >= tasks.size()) {
                    System.out.println("☹ OOPS!!! This task does not exist");
                    System.out.println(line);
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(target).markDone());
                    System.out.println(line);
                }

            } else if (input.startsWith("unmark")) {

                int target = Integer.parseInt(input.substring(7)) - 1;

                if (target >= tasks.size()) {
                    System.out.println("☹ OOPS!!! This task does not exist");
                    System.out.println(line);
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(target).markNotDone());
                    System.out.println(line);
                }

            } else if (input.startsWith("delete")) {

                int target = Integer.parseInt(input.substring(7)) - 1;
                if (target >= tasks.size()) {
                    System.out.println("☹ OOPS!!! This task does not exist");
                    System.out.println(line);
                } else {
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(target).toString());
                    tasks.remove(target);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                }

            } else {

                Task curr;
                StringBuilder description = new StringBuilder();
                StringBuilder start = new StringBuilder();

                if (input.startsWith("todo")) {

                    String des = input.substring(4).trim();
                    if (des.equals("")) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Todo(des);
                    }

                } else if (input.startsWith("deadline")) {

                    int i = 9;
                    while (i < input.length() && input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    if (description.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (i >= input.length() || input.substring(i).equals("")) {
                        System.out.println("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Deadline(description.toString(), input.substring(i));
                    }


                } else if (input.startsWith("event")) {

                    int i = 6;
                    while (i < input.length() && input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 6;
                    while (i < input.length() && input.charAt(i) != '/') {
                        start.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    if (description.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (start.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The start time of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (i >= input.length() || input.substring(i).equals("")) {
                        System.out.println("☹ OOPS!!! The end time of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Event(description.toString(), start.toString(), input.substring(i));
                    }

                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                    curr = null;
                }

                if (curr != null) {
                    tasks.add(curr);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                }
            }
        }

        //write list to storage
        try {
            StringBuilder storage = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                storage.append(String.format(tasks.get(i).toStorageString() + "%n"));
            }
            Files.write(tmpDir.toPath(), storage.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
