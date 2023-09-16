package duke;
import dukeuielements.Ui;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * This class deals with making sense of the user input and carrying out tasks accordingly.
 */
public class Parser {
    public enum TaskKeyVal {ToDo, Deadline, Event, Delete, mark, unmark, bye, list, find};

    /**
     * Returns a boolean based on the user input choice.
     * bye -> initiates user exit.
     * list -> lists all the tasks.
     * mark -> marks the task.
     * unmark -> unmarks the task.
     * ToDo -> creates a new ToDo and adds to task list.
     * Deadline -> creates a new Deadline and adds to task list.
     * Event -> creates a new Event and adds to task list.
     * Delete -> Deletes task at given number.
     *
     * @param userInput String value provided to be made sense of and carry task.
     * @return boolean value false to break out of program, true otherwise.
     * @throws DukeException when incorrect / invalid input is entered.
     */
    public static String parse(String userInput) throws DukeException {
        String[] userInputList = userInput.split(" ", 2);
        assert userInputList != null;
        String userTaskChoiceKey = userInputList[0];
        //Stores enum value. might throw exception if invalid input entered.
        TaskKeyVal taskKeyVal = TaskKeyVal.valueOf(userTaskChoiceKey);

        switch (taskKeyVal) {
        case bye:
            return userExit();
        case list:
            return TaskList.userListChoice();
        case mark:
        case unmark:
            String userMarkerChoice = userInputList[1];
            return TaskList.userMarkUnmark(userMarkerChoice, userTaskChoiceKey);
        case ToDo:
            return TaskList.addToDo(userInputList[1]);
        case Deadline:
            String[] deadlineList = userInputList[1].split("/", 2);
            return TaskList.addDeadline(deadlineList[0], deadlineList[1]);
        case Event:
            String[] eventList = userInputList[1].split("/", 3);
            return TaskList.addEvent(eventList[0], eventList[1], eventList[2]);
        case Delete:
            Integer delUserChoice = Integer.parseInt(userInputList[1]);
            return TaskList.deleteTask(delUserChoice);
        case find:
            String findThis = userInputList[1];
            return TaskList.taskToBeFound(findThis);

        default:
            if (userInputList.length == 1 && enumCheck(userTaskChoiceKey)) {
                throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
            } else { //in case wrong input like Delete abc entered
                throw new DukeException("OOPS!!! Sorry, but i do not know what that means :-(");
            }
        }
    }

    private static boolean enumCheck(String input) {
        for (TaskKeyVal taskKey : TaskKeyVal.values()) {
            if (taskKey.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
    private static String userExit() {
        try {
            Files.write(Duke.pathOfDirectory, new byte[0], StandardOpenOption.TRUNCATE_EXISTING); //closes file and truncates it
            for (int i = 0; i < TaskList.getTaskSize(); i++) {
                String taskToString = TaskList.getStoreTask().get(i).storeToDiskFormat() + "\n";
                Files.write(Duke.pathOfDirectory, taskToString.getBytes(), StandardOpenOption.APPEND);
            }
            return Ui.endDukeMsg();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred...";
        }
    }
}
