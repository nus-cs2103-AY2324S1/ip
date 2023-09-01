import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatterBot {
    public static ArrayList<Task> list = new ArrayList<>();
    public static String file = "../data/ChatterBot.txt";
    public static void main(String[] args) {

        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        File f = new File(file);
        File folder = f.getParentFile();

        if (!folder.exists()) {
            System.out.println("Error! No data folder found");
        }

        try {
            copyFileContents("../data/ChatterBot.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } // also catch data file not found exception

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();

            if (userMessage.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userMessage.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task t : list) {
                    System.out.println((list.indexOf(t) + 1) + ". "
                            + t.toString());
                }
            } else if (userMessage.startsWith("mark") && isInteger(userMessage.substring(5))) {
                String toMark = userMessage.substring(5);
                list.get(Integer.parseInt(toMark) - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + list.get(Integer.parseInt(toMark) - 1).description);
            } else if (userMessage.startsWith("unmark")) {
                String toUnmark = userMessage.substring(7);
                list.get(Integer.parseInt(toUnmark) - 1).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(Integer.parseInt(toUnmark) - 1).description);
            } else {
                if (userMessage.startsWith("deadline")) {
                    try {
                        if (userMessage.length() <= 9) {
                            throw new IllegalArgumentException("No task description");
                        }
                        try {
                            appendToFile(file, userMessage);
                        } catch (IOException e) {
                            System.out.println("Unable to append to file!");
                        }
                        int slashDeadline = userMessage.indexOf("/");
                        String deadlineDescription = userMessage.substring(9, slashDeadline).trim();
                        String deadlineBy = userMessage.substring(slashDeadline + 3).trim();
                        Deadline d = new Deadline(deadlineDescription, deadlineBy);
                        list.add(d);
                        System.out.println("Got it. I've added this task:\n" + d.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else if (userMessage.startsWith("todo")) {
                    try {
                        if (userMessage.length() <= 5) {
                            throw new IllegalArgumentException("No task description");
                        }
                        try {
                            appendToFile(file, userMessage);
                        } catch (IOException e) {
                            System.out.println("Unable to append to file!");
                        }
                        Todo td = new Todo(userMessage.substring(5));
                        list.add(td);
                        System.out.println("Got it. I've added this task:\n" + td.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else if (userMessage.startsWith("event")) {
                    try {
                        if (userMessage.length() <= 6) {
                            throw new IllegalArgumentException("No task description");
                        }
                        try {
                            appendToFile(file, userMessage);
                        } catch (IOException e) {
                            System.out.println("Unable to append to file!");
                        }
                        String[] eventSplit = userMessage.split("/");
                        String eventDescription = eventSplit[0].substring(6);
                        String eventTo = eventSplit[1].substring(5);
                        String eventFrom = eventSplit[2].substring(3);
                        Event e = new Event(eventDescription, eventTo, eventFrom);
                        list.add(e);
                        System.out.println("Got it. I've added this task:\n" + e.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else if (userMessage.startsWith("delete") && isInteger(userMessage.substring(7))) {
                    System.out.println("Noted. I've removed this task:\n" + list.get((Integer.parseInt(userMessage.substring(7)))-1) + "\nNow you have " + (list.size() - 1) + " tasks in the list.");
                    list.remove((Integer.parseInt(userMessage.substring(7))) - 1);
                    try {
                        writeToFile(file, convertToString(list));
                    } catch (IOException e) {
                        System.out.println("Error!");
                    }
                } else {
                    System.out.println("Unknown command: " + userMessage);
                }
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void copyFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.contains("todo")) {
                Todo todo = new Todo(line.substring(5));
                list.add(todo);
            } else if (line.contains("deadline")) {
                int slashDeadline = line.indexOf("/");
                String deadlineDescription = line.substring(9, slashDeadline).trim();
                String deadlineBy = line.substring(slashDeadline + 3).trim();
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                list.add(deadline);
            } else if (line.contains("event")) {
                String[] eventSplit = line.split("/");
                String eventDescription = eventSplit[0].substring(6);
                String eventTo = eventSplit[1].substring(5);
                String eventFrom = eventSplit[2].substring(3);
                Event event = new Event(eventDescription, eventTo, eventFrom);
                list.add(event);
            }
        }
        s.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static String convertToString(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : list) {
            stringBuilder.append(task.forFile()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static void writeToFile(String filePath, String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }
}