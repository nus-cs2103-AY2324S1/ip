package friday.util;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import friday.exception.InvalidNoteFormatException;
import friday.exception.InvalidTaskFormatException;
import friday.item.Deadline;
import friday.item.Event;
import friday.item.Note;
import friday.item.Task;
import friday.item.Todo;



/**
 * The Parser class is responsible for interpreting user input and executing the corresponding commands.
 */
public class Parser {

    private static final String NOTE_CMD = "note";
    private static final String ADD_CMD = "add";
    private static final int SPACE_LENGTH = 1;
    private static final int NOTE_ADD_CMD_LENGTH = NOTE_CMD.length() + ADD_CMD.length() + SPACE_LENGTH;
    private static final int MIN_NOTE_PARTS = 3;
    private static final int TASK_NUMBER_POSITION_IN_INPUT = 1;
    private static final int INDEX_OFFSET_FOR_ZERO_BASED_LIST = 1;
    private static final int MIN_TASK_INPUT = 2;
    private static final int DEADLINE_DELIMITER = 3;


    /**
     * Saves tasks to tasks.txt.
     * If an error occurs during save operation, an error message is printed.
     *
     * @param taskList The list of tasks to save.
     * @param storage The storage object to save tasks.
     */
    private void saveTasks(String taskList, Storage storage) throws InvalidTaskFormatException {
        try {
            storage.saveFile(taskList);
        } catch (IOException e) {
            throw new InvalidTaskFormatException("Save Task Error: " + e.getMessage());
        }
    }

    /**
     * Saves notes to notes.txt.
     * If an error occurs during save operation, an error message is printed.
     *
     * @param noteList The list of notes to save.
     * @param storage  The storage object to save notes.
     */
    private void saveNotes(String noteList, Storage storage) throws InvalidNoteFormatException {
        try {
            storage.saveFile(noteList);
        } catch (IOException e) {
            throw new InvalidNoteFormatException("SaveNote Error: " + e.getMessage());
        }
    }

    /**
     * Processes user input and interacts with the TaskList to execute user commands.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    public String processTaskCommand(String userInput, TaskList taskList, Storage storage) {
        String generalCommandsFunctionResponse = handleGeneralCommands(userInput);
        if (generalCommandsFunctionResponse != null) {
            return generalCommandsFunctionResponse;
        }
        try {
            return handleTaskCommands(userInput, taskList, storage);
        } catch (InvalidTaskFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes user input and interacts with the TaskList to execute user commands.
     *
     * @param userInput The command input by the user.
     * @param noteList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    public String processNoteCommand(String userInput, NoteList noteList, Storage storage) {
        try {
            return handleNoteCommand(userInput, noteList, storage);
        } catch (InvalidNoteFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles general commands that are not task-specific.
     *
     * @param userInput The command input by the user.
     * @return A string response after processing the command or null if the command is not recognized.
     */
    private String handleGeneralCommands(String userInput) {
        if (userInput.equals("hello") || userInput.equals("hi") || userInput.equals("hey")) {
            return "Hello! How can I assist you today?";
        }

        if (userInput.contains("thank")) {
            return "You're welcome!";
        }

        if (userInput.contains("what can you do")) {
            return "I can manage tasks for you! You can add tasks, mark them as done, or delete them.";
        }

        if (userInput.contains("bye") || userInput.contains("goodbye")) {
            return "Goodbye! If you need anything else, just let me know.";
        }

        return null;
    }

    /**
     * Determines the type of note-related command based on the user input.
     * @param userInput The user's input string.
     * @return The type of note-related command as an enum.
     */
    private NoteCommandType getNoteCommandType(String userInput) {
        if (userInput.startsWith("note list")) {
            return NoteCommandType.LIST;
        } else if (userInput.startsWith("note add")) {
            return NoteCommandType.ADD;
        } else if (userInput.startsWith("note delete")) {
            return NoteCommandType.DELETE;
        } else {
            return NoteCommandType.UNKNOWNNNOTECOMMAND;
        }
    }

    /**
     * Handles user commands related to notes.
     * @param userInput The user's input string.
     * @return A response string for note-related commands.
     */
    private String handleNoteCommand(String userInput, NoteList noteList, Storage noteStorage)
            throws InvalidNoteFormatException {
        NoteCommandType commandType = getNoteCommandType(userInput);
        switch (commandType) {
        case LIST:
            return listNotes(noteList);
        case ADD:
            return addNote(userInput, noteList, noteStorage);
        case DELETE:
            return deleteNote(userInput, noteList, noteStorage);
        default:
            throw new InvalidNoteFormatException("OOPS!!! I'm sorry, but I don't know what that means for notes :-(");
        }
    }

    private String listNotes(NoteList noteList) {
        return "Here are the notes in your list:\n" + noteList.toString();
    }

    private String addNote(String userInput, NoteList noteList, Storage noteStorage)
            throws InvalidNoteFormatException {
        String[] addInputParts = userInput.split(" ");
        if (addInputParts.length < MIN_NOTE_PARTS) {
            throw new InvalidNoteFormatException("Please provide content for the note.");
        }
        String noteContent = userInput.substring(NOTE_ADD_CMD_LENGTH);
        Note note = new Note(noteContent);
        noteList.add(note);
        saveNotes(noteList.toString(), noteStorage);
        return "Note added: " + note;
    }

    private String deleteNote(String userInput, NoteList noteList, Storage noteStorage)
            throws InvalidNoteFormatException {
        String[] deleteInputParts = userInput.split(" ");
        if (deleteInputParts.length < MIN_NOTE_PARTS) {
            throw new InvalidNoteFormatException("Please provide a valid note number to delete.");
        }
        int deleteNoteNumber = Integer.parseInt(deleteInputParts[2]);
        String deleteNoteMessage = noteList.delete(deleteNoteNumber - 1);
        saveNotes(noteList.toString(), noteStorage);
        return "Note deleted successfully!\n" + deleteNoteMessage;
    }


    /**
     * Handles task-specific commands.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleTaskCommands(String userInput, TaskList taskList, Storage storage)
            throws InvalidTaskFormatException {
        TaskCommandType commandType = getCommandType(userInput);
        switch (commandType) {
        case LIST:
            return listTasks(taskList);
        case UNMARK:
            return unmarkTask(userInput, taskList, storage);
        case MARK:
            return markTask(userInput, taskList, storage);
        case DELETE:
            return deleteTask(userInput, taskList, storage);
        case FIND:
            return findTask(userInput, taskList);
        case TODO:
            return handleTodoCommand(userInput, taskList, storage);
        case DEADLINE:
            return handleDeadlineCommand(userInput, taskList, storage);
        case EVENT:
            return handleEventCommand(userInput, taskList, storage);
        default:
            throw new InvalidTaskFormatException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * Returns a string representation of all tasks in the task list.
     *
     * @param taskList The list of tasks.
     * @return A string detailing all tasks in the task list.
     */
    private String listTasks(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.toString();
    }


    /**
     * Processes the 'unmark' command to mark a task as incomplete.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A response message after unmarking the task.
     * @throws InvalidTaskFormatException If an error occurs during processing.
     */
    private String unmarkTask(String userInput, TaskList taskList, Storage storage) throws InvalidTaskFormatException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < MIN_TASK_INPUT) {
            throw new InvalidTaskFormatException("Please provide a task number to unmark.");
        }
        int unmarkTaskNumber;
        try {
            unmarkTaskNumber = Integer.parseInt(splitInput[TASK_NUMBER_POSITION_IN_INPUT])
                    -
                    INDEX_OFFSET_FOR_ZERO_BASED_LIST;
        } catch (NumberFormatException e) {
            throw new InvalidTaskFormatException("Invalid task number. Please provide a valid number to unmark.");
        }

        try {
            String unmarkMessage = taskList.unmark(unmarkTaskNumber);
            saveTasks(taskList.toString(), storage);
            return "Nice! I've marked this task as not done yet: \n" + unmarkMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException("The task number provided is out of bounds. "
                    +
                    "Please provide a valid task number based on the task list.");
        }
    }



    /**
     * Processes the 'mark' command to mark a task as complete.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A response message after marking the task.
     * @throws InvalidTaskFormatException If an error occurs during processing.
     */
    private String markTask(String userInput, TaskList taskList, Storage storage) throws InvalidTaskFormatException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < MIN_TASK_INPUT) {
            throw new InvalidTaskFormatException("Please provide a task number to mark.");
        }
        int markTaskNumber;
        try {
            markTaskNumber = Integer.parseInt(splitInput[TASK_NUMBER_POSITION_IN_INPUT])
                    -
                    INDEX_OFFSET_FOR_ZERO_BASED_LIST;
        } catch (NumberFormatException e) {
            throw new InvalidTaskFormatException("Invalid task number. Please provide a valid number to mark.");
        }

        try {
            String markMessage = taskList.mark(markTaskNumber);
            saveTasks(taskList.toString(), storage);
            return "Nice! I've marked this task as done:\n" + markMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException("The task number provided is out of bounds. "
                    +
                    "Please provide a valid task number based on the task list.");
        }
    }



    /**
     * Processes the 'delete' command to remove a task from the task list.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A response message after deleting the task.
     * @throws InvalidTaskFormatException If an error occurs during processing.
     */
    private String deleteTask(String userInput, TaskList taskList, Storage storage)
            throws InvalidTaskFormatException {
        try {
            int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[TASK_NUMBER_POSITION_IN_INPUT]);

            if (deleteTaskNumber <= 0 || deleteTaskNumber > taskList.size()) {
                throw new InvalidTaskFormatException("Invalid task number. Please provide a valid number");
            }

            String deleteMessage = taskList.delete(deleteTaskNumber - INDEX_OFFSET_FOR_ZERO_BASED_LIST);
            saveTasks(taskList.toString(), storage);
            return "Task deleted successfully!\n" + deleteMessage;

        } catch (NumberFormatException e) {
            throw new InvalidTaskFormatException("Please provide a valid task number to delete.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException("Please provide a task number after the 'delete' command.");
        }
    }


    /**
     * Processes the 'find' command to search for tasks by a given keyword.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @return A string detailing all tasks that match the search keyword.
     * @throws InvalidTaskFormatException If an error occurs during processing.
     */
    private String findTask(String userInput, TaskList taskList) throws InvalidTaskFormatException {
        String[] findInput = userInput.split(" ", MIN_TASK_INPUT);
        if (findInput.length < MIN_TASK_INPUT || findInput[TASK_NUMBER_POSITION_IN_INPUT].trim().isEmpty()) {
            throw new InvalidTaskFormatException("Oops! Please add a keyword to search for!");
        }

        ArrayList<Task> result = taskList.findTasks(findInput[1]);

        StringBuilder resultBuilder = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < result.size(); i++) {
            Task task = result.get(i);
            resultBuilder.append(i + 1).append(". ").append(task.toString()).append("\n");
        }

        return resultBuilder.toString();
    }


    /**
     * Represents the types of commands specifically related to notes in the Friday application.
     */
    public enum NoteCommandType {
        LIST, ADD, DELETE, UNKNOWNNNOTECOMMAND
    }

    /**
     * Represents the types of commands in the Friday application.
     */
    public enum TaskCommandType {
        UNMARK, MARK, DELETE, FIND, TODO, DEADLINE, EVENT, LIST, UNKNOWNTASKCOMMAND
    }

    /**
     * Determines the type of command based on the user input.
     *
     * @param userInput The command input by the user.
     * @return The type of command as a string.
     */
    private TaskCommandType getCommandType(String userInput) {
        if (userInput.contains("list")) {
            return TaskCommandType.LIST;
        } else if (userInput.contains("unmark")) {
            return TaskCommandType.UNMARK;
        } else if (userInput.contains("mark")) {
            return TaskCommandType.MARK;
        } else if (userInput.contains("delete")) {
            return TaskCommandType.DELETE;
        } else if (userInput.contains("find")) {
            return TaskCommandType.FIND;
        } else if (userInput.contains("todo")) {
            return TaskCommandType.TODO;
        } else if (userInput.contains("deadline")) {
            return TaskCommandType.DEADLINE;
        } else if (userInput.contains("event")) {
            return TaskCommandType.EVENT;
        } else {
            return TaskCommandType.UNKNOWNTASKCOMMAND;
        }
    }

    /**
     * Handles the 'todo' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleTodoCommand(String userInput, TaskList taskList, Storage storage)
            throws InvalidTaskFormatException {
        String[] todoInput = userInput.split(" ", MIN_TASK_INPUT);
        if (todoInput.length < MIN_TASK_INPUT
                ||
                todoInput[TASK_NUMBER_POSITION_IN_INPUT].trim().isEmpty()) {
            throw new InvalidTaskFormatException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(todoInput[1]);
        taskList.add(todo);
        saveTasks(taskList.toString(), storage);
        return "added: " + todo;
    }

    /**
     * Handles the 'deadline' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleDeadlineCommand(String userInput, TaskList taskList, Storage storage)
            throws InvalidTaskFormatException {
        String[] commandAndDetails = userInput.split(" ", MIN_TASK_INPUT);
        if (commandAndDetails.length < MIN_TASK_INPUT || !userInput.contains("/by")) {
            return "Incorrect format for 'deadline'. Here is a sample:\ndeadline return book /by 01/01/2001";
        }
        String[] taskAndDate = commandAndDetails[1]
                .split(" /by ", MIN_TASK_INPUT);
        if (taskAndDate.length < MIN_TASK_INPUT) {
            return "Please provide both a task description and a deadline date.";
        }
        String taskDescription = taskAndDate[0];
        String deadlineDate = taskAndDate[1];
        System.out.println(taskDescription);
        System.out.println(deadlineDate);

        Deadline deadline;
        try {
            deadline = new Deadline(taskDescription, deadlineDate);
            taskList.add(deadline);
            saveTasks(taskList.toString(), storage);
            return "added: " + deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFormatException("Invalid date format provided for deadline. "
                    +
                    "Supported formats: MM/dd/yyyy, MM-dd-yyyy, yyyy/MM/dd");
        }
    }


    /**
     * Handles the 'event' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleEventCommand(String userInput, TaskList taskList, Storage storage)
            throws InvalidTaskFormatException {
        String[] commandAndDetails = userInput.split(" ", MIN_TASK_INPUT);
        if (commandAndDetails.length < MIN_TASK_INPUT || !userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidTaskFormatException("Incorrect format for 'event'. "
                    +
                    "Expected format: event TASK_DESCRIPTION /from START_TIME /to END_TIME");
        }
        String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", DEADLINE_DELIMITER);
        if (taskAndTimes.length < DEADLINE_DELIMITER) {
            throw new InvalidTaskFormatException("Please provide a task description, start time, and end time.");
        }
        String taskDescription = taskAndTimes[0];
        String startTime = taskAndTimes[1];
        String endTime = taskAndTimes[2];
        Event event = new Event(taskDescription, startTime, endTime);
        taskList.add(event);
        saveTasks(taskList.toString(), storage);
        return "added: " + event;
    }
}
