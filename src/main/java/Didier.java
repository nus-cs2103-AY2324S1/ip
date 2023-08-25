import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Didier {

    private static String tasksFileName = "didier.txt";
    private static String tasksDirectoryPath = "data/";
    enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        UNKOWN;

        static Command textToCommand(String text) {
            try {
                Command command = Command.valueOf(text.toUpperCase());
                return command;
            } catch (IllegalArgumentException e) {
                return Command.UNKOWN;
            }
        }
    }

    public static void main(String[] args) {
        Didier.botPrintBr();
        Didier.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Didier.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String[] userInput = scanner.nextLine().split(" ", 2);
        Command command = Command.textToCommand(userInput[0]);
        ArrayList<Task> tasks = getTasks();
        while (!command.equals(Command.BYE)) {
            // Carry out the action determined by the command
            try {
                switch (command) {
                case LIST:
                    Didier.botPrintMessage("The tasks in your list are as follows:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Didier.botPrintMessage(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                    break;
                case MARK:
                case UNMARK:
                    try {
                        Task task = tasks.get(Integer.parseInt(userInput[1]) - 1);
                        if (userInput[0].equals("mark")) {
                            task.markAsDone();
                            Didier.botPrintMessage("Okay! I've marked the following task as done:");
                        } else {
                            task.markAsNotDone();
                            Didier.botPrintMessage("Okay! I've marked the following task as undone:");
                        }
                        Didier.botPrintMessage(task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new TaskNumberException(userInput[1]);
                    }
                    break;
                case DELETE:
                    try {
                        Task task = tasks.remove(Integer.parseInt(userInput[1]) - 1);
                        Didier.botPrintMessage("Okay! I've removed this task:");
                        Didier.botPrintMessage(task.toString());
                        Didier.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new TaskNumberException(userInput[1]);
                    }
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task task;
                    if (userInput.length == 1 || userInput[1].isBlank()) {
                        throw new ElementMissingException("description");
                    }
                    if (userInput[0].equals("todo")) {
                        task = new ToDo(userInput[1]);
                    } else if (userInput[0].equals("deadline")) {
                        String[] deadlineCommand = userInput[1].split("\\\\by ", 2);
                        if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                            throw new ElementMissingException("deadline");
                        } else {
                            try {
                                task = new Deadline(deadlineCommand[0], LocalDate.parse(deadlineCommand[1].trim()));
                            } catch (DateTimeParseException e) {
                                throw new DateFormatException();
                            }
                        }
                    } else {
                        String[] fromCommand = userInput[1].split("\\\\from ", 2);
                        if (fromCommand.length == 1 || fromCommand[1].isBlank()) {
                            throw new ElementMissingException("start time");
                        }
                        String[] toCommand = fromCommand[1].split("\\\\to ", 2);
                        if (toCommand.length == 1 || toCommand[1].isBlank()) {
                            throw new ElementMissingException("end time");
                        } else {
                            try {
                                task = new Event(fromCommand[0], LocalDate.parse(toCommand[0].trim()),
                                        LocalDate.parse(toCommand[1].trim()));
                            } catch (DateTimeParseException e) {
                                throw new DateFormatException();
                            }
                        }
                    }
                    tasks.add(task);
                    Didier.botPrintMessage("Okay! I've added your task:");
                    Didier.botPrintMessage(task.toString());
                    Didier.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    break;
                case UNKOWN:
                    throw new InvalidCommandException(userInput[0]);
                }
                saveTasks(tasks);
            } catch (DidierException e) {
                Didier.botPrintMessage(e.getMessage() + "Please try again.");
            }
            Didier.botPrintBr();
            userInput = scanner.nextLine().split(" ", 2);
            command = Command.textToCommand(userInput[0]);
        }

        Didier.botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        Didier.botPrintBr();
        scanner.close();
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    private static void botPrintMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line break to the standard output.
     */
    private static void botPrintBr() {
        String lineBreak = "---------------------------------------------------------------------";
        Didier.botPrintMessage(lineBreak);
    }

    private static ArrayList<Task> getTasks() {
        File directory = new File(Didier.tasksDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(Didier.tasksDirectoryPath + Didier.tasksFileName);
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = Task.parseFileString(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        File directory = new File(Didier.tasksDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(Didier.tasksDirectoryPath + Didier.tasksFileName);
            for (Task task: tasks) {
                fileWriter.write(task.composeToFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
