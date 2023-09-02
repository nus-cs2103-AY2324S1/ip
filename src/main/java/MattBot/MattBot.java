import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class MattBot {
    private static final String NAME = "MattBot";
    private static Storage mattmory;
    private static TaskList tasks;
    private static DateTimeFormatter dTFormat  = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    public static void main(String[] args) {
        // Load save file
        // List<Task> taskList = new ArrayList<Task>();
        try {
            mattmory = new Storage();
            tasks = mattmory.load();
        } catch (IOException e) {
            System.out.println(e);
            tasks = new TaskList();
        }

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String userInput;
        label:
        while (true) {
            // Take user input in, and process user input
            userInput = sc.nextLine();
            String command = userInput.split(" ",2)[0];
            switch (command) {
                case "bye":
                    printTop();
                    System.out.println("Bye, Hope to see you soon!");
                    printBottom();
                    break label;
                case "list":
                    printTop();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.getTask(i + 1);
                        System.out.println(String.format("%d. %s", i + 1, t));
                    }
                    printBottom();
                    break;
                case "mark": {
                    printTop();
                    int taskId = Integer.parseInt(userInput.split(" ", 2)[1]);
                    tasks.markTask(taskId);
                    mattmory.writeBack(tasks);
                    Task t = tasks.getTask(taskId);
                    System.out.println("Great job! You have completed the task " + t.showName());
                    printBottom();
                    break;
                }
                case "unmark": {
                    printTop();
                    int taskId = Integer.parseInt(userInput.split(" ", 2)[1]);
                    tasks.unmarkTask(taskId);
                    mattmory.writeBack(tasks);
                    Task t = tasks.getTask(taskId);
                    System.out.println("Oh no, you have uncompleted " + t.showName());
                    printBottom();
                    break;
                }
                default: {
                    int len = userInput.split(" ", 2).length;
                    if (len <= 1) {
                        printTop();
                        System.out.println("Oh no, your input is bad.");
                        System.out.println(command + " requires an argument.");
                        printBottom();
                        continue;
                    }
                    printTop();
                    String arguments = userInput.split(" ", 2)[1];
                    Task t;
                    if (command.equals("todo")) {
                        t = new Todo(arguments);
                        tasks.addTask(t);
                        mattmory.writeBack(tasks);
                        System.out.println("I've added this to your tasks: ");
                        System.out.println(t);
                    } else if (command.equals("deadline")) {
                        String name = arguments.split(" /by ", 2)[0];
                        String dueDate = arguments.split(" /by ", 2)[1];
                        try {
                            LocalDateTime dtDueDate = LocalDateTime.parse(dueDate, dTFormat);
                            t = new Deadline(name, dtDueDate);
                            tasks.addTask(t);
                            mattmory.writeBack(tasks);
                            System.out.println("I've added this to your tasks: ");
                            System.out.println(t);
                        } catch (DateTimeParseException e) {
                            System.out.println("Your date is invalid. It should be in the form YYYYMMDDTHHMM. An example is 20231231T2359.");
                        }
                    } else if (command.equals("event")) {
                        String name = arguments.split(" /from ", 2)[0];
                        String dates = arguments.split(" /from ", 2)[1];
                        String startDate = dates.split(" /to ")[0];
                        String endDate = dates.split(" /to ", 2)[1];
                        try {
                            LocalDateTime dtStartDate = LocalDateTime.parse(startDate, dTFormat);
                            LocalDateTime dtEndDate = LocalDateTime.parse(endDate, dTFormat);
                            t = new Event(name, dtStartDate, dtEndDate);
                            tasks.addTask(t);
                            mattmory.writeBack(tasks);
                            System.out.println("I've added this to your tasks: ");
                            System.out.println(t);
                        } catch (DateTimeParseException e) {
                            System.out.println("Your date is invalid. It should be in the form YYYYMMDDTHHMM. An example is 20231231T2359.");
                        }
                    } else if (command.equals("delete")) {
                        if (tasks.size() == 0 || tasks.size() < Integer.parseInt(arguments)) {
                            System.out.println("Oops, you're deleting a task that doesn't exist.");
                            continue;
                        }
                        t = tasks.getTask(Integer.parseInt(arguments));
                        System.out.println("I have removed this task:");
                        System.out.println(t);
                        tasks.removeTask(Integer.parseInt(arguments));
                        mattmory.writeBack(tasks);
                    } else {
                        System.out.println("I didn't quite understand your input.");
                        continue;
                    }
                    printBottom();
                    break;
                }
            }
        }
    }
    public static void printTop() {
        System.out.println("____________________________________________________________");
    }
    public static void printBottom() {
        System.out.println("____________________________________________________________");
    }
}
