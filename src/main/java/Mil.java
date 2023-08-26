import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mil {
    private static final String INDENTATION = "     ";
    private static final String HORIZONTAL_LINE = "__________________________________________________________________________";
    private static List<Task> taskList;
    private static FileWriter tasksOutput;
    private static void printWelcomeMessage() {
        String logo = " ____     ____    _    _\n"
                + "|     \\__/    |  | |  | |\n"
                + "|  | \\ _ / |  |  | |  | |\n"
                + "|  |       |  |  | |  | |____\n"
                + "|__|       |__|  |_|  |______|\n";
        System.out.println(logo);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hi there, I'm Mil - your personal chatbot.\n     How can I help you today?");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    private static void printGoodbyeMessage() {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Have a nice day and see you again soon!");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    private static void printErrorMessage(String message) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    private static void saveTasksToFile() {
        try {
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Task task : taskList) {
                outputFile.write(task.formatToFile());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }

    }
    private static void addNewTask(Task task) {
        taskList.add(task);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
        String input;
        printWelcomeMessage();
        try {
            tasksOutput = new FileWriter("..\\src\\main\\data\\mil.txt");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        while(scanner.hasNext()) {
            input = scanner.nextLine();
            if(input.equals("bye")) {
                saveTasksToFile();
                printGoodbyeMessage();

                break;
            } else if(input.equals("list")) {
                System.out.println(INDENTATION + HORIZONTAL_LINE);
                System.out.println(INDENTATION+ "Here are the tasks in your list:");
                int i = 1;
                for (Task task : taskList) {
                    System.out.println(String.format("%s%d.%s",
                            INDENTATION, i, task.toString()));
                    i++;
                }
                System.out.println(INDENTATION + HORIZONTAL_LINE);
            } else if(input.startsWith("mark") || input.startsWith("unmark")) {
                int index;
                try {
                    if(input.split(" ").length < 2) {
                        throw new NoTaskIndexException();
                    }
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= taskList.size()) {
                        throw new InvalidTaskIndexException();
                    }
                } catch (NoTaskIndexException e) {
                    printErrorMessage(e.getMessage());
                    continue;
                } catch (InvalidTaskIndexException e) {
                    printErrorMessage(e.getMessage());
                    continue;
                }
                Task task = taskList.get(index);
                if(input.startsWith("mark")) {
                    task.markAsDone();
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                    System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                    System.out.println(INDENTATION + task);
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                } else {
                    task.markAsUndone();
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                    System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
                    System.out.println(INDENTATION  + task);
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                }
            } else if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task task;
                try {
                    if(input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                        throw new EmptyTaskException();
                    }
                } catch (EmptyTaskException e) {
                    printErrorMessage(e.getMessage());
                    continue;
                }
                if(input.startsWith("todo")) {
                    task = new Todo(input.substring(5));
                    addNewTask(task);
                } else if(input.startsWith("deadline")) {
                    try {
                        if(!input.contains("/by") || input.trim().split("/by").length == 1) {
                            throw new InvalidDeadlineException();
                        }
                    } catch (InvalidDeadlineException e) {
                        printErrorMessage(e.getMessage());
                        continue;
                    }
                    try {
                        LocalDate deadlineDate = LocalDate.parse(input.split("/")[1].substring(3).trim());
                        task = new Deadline(input.split("/")[0].substring(9),
                                deadlineDate);
                        addNewTask(task);
                    } catch (DateTimeParseException e) {
                        printErrorMessage(e.getMessage());
                    }


                } else {
                    try {
                        if(!input.contains("/from") || !input.contains("/to")) {
                            throw new MilException("No time event, please include /from and /to commands");
                        }
                    } catch (MilException e) {
                        System.out.println(INDENTATION + HORIZONTAL_LINE);
                        System.out.println(INDENTATION + "☹ Oopsie! " + e.getMessage());
                        System.out.println(INDENTATION + HORIZONTAL_LINE);
                        continue;
                    }
                    LocalDate fromDate = LocalDate.parse(input.split("/")[1].substring(5).trim());
                    LocalDate toDate = LocalDate.parse(input.split("/")[2].substring(3).trim());
                    task = new Event(input.split("/")[0].substring(6),
                            fromDate, toDate);
                    addNewTask(task);
                }


            }  else if(input.startsWith("delete")) {
                try {
                    if(input.split(" ").length < 2) {
                        throw new MilException("Empty task index");
                    }
                } catch (MilException e) {
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                    System.out.println(INDENTATION + "☹ Oopsie! You did not input the task index");
                    System.out.println(INDENTATION + HORIZONTAL_LINE);
                    continue;
                }
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                try {
                    if (index < 0 || index >= taskList.size()) {
                        throw new InvalidTaskIndexException();
                    }
                } catch (InvalidTaskIndexException e) {
                    printErrorMessage(e.getMessage());
                    continue;
                }
                Task task = taskList.get(index);
                taskList.remove(index);
                System.out.println(INDENTATION + HORIZONTAL_LINE);
                System.out.println(INDENTATION + "Noted. I've removed this task:");
                System.out.println(INDENTATION + "  " + task.toString());
                System.out.println(INDENTATION + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(INDENTATION + HORIZONTAL_LINE);
            }  else {
                System.out.println("☹ Oopsie! I'm sorry, but I don't know what that means.");
            }
        }

    }
}
