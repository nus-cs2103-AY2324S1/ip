import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class JamesBond {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static void main(String[] args) throws EmptyDescException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        TaskList taskList = new TaskList();
<<<<<<< .merge_file_PlWZJ2

        // load prvs data
        Storage storage = new Storage("/Users/jamesbond/ip/src/main/data/jamesbond.txt");
        taskList = storage.loadTasksFromFile();

        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(logo);
=======
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
>>>>>>> .merge_file_whwwFW
        try {
            while (sc.hasNextLine()) {
                String firstWord = sc.next();
                if (firstWord.equalsIgnoreCase("mark")) {
                    if (sc.hasNextInt()) {
                        int taskNumber = sc.nextInt();
                        taskList.markTask(taskNumber);
                    } else {
                        System.out.println("Invalid Input, re-enter Mark followed by task number");
                        sc.nextLine();
                    }
                } else if (firstWord.equalsIgnoreCase("unmark")) {
                    int taskNumber = sc.nextInt();
                    taskList.unMarkTask(taskNumber);
                }
                else if (firstWord.equalsIgnoreCase("delete")) {
                    int taskNumber = sc.nextInt();
                    taskList.deleteTask(taskNumber);
                }
                else {
                    String input = sc.nextLine().trim();
                    if (firstWord.equalsIgnoreCase("todo")) {
                        taskList.addToDo(input);
                        storage.saveTasksToFile(taskList);
                    } else if (firstWord.equalsIgnoreCase("deadline")) {
                        int byIndex = input.indexOf("/by");
                        if (byIndex != -1) {
                            String taskDescription = input.substring(0, byIndex).trim();
                            LocalDateTime dueDate = LocalDateTime.parse(input.substring(byIndex + 4).trim(), formatter);

                            taskList.addDead(taskDescription, dueDate);
                        } else {
                            throw new IllegalArgumentException("Deadline not formatted correctly, type again in the format /by (deadline)");
                        }
                    } else if (firstWord.equalsIgnoreCase("event")) {
                        int fromIndex = input.indexOf("/from");
                        int toIndex = input.indexOf("/to");
                        if (fromIndex != -1 && toIndex != -1) {
                            String taskDescription = input.substring(0, fromIndex).trim();
                            System.out.println(taskDescription);
                            LocalDateTime startTime = LocalDateTime.parse(input.substring(fromIndex + 6, toIndex).trim(), formatter);
                            LocalDateTime endTime = LocalDateTime.parse(input.substring(toIndex + 4).trim(), formatter);
                            taskList.addEvent(taskDescription, startTime, endTime);
                        } else {
                            throw new IllegalArgumentException("Event format incorrect, type again in the format /from (timing) /to (timing)");
                        }
                    } else if (firstWord.equalsIgnoreCase("bye")) {
                        System.out.println("Bye. Till the next time.");
                        break;
                    } else if (firstWord.equalsIgnoreCase("list")) {
                        taskList.listOut();
                    } else {
                        throw new EmptyDescException("please indicate task type: Todo, deadline or event");
                    }
                }

            }
            // save data

        } catch (EmptyDescException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid task number: " + e.getMessage());
        }
    }
}
