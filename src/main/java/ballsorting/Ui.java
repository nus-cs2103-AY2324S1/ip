package ballsorting;

import java.time.LocalDateTime;

/**
 * Handles interactions with the user.
 */
public class Ui {
    public Ui() {
    }

    /**
     * Handles the list command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return list of tasks in the tasklist
     */
    public String handleList(String input, TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        output.append(taskList.getStringList());
        return output.toString();
    }

    /**
     * Handles the help command from the user.
     * @return a list of possible commands for the bot
     */
    public String handleHelp() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the list of possible commands:\n");
        output.append("1. todo {description}\n");
        output.append("2. deadline {description} /by{yyyy-MM-dd HH:mm}\n");
        output.append("3. event {description} /from{yyyy-MM-dd HH:mm} /to{yyyy-MM-dd HH:mm}\n");
        output.append("4. mark {task number} - mark as done\n");
        output.append("5. unmark {task number} - mark as not done\n");
        output.append("6. delete {task number} - delete a task\n");
        output.append("7. find {key word} - search for a task\n");
        output.append("8. bye - quits the chatbot\n");
        return output.toString();
    }

    /**
     * Handles the mark command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show that the task has been successfully marked as done
     */
    public String handleMark(String input, TaskList taskList) {
        int target = Integer.parseInt(input.substring(5)) - 1;
        return taskList.markTask(target);
    }

    /**
     * Handles the unmark command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show that task has been successfully marked as not done
     */
    public String handleUnmark(String input, TaskList taskList) {
        int target = Integer.parseInt(input.substring(7)) - 1;
        return taskList.unmarkTask(target);
    }

    /**
     * Handles the delete command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show that the task has been successfully deleted
     */
    public String handleDelete(String input, TaskList taskList) {
        int target = Integer.parseInt(input.substring(7)) - 1;
        return taskList.deleteTask(target);
    }

    /**
     * Handles the find command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return List of tasks that matches the search term
     * @throws CustomError when no search term is input
     */
    public String handleFind(String input, TaskList taskList) throws CustomError {
        if (input.length() == 4) {
            throw new CustomError.emptySearchTermException();
        } else {
            String searchString = input.substring(4).trim();
            if (searchString.equals("")) {
                throw new CustomError.emptySearchTermException();
            } else {
                return taskList.getSearchList(input.substring(5));
            }
        }
    }

    /**
     * Handles the todo command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show the todo has been successfully added to tasklist
     * @throws CustomError when there are missing input fields or duplicated description
     */
    public String handleTodo(String input, TaskList taskList) throws CustomError {
        Task curr = null;
        StringBuilder description = new StringBuilder();

        description.append(input.substring(4).trim());
        if (description.toString().equals("")) {
            throw new CustomError.emptyTodoDescriptionException();
        } else {
            assert !description.toString().equals("");
            curr = new Todo(description.toString());
            assert curr != null;
            assert !description.toString().equals("");
            if (taskList.isDuplicate(description.toString())) {
                return CustomErrorHandling.duplicatedTask();
            }
            return taskList.addTask(curr);
        }
    }

    /**
     * Handles the deadline command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show the deadline has been successfully added to tasklist
     * @throws CustomError when there are missing input fields or duplicated description
     */
    public String handleDeadline(String input, TaskList taskList) throws CustomError {
        Task curr = null;
        StringBuilder description = new StringBuilder();

        int i = "deadline ".length();
        while (i < input.length() && input.charAt(i) != '/') {
            description.append(input.charAt(i));
            i++;
        }
        i += "/by ".length();

        if (description.toString().equals("")) {
            throw new CustomError.emptyDeadlineDescriptionException();
        } else if (i >= input.length() || input.substring(i).equals("")) {
            throw new CustomError.emptyDeadlineDuedateException();
        } else {
            LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
            assert !description.toString().equals("");
            curr = new Deadline(description.toString(), endDateTime);

            assert curr != null;
            assert !description.toString().equals("");
            if (taskList.isDuplicate(description.toString())) {
                throw new CustomError.duplicatedTaskException();
            }
            return taskList.addTask(curr);
        }
    }

    /**
     * Handles the event command from the user.
     * @param input command string from the user
     * @param taskList tasklist of the bot
     * @return String to show the event has been successfully added to tasklist
     * @throws CustomError when there are missing input fields or duplicated description
     */
    public String handleEvent(String input, TaskList taskList) throws CustomError {
        Task curr = null;
        StringBuilder description = new StringBuilder();
        StringBuilder start = new StringBuilder();

        int i = "event ".length();
        while (i < input.length() && input.charAt(i) != '/') {
            description.append(input.charAt(i));
            i++;
        }
        i += "/from ".length();
        while (i < input.length() && input.charAt(i) != '/') {
            start.append(input.charAt(i));
            i++;
        }
        i += "/to ".length();
        if (description.toString().equals("")) {
            throw new CustomError.emptyEventDescriptionException();
        } else if (start.toString().equals("")) {
            throw new CustomError.emptyEventStartDateException();
        } else if (i >= input.length() || input.substring(i).equals("")) {
            throw new CustomError.emptyEventEndDateException();
        } else {
            LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
            if (endDateTime.isBefore(startDateTime)) {
                throw new CustomError.invalidEventDatesException();
            }
            assert !description.toString().equals("");
            curr = new Event(description.toString(), startDateTime, endDateTime);

            assert curr != null;
            assert !description.toString().equals("");
            if (taskList.isDuplicate(description.toString())) {
                throw new CustomError.duplicatedTaskException();
            }
            return taskList.addTask(curr);
        }
    }
}
