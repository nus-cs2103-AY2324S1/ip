package duke;

import java.io.IOException;
import java.util.Scanner;
public class Parser {

    private static final String exitWord = "bye";
    private static Scanner scanner = new Scanner(System.in);
    public static void readInput(String userInput, TaskList taskList) throws GmanException {
        while(!userInput.equals(exitWord)) {
            String[] words = userInput.split(" ");
            if (userInput.equals("list")) {
                try {
                    if (taskList.getSize() == 0) {
                        throw new GmanException("There's nothing to print in the list bozo...");
                    }
                    Ui.listTasks(taskList); //attention
                } catch (GmanException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("unmark")) { //replace with case
                int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
                taskList.unmark(index);
            } else if (words[0].equals("mark")) {
                int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
                taskList.mark(index);
            } else if (words[0].equals("todo")) {
                try {
                    if (userInput.substring(4).isEmpty()) { //add if there is only space
                        throw new GmanException("OOOOOPs! The description of a todo cannot be empty!");
                    }
                    String description = userInput.substring(4); //cut after the space
                    taskList.addTask(new Todo(description));
                    Ui.numberOfTasks(taskList);
                } catch (GmanException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("deadline")) {
                try {
                    if (userInput.substring(8).isEmpty()) { //add if there is only space
                        throw new GmanException("OOOOOPs! The description of a deadline cannot be empty!");
                    }
                    String segments[] = userInput.substring(8).split("/by ");
                    String description = segments[0];
                    String by = segments[1];
                    taskList.addTask(new Deadline(description, by));
                    Ui.numberOfTasks(taskList);
                } catch (GmanException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("event")) {
                try {
                    if (userInput.substring(5).isEmpty()) { //add if there is only space
                        throw new GmanException("OOOOOPs! The description of an event cannot be empty!");
                    }
                    String segments[] = userInput.substring(5).split("/");
                    String description = segments[0];
                    String from = segments[1].substring(5); //cut aft space, below too
                    String to = segments[2].substring(3);
                    taskList.addTask(new Event(description, from, to));
                    Ui.numberOfTasks(taskList);
                } catch (GmanException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("delete")) {
                try {
                    String segments[] = userInput.split(" ");
                    int indexToDelete = Integer.valueOf(segments[1]) - 1;
                    taskList.deleteTask(indexToDelete);
                    Ui.numberOfTasks(taskList);
                } catch (GmanException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new GmanException("OOPS! I'm sorry, I don't know what that means! Please start " +
                        "with keywords: todo, deadline, or event!");
            }
            userInput = scanner.nextLine();
        }
        try {
            taskList.write();
            Ui.goodbye();
        } catch (IOException e) {
            System.out.println("Sorry... I could not save your tasks :C");
        }
    }
}
