import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public static void main(String[] args) {

        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;

        String exitMessage = HORIZONTAL_LINE
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;

        ArrayList<Task> list = new ArrayList<>();
        File file = new File("./data/duke.txt");

        loadData(list, file);

        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println(entryMessage);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("mark")) {
                try {
                    markTaskAsDone(input, list);
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark")) {
                try {
                    markTaskAsNotDone(input, list);
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("delete")) {
                try {
                    deleteTask(input, list);
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    addDeadline(input, list);
                } catch (DeadlineException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    addEvent(input, list);
                } catch (EventException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    addToDo(input, list);
                } catch (ToDoException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equals("bye")) {
                saveData(list, file);
                System.out.println(exitMessage);
                break;
            } else {
                System.out.print(HORIZONTAL_LINE);
                System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
                System.out.println(HORIZONTAL_LINE);
            }
        }
        scanner.close();
    }

    private static void loadData(ArrayList<Task> list, File file) {
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print(HORIZONTAL_LINE);
            System.out.println("Error: File not found");
            System.out.println(HORIZONTAL_LINE);
        }

        while (sc != null && sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.charAt(0) == 'D') {
                int byIndex = input.indexOf('|', 7);

                String description = input.substring(8, byIndex - 1);
                String by = input.substring(byIndex + 2);

                LocalDate byDate = LocalDate.parse(by);

                Deadline deadline = new Deadline(description, byDate);
                list.add(deadline);

                if (input.charAt(4) == '1') {
                    deadline.markAsDone();
                }
            } else if (input.charAt(0) == 'E') {
                int fromIndex = input.indexOf('|', 7);
                int toIndex = input.indexOf('|', fromIndex + 1);

                String description = input.substring(8, fromIndex - 1);
                String from = input.substring(fromIndex + 2, toIndex - 1);
                String to = input.substring(toIndex + 2);

                LocalDate fromDate = LocalDate.parse(from);
                LocalDate toDate = LocalDate.parse(to);

                Event event = new Event(description, fromDate, toDate);
                list.add(event);

                if (input.charAt(4) == '1') {
                    event.markAsDone();
                }

            } else if (input.charAt(0) == 'T') {
                String description = input.substring(8);

                ToDo todo = new ToDo(description);
                list.add(todo);

                if (input.charAt(4) == '1') {
                    todo.markAsDone();
                }
            }
        }
    }

    private static void saveData(ArrayList<Task> list, File file) {
        String newData = "";

        for (int i = 0; i < list.size(); i++) {
            String input = list.get(i).toString();
            String taskType = String.valueOf(input.charAt(1));
            String status = input.charAt(4) == 'X' ? "1" : "0";

            newData += taskType + " | " + status + " | ";
            if (taskType.equals("D")) {
                int byIndex = input.indexOf("(by:");
                int endIndex = input.indexOf(")", byIndex);

                String description = input.substring(7, byIndex - 1);
                String by = input.substring(byIndex + 5, endIndex);

                newData += description + " | " + by;
            } else if (taskType.equals("E")) {
                int fromIndex = input.indexOf("(from:");
                int toIndex = input.indexOf("to:", fromIndex);
                int endIndex = input.indexOf(")", toIndex);

                String description = input.substring(7, fromIndex - 1);
                String from = input.substring(fromIndex + 7, toIndex - 1);
                String to = input.substring(toIndex + 4, endIndex);

                newData += description + " | " + from + " | " + to;
            } else if (taskType.equals("T")) {
                String description = input.substring(7);

                newData += description;
            }
            newData += System.lineSeparator();
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newData);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save list");
        }

    }

    private static void printList(ArrayList<Task> list) {
        System.out.print(HORIZONTAL_LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "."+ list.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsDone(String input, ArrayList<Task> list) throws TaskException {
        if (input.length() <= 5) {
            throw new TaskException();
        } else if ((Integer.valueOf(input.substring(5)) - 1) >= list.size()) {
            throw new TaskException();
        }

        Task task = list.get(Integer.valueOf(input.substring(5)) - 1);
        task.markAsDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsNotDone(String input, ArrayList<Task> list) throws TaskException {
        if (input.length() <= 7) {
            throw new TaskException();
        } else if ((Integer.valueOf(input.substring(7)) - 1) >= list.size()) {
            throw new TaskException();
        }

        Task task = list.get(Integer.valueOf(input.substring(7)) - 1);
        task.markAsNotDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void deleteTask(String input, ArrayList<Task> list) throws TaskException {
        if (input.length() <= 7) {
            throw new TaskException();
        } else if ((Integer.valueOf(input.substring(7)) - 1) >= list.size()) {
            throw new TaskException();
        }

        int index = Integer.valueOf(input.substring(7)) - 1;
        Task task = list.get(index);
        list.remove(index);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String input, ArrayList<Task> list) throws DeadlineException {
        int byIndex = input.indexOf("/by");

        if (input.length() <= 9) {
            throw new DeadlineException();
        } else if (byIndex == -1) {
            throw new DeadlineException();
        }

        String description = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 4);

        LocalDate byDate = LocalDate.parse(by);

        Deadline deadline = new Deadline(description, byDate);
        list.add(deadline);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEvent(String input, ArrayList<Task> list) throws EventException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (input.length() <= 6) {
            throw new EventException();
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new EventException();
        } else if (fromIndex > toIndex) {
            throw new EventException();
        }

        String description = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);

        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        Event event = new Event(description, fromDate, toDate);
        list.add(event);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addToDo(String input, ArrayList<Task> list) throws ToDoException {
        if (input.length() <= 5) {
            throw new ToDoException();
        }

        ToDo todo = new ToDo(input.substring(5));
        list.add(todo);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

}
