package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Parser {
    protected String[] words;
    private Ui ui;

    public Parser(String command) {
        this.words = command.split(" ");
        ui = new Ui();
    }

    private static final Integer TODO_NUMBER = 1;
    private static final Integer DEADLINE_NUMBER = 2;
    private static final Integer EVENT_NUMBER = 3;
    private static final Integer MARK_NUMBER = 4;
    private static final Integer UNMARK_NUMBER = 5;
    private static final Integer DELETE_NUMBER = 6;

    /**
     * Analyses user's commands and performs the appropriate actions.
     *
     * @param tasks TaskList object consisting of user's existing tasks.
     * @return String generated from the UI class. Aids in generation of DialogBox.
     * @throws Exception if the command is Bye.
     */
    public String validateParser(TaskList tasks) throws Exception {
        String action = this.words[0];
        String taskDescription = getTaskDescription(this.words);
        try {
            if (Arrays.asList("todo", "deadline", "event", "list").contains(action)) {
                return handleTaskCreationCommands(action, tasks);
            } else if (Objects.equals(action, "mark")) {
                if (validOthers(this.words, tasks)) {
                    return performOthersAction(MARK_NUMBER, tasks);
                }
            } else if (Objects.equals(action, "unmark")) {
                if (validOthers(this.words, tasks)) {
                    return performOthersAction(UNMARK_NUMBER, tasks);
                }
            } else if (Objects.equals(action, "delete")) {
                if (validOthers(this.words, tasks)) {
                    return performOthersAction(DELETE_NUMBER, tasks);
                }
            } else if (Objects.equals(action, "find"))  {
                assert(!Objects.equals(taskDescription, ""));
                return performFiltering(taskDescription, tasks);
            } else if (Objects.equals(action, "bye")) {
                throw new Exception("Bye. Hope to see you again soon!"); }
            else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException DE) {
            return DE.getMessage();
        }
        return "Something went wrong!";
    }

    /**
     * Returns task description for various Task types (Todo/Deadline/Event).
     *
     * @param arrUserCommand Array of the particular user's command split by " ".
     * @return Task description for a particular Task (String).
     */
    public String getTaskDescription(String[] arrUserCommand) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < arrUserCommand.length; i++) {
            if ("/by".equals(arrUserCommand[i]) || "/from".equals(arrUserCommand[i])) {
                break;
            }
            result.append(" ").append(arrUserCommand[i]);
        }
        return result.toString().trim();
    }

    /**
     * Analyses user's commands and performs the appropriate actions.
     * This function only works for "todo", "deadline", "Event".
     * Refactoring due to Original Main function being too long.
     *
     * @param action String representation ["Todo", "deadline", "Event"].
     * @param tasks TaskList object consisting of user's existing tasks.
     * @return String generated from the UI class. Aids in generation of DialogBox.
     * @throws DukeException if not appropriate action.
     */
    private String handleTaskCreationCommands(String action, TaskList tasks) throws DukeException {
        String taskDescription = getTaskDescription(this.words);
        if (Objects.equals(action, "todo")) {
            if (isValidToDoCommand(this.words)) {
                return performValidTaskAction(TODO_NUMBER,taskDescription, tasks);
            }
        } else if (Objects.equals(action, "deadline")) {
            if (isValidDeadlineCommand(this.words)) {
                return performValidTaskAction(DEADLINE_NUMBER, taskDescription, tasks);
            }
        } else if (Objects.equals(action, "event")) {
            if (isValidEventCommand(this.words)) {
                return performValidTaskAction(EVENT_NUMBER, taskDescription, tasks);
            }
        } else if (Objects.equals(action, "list")) {
            assert(this.words.length == 1);
            return performValidListAction(tasks);
        }
        throw new DukeException("Invalid");
    }

    /**
     * Returns the appropriate string response as well as adding new task to pre-existing TaskList.
     * Works for all the Task Object [Todo, Deadline, Event].
     *
     * @param taskNumber Magic Number as stated above. 1 for Todo, 2 for Deadline, 3 for Event.
     * @param taskDescription String description of the particular task.
     * @param tasks Pre-existing TaskList object containing the user's tasks.
     * @return String response after successfully adding a new task.
     * @throws DukeException If the magic digit is not stated in the conditional flow.
     */
    public String performValidTaskAction(Integer taskNumber, String taskDescription, TaskList tasks) throws DukeException {
        Task newTask = null;
        if (taskNumber == 1) {
            newTask = new Todo(taskDescription);
        } else if (taskNumber == 2) {
            newTask = new Deadline(taskDescription, searchDeadline(this.words, "/by"));
        } else if (taskNumber == 3) {
            String[] fromTo = searchFromTo(this.words, "/from", "/to");
            newTask = new Event(taskDescription, fromTo[0], fromTo[1]);
        }
        if (newTask == null) {
            throw new DukeException("Something went wrong!");
        }
        tasks.addTask(newTask);
        return ui.addTaskText(newTask, tasks.userData.size());
    }

    /**
     * Returns True if the user's command for a Todo event is valid. I.e. has to contain >= 1 words.
     * Throws DukeException if the user's command contains only 1 word (No Task Description).
     *
     * @param userCommand Array of the particular user's command split by " ".
     * @return True if appropriate Todo command.
     * @throws DukeException If the command is only one word. I.e. no task description.
     */
    public boolean isValidToDoCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            String error = String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]);
            throw new DukeException(error);
        } else {
            return true;
        }
    }

    /**
     * Returns string from delimiter to end of array. Array is from user commands.
     *
     * @param userCommand Array of the particular user's command split by " ".
     * @param delimiter Use-case would be "/by" for a deadline task.
     * @return In Deadline task context, it should return the due date that the user has input. (String).
     */
    public String searchDeadline(String[] userCommand, String delimiter) {
        StringBuilder result = new StringBuilder();
        int index = -1;
        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index + 1; i < userCommand.length; i++) {
                result.append(userCommand[i]).append(" ");
            }
        }
        return result.toString().trim();
    }

    /**
     * Returns an Array of length 2 where the first index is the "/from" specification.
     * Second index is the "/to" specification for Event Task.
     *
     * @param userCommand Array of the particular user's command split by " ".
     * @param delimiter Use-case would be "/from" for an "Event" task.
     * @param delimiter_2 Use-case would be "/to" for an "Event" task.
     * @return In Event task context, it should return an array where array[0] is "/from" specs and array[1] is "/to" specs.
     */
    public String[] searchFromTo(String[] userCommand, String delimiter, String delimiter_2) {
        StringBuilder firstResult = new StringBuilder();
        StringBuilder secondResult = new StringBuilder();
        int index = -1;
        int index_2 = -1;
        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
            } else if (delimiter_2.equals(userCommand[i])) {
                index_2 = i;
            }
        }
        if (index != -1 && index_2 != -1 && index < index_2) {
            for (int i = index + 1; i < index_2; i++) {
                firstResult.append(userCommand[i]).append(" ");
            }
            for (int i = index_2 + 1; i < userCommand.length; i++) {
                secondResult.append(userCommand[i]).append(" ");
            }
        }
        return new String[]{ firstResult.toString().trim(), secondResult.toString().trim() };
    }

    public boolean isValidDeadlineCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For deadlines, please give a gauge of when it is due");
        } else {
            String result = searchDeadline(userCommand, "/by");
            if (Objects.equals(result, "")) {
                throw new DukeException("For deadlines, please give a gauge of when it is due");
            } else {
                String datePattern = "\\d{4}-\\d{2}-\\d{2}";
                String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
                if (!result.matches(datePattern) && !result.matches(dateTimePattern)) {
                    throw new DukeException("Please specify the deadline in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
                }
            }
        }
        return true;
    }

    public boolean isValidEventCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
        } else {
            String[] result = searchFromTo(userCommand, "/from", "/to");
            if (result[0].equals("") || result[1].equals("")) {
                throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
            }
        }
        return true;
    }

    /**
     * Returns a boolean on whether the user input for "Mark"/"Unmark"/"Delete" is appropriate.
     * I.e. a digit has to be provided and the digit has to be within the bounds of the length of list.
     *
     * @param userCommand Array of the particular user's command split by " ".
     * @param tasks TaskList object consisting of user's existing tasks.
     * @return Boolean. True if provided with appropriate command that follows the guidelines else False.
     * @throws DukeException
     */
    public boolean validOthers(String[] userCommand, TaskList tasks) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! Please provide an integer for a %s event.", userCommand[0]));
        } else if (userCommand.length > 2) {
            throw new DukeException("OOPS!!! Please only provide one digit!");
        } else {
            try {
                int digit = Integer.parseInt(userCommand[1]);
                if (digit <= 0 || digit > tasks.userData.size()) {
                    throw new DukeException("The digit you provided is out of bounds!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number.");
            }
        }
        return true;
    }

    /**
     * Returns the string representation of the user's existing tasks.
     *
     * @param tasks TaskList object that stores the user's existing tasks.
     * @return String representation of user's tasks.
     */
    public String performValidListAction(TaskList tasks) {
        StringBuilder taskDetails = new StringBuilder();
        for (int i = 0; i < tasks.userData.size(); i++) {
            Task currentTask = tasks.userData.get(i);
            taskDetails.append(ui.displayTaskInList(i, currentTask)).append("\n");
        }
        return taskDetails.toString();
    }

    /**
     * Returns the representation whenever a valid Mark/UnMark/Delete prompt is executed.
     *
     * @param othersIndex Magic Number as stated above. 4 for Mark, 5 for Unmark, 6 for Delete.
     * @param tasks TaskList object that stores the user's existing tasks.
     * @return String response after successfully executing user's request.
     */
    public String performOthersAction(Integer othersIndex, TaskList tasks) {
        int index = Integer.parseInt(this.words[1]);
        Task currentTask = tasks.userData.get(index - 1);
        if (othersIndex == 4) {
            currentTask.isDone = true;
            return ui.markTaskText(currentTask);
        } else if (othersIndex == 5) {
            currentTask.isDone = false;
            return ui.unmarkTaskText(currentTask);
        } else if (othersIndex == 6) {
            tasks.deleteTask(index - 1);
            return ui.deleteTaskText(currentTask, tasks.userData.size());
        } else {
            return ui.textGenerator("Something went wrong!");
        }
    }

    /**
     * Performs filtering based on a specific keyword and return all related tasks.
     *
     * @param keyword String in which user wants to filter the existing task on.
     * @param tasks TaskList object that stores the user's existing tasks.
     * @return String representation of user's filtered tasks.
     */
    public String performFiltering(String keyword, TaskList tasks) {
        ArrayList<Task> filteredTasks = tasks.filter(keyword);
        StringBuilder taskDetails = new StringBuilder();
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task currentTask = filteredTasks.get(i);
            taskDetails.append(ui.displayTaskInList(i, currentTask)).append("\n");
        }
        return taskDetails.toString();
    }
}
