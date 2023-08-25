import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final ArrayList<Task> tasks = new ArrayList<>();
    static final Scanner scanner = new Scanner(System.in);
    static final String saveFilePath = "./data/duke.txt";

    private static void loadSaveFile() {
        File saveFile = new File(saveFilePath);
        try {
            Scanner saveFileReader = new Scanner(saveFile);
            while (saveFileReader.hasNextLine()) {
                String data = saveFileReader.nextLine();
                String[] tokens = data.split(" \\| ");
                String taskType = tokens[0];
                boolean status = tokens[1].equals("1");
                switch (taskType) {
                    case "T": {
                        addTaskSilent(new Todo(tokens[2]), status);
                        break;
                    }
                    case "D": {
                        addTaskSilent(new Deadline((tokens[2]), validateLocalDateTime(tokens[3])), status);
                        break;
                    }
                    case "E": {
                        addTaskSilent(new Event(tokens[2], validateLocalDateTime(tokens[3]), validateLocalDateTime(tokens[4])), status);
                        break;
                    }
                }
            }
            saveFileReader.close();
        } catch (FileNotFoundException e1) {
            try {
                File dataFolder = new File("./data/");
                if (!dataFolder.exists()) {
                    dataFolder.mkdir();
                }
                saveFile.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Save file corrupted. Wiping it!");
            tasks.clear();
            saveToFile();
        }

    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
            for (Task task : tasks) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadSaveFile();

        String logo =
                "    ,o888888o.    8 8888        8          .8.    8888888 8888888888 8888888 8888888888 `8.`8888.      ,8'\n" +
                        "   8888     `88.  8 8888        8         .888.         8 8888             8 8888        `8.`8888.    ,8'\n" +
                        ",8 8888       `8. 8 8888        8        :88888.        8 8888             8 8888         `8.`8888.  ,8'\n" +
                        "88 8888           8 8888        8       . `88888.       8 8888             8 8888          `8.`8888.,8'\n" +
                        "88 8888           8 8888        8      .8. `88888.      8 8888             8 8888           `8.`88888'\n" +
                        "88 8888           8 8888        8     .8`8. `88888.     8 8888             8 8888            `8. 8888\n" +
                        "88 8888           8 8888888888888    .8' `8. `88888.    8 8888             8 8888             `8 8888\n" +
                        "`8 8888       .8' 8 8888        8   .8'   `8. `88888.   8 8888             8 8888              8 8888\n" +
                        "   8888     ,88'  8 8888        8  .888888888. `88888.  8 8888             8 8888              8 8888\n" +
                        "    `8888888P'    8 8888        8 .8'       `8. `88888. 8 8888             8 8888              8 8888\n";


        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");

        label:
        while (true) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ", 2);

            System.out.println("------------------------------------------");
            try {
                String rootCmd = tokens[0];
                switch (rootCmd) {
                    case "list":
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    case "bye":
                        System.out.println("Oh.. bye");
                        break label;

                    case "delete": {
                        if (tokens.length == 1) {
                            throwException("The task index of a task cannot be empty", "delete <task index>");
                        }
                        int index = validateTaskIndex(tokens[1]);
                        Task task = tasks.get(index);
                        tasks.remove(index);
                        System.out.printf("Noted. I've removed this task:\n\t%s\n", task);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    }
                    case "mark": {
                        if (tokens.length == 1) {
                            throwException("The task index of a task cannot be empty", "mark <task index>");
                        }
                        int index = validateTaskIndex(tokens[1]);
                        Task task = tasks.get(index);
                        task.setStatus(true);
                        break;
                    }
                    case "unmark": {
                        if (tokens.length == 1) {
                            throwException("The task index of a task cannot be empty", "unmark <task index>");
                        }
                        int index = validateTaskIndex(tokens[1]);
                        Task task = tasks.get(index);
                        task.setStatus(false);

                        break;
                    }
                    case "todo":
                        if (input.length() < 5) {
                            throwException("The description of a todo cannot be empty", "todo <description>");
                        }
                        String todoText = input.substring(5);
                        if (todoText.trim().equals("")) {
                            throwException("The description of a todo cannot be empty", "todo <description>");
                        }
                        addTask(new Todo(todoText));
                        break;
                    case "deadline": {
                        String usageText = "deadline <description> /by <due date/time>";
                        if (tokens.length == 1) {
                            throwException("The description and due date of a deadline cannot be empty.", usageText);
                        }

                        String deadlineText = tokens[1];
                        String[] parts = deadlineText.split("/by", 2);

                        if (parts.length == 0) {
                            throwException("The description and due date of a deadline cannot be empty.", usageText);
                        } else if (parts.length == 1) {
                            throwException("The due date of a deadline cannot be empty.", usageText);
                        }

                        String description = parts[0].trim();
                        String date = parts[1].trim();
                        if (description.trim().equals("")) {
                            throwException("The description of a deadline cannot be empty.", usageText);
                        }
                        if (date.trim().equals("")) {
                            throwException("The due date of a deadline cannot be empty.", usageText);
                        }
                        addTask(new Deadline(description, validateLocalDateTime(date)));
                        break;
                    }
                    case "event": {
                        String usageText = "event <description> /from <start date/time> /to <end date/time>";
                        if (tokens.length == 1) {
                            throwException("The description, start date/time and end date/time of an event cannot be empty.", usageText);
                        }

                        String eventText = tokens[1];
                        String[] eventParts = eventText.split("/from", 2);

                        if (eventParts.length == 0) {
                            throwException("The description, start date/time and end date/time of an event cannot be empty.", usageText);
                        } else if (eventParts.length == 1) {
                            throwException("The start date/time and end date/time of an event cannot be empty.", usageText);
                        }

                        String description = eventParts[0].trim();
                        String[] fromToParts = eventParts[1].split("/to", 2);

                        if (fromToParts.length == 0) {
                            throwException("The start date/time and end date/time of an event cannot be empty.", usageText);
                        } else if (fromToParts.length == 1) {
                            throwException("The end date/time of an event cannot be empty.", usageText);
                        }

                        String fromDate = fromToParts[0].trim();
                        String toTime = fromToParts[1].trim();
                        if (description.trim().equals("")) {
                            throwException("The description of an event cannot be empty.", usageText);
                        }
                        if (fromDate.trim().equals("")) {
                            throwException("The start date/time of an event cannot be empty.", usageText);
                        }
                        if (toTime.trim().equals("")) {
                            throwException("The end date/time of an event cannot be empty.", usageText);
                        }
                        addTask(new Event(description, validateLocalDateTime(fromDate), validateLocalDateTime(toTime)));
                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("------------------------------------------");

            saveToFile();
        }
    }

    private static LocalDateTime validateLocalDateTime(String input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException error) {
            throw new DukeException("Invalid date format. <yyyy-MM-dd HH:mm> expected");
        }
    }

    private static int validateTaskIndex(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input);
            if (number < 1 || number > 100) {
                throw new DukeException("Please provide a task index that is 1 <= task index <= 100.");
            } else if (tasks.size() < number) {
                throw new DukeException("The given task index is higher than current task list: " + tasks.size() + ".");
            }

            int index = number - 1;
            Task task = tasks.get(index);
            if (task == null) {
                throw new DukeException("There is no task at the given task index.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number format");
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:\n\t%s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void addTaskSilent(Task task, boolean status) {
        tasks.add(task);
        task.setStatusSilent(status);
    }

    private static void throwException(String message, String usageText) throws DukeException {
        throw new DukeException(String.format("%s\n\n\tUsage: %s", message, usageText));
    }

}
