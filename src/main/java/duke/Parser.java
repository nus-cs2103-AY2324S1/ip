package duke;
import dukeUiElements.Ui;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * This class deals with making sense of the user input and carrying out tasks accordingly.
 */
public class Parser {
    public enum TaskKeyVal {ToDo, Deadline, Event, Delete, mark, unmark, bye, list};

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
    public static boolean parse(String userInput) throws DukeException {
        String[] userInputList = userInput.split(" ", 2);
        String userTaskChoiceKey = userInputList[0];
        //Stores enum value. might throw exception if invalid input entered.
        TaskKeyVal taskKeyVal = TaskKeyVal.valueOf(userTaskChoiceKey);

        if (taskKeyVal == TaskKeyVal.bye) {
            userExit();
            return false;
        } else if (taskKeyVal == TaskKeyVal.list) {
            TaskList.userListChoice();
            return true;
        } else if (taskKeyVal == TaskKeyVal.mark || taskKeyVal == TaskKeyVal.unmark) {
            String userMarkerChoice = userInputList[1];
            TaskList.userMarkUnmark(userMarkerChoice, userTaskChoiceKey);
            return true;
        } else if (userInputList.length == 1 && enumCheck(userTaskChoiceKey)) {
            throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
        } else if (taskKeyVal == TaskKeyVal.ToDo) {
            TaskList.addToDo(userInputList[1]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Deadline) {
            String[] deadlineList = userInputList[1].split("/", 2);
            TaskList.addDeadline(deadlineList[0], deadlineList[1]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Event) {
            String[] eventList = userInputList[1].split("/", 3);
            TaskList.addEvent(eventList[0], eventList[1], eventList[2]);
            return true;
        } else if (taskKeyVal == TaskKeyVal.Delete) {
            Integer delUserChoice = Integer.parseInt(userInputList[1]);
            TaskList.deleteTask(delUserChoice);
            return true;
        } else {
            throw new DukeException("☹ OOPS!!! Sorry, but i do not know what that means :-(");
        }
    }

    private static boolean enumCheck (String input){
        for (TaskKeyVal taskKey : TaskKeyVal.values()) {
            if (taskKey.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
    private static void userExit() {
        try {
            Files.write(Duke.pathOfDirectory, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);    //closes file and truncates it
            for (int i = 0; i < TaskList.storeTask.size(); i++) {
                String taskToString = TaskList.storeTask.get(i).storeToDiskFormat() + "\n";
                Files.write(Duke.pathOfDirectory, taskToString.getBytes(), StandardOpenOption.APPEND);
            }
            Ui.endDukeMsg();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred...");
        }
    }
}
