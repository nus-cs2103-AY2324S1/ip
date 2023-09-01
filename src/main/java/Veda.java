import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Veda {

    private final static String NAME = "Veda";
    private final static Storage storage = new Storage();
    private final static TaskList tasks = new TaskList();

    private static void addTask(String taskArgs) throws NoDescriptionException, DateTimeParseException {
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
                //Expected CL input: deadline <Description> /by <Due date in dd/MM/yyyy HHmm>
                //TODO add error handling for no "/by" keyword
                if (description.toLowerCase() == type) {
                    throw new NoDescriptionException("Please input the right order: deadline <Description> /by <due date>");
                }

                description = taskArgs.replaceFirst("deadline ", "");
                descriptions = description.split("/by ");

                if (descriptions.length < 2) {
                    throw new IncorrectInputException("Please input the right order: deadline <Description> /by <due date>");
                }

                newTask = new Deadline(descriptions[0], descriptions[1]);
                break;

            case "event":
                //Expected CL input: event <Description> /from <start> /to <end>
                if (description.toLowerCase() == type) {
                    throw new NoDescriptionException("");
                }

                description = taskArgs.replaceFirst("event ", ""); //Remove type

                descriptions = description.split("/from "); //Split remaining args into description + (from and to)
                String from = descriptions[1].split(" /to ")[0]; //Split (from and to) into from and to
                String to = descriptions[1].split(" /to ")[1];

                newTask = new Event(descriptions[0], from, to);
                break;

            default:
                //TODO error input
                break;
        }

        //Trf to tasklist
        tasks.addTask(newTask);
    }

    public static void main(String[] args) {
        //Greet users upon initialisation
        System.out.println("____________________________________________________________");
        System.out.println(NAME + " initialised. How may I help you?");
        System.out.println("____________________________________________________________");

        Scanner inScanner = new Scanner(System.in);

        tasks.load();

        while (true) {
            String input = inScanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                //User wishes to exit the program
                inScanner.close();
                break;
            } else if (input.toLowerCase().equals("list")) {
                //User wishes to see his listed missions
                tasks.printList();

                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("mark")) {
                //User wishes to mark task as done
                tasks.markAsDone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("unmark")) {
                //User wishes to mark task as undone
                tasks.markUndone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("delete")) {
                //User wishes to delete a task
                tasks.deleteTask(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            }

            //Add tasks
            try {
                addTask(input);
            } catch (IncorrectInputException e) {
                System.out.println(e);
            } catch (DateTimeParseException e) {
                System.out.println("Ensure your deadline is of the format {dd/MM/yyyy HHmm}");
            }
        }


        //Exits the program
        System.out.println("Bye. All the best for your mission!");
    }
}
