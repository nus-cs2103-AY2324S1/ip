import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Veda {

    private final static String NAME = "Veda";
    private final static Storage storage = new Storage();
    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    private static boolean addFile() {
        boolean isSuccessful = storage.addFile();

        return isSuccessful;
    }

    private static boolean loadData() {
        if (storage.checkFileExists()) {
            //File does exist
            try {
                tasks = storage.retrieveTasks();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file.");
            }
        } else {
            //File does not exist
            addFile();
        }

        return true;
    }

    private static void addTask(String taskArgs) throws NoDescriptionException {
        String type = taskArgs.split(" ")[0].toLowerCase();
        Task newTask = null;
        String description = "";
        String[] descriptions = null; //For multiple arguments

        switch(type) {
            case "todo":
                description = taskArgs.replaceFirst("todo ", "");

                if (description.toLowerCase() == type) {
                    throw new NoDescriptionException("");
                }

                newTask = new ToDo(description);
                break;

            case "deadline":
                //TODO add error handling for no "/by" keyword
                if (description.toLowerCase() == type) {
                    throw new NoDescriptionException("");
                }

                description = taskArgs.replaceFirst("deadline ", "");
                descriptions = description.split("/by");

                newTask = new Deadline(descriptions[0], descriptions[1]);
                break;

            case "event":
                if (description.toLowerCase() == type) {
                    throw new NoDescriptionException("");
                } else if (description.split("/").length < 3) {
                    throw new IncorrectInputException("");
                }

                description = taskArgs.replaceFirst("event ", "");
                descriptions = description.split("/from ");
                String from = descriptions[1].split(" /to ")[0];
                String to = description.split("/to")[1];

                newTask = new Event(descriptions[0], from, to);
                break;

            default:
                //TODO error input
                break;
        }

        if (newTask != null && tasks.add(newTask)) {
            System.out.println("added in mission:\n" + newTask);

            try {
                storage.updateData(tasks, true);
            } catch(IOException e) {
                System.out.println("Error writing to file.");
            }

        } else {
            System.out.println("System is unable to accommodate the new mission");
        }
    }

    private static void deleteTask(int taskIndex) {
        try {
            Task task = tasks.remove(taskIndex);
            storage.updateData(tasks, false);

            System.out.println("Noted. I have removed the following mission:");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            System.out.println("Unable to update file.");
        }
    }

    private static void markAsDone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (task.isDone()) {
                //Task already marked as done
                System.out.println("Mission has been completed previously.");
                return;
            }

            task.updateCompletionStatus();
            storage.updateData(tasks, false);

            System.out.println("Mission status updated! Mission completed successfully.");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            System.out.println("Unable to update file.");
        }
    }

    private static void markUndone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (!(task.isDone())) {
                //task already marked as undone
                System.out.println("Mission is already marked as undone!");
                return;
            }

            task.updateCompletionStatus();
            storage.updateData(tasks, false);

            System.out.println("Mission status updated! Mission completion status reverted.");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to update file.");
        }
    }


    public static void main(String[] args) {
        //Greet users upon initialisation
        System.out.println("____________________________________________________________");
        System.out.println(NAME + " initialised. How may I help you?");
        System.out.println("____________________________________________________________");

        Scanner inScanner = new Scanner(System.in);

        loadData();

        while (true) {
            String input = inScanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                //User wishes to exit the program
                inScanner.close();
                break;
            } else if (input.toLowerCase().equals("list")) {
                //User wishes to see his listed missions
                System.out.println("Missions:");

                tasks.forEach( task -> System.out.println(
                        (tasks.indexOf(task) + 1) + "." + task
                ));

                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("mark")) {
                //User wishes to mark task as done
                markAsDone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("unmark")) {
                //User wishes to mark task as undone
                markUndone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("delete")) {
                //User wishes to delete a task
                deleteTask(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            }

            //Add tasks
            try {
                addTask(input);
            } catch (IncorrectInputException e) {
                System.out.println(e);
            }
        }


        //Exits the program
        System.out.println("Bye. All the best for your mission!");
    }
}
