import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Duke {
    private String name = "Lakinta";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private final String filePath = "./data/duke.txt";

    public Duke() {
        // Load tasks from the file when the chatbot starts up
        loadTasksFromFile();
    }

    public void greeting() {
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void chat() {
        while (true) {
            String response = scanner.nextLine();

            if (response.equals("bye")) {
                exit();
                break;
            } else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                listTasks();
            } else if (response.length() > 5 && response.startsWith("mark ")) {
                int id = Integer.parseInt(response.substring(5));
                String message = markTaskAsDone(id);
                System.out.println(message);
            } else if (response.length() > 7 && response.startsWith("unmark ")) {
                int id = Integer.parseInt(response.substring(7));
                String message = unmarkTask(id);
                System.out.println(message);
            } else if (response.startsWith("todo ") && response.length() > 5) {
                String description = response.substring(5);
                String message = addTodoTask(description);
                System.out.println(message);
            } else if (response.startsWith("event ") && response.length() > 6) {
                String[] parts = response.split("/at", 2);
                if (parts.length == 2) {
                    String description = parts[0].substring(6).trim();
                    String dateTimeString = parts[1].trim();
                    String message = addEventTask(description, dateTimeString);
                    System.out.println(message);
                } else {
                    System.out.println("Invalid event format. Use 'event <description> /at <datetime>'.");
                }
            } else if (response.startsWith("deadline ") && response.length() > 9) {
                String[] parts = response.split("/by", 2);
                if (parts.length == 2) {
                    String description = parts[0].substring(9).trim();
                    String dateTimeString = parts[1].trim();
                    String message = addDeadlineTask(description, dateTimeString);
                    System.out.println(message);
                } else {
                    String message = "Invalid deadline format. Use 'deadline <description> /by <datetime>'.";
                    System.out.println(message);
                }
            } else if (response.startsWith("delete ") && response.length() > 7) {
                int id = Integer.parseInt(response.substring(7));
                String message = deleteTask(id);
                System.out.println(message);
            } else {
                System.out.println("I'm sorry, I don't understand that command.");
            }
        }
        scanner.close();
        saveTasksToFile();
    }

    private void listTasks() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    private String markTaskAsDone(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (!task.isDone) {
                task.markAsDone();
                return "Nice! I've marked this task as done:\n" + task.toString();
            } else {
                return "This task is already marked as done:\n" + task.toString();
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    private String unmarkTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (task.isDone) {
                task.markAsUndone();
                return "OK, I've marked this task as not done yet:\n" + task.toString();
            } else {
                return "This task is already marked as not done:\n" + task.toString();
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    private String addTodoTask(String description) {
        taskArrayList.add(new ToDo(description));
        return "Got it. I've added this task:\n" + taskArrayList.get(taskArrayList.size() - 1).toString()
                + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
    }

    private String addEventTask(String description, String dateTimeString) {
        LocalDateTime dateTime = parseCustomDateTime(dateTimeString);
        if (dateTime != null) {
            taskArrayList.add(new Event(description, dateTime, null)); // Pass null for the end date/time
            return "Got it. I've added this task:\n" + taskArrayList.get(taskArrayList.size() - 1).toString()
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        } else {
            return "Invalid date format. Please use yyyy-MM-dd HH:mm.";
        }
    }

    private String addDeadlineTask(String description, String dateTimeString) {
        LocalDateTime dateTime = parseCustomDateTime(dateTimeString);
        LocalDate date = parseCustomDate(dateTimeString);

        if (dateTime != null || date != null) {
            if (dateTime != null) {
                taskArrayList.add(new Deadline(description, dateTime));
            } else {
                // If dateTime is null, use the date parsed from the input
                taskArrayList.add(new Deadline(description, date));
            }

            return "Got it. I've added this task:\n" + taskArrayList.get(taskArrayList.size() - 1).toString()
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        } else {
            return "Invalid date format. Please use dd/MM/yyyy HHmm or yyyy-MM-dd.";
        }
    }

    private LocalDateTime parseCustomDateTime(String dateTimeString) {
        // Try parsing with "dd/MM/yyyy HHmm" format
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter1);
        } catch (Exception e) {
            // Parsing with the first format failed, try the second format below
        }

        // Try parsing with "yyyy-MM-dd" format
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateTimeString, formatter2);
            return date.atStartOfDay();
        } catch (Exception e) {
            // Both parsing attempts failed
            return null;
        }
    }

    private LocalDate parseCustomDate(String dateString) {
        // Try parsing with "dd/MM/yyyy" format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            // Parsing with the first format failed, try the second format below
        }

        // Try parsing with "yyyy-MM-dd" format
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString, formatter2);
        } catch (Exception e) {
            // Both parsing attempts failed
            return null;
        }
    }

    private String deleteTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.remove(id - 1);
            return "Noted. I've removed this task:\n" + task.toString()
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    private void saveTasksToFile() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Write the taskArrayList to the file
                oos.writeObject(taskArrayList);
            } catch (IOException e) {
                System.out.println("Error saving tasks to file!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file or directory!");
            e.printStackTrace();
        }
    }

    private void loadTasksFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Read the taskArrayList from the file
                taskArrayList = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions, e.g., if the file is corrupted
                taskArrayList = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            taskArrayList = new ArrayList<>();
        }
    }



    public static void main(String[] args) {
        Duke myBot = new Duke();
        myBot.greeting();
        myBot.chat();
    }
}

