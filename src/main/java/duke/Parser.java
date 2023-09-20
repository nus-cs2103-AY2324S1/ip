package duke;

/**
 * This class deals with making sense of the user input and carrying out tasks accordingly.
 */
public class Parser {
    private enum TaskKeyVal {todo, deadline, event, delete, mark, unmark, save, list, find }

    /**
     * Returns a boolean based on the user input choice.
     * bye -> initiates user exit.
     * list -> lists all the tasks.
     * mark -> mark the task.
     * unmark -> unmark the task.
     * ToDo -> creates a new ToDo and adds to task list.
     * Deadline -> creates a new Deadline and adds to task list.
     * Event -> creates a new Event and adds to task list.
     * Delete -> Deletes task at given number.
     * find -> Finds task based on a certain keyword.
     *
     * @param userInput String value provided to be made sense of and carry task.
     * @return returns string value received after processing the task.
     * @throws DukeException when incorrect / invalid input is entered.
     */
    public static String parse(String userInput) throws DukeException {
        String[] userInputList = userInput.split(" ", 2);
        assert userInputList != null;
        String userTaskChoiceKey = userInputList[0];
        //Stores enum value. might throw exception if invalid input entered.
        TaskKeyVal taskKeyVal = TaskKeyVal.valueOf(userTaskChoiceKey);

        return getString(userInputList, userTaskChoiceKey, taskKeyVal);
    }

    private static String getString(String[] userInputList, String userTaskChoiceKey, TaskKeyVal taskKeyVal)
            throws DukeException {
        switch (taskKeyVal) {
        case save:
            return TaskList.saveData();
        case list:
            return TaskList.userListChoice();
        case mark:
        case unmark:
            String userMarkerChoice = userInputList[1];
            return TaskList.userMarkUnmark(userMarkerChoice, userTaskChoiceKey);
        case todo:
            return TaskList.addToDo(userInputList[1]);
        case deadline:
            String[] deadlineList = userInputList[1].split("/", 2);
            return TaskList.addDeadline(deadlineList[0], deadlineList[1]);
        case event:
            String[] eventList = userInputList[1].split("/", 3);
            return TaskList.addEvent(eventList[0], eventList[1], eventList[2]);
        case delete:
            Integer delUserChoice = Integer.parseInt(userInputList[1]);
            return TaskList.deleteTask(delUserChoice);
        case find:
            String findThis = userInputList[1];
            return TaskList.findTask(findThis);

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

}
