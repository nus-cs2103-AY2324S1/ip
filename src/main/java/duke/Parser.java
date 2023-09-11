package duke;

import java.util.ArrayList;
import java.util.Objects;

public class Parser {
    protected String[] words;
    private Ui ui;

    public Parser(String command) {
        this.words = command.split(" ");
        ui = new Ui();
    }

    /**
     * Analyses user's commands and performs the appropriate actions.
     *
     * @param tasks TaskList object consisting of user's existing tasks.
     * @return String generated from the UI class. Aids in generation of DialogBox.
     * @throws Exception if the command is Bye
     */
    public String validateParser(TaskList tasks) throws Exception {
        String action = this.words[0];
        String taskDescription = getTaskDescription(this.words);
        try {
            if (Objects.equals(action, "todo")) {
                if (isValidToDoCommand(this.words)) {
                    Task newTask = new Todo(taskDescription);
                    tasks.addTask(newTask);
                    return ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "deadline")) {
                if (isValidDeadlineCommand(this.words)) {
                    Task newTask = new Deadline(taskDescription, searchDeadline(this.words, "/by"));
                    tasks.addTask(newTask);
                    return ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "event")) {
                String[] fromTo = searchFromTo(this.words, "/from", "/to");
                if (isValidEventCommand(this.words)) {
                    Task newTask = new Event(taskDescription, fromTo[0], fromTo[1]);
                    tasks.addTask(newTask);
                    return ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "bye")) {
                throw new Exception("Bye. Hope to see you again soon!");
            } else if (Objects.equals(action, "list")) {
                assert(this.words.length == 1);
                StringBuilder taskDetails = new StringBuilder();
                for (int i = 0; i < tasks.userData.size(); i++) {
                    Task currentTask = tasks.userData.get(i);
                    taskDetails.append(ui.displayTaskInList(i, currentTask)).append("\n");
                }
                return taskDetails.toString();
            } else if (Objects.equals(action, "mark")) {
                if (validOthers(this.words, tasks)) {
                    int index = Integer.parseInt(this.words[1]);
                    Task currentTask = tasks.userData.get(index - 1);
                    currentTask.isDone = true;
                    return ui.markTaskText(currentTask);
                }
            } else if (Objects.equals(action, "unmark")) {
                if (validOthers(this.words, tasks)) {
                    int index = Integer.parseInt(this.words[1]);
                    Task currentTask = tasks.userData.get(index - 1);
                    currentTask.isDone = false;
                    return ui.unmarkTaskText(currentTask);
                }
            } else if (Objects.equals(action, "delete")) {
                if (validOthers(this.words, tasks)) {
                    int index = Integer.parseInt(this.words[1]);
                    Task deletedTask = tasks.userData.get(index - 1);
                    tasks.deleteTask(index - 1);
                    return ui.deleteTaskText(deletedTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "find"))  {
                String keyword = taskDescription;
                assert(!Objects.equals(keyword, ""));
                ArrayList<Task> filteredTasks = tasks.filter(keyword);
                for (int i = 0; i < filteredTasks.size(); i++) {
                    Task currentTask = filteredTasks.get(i);
                    return ui.displayTaskInList(i, currentTask);
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException DE) {
            return DE.getMessage();
        }
        return "Something went wrong!";
    }

    /**
     * Returns task description for various Task types (Todo/Deadline/Event)
     *
     * @param arrUserCommand Array of the particular user's command split by " "
     * @return Task description for a particular Task (String)
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
     * Returns True if the user's command for a Todo event is valid. I.e. has to contain >= 1 words
     * Throws DukeException if the user's command contains only 1 word (No Task Description)
     *
     * @param userCommand Array of the particular user's command split by " "
     * @return True if appropriate Todo command
     * @throws DukeException If the command is only one word. I.e. no task description
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
     * @param userCommand Array of the particular user's command split by " "
     * @param delimiter Use-case would be "/by" for a deadline task
     * @return In Deadline task context, it should return the due date that the user has input. (String)
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
     * Returns an Array of length 2 where the first index is the "/from" specification and the second index is the "/to" specification for Event Task
     *
     * @param userCommand Array of the particular user's command split by " "
     * @param delimiter Use-case would be "/from" for an "Event" task
     * @param delimiter_2 Use-case would be "/to" for an "Event" task
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
     * Returns a boolean on whether the user input for "Mark"/"Unmark"/"Delete" is appropriate
     * I.e. a digit has to be provided and the digit has to be within the bounds of the length of list
     *
     * @param userCommand Array of the particular user's command split by " "
     * @param tasks TaskList object consisting of user's existing tasks
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
}
