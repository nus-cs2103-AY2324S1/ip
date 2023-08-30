import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ----------------------------------------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static Boolean programRunning = true;

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    private static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }
    
    private static void greet() {
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)");
        messages.add("What do you want to do today?");
        Duke.respond(messages);
    }

    private static void exit() {
        Duke.respond("Bye. Hope to see you again soon!");
    }

    private static void listTasks(ArrayList<Task> tasks) throws DukeException{
        ArrayList<String> output = new ArrayList<String>();

        for (int i = 0; i < tasks.size(); i++) {
            String taskOutput = String.format("%d. %s", i + 1, tasks.get(i));
            output.add(taskOutput);
        }

        if (output.size() == 0) {
            Duke.respond("There are no tasks in your list.");
            return;
        }

        Duke.respond(output);
    }

    private static void markTaskAsDone(String args) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"mark 1\" to mark the first task as done.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            Task targetTask = tasks.get(taskIndex);
            targetTask.markAsDone();

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Nice! I've marked this task as done:");
            messages.add(String.format("  %s",targetTask.toString()));

            Duke.respond(messages);
            Duke.storeTasks();
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void markTaskAsUndone(String args) throws DukeException{
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"unmark 1\" to mark the first task as not done.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            Task targetTask = tasks.get(taskIndex);
            targetTask.markAsUndone();

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("OK, I've marked this task as not done yet:");
            messages.add(String.format("  %s",targetTask.toString()));

            Duke.respond(messages);
            Duke.storeTasks();
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void deleteTask(String args) throws DukeException{
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        String taskIndexString = args.split(" ")[0];

        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                if (tasks.size() == 1) {
                    throw new DukeException(String.format("Task %d does not exist.\n     "
                                                          + "Use \"delete 1\" to delete the first task.", taskIndex + 1));
                }

                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                      taskIndex + 1,
                                                      tasks.size()));
            }


            String targetTaskInfo = tasks.get(taskIndex).toString();
            tasks.remove(taskIndex);

            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Noted. I've removed this task:");
            messages.add(String.format("  %s", targetTaskInfo));
            messages.add(String.format("Now you have %d tasks in the list.", tasks.size()));

            Duke.respond(messages);
            Duke.storeTasks();
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                  + "Please retry with a valid task number.", taskIndexString));
        }
    }

    private static void addTask(TaskType taskType, String args) {
        Duke.addTask(taskType, args, false, false);
    }

    private static void addTask(TaskType taskType, String args, boolean completed, boolean silent) {
        try {
            Optional<Task> taskToAdd = Optional.empty();
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");

            switch (taskType) {
            case TODO:
                if (args.trim().equals("")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                taskToAdd = Optional.of(new Todo(args));
                break;

            case DEADLINE:
                String[] deadlineArguments = args.split("/");
                String deadlineDescription = deadlineArguments[0].trim();
                TemporalAccessor dueDateTime = null;

                for (int i = 1; i < deadlineArguments.length; i++) {
                    if (deadlineArguments[i].startsWith("by ")) {
                        try {
                            dueDateTime = parser.parseBest(deadlineArguments[i].substring(3).trim(),
                                            LocalDateTime::from,
                                            LocalDate::from);

                        } catch (Exception e) {
                            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
                        }
                    }
                }

                if (deadlineDescription.equals("")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }

                if (dueDateTime == null) {
                    throw new DukeException("No due date/time specified. Please specify a due date/time");
                }

                taskToAdd = Optional.of(new Deadline(deadlineDescription, dueDateTime));
                break;

            case EVENT:
                String[] eventArguments = args.split("/");

                String eventDescription = eventArguments[0].trim();
                TemporalAccessor startDatetime = null;
                TemporalAccessor endDatetime = null;
                
                for (int i = 1; i < eventArguments.length; i++) {
                    if (eventArguments[i].startsWith("from ")) {
                        try {
                            startDatetime = parser.parseBest(eventArguments[i].substring(5).trim(),
                                            LocalDateTime::from,
                                            LocalDate::from);

                        } catch (Exception e) {
                            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
                        }
                    }

                    if (eventArguments[i].startsWith("to ")) {
                        try {
                            endDatetime = parser.parseBest(eventArguments[i].substring(3).trim(),
                                            LocalDateTime::from,
                                            LocalDate::from);

                        } catch (Exception e) {
                            throw new DukeException("Please use the format \"DD-MM-YYYY [HHMM]\"");
                        }
                    }
                }

                if (eventDescription.equals("")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                
                if (startDatetime == null) {
                    throw new DukeException("No start date/time specified. Please specify a start date/time.");
                }

                if (endDatetime == null) {
                    throw new DukeException("No end date/time specified. Please specify an end date/time.");
                }

                if (startDatetime instanceof LocalDate) {
                    if (!(endDatetime instanceof LocalDate)) {
                        throw new DukeException("Please ensure that both arguments have the same format.");
                    }

                    LocalDate startDate = (LocalDate) startDatetime;
                    LocalDate endDate = (LocalDate) endDatetime;

                    if (startDate.isAfter(endDate)) {
                        throw new DukeException("Start date cannot be after the end date");
                    }
                }

                if (startDatetime instanceof LocalDateTime) {
                    if (!(endDatetime instanceof LocalDateTime)) {
                        throw new DukeException("Please ensure that both arguments have the same format.");
                    }

                    LocalDateTime startDate = (LocalDateTime) startDatetime;
                    LocalDateTime endDate = (LocalDateTime) endDatetime;

                    if (startDate.isAfter(endDate)) {
                        throw new DukeException("Start date cannot be after the end date");
                    }
                }

                taskToAdd = Optional.of(new Event(eventDescription, startDatetime, endDatetime));
                break;
            }

            taskToAdd.ifPresent(
                task -> {
                    tasks.add(task);
                    if (!silent) {
                        ArrayList<String> messages = new ArrayList<String>();
                        messages.add("Got it. I've added this task:");
                        messages.add(String.format("  %s", task.toString()));
                        messages.add(String.format("Now you have %d tasks in the list.", tasks.size()));
                        Duke.respond(messages);
                    }

                    try {
                        Duke.storeTasks();
                    } catch (DukeException e) {
                        Duke.respond(e);
                    }
                }
            );
        } catch (DukeException e) {
            Duke.respond(e);
        }
    }

    private static void loadTasks() throws DukeException{
        Path taskFilePath = Path.of("./data/duke.txt");

        try {
            if (!Files.exists(taskFilePath)) {
                Files.createDirectories(taskFilePath.getParent());
                Files.createFile(taskFilePath);
            }

            BufferedReader reader = Files.newBufferedReader(taskFilePath);
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
            reader.lines().forEach(line -> {
                String[] taskData = line.split(" \\| ");
                
                // Handles case where empty line is read
                if (taskData.length == 0) {
                    return;
                }

                switch (taskData[0]) {
                case "T":
                    // Ensures there is completion status and description
                    if (taskData.length < 3) {
                        return;
                    }

                    boolean todoCompletion = taskData[1].equals("1");
                    String todoDescription = taskData[2];

                    Duke.addTask(TaskType.TODO, todoDescription, todoCompletion, true);
                    break;
                case "D":
                    // Ensure there is completion status, description and deadline
                    if (taskData.length < 4) {
                        return;
                    }

                    boolean deadlineCompletion = taskData[1].equals("1");
                    String deadlineDescription = taskData[2];
                    TemporalAccessor deadlineDueDate = parser.parseBest(taskData[3],
                                                                        LocalDateTime::from,
                                                                        LocalDate::from);

                    Duke.addTask(TaskType.DEADLINE,
                                 String.format("%s /by %s",
                                               deadlineDescription,
                                               parser.format(deadlineDueDate)),
                                 deadlineCompletion,
                                 true);
                    break;
                case "E":
                    // Ensure there is completion status, description, start and end date
                    if (taskData.length < 5) {
                        return;
                    }

                    boolean eventCompletion = taskData[1].equals("1");
                    String eventDescription = taskData[2];
                    TemporalAccessor eventStartDate = parser.parseBest(taskData[3],
                                                                        LocalDateTime::from,
                                                                        LocalDate::from);
                    TemporalAccessor eventEndDate = parser.parseBest(taskData[4],
                                                                        LocalDateTime::from,
                                                                        LocalDate::from);

                    Duke.addTask(TaskType.EVENT,
                                 String.format("%s /from %s /to %s",
                                               eventDescription,
                                               parser.format(eventStartDate),
                                               parser.format(eventEndDate)),
                                 eventCompletion,
                                 true);
                    break;
                }
            });
        } catch (Exception e) {
            throw new DukeException("Unable to load previously saved tasks.");
        }
    }

    private static void storeTasks() throws DukeException{
        Path taskFilePath = Path.of("./data/duke.txt");
        
        try {
            Files.deleteIfExists(taskFilePath);
        } catch (Exception e) {
            throw new DukeException("Unable to save task data.");
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(taskFilePath);
            for (Task task: tasks) {
                 try {
                    writer.write(task.getDataString());
                    writer.newLine();
                } catch (Exception e) {
                    throw new DukeException("Unable to write task data.");
                }
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException("Unable to open file.");
        }
    }

    public static void main(String[] args) {
        try {
            Duke.loadTasks();
        } catch (DukeException e) {
            Duke.respond(e);
        }

        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        while (programRunning) {
            String input = scanner.nextLine();
            System.out.println();

            if (input.trim().equals("")) {
                continue;
            }

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String arguments = splitInput.length > 1 ? splitInput[1] : "";

            try {
                switch(command) {
                case "bye":
                    programRunning = false;
                    break;
                case "list":
                    Duke.listTasks(tasks);
                    break;
                case "mark":
                    Duke.markTaskAsDone(arguments);
                    break;
                case "unmark":
                    Duke.markTaskAsUndone(arguments);
                    break;
                case "todo":
                    Duke.addTask(TaskType.TODO, arguments);
                    break;
                case "deadline":
                    Duke.addTask(TaskType.DEADLINE, arguments);
                    break;
                case "event":
                    Duke.addTask(TaskType.EVENT, arguments);
                    break;
                case "delete": 
                    Duke.deleteTask(arguments);
                    break;
                default:
                    throw new DukeException(String.format("Command \"%s\" does not exist!"
                                                          + "Please type another command.", command));
                }
            } catch (DukeException e) {
                Duke.respond(e);
                continue;
            }
        }

        scanner.close();
        Duke.exit();
    }
}
