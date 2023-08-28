import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Sally {

    private static void handleMark(String input, ArrayList<Task> tasks) throws SallyException {
        if (input.length() < 6 || !input.contains(" ")) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }

        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new SallyException("OOPS! Provide a valid task number to unmark.");
            } else {
                Task task = tasks.get(taskIndex);
                task.mark();
                saveTasksToFile(tasks);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + task);
            }
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }
    }

    private static void handleUnmark (String input, ArrayList<Task> tasks) throws SallyException {
        if (input.length() < 8 || !input.contains(" ")) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }

        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new SallyException("OOPS! Provide a valid task number to unmark.");
            } else {
                Task task = tasks.get(taskIndex);
                task.unmark();
                saveTasksToFile(tasks);
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(" " + task);
            }
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }
    }

    private static void handleDelete(String input, ArrayList<Task> tasks) throws SallyException {
        if (input.length() < 7 || !input.contains(" ")) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }

        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new SallyException("OOPS! Provide a valid task number to delete.");
            }
            Task deletedTask = tasks.remove(taskIndex);
            saveTasksToFile(tasks);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }
    }

    private static void handleTodo(String input, ArrayList<Task> tasks) throws SallyException {
        if (input.length() < 6 || !input.contains(" ")) {
            throw new SallyException("OOPS! The description of a todo cannot be empty.");
        }

        Task newToDo = new Todo(input.substring(5));
        tasks.add(newToDo);
        saveTasksToFile(tasks);
        System.out.println("Added to My List: ");
        System.out.println(" " + newToDo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks) throws SallyException {
        if (input.length() < 10 || !input.contains(" /by ")) {
            throw new SallyException("OOPS! The description of a deadline cannot be incomplete, you need a ' /by '.");
        }

        String[] parts = input.split(" /by ");

        if (!parts[0].contains(" ")) {
            throw new SallyException("OOPS! Add a space between 'deadline' and the description of a deadline.");
        }

        if (parts.length < 2) {
            throw new SallyException("OOPS! The time of a deadline cannot be empty.");
        }

        Task newDeadline = new Deadline(parts[0].substring(9), parts[1]);

        tasks.add(newDeadline);
        saveTasksToFile(tasks);
        System.out.println("Added to My List: ");
        System.out.println(" " + newDeadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input, ArrayList<Task> tasks) throws SallyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new SallyException("OOPS! The description of an event cannot be incomplete, you need a '/from' and '/to'.");
        }

        String[] parts = input.split(" /from | /to ");

        if (!parts[0].contains(" ")) {
            throw new SallyException("OOPS! Add a space between 'event' and the description of an event.");
        }

        if (parts.length < 3) {
            throw new SallyException("OOPS! The start or end time of an event cannot be empty.");
        }

        Task newEvent = new Event(parts[0].substring(6), parts[1], parts[2]);

        tasks.add(newEvent);
        saveTasksToFile(tasks);
        System.out.println("Added to My List:");
        System.out.println(" " + newEvent);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static final String DATA_FILE_PATH = "./data/sally.txt";

    private static void saveTasksToFile(ArrayList<Task> tasks) throws SallyException {
        try {
            File file = new File(DATA_FILE_PATH);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter filewriter = new FileWriter(file);
            for (Task task : tasks) {
                filewriter.write(task.toFileString() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            throw new SallyException("OOPS! Something went wrong while saving your tasks.");
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(DATA_FILE_PATH);
            if(!file.exists()) {
                return tasks;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                tasks.add(task);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("OOPS! Something went wrong while loading your tasks.");
        }
        return tasks;
    }

    public static void main(String[] args) {
        System.out.println("Hey! It's Sally here!\n" + "How can I help you today?");

        ArrayList<Task> tasks = loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye! See you again next time.");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("My List:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + ". " + task);
                    }
                } else if (input.startsWith("mark")) {
                    handleMark(input, tasks);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input, tasks);
                } else if (input.startsWith("todo")) {
                    handleTodo (input, tasks);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input, tasks);
                } else if (input.startsWith("event")) {
                    handleEvent(input, tasks);
                } else if (input.startsWith("delete")) {
                    handleDelete(input, tasks);
                } else {
                    System.out.println("Sorry, I do not understand the command :-(");
                }
            } catch (SallyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
