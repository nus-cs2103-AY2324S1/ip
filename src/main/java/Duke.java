import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    //private Storage storage;
    //private TaskList tasks;
    //private Ui ui;



    public static void main(String[] args) {
        String name = "Johnnythesnake";
        System.out.println("Hello I'm " + name + "\n" + "What can I do for you? Aside from completing your CS2103 project for you");
        Scanner scanner = new Scanner(System.in);
        String filename = "tasks.txt";
        // Create a File object with the filename
        File file = new File(filename);

        TaskList tasks = new TaskList();
        if (file.exists()) {
            tasks = TaskReader.readTasksFromFile(filename);
            System.out.println(tasks);
        }
        while (true) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) { // bye exits the code
                Exit exit = new Exit();
                System.out.println(exit.exitMessage());
                break;
            } else if (command.equalsIgnoreCase("list")) { //list shows the task list
                tasks.listOfTasks();
            } else if (command.startsWith("unmark")) { // unmark the task in question
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber < tasks.size()) {
                    tasks.unmarkTask(taskNumber);;
                }
            } else if (command.startsWith("mark")) { // mark the task in question
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                if (taskNumber < tasks.size()) {
                    tasks.markTaskAsDone(taskNumber);
                }


            } else if (command.startsWith("todo")) {
                String description = command.substring(4).trim(); // Trim any leading/trailing spaces

                try {
                    if (description.isEmpty()) {
                        throw new EmptyTodoException();
                    }

                    Todo todo = new Todo(description, false);
                    tasks.addTask(todo);

                } catch (EmptyTodoException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("deadline")) {
                // Split the input
                String descriptionDeadline = command.substring(8).trim(); // Remove "deadline" and leading spaces

                if (descriptionDeadline.isEmpty()) {
                    try {
                        throw new EmptyDeadlineException();
                    } catch (EmptyDeadlineException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Find the index of the deadline separator "/"
                    int separatorIndex = descriptionDeadline.indexOf('/');

                    if (separatorIndex != -1) { // Ensure the separator exists in the input
                        // Extract the task description and deadline

                        String description = descriptionDeadline.substring(0, separatorIndex).trim();
                        String deadline = descriptionDeadline.substring(separatorIndex + 4).trim();
                        String pattern = "\\d{4}/\\d{2}/\\d{2}";
                        Pattern datePattern = Pattern.compile(pattern);
                        Matcher matcher = datePattern.matcher(deadline);
                        if (matcher.find()) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDate localDateDeadline = LocalDate.parse(deadline, formatter);
                            Deadline deadlineTask = new Deadline(description,false, localDateDeadline);
                            tasks.addTask(deadlineTask);

                        } else {
                            System.out.println("Please input your deadline in YYYY/MM/DD format");
                        }
                    } else {
                        System.out.println("Invalid input format for deadline. Please input in the following format: <deadline> <description> /by <YYYY/MM/DD> ");
                    }
                }
            } else if (command.startsWith("event")) {
                // split the input
                String descriptionStartEndTime = command.substring(5).trim(); // Remove "event" and leading spaces
                if (descriptionStartEndTime.isEmpty()) {
                    try {
                        throw new EmptyEventException();
                    } catch (EmptyEventException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Find the indices of the time separators
                    int fromIndex = descriptionStartEndTime.indexOf("/from");
                    int toIndex = descriptionStartEndTime.indexOf("/to");

                    if (fromIndex != -1 && toIndex != -1) {
                        // Extract the task description, startTime, and endTime
                        String description = descriptionStartEndTime.substring(0, fromIndex).trim();
                        String startTime = descriptionStartEndTime.substring(fromIndex + 5, toIndex).trim();
                        String endTime = descriptionStartEndTime.substring(toIndex + 3).trim();

                        // Create a new Event object
                        Event eventTask = new Event(description, false, startTime, endTime);
                        tasks.addTask(eventTask);

                    } else {
                        System.out.println("Invalid input format for event command.");
                    }
                }
            } else if (command.startsWith("delete")) {
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber < tasks.size()) {
                    tasks.deleteTask(taskNumber);

                }


            } else {
                try {
                    throw new UnknownInputException();
                } catch (UnknownInputException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        TaskWriter.writeTasksToFile(tasks, "tasks.txt");
    }
}

