import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bee {
    private static TaskList listOfTasks = new TaskList();

    private static final String DATA_FILE_PATH = "./data/bee.txt";

    private enum TaskClass {
        TODO, DEADLINE, EVENT
    }

    private enum TaskAction {
        MARK, UNMARK, DELETE
    }

    private static void greet() {
        String logo = "\n" +
                "__________\n" +
                "\\______   \\ ____   ____\n" +
                " |    |  _// __ \\_/ __ \\\n" +
                " |    |   \\  ___/\\  ___/\n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/\n";
        System.out.println("Hello! I'm" + logo);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");
    }

    private static void createTask(TaskClass task, String userInput) throws BeeException {
        switch (task) {
            case TODO:
                try {
                    String editedInput = userInput.substring(5);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(editedInput);
                    listOfTasks.addTask(todo);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                }
                break;
            case DEADLINE:
                try {
                    String editedInput = userInput.substring(9);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    String[] splitEditedInput = editedInput.split(" /by ");
                    String deadlineDescription = splitEditedInput[0];
                    String deadlineDateString = splitEditedInput[1];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, formatter);

                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                    listOfTasks.addTask(deadlineTask);
                } catch (DateTimeParseException e) {
                    throw new BeeException("OOPS!! Invalid deadline date format. Please use yyyy-MM-dd HHmm");
                }
                catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of the deadline cannot be empty.");
                }
                break;
            case EVENT:
                try {
                    String editedInput = userInput.substring(6);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of an event cannot be empty.");
                    }
                    String[] splitEditedInput = editedInput.split(" /from ");
                    String[] splitEditedInput2 = splitEditedInput[1].split(" /to ");
                    String eventDescription = splitEditedInput[0];
                    String eventStartDate = splitEditedInput2[0];
                    String eventEndDate = splitEditedInput2[1];
                    Event event = new Event(eventDescription, eventStartDate, eventEndDate);
                    listOfTasks.addTask(event);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of an event cannot be empty.");
                }
                break;

            default:
                listOfTasks.addTask(new Task(userInput));
        }
    }

    public static void updateTask(TaskAction action, String userInput) throws BeeException {
        String[] splitInput = userInput.split(" ");
        switch (action) {
            case MARK:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    listOfTasks.setTaskDone(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The task number cannot be empty.");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPS!! You must have entered an invalid task number.");
                }
                break;
            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    listOfTasks.setTaskNotDone(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The task number cannot be empty.");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                }
                break;
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    listOfTasks.deleteTask(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPSS!! Please enter a task number");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                }
                break;
            default:
                throw new BeeException("OOPSS!! I can't do that!!!");
        }
    }

    // Parse string information from file into task
    private static void parseTask(String taskData) throws BeeException{
        String[] taskDataSplit = taskData.split("]");
        String taskType = taskDataSplit[0].substring(1);
        Boolean isDone = taskDataSplit[1].substring(1).equals("X");
        String taskDescription = taskDataSplit[2].substring(1);

        switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDescription, isDone);
                listOfTasks.quietlyAddTask(todo);
                break;
            case "D":
                try {
                    String[] splitEditedInput = taskDescription.split("by: ");
                    String deadlineDescription = splitEditedInput[0];
                    deadlineDescription = deadlineDescription.substring(0, deadlineDescription.indexOf("(") - 1);
                    String deadlineDateString = splitEditedInput[1];
                    deadlineDateString = deadlineDateString.substring(0, deadlineDateString.indexOf(")"));

                    DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, storageFormatter);

                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate, isDone);
                    listOfTasks.quietlyAddTask(deadlineTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of the deadline cannot be empty.");
                }
                break;
            case "E":
                try {
                    String[] splitEditedInput = taskDescription.split("from: ");
                    String[] splitEditedInput2 = splitEditedInput[1].split(" to: ");
                    String eventDescription = splitEditedInput[0];
                    eventDescription = eventDescription.substring(0, eventDescription.indexOf("(") - 1);
                    String eventStartDate = splitEditedInput2[0];
                    String eventEndDate = splitEditedInput2[1];
                    eventEndDate = eventEndDate.substring(0, eventEndDate.indexOf(")"));
                    Event event = new Event(eventDescription, eventStartDate, eventEndDate, isDone);
                    listOfTasks.quietlyAddTask(event);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of an event cannot be empty.");
                }
                break;
        }
    }

    // Load tasks from file
    private static void loadTasksFromFile() throws BeeException {
        try {
            File file = new File(DATA_FILE_PATH);
            if (!file.exists()) {
                // If file doesn't exist, create an empty one
                file.createNewFile();
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                parseTask(taskData);
                // Parse taskData and add tasks to the list
                // Example: T | 1 | read book
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    // Save tasks to file
    private static void saveTasksToFile() {
        try {
            File file = new File(DATA_FILE_PATH);
            FileWriter writer = new FileWriter(file);

            for (Task task : listOfTasks.getTasks()) {
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        try {
            loadTasksFromFile();
        } catch (BeeException e) {
            System.out.println(e.getMessage());
        }

        while (isRunning) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            String command = splitInput[0].toLowerCase();
            try {
                switch (command) {
                    case "bye":
                        System.out.println("Bye-bye! Have a great day! ~Bzzz~");
                        isRunning = false;
                        break;
                    case "list":
                        listOfTasks.listAllTasks();
                        break;
                    case "todo":
                        createTask(TaskClass.TODO, userInput);
                        break;
                    case "deadline":
                        createTask(TaskClass.DEADLINE, userInput);
                        break;
                    case "event":
                        createTask(TaskClass.EVENT, userInput);
                        break;
                    case "mark":
                        updateTask(TaskAction.MARK, userInput);
                        break;
                    case "unmark":
                        updateTask(TaskAction.UNMARK, userInput);
                        break;
                    case "delete":
                        updateTask(TaskAction.DELETE, userInput);
                        break;
                    default:
                        throw new BeeException("Sorry, you need to use a command!");

                }
            } catch (BeeException e) {
                System.out.println(e.toString());
            } catch (Exception e) {
                System.out.println("    _  _\n" +
                        "   | )/ )\n" +
                        "\\\\ |//,' __\n" +
                        "(\")(_)-\"()))=-\n" +
                        "   (\\\\ BZZZZZZZ!!!! Something went very wrong!!");
            }
            saveTasksToFile();
        }
        scanner.close();
    }
}