package gman;

import java.io.IOException;
import java.util.Scanner;
public class Parser {

    private static final String exitWord = "bye";
    private static Scanner scanner = new Scanner(System.in);
    private static String lastCommand = null;
    private static Task lastTaskDeleted = null;
    private static int lastIndexChanged;

    //instead of just printing it out, return a string to the GUI.
    public static String readInput(String userInput, TaskList taskList) {
        if (userInput.equals(exitWord)) {
            try {
                taskList.write();
                return Ui.goodbye();
            } catch (IOException e) {
                return Ui.showError(new GmanException("Sorry... I could not save your tasks :C"));
            }
        }

        String[] words = userInput.split(" ");

        if (userInput.equals("list")) {
            if (taskList.getSize() == 0) {
                lastCommand = "error";
                return Ui.showError(new GmanException("There's nothing to print in the list bozo..."));
            }
            lastCommand = "list";
            return Ui.listTasks(taskList); //attention
        } else if (words[0].equals("unmark")) { //can replace with case
            int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
            lastCommand = "unmark";
            lastIndexChanged = index;
            return taskList.unmark(index);

        } else if (words[0].equals("mark")) {
            int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
            lastCommand = "mark";
            lastIndexChanged = index;
            return taskList.mark(index);

        } else if (words[0].equals("todo")) {
            if (userInput.substring(4).isEmpty()) { //add if there is only space
                lastCommand = "error";
                return Ui.showError(new GmanException("OOOOOPs! The description of a todo cannot be empty!"));
            } else {
                String description = userInput.substring(4); //cut after the space
                lastCommand = "add_task";
                lastIndexChanged = taskList.getSize();
                //no need to + 1 since the getSize already + 1 (not zero indexed)
                return taskList.addTask(new Todo(description)) + " \n" + Ui.numberOfTasks(taskList);
            }

        } else if (words[0].equals("deadline")) {
            if (userInput.substring(8).isEmpty()) { //add if there is only space
                lastCommand = "error";
                return Ui.showError(new GmanException("OOOOOPs! The description of a deadline cannot be empty!"));
            } else {
                String segments[] = userInput.substring(8).split("/by ");
                String description = segments[0];
                String by = segments[1];
                lastCommand = "add_task";
                lastIndexChanged = taskList.getSize();
                return taskList.addTask(new Deadline(description, by)) + "\n" + Ui.numberOfTasks(taskList);
            }

        } else if (words[0].equals("event")) {
            if (userInput.substring(5).isEmpty()) { //add if there is only space
                lastCommand = "error";
                return Ui.showError(new GmanException("OOOOOPs! The description of an event cannot be empty!"));
            }
            else {
                String segments[] = userInput.substring(5).split("/");
                String description = segments[0];
                String from = segments[1].substring(5); //cut aft space, below too
                String to = segments[2].substring(3);
                lastCommand = "add_task";
                lastIndexChanged = taskList.getSize();
                return taskList.addTask(new Event(description, from, to)) + "\n" + Ui.numberOfTasks(taskList);
            }

        } else if (words[0].equals("delete")) {
                String segments[] = userInput.split(" ");
                int indexToDelete = Integer.valueOf(segments[1]) - 1;
                lastTaskDeleted = taskList.getTask(indexToDelete);
                lastCommand = "delete";
                return taskList.deleteTask(indexToDelete) + "\n" + Ui.numberOfTasks(taskList);

        } else if (words[0].equals("find")) { //add exception if more than one searched word, i.e space, e.g hi hi
            String keyword = words[1];
            return taskList.findTasks(keyword);

        } else if (words[0].equals("undo")) {
            if (!isValidUndo()) {
                return Ui.showError(new GmanException("OOPS! Your last command is not undo-able!"));
                //this never shows, debug in the future.
            }
            switch (lastCommand) {
                case "delete":
                    return undoDeleteTask(taskList);
                case "add_task":
                    return undoAddTask(taskList, lastIndexChanged);
                case "mark":
                    return undoMark(taskList, lastIndexChanged);
                case "unmark":
                    return undoUnmark(taskList, lastIndexChanged);
            }
        }

        return Ui.showError(new GmanException("OOPS! I'm sorry, I don't know what that means! Please start " +
                "with " +
                "keywords: todo, deadline, or event!"));

    }

    public static String undoDeleteTask(TaskList taskList) {
        //need to add ui
        return "Undo successful! \n" + taskList.addTask(lastTaskDeleted) + "\n" + Ui.numberOfTasks(taskList);
    }

    public static String undoAddTask(TaskList taskList, int indexToDelete) {
        return "Undo successful! \n" + taskList.deleteTask(indexToDelete) + "\n" + Ui.numberOfTasks(taskList);
    }

    public static String undoMark(TaskList taskList, int indexToChange) {
        return "Undo successful! \n" + taskList.unmark(indexToChange);
    }

    public static String undoUnmark(TaskList taskList, int indexToChange) {
        return "Undo successful! \n" + taskList.mark(indexToChange);
    }


    public static boolean isValidUndo() {
        if (lastCommand.equals("error") || lastCommand.isEmpty()) {
            return false;
        }
        if (lastCommand.equals("delete") || lastCommand.equals("add_task") || lastCommand.equals("mark")
                || lastCommand.equals("unmark")) {
            return true;
        } else {
            return false;
        }
    }

}


