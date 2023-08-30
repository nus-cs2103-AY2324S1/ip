import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CommandFactory class for handling various task-related commands.
 */
public class CommandFactory {

    /**
     * Enum to represent different types of commands.
     */
    enum Command {
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        CLEAR
    }


    public static ArrayList<Task> readFromDB() {
        try {
            ArrayList<Task> tasklists = new ArrayList<>();
            File f = new File("./data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskParts = s.nextLine().split("\\|");
                System.out.println(taskParts.length);
                Task newTask = null;
                if (taskParts.length == 3) {
                    // its task
                    newTask = new ToDo(taskParts[2].trim());
                } else if (taskParts.length == 4) {
                    // deadline
                    newTask = new Deadline(taskParts[2].trim(),
                            taskParts[3].trim());
                } else if (taskParts.length == 5) {
                    // event
                    newTask = new Event(taskParts[2].trim(),
                            taskParts[3].trim(),
                            taskParts[4].trim());

                }
                if (taskParts[1].trim().contains("1")) {
                    newTask.markAsDone();
                } else {
                    newTask.markAsNotDone();
                }
                tasklists.add(newTask);
            }
            return tasklists;
        }
//        catch (FileNotFoundException e) {
//            try {
//                FileWriter fw = new FileWriter("./data/duke.txt");
//            } catch (Exception error) {
//                System.out.println("Error");
//            }
//        }
        catch (Exception error) {
            System.out.println("the file is corrupted, deleting the content");
            return new ArrayList<>();
        }
    }

    public static void writeToDB(ArrayList<Task> taskList) {
        try {
            Path path = Paths.get("./data/duke.txt");
            String content = "";
            for (Task newTask : taskList) {
                content += newTask.getDBString();
                content += "\n";
                System.out.println(content);
            }
            Files.write(path, content.getBytes());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Handles the actions of different commands, like adding tasks, deleting them, or marking them as done.
     */
    public static void CommandActions() {
        Scanner scanner = new Scanner(System.in);
        // read from duke.txt into classes
        ArrayList<Task> tasklists = readFromDB();;
        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");
            String[] parts = input.split(" ");
            String[] details = input.split("/");
            Command command = null;

            try{
                try {
                    command = Command.valueOf(parts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidInputException();
                }

                if (command != null) {
                    Task newTask = null;
                    Task oldtask = null;
                    int number = 0;
                    if (command == Command.MARK
                            || command == Command.UNMARK
                            || command == Command.DELETE){
                        try {
                            number = Integer.parseInt(parts[1]);
                            oldtask = tasklists.get(number-1);
                        } catch (Exception e) {
                            throw new InvalidListNumberException();
                        }
                    }
                    switch (command) {
                        case BYE:
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        case DELETE:
                            // update duke.txt
                            oldtask.deleteTask();
                            tasklists.remove(number-1);
                            break;
                        case LIST:
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 1; i < tasklists.size()+1; i++) {
                                Task taskToPrint = tasklists.get(i - 1);
                                System.out.println(i + ". " + taskToPrint.getStatusAndDescription());
                            }
                            break;

                        case UNMARK:
                            // update duke.txt
                            oldtask.markAsNotDone();
                            break;

                        case MARK:
                            // update duke.txt
                            oldtask.markAsDone();
                            break;

                        case TODO:
                            // update duke.txt
                            if (details[0].trim().length() == 4) {
                                throw new InvalidToDoException();
                            } else {
                                newTask = new ToDo(details[0].substring(5));
                                tasklists.add(newTask);
                            }
                            break;

                        case DEADLINE:
                            // update duke.txt
                            if (details[0].trim().length() == 8) {
                                throw new InvalidDeadlineException();
                            } else {
                                newTask = new Deadline(details[0].split("/")[0].substring(9),
                                        input.split("by")[1].trim());
                                tasklists.add(newTask);
                            }
                            break;

                        case EVENT:
                            // update duke.txt
                            if (details[0].trim().length() == 5) {
                                throw new InvalidEventException();
                            } else {
                                newTask = new Event(details[0].split("/")[0].substring(6),
                                        input.split("from")[1].split("/to")[0].trim(),
                                        input.split("to")[1].trim());
                                tasklists.add(newTask);
                            }
                            break;
                        case CLEAR:

                            tasklists.clear();
                            Task.clear();
                            break;

                        default:
                            break;
                    }
                }
                if (command == Command.BYE) {
                    writeToDB(tasklists);
                    break;
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            System.out.println("____________________________________________________________");

        }

        scanner.close();
    }
}
