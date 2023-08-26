import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spot {
    private final static String DIRECTORY_NAME = "./data";

    private final static String FILE_NAME = "spot.txt";

    private final static String FULL_PATH = DIRECTORY_NAME + "/" + FILE_NAME;

    private final static String HELLO_MESSAGE = "Hello, it's Spot!";

    private final static String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";

    private final static String LIST_MESSAGE = "Spot's got a list of your tasks, here!";

    private final static String EMPTY_MESSAGE = "You don't have any tasks for now! Want Spot to help find some?";

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void sayHello() {
        System.out.println(HELLO_MESSAGE);
    }

    public static void loadTask(String task) {
        String[] keywords = task.trim().split("\\Q | \\E");
        if (keywords[0].equals("T")) {
            if (keywords[1].equals("X")) {
                Spot.addTodo(keywords[2], true);
            } else {
                Spot.addTodo(keywords[2], false);
            }
        } else if (keywords[0].equals("D")) {
            if (keywords[1].equals("X")) {
                Spot.addDeadline(keywords[2], true,
                        keywords[3]);
            } else {
                Spot.addDeadline(keywords[2], false, keywords[3]);
            }
        } else {
            if (keywords[1].equals("X")) {
                Spot.addEvent(keywords[2], true, keywords[3], keywords[4]);
            } else {
                Spot.addEvent(keywords[2], false, keywords[3], keywords[4]);
            }
        }
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println(EMPTY_MESSAGE);
        } else {
            System.out.println(LIST_MESSAGE);
            for (int i = 0; i < taskList.size(); i++) {
                int position = i + 1;
                System.out.println(position + ". " + taskList.get(i).toString());
            }
        }
    }

    public static void markTask(int position) {
        try {
            if (position < 0 || position > taskList.size()) {
                throw new SpotException("Spot thinks that task doesn't exist!");
            }
            taskList.get(position - 1).markAsDone();
        } catch (SpotException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unmarkTask(int position) {
        try {
            if (position < 0 || position > taskList.size()) {
                throw new SpotException("Spot thinks that task doesn't exist!");
            }
            taskList.get(position - 1).markAsNotDone();
        } catch (SpotException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(int position) {
        try {
            if (position < 0 || position > taskList.size()) {
                throw new SpotException("Spot thinks that task doesn't exist!");
            }
            Task toRemove = taskList.remove(position - 1);
            System.out.println("Spot has removed this task: " + "\n" + toRemove.toString());
            System.out.println("Tasks in list: " + taskList.size());
        } catch (SpotException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseInput(String input) {
        if (input.startsWith("list")) {
            Spot.listTasks();
        } else if (input.startsWith("mark")) {
            try {
                if (input.length() <= 5) {
                    throw new SpotException("Spot needs more details than that!");
                }
                int position = Integer.parseInt(input.substring(5));
                Spot.markTask(position);
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("unmark")) {
            try {
                if (input.length() <= 7) {
                    throw new SpotException("Spot needs more details than that!");
                }
                int position = Integer.parseInt(input.substring(7));
                Spot.unmarkTask(position);
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("delete")) {
            try {
                if (input.length() <= 7) {
                    throw new SpotException("Spot needs more details than that!");
                }
                int position = Integer.parseInt(input.substring(7));
                Spot.deleteTask(position);
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("tasks on")) {
            try {
                if (input.length() <= 9) {
                    throw new SpotException("Spot thinks you might've " +
                            "forgotten to add a date!");
                }
                String d = input.substring(9);
                LocalDate date = LocalDate.parse(d,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Spot.printTasks(date);
            } catch (SpotException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("todo")) {
            try {
                if (input.length() <= 5) {
                    throw new SpotException("Spot wonders if you've " +
                            "forgotten the description?");
                }
                Spot.addTodo(input.substring(5).trim());
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("deadline")) {
            try {
                if (input.length() <= 9) {
                    throw new SpotException("Spot needs more details than that!");
                }
                String[] keywords = input.substring(9).trim().split("/by");
                if (keywords.length == 0 || keywords[0].trim().isEmpty()) {
                    throw new SpotException("Spot wonders if you've " +
                            "forgotten the description?");
                }
                if (keywords.length < 2) {
                    throw new SpotException("Spot thinks you're missing a deadline!");
                }
                Spot.addDeadline(keywords[0].trim(), keywords[1].trim());
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("event")) {
            try {
                if (input.length() <= 6) {
                    throw new SpotException("Spot needs more details than that!");
                }
                String[] keywords = input.substring(6).trim().split("/from|/to");
                if (keywords.length == 0 || keywords[0].trim().isEmpty()) {
                    throw new SpotException("Spot wonders if you've " +
                            "forgotten the description?");
                }
                if (keywords.length < 3 || keywords[1].trim().isEmpty()
                        || keywords[2].trim().isEmpty()) {
                    throw new SpotException("Spot can't find a start time" +
                            " and/or an end time!");
                }
                Spot.addEvent(keywords[0].trim(), keywords[1].trim(), keywords[2].trim());
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                throw new SpotException("Spot doesn't know what that is!");
            } catch (SpotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printTasks(LocalDate deadline) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.fallsOn(deadline)) {
                System.out.println(task);
            }
        }
    }

    public static void addTodo(String description) {
        try {
            ToDo newTask = new ToDo(description);
            taskList.add(newTask);
            System.out.println("Spot will add this new task to your list: "
                    + "\n" + "  " + newTask);
            System.out.println("Tasks in list: " + taskList.size());
        } catch (SpotException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTodo(String description, boolean isDone) {
        try {
            ToDo newTask = new ToDo(description, isDone);
            taskList.add(newTask);
        } catch (SpotException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDeadline(String description, String deadline) {
        try {
            LocalDate dateDeadline = LocalDate.parse(deadline,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Deadline newTask = new Deadline(description, dateDeadline);
            taskList.add(newTask);
            System.out.println("Spot will add this new task to your list: "
                    + "\n" + "  " + newTask);
            System.out.println("Tasks in list: " + taskList.size());
        } catch (SpotException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDeadline(String description, boolean isDone, String deadline) {
        try {
            LocalDate dateDeadline = LocalDate.parse(deadline,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Deadline newTask = new Deadline(description, isDone, dateDeadline);
            taskList.add(newTask);
        } catch (SpotException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEvent(String description, String start, String end) {
        try {
            LocalDate dateStart = LocalDate.parse(start,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate dateEnd = LocalDate.parse(end,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Event newTask = new Event(description, dateStart, dateEnd);
            taskList.add(newTask);
            System.out.println("Spot will add this new task to your list: "
                    + "\n" + "  " + newTask);
            System.out.println("Tasks in list: " + taskList.size());
        } catch (SpotException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEvent(String description, boolean isDone, String start, String end) {
        try {
            LocalDate dateStart = LocalDate.parse(start,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate dateEnd = LocalDate.parse(end,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Event newTask = new Event(description, isDone, dateStart, dateEnd);
            taskList.add(newTask);
        } catch (SpotException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveTasks() {
        try {
            FileWriter fileWriter = new FileWriter(Spot.FULL_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                bufferedWriter.write(task.toLine());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public static void main(String[] args) {
        Spot.sayHello();
        try {
            File directory = new File(DIRECTORY_NAME);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(Spot.FULL_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    Spot.loadTask(line);
                }
                fileScanner.close();
                Scanner inputScanner = new Scanner(System.in);
                while (inputScanner.hasNextLine()) {
                    String input = inputScanner.nextLine();
                    if (!input.equals("bye")) {
                        Spot.parseInput(input);
                    } else {
                        break;
                    }
                }
                inputScanner.close();
                Spot.saveTasks();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Spot.sayGoodbye();
    }
}
