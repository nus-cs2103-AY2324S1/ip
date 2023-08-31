import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        String[] actions = new String[100];
        boolean[] isDone = new boolean[100];
        String[] type = new String[100];
        String logo = "Hello! I'm bob\nWhat can I do for you?";
        System.out.println(logo);
        File file = new File("./data");
        //Save data.txt to file
        counter = load("./data/duke.txt", actions, type, isDone);

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.startsWith("delete")) {
                int num3 = Integer.parseInt(input.substring(7).trim());
                String action2 = helper(actions[num3 - 1], type[num3 - 1], isDone[num3 - 1]);
                for (int j = num3 - 1; j < counter - 1; j++) {
                    actions[j] = actions[j + 1];
                    type[j] = type[j + 1];
                    isDone[j] = isDone[j + 1];
                }
                counter = counter - 1;
                System.out.println("Noted. I've removed this task:\n" + action2);
                System.out.println("Now you have " + counter + " tasks in the list.");
            } else if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                String DMYString = "";
//                for (int i = 0; i < counter; i++) {
//                    System.out.println((i + 1) + "." + helper(actions[i], type[i], isDone[i]));
                for (int i = 0; i < counter; i++) {
                    if (type[i].equals("D") || type[i].equals("E")) {
                        LocalDateTime DMY = LocalDateTime.parse(actions[i], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        DMYString = DMY.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                    }
                    System.out.println((i + 1) + "." + helper(actions[i], type[i], isDone[i]) + " " + DMYString);
                }
            } else if (input.startsWith("todo")) {
                if (input.length() <= 4) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String action = input.substring(5).trim();
                    isDone[counter] = false;
                    actions[counter] = action;
                    type[counter] = "T";
                    counter++;
                    System.out.println("Got it. I've added this task:\n" + helper(action, "T", false));
                    System.out.println("Now you have " + counter + " tasks in the list.");
                }
            } else if (input.startsWith("deadline")) {
            String action = input.substring(9, input.indexOf("/by")).trim();
            String by = input.substring(input.indexOf("/by") + 4).trim(); // Adjusted index
            LocalDateTime timeDeadline = dateTask(by);
            actions[counter] = action;
            type[counter] = "D";
            isDone[counter] = false;
            counter++;
            System.out.println("Got it. I've added this task: \n" + helper(action, "D", false) + " (by: " + timeDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
            System.out.println("Now you have " + counter + " tasks in the list.");
        } else if (input.startsWith("event")) {
                String action = input.substring(6, input.indexOf("/from")).trim();
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                String to = input.substring(input.indexOf("/to") + 4).trim();
                LocalDateTime startTimeEvent = dateTask(from);
                LocalDateTime endTimeEvent = dateTask(to);
                actions[counter] = action;
                isDone[counter] = false;
                type[counter] = "E";
                counter++;
                System.out.println("Got it. I've added this task:\n" + helper(action, "E", false) + " (from: " + from + " to: " + to + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
            } else if (input.startsWith("mark")) {
                int num = Integer.parseInt(input.substring(5).trim());
                if (num - 1 < counter) {
                    isDone[num - 1] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + helper(actions[num - 1], type[num - 1], isDone[num - 1]));
                }
            } else if (input.startsWith("unmark")) {
                int num2 = Integer.parseInt(input.substring(7).trim());
                if (num2 - 1 < counter) {
                    isDone[num2 - 1] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + helper(actions[num2 - 1], type[num2 - 1], isDone[num2 - 1]));
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            }
            save("./data/duke.txt", actions, type, isDone, counter);
        }
    }


    private static String helper(String task, String taskType, boolean isDone) {
        String taskIcon;
        if (taskType.equals("T")) {
            taskIcon = "[T]";
        } else if (taskType.equals("D")) {
            taskIcon = "[D]";
        } else if (taskType.equals("E")) {
            taskIcon = "[E]";
        } else {
            taskIcon = "[ ]";
        }
        return taskIcon + "[" + (isDone ? "X" : " ") + "] " + task;
    }

    public static void save(String fileName, String[] actions, String[] type, boolean[] isDone, int counter) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(counter + "\n");
            for (int i = 0; i < counter; i++) {
                int isDoneNum = isDone[i]
                        ? 1
                        : 0;
                writer.write(type[i] + " | " + isDoneNum + " | " + actions[i] + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int load(String fileName, String[] actions, String[] type, boolean[] isDone) {
        int count = 0;
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine() && count < actions.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                // to split the line read into the different parts action num and done
                if (parts.length >= 3) {
                    type[count] = parts[0];
                    isDone[count] = parts[1].equals("1");
                    actions[count] = parts[2];
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    private static LocalDateTime dateTask (String dateTimeStr) {
        DateTimeFormatter DMYhelper = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // HHmm for the hour and minutes
        return LocalDateTime.parse(dateTimeStr, DMYhelper);
    }
}



