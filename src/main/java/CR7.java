import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
public class CR7 {
    // Function to print a horizontal line
    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }
    private static void loadFiles(String filepath, ArrayList<Task> tasks) throws CR7Exception, FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            createTask(s.nextLine(), tasks);
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void saveFiles(String filepath, ArrayList<Task> tasks) throws IOException {
        int i = tasks.size();
        String taskList = "";
        for (int j = 0; j < i; j++) {
            if (j == i - 1) {
                taskList += tasks.get(j).toString();
            } else {
                taskList += tasks.get(j).toString() + System.lineSeparator();
            }
        }
        writeToFile(filepath, taskList);
    }

    private static void createTask(String input, ArrayList<Task> tasks) throws CR7Exception {
        if (input.startsWith("[D]")) {
            int y = input.indexOf("(by: ");
            String desc = input.substring(7, y - 1);
            int end = input.indexOf(")");
            String by = input.substring(y + 5, end);
            LocalDateTime date = parseDate(by);
            Task t = new Deadline(desc, date);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        } else if (input.startsWith("[T]")) {
            String desc = input.substring(7);
            Task t = new ToDo(desc);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        } else if (input.startsWith("[E]")) {
            int fromIndex = input.indexOf("(from: ");
            int toIndex = input.indexOf("to: ");
            String desc = input.substring(7, input.indexOf("(") - 1);
            String s = input.substring(fromIndex + 7, toIndex - 1).trim();
            String e = input.substring(toIndex + 4, input.indexOf(")")).trim();
            LocalDateTime start = parseDate(s);
            LocalDateTime end = parseDate(e);
            Task t = new Event(desc, start, end);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        }
    }

    enum DateTimeFormat {
        FORMAT1("yyyy-MM-dd HH:mm"),
        FORMAT2("dd.MM.yyyy HH:mm"),
        FORMAT3("MM/dd/yyyy HH:mm"),
        FORMAT4("yyyy-MM-dd hh:mm a"),
        FORMAT5("dd.MM.yyyy hh:mm a"),
        FORMAT6("MM/dd/yyyy hh:mm a"),
        FORMAT7("yyyy-MM-dd HHmm"),
        FORMAT8("dd.MM.yyyy HHmm"),
        FORMAT9("MM/dd/yyyy HHmm");

        private final DateTimeFormatter formatter;

        DateTimeFormat(String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }

    public static LocalDateTime parseDate(String dateString) throws WrongDateException {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                LocalDateTime d = LocalDateTime.parse(dateString, format.getFormatter());
                return d;
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }
        // None of the formats matched
        throw new WrongDateException();
    }


    public static class CR7Exception extends Throwable {}

    public static class EmptyDescriptionException extends CR7Exception {}

    public static class EmptyDeadlineException extends CR7Exception {}

    public static class EmptyTimingException extends CR7Exception {}

    public static class WrongDateException extends CR7Exception {}

    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
        String input = "";
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            loadFiles("src/main/data/CR7.txt", tasks);
        } catch (CR7Exception error) {
            System.out.println("Your File has been corrupted! SIUUUU!");
        } catch (FileNotFoundException error) {
            System.out.println("Unable to load data");
        }
        while (!input.equals("bye")) {
            try {
                input = myObj.nextLine();
                if (input.equals("bye")) {
                    printHorizontalLine();
                    System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
                    printHorizontalLine();
                    break;
                }
                if (input.equals("list")) {
                    printHorizontalLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < tasks.size() + 1; i++) {
                        Task x = tasks.get(i - 1);
                        System.out.println(i + "." + x.toString());
                    }
                    System.out.println();
                    printHorizontalLine();
                } else {
                    String[] words = input.split(" ");
                    String first = words[0];
                    if (first.equals("delete")) {
                        int s = Integer.valueOf(words[1]);
                        Task k = tasks.remove(s - 1);
                        printHorizontalLine();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + k.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                        printHorizontalLine();
                        saveFiles("src/main/data/CR7.txt", tasks);
                    } else if (first.equals("mark")) {
                        int s = Integer.valueOf(words[1]);
                        Task k = tasks.get(s - 1);
                        k.markAsDone();
                        printHorizontalLine();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + k.toString() + "\n");
                        printHorizontalLine();
                        saveFiles("src/main/data/CR7.txt", tasks);
                    } else if (first.equals("unmark")) {
                        int s = Integer.valueOf(words[1]);
                        Task k = tasks.get(s - 1);
                        k.unmarkAsDone();
                        printHorizontalLine();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + k.toString() + "\n");
                        printHorizontalLine();
                        saveFiles("src/main/data/CR7.txt", tasks);
                    } else if (first.equals("todo")) {
                        if (input.length() <= 5) {
                            throw new EmptyDescriptionException();
                        } else {
                            String desc = input.substring(5);
                            Task t = new ToDo(desc);
                            tasks.add(t);
                            printHorizontalLine();
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + t.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                            printHorizontalLine();
                            saveFiles("src/main/data/CR7.txt", tasks);
                        }
                    } else if (first.equals("deadline")) {
                        if (input.length() <= 9) {
                            throw new EmptyDescriptionException();
                        } else {
                            int y = input.indexOf("/by ");
                            Task t = null;
                            if (y == -1) {
                                throw new EmptyDeadlineException();
                            } else {
                                String desc = input.substring(9, y - 1);
                                String d = input.substring(y + 4);
                                LocalDateTime date = parseDate(d);
                                t = new Deadline(desc, date);
                            }
                            tasks.add(t);
                            printHorizontalLine();
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + t.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                            printHorizontalLine();
                            saveFiles("src/main/data/CR7.txt", tasks);
                        }
                    } else if (first.equals("event")) {
                        if (input.length() <= 6) {
                            throw new EmptyDescriptionException();
                        } else {
                            int fromIndex = input.indexOf("/from ");
                            int toIndex = input.indexOf("/to ");
                            Task t = null;
                            if (fromIndex == -1 || toIndex == -1) {
                                throw new EmptyTimingException();
                            } else {
                                String desc = input.substring(6, input.indexOf("/") - 1);
                                String s = input.substring(fromIndex + 6, toIndex).trim();
                                String e = input.substring(toIndex + 4).trim();
                                LocalDateTime start = parseDate(s);
                                LocalDateTime end = parseDate(e);
                                t = new Event(desc, start, end);
                                tasks.add(t);
                                printHorizontalLine();
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + t.toString());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                                printHorizontalLine();
                                saveFiles("src/main/data/CR7.txt", tasks);
                            }
                        }
                    } else {
                        printHorizontalLine();
                        System.out.println("OOPS!! I'm sorry, but I don't know what that means :(\n");
                        printHorizontalLine();
                    }
                }
            } catch (EmptyDescriptionException e) {
                printHorizontalLine();
                System.out.println("OOPS!!! The description of a task cannot be empty.\n");
                printHorizontalLine();
            } catch (EmptyDeadlineException e) {
                printHorizontalLine();
                System.out.println("OOPS!!! Please enter the deadline of the task in the correct format.\n");
                printHorizontalLine();
            } catch (EmptyTimingException e) {
                printHorizontalLine();
                System.out.println("OOPS!!! Please enter the start and end time" +
                        " of the task in the correct format.\n");
                printHorizontalLine();
            } catch (IOException e) {
                System.out.println("SIUUUUU");
            } catch (WrongDateException e) {
                System.out.println("OOPS!!! PLease enter the date and time in the correct format. \n");
            }
        }
    }
}
