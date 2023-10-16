package ax.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingFormatArgumentException;

import ax.display.Ui;
import ax.task.Deadlines;
import ax.task.Events;
import ax.task.ListItem;
import ax.task.TaskList;
import ax.task.Todos;

/**
 * The Parser class for handling user actions
 */
public class Parser {

    /**
     * Handles input from the user and performs the corresponding action.
     * If the input is "list", displays the current to-do list.
     * If the input is "todo", adds a todo with specified text.
     * If the input is "deadline", adds a deadline with specified text and /by for date.
     * If the input is "event", adds an event with specified text and /from date and /to date.
     * If the input is "delete", prompts the user for the index of the item to delete from the list.
     * If the input is "mark", sets the item at specified index to true
     * If the input is "unmark", sets the item at specified index to false
     * If the input is anything else, throws a NoSuchMethodException.
     * If the input is missing an argument, throws a MissingFormatArgumentException.
     *
     * @param input the string to use for input
     * @return String for the output
     */
    @SuppressWarnings("unused")
    public static String getInputString(String input) {
        try {
            String[] inputs = input.split(" ", 2);
            String[] dates = input.split("/(by|from|to) ");
            return handleArguments(input, inputs, dates);
        } catch (MissingFormatArgumentException e) {
            return handleMissingFormatArgumentException();
        } catch (NoSuchMethodException e) {
            return handleNoSuchMethodException();
        } catch (Exception e) {
            return handleGenericException(e);
        }
    }

    private static String handleArguments(String input, String[] inputs, String[] dates)
            throws IOException, NoSuchMethodException {
        if (input.equals("bye")) { // check if it is bye, then return true, so it will exit the loop
            // delete existing file
            return writeSaveFile();
        } else if (input.equals("list")) {
            // call the list function
            return Ui.listTheListString();
        } else if (input.startsWith("mark")) {
            return markItem(inputs);
        } else if (input.startsWith("unmark")) {
            return unmarkItem(inputs);
        } else if (input.startsWith("todo")) {
            createTodo(inputs);
            return "SUCCESS";
        } else if (input.startsWith("deadline")) {
            createDeadline(inputs, dates);
            return "SUCCESS";
        } else if (input.startsWith("event")) {
            createEvent(inputs, dates);
            return "SUCCESS";
        } else if (input.startsWith("delete")) {
            return deleteItem(inputs);
        } else if (input.startsWith("find")) {
            return Ui.listTheListString(inputs[1]);
        } else if (input.startsWith("reminders")) {
            return Ui.listDueItems();
        } else {
            throw new NoSuchMethodException("no method");
        }
    }

    private static String handleGenericException(Exception e) {
        System.out.println("I DONT KNOW WHATS HAPPENING!!! SAVE ME ");
        System.out.println("But seriously, its this ");
        System.out.print("error = " + e);
        return "I DONT KNOW WHATS HAPPENING!!! SAVE ME "
                + "But seriously, its this "
                + "error = " + e;
    }

    private static String handleNoSuchMethodException() {
        System.out.println(
                "Oh nos what is this foreign language, I haven't learnt that yet "
        );
        return "Oh nos what is this foreign language, I haven't learnt that yet ";
    }

    private static String handleMissingFormatArgumentException() {
        System.out.println(
                "You're missing some arguments I'm smart but I can't read minds!"
        );
        return "You're missing some arguments I'm smart but I can't read minds!";
    }

    private static String deleteItem(String[] inputs) {
        if (inputs.length > 1) {
            TaskList.getListItems().remove(Integer.parseInt(inputs[1]) - 1);
            return "Successfully deleted";
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static void createEvent(String[] inputs, String[] dates) {
        if (inputs.length > 1 && dates.length > 2) {
            TaskList.getListItems().add((
                    new Events(inputs[1].split("/")[0], dates[1], dates[2]))
            );
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static void createDeadline(String[] inputs, String[] dates) {
        if (inputs.length > 1 && dates.length > 1) {
            TaskList.getListItems().add((new Deadlines(inputs[1].split("/")[0], dates[1])));
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static void createTodo(String[] inputs) {
        if (inputs.length > 1) {
            TaskList.getListItems().add(new Todos(inputs[1]));
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static String unmarkItem(String[] inputs) {
        if (inputs.length > 1) {
            ListItem task = TaskList.getListItems().get(Integer.parseInt(inputs[1]) - 1);
            task.setDone(false);
            return "Unmarked" + task;
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static String markItem(String[] inputs) {
        if (inputs.length > 1) {
            ListItem task = TaskList.getListItems().get(Integer.parseInt(inputs[1]) - 1);
            task.setDone(true);
            return "Marked" + task;
        } else {
            throw new MissingFormatArgumentException("no arg");
        }
    }

    private static String writeSaveFile() throws IOException {
        Path saveFile = Paths.get("data/save.txt");
        Path dataDir = Paths.get("data");
        try {
            Files.createDirectory(dataDir);
            Files.createFile(saveFile);
        } finally {
            System.out.println(saveFile.toAbsolutePath());
            if (saveFile.toFile().exists()) {
                Files.delete(saveFile);
            }
            // write new file
            FileWriter fileWriter = new FileWriter(saveFile.toFile());
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (ListItem listItem : TaskList.getListItems()) {
                System.out.println(listItem);
                printWriter.println(listItem);
            }
            printWriter.close();

            return "save file written";
        }

    }
}
