package duke;

import java.io.IOException;
import java.util.Scanner;
public class Parser {

    private static final String exitWord = "bye";
    private static Scanner scanner = new Scanner(System.in);

    //instead of just printing it out, return a string to the GUI.
    public static String readInput(String userInput, TaskList taskList) {
        if(!userInput.equals(exitWord)) {
            String[] words = userInput.split(" ");
            if (userInput.equals("list")) {
                if (taskList.getSize() == 0) {
                    Ui.showError(new GmanException("There's nothing to print in the list bozo..."));
                }
                return Ui.listTasks(taskList); //attention
            } else if (words[0].equals("unmark")) { //can replace with case
                int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
                return taskList.unmark(index);

            } else if (words[0].equals("mark")) {
                int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
                return taskList.mark(index);

            } else if (words[0].equals("todo")) {
                if (userInput.substring(4).isEmpty()) { //add if there is only space
                    return Ui.showError(new GmanException("OOOOOPs! The description of a todo cannot be empty!"));
                } else {
                    String description = userInput.substring(4); //cut after the space
                    return taskList.addTask(new Todo(description)) + " \n" + Ui.numberOfTasks(taskList);
                }

            } else if (words[0].equals("deadline")) {
                if (userInput.substring(8).isEmpty()) { //add if there is only space
                    return Ui.showError(new GmanException("OOOOOPs! The description of a deadline cannot be empty!"));
                } else {
                    String segments[] = userInput.substring(8).split("/by ");
                    String description = segments[0];
                    String by = segments[1];
                    return taskList.addTask(new Deadline(description, by)) + "\n" + Ui.numberOfTasks(taskList);
                }

            } else if (words[0].equals("event")) {
                if (userInput.substring(5).isEmpty()) { //add if there is only space
                    return Ui.showError(new GmanException("OOOOOPs! The description of an event cannot be empty!"));
                }
                else {
                    String segments[] = userInput.substring(5).split("/");
                    String description = segments[0];
                    String from = segments[1].substring(5); //cut aft space, below too
                    String to = segments[2].substring(3);
                    return taskList.addTask(new Event(description, from, to)) + "\n" + Ui.numberOfTasks(taskList);
                }


            } else if (words[0].equals("delete")) {

                    String segments[] = userInput.split(" ");
                    int indexToDelete = Integer.valueOf(segments[1]) - 1;
                    return taskList.deleteTask(indexToDelete) + "\n" + Ui.numberOfTasks(taskList);

            } else if (words[0].equals("find")) { //add exception if more than one searched word, i.e space, e.g hi hi
                String keyword = words[1];
                return taskList.findTasks(keyword);
            }
            else {
                return Ui.showError(new GmanException("OOPS! I'm sorry, I don't know what that means! Please start " +
                        "with " +
                        "keywords: todo, deadline, or event!"));
            }
        } else {
            try {
                taskList.write();
                return Ui.goodbye();
            } catch (IOException e) {
                return Ui.showError(new GmanException("Sorry... I could not save your tasks :C"));
            }
        }

    }
}
