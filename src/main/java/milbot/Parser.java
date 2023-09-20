package milbot;

import exception.*;
import extensions.Tag;
import extensions.TagList;
import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Todo;
import taskclasses.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private TagList tagList;


    public Parser(TaskList taskList, Ui ui, Storage storage, TagList tagList) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.tagList = tagList;
    }
    /**
     * Parses the user input and performs corresponding actions based on the input.
     *
     * @param input The input provided by the user.
     * @return response from the chatbot.
     */
    public String parseInput(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.equals("bye")) {
            return executeByeCommand();
        } else if (trimmedInput.equals("list")) {
            return executeListCommand();
        } else if (trimmedInput.startsWith("mark") || trimmedInput.startsWith("unmark")) {
            return executeMarkCommand(trimmedInput);
        } else if (trimmedInput.startsWith("todo")) {
            return executeTodoCommand(trimmedInput);
        } else if (trimmedInput.startsWith("deadline")) {
            return executeDeadlineCommand(trimmedInput);
        } else if (trimmedInput.startsWith("event")) {
            return executeEventCommand(trimmedInput);
        }  else if(trimmedInput.startsWith("add tag")) {
            return executeAddTagCommand(trimmedInput);
        } else if(trimmedInput.startsWith("delete tag")) {
            return executeRemoveTagCommand(trimmedInput);
        } else if(trimmedInput.equals("tags")) {
            return executeTagListCommand();
        } else if(trimmedInput.startsWith("tag")) {
            return executeTagCommand(trimmedInput);
        } else if(trimmedInput.startsWith("untag")) {
            return executeUntagCommand(trimmedInput);
        } else if(trimmedInput.startsWith("find")) {
            return executeFindCommand(trimmedInput);
        } else if (trimmedInput.startsWith("delete")) {
            return executeDeleteCommand(trimmedInput);
        }
        return ui.printUnknownMessage();
    }

    /**
     * Executes the "bye" command, which saves tasks and tags to files and
     * prints a goodbye message.
     *
     * @return The goodbye message.
     */
    public String executeByeCommand() {
        storage.saveTasksToFile(taskList);
        storage.saveTagsToFile(tagList);
        return ui.printGoodbyeMessage();
    }

    /**
     * Executes the "list" command, which prints the task list.
     *
     * @return The task list as a string.
     */
    public String executeListCommand() {
        return ui.printTaskList(taskList);
    }

    /**
     * Executes the "mark" or "unmark" command to mark or unmark a task as done.
     *
     * @param input The input command.
     * @return The response message indicating the task's status change.
     */
    public String executeMarkCommand(String input) {
        try {
            int index = extractTaskIndex(input);
            Task task = taskList.getTask(index);
            if (input.startsWith("mark")) {
                task.markAsDone();
                return ui.printMarkTask(task);
            } else {
                task.markAsUndone();
                return ui.printUnmarkTask(task);
            }
        } catch (NoTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Extracts the task index from a command input.
     *
     * @param input The input command.
     * @return The task index.
     * @throws NoTaskIndexException    If the task index is missing.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    private int extractTaskIndex(String input) throws NoTaskIndexException, InvalidTaskIndexException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new NoTaskIndexException();
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        }

        return index;
    }

    /**
     * Executes the "todo" command to add a new todo task.
     *
     * @param input The input command.
     * @return The response message indicating the addition of the task.
     */
    public String executeTodoCommand(String input) {
        try {
            if (input.equals("todo")) {
                throw new EmptyTaskException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        Todo task = new Todo(input.substring(5));
        taskList.addTask(task);

        return ui.printNewTask(taskList, task);
    }

    /**
     * Executes the "deadline" command to add a new deadline task.
     *
     * @param input The input command.
     * @return The response message indicating the addition of the task.
     */
    public String executeDeadlineCommand(String input) {
        try {
            if (input.equals("deadline")) {
                throw new EmptyTaskException();
            }
            if (!input.contains("/by") || input.split("/by").length == 1) {
                throw new InvalidDeadlineException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidDeadlineException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        try {
            LocalDate deadlineDate = LocalDate.parse(input.split("/by")[1].trim());
            Deadline deadline = new Deadline(input.split("/by")[0].substring(9),
                    deadlineDate);
            taskList.addTask(deadline);
            return ui.printNewTask(taskList, deadline);
        } catch (DateTimeParseException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Executes the "event" command to add a new event task.
     *
     * @param input The input command.
     * @return The response message indicating the addition of the task.
     */
    public String executeEventCommand(String input) {
        try {
            if (input.equals("event")) {
                throw new EmptyTaskException();
            }
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new InvalidEventException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidEventException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        LocalDate fromDate = LocalDate.parse(input.split("/")[1].substring(5).trim());
        LocalDate toDate = LocalDate.parse(input.split("/")[2].substring(3).trim());
        Event event = new Event(input.split("/")[0].substring(6),
                fromDate, toDate);
        taskList.addTask(event);

        return ui.printNewTask(taskList, event);
    }

    /**
     * Executes the "delete" command to remove a task.
     *
     * @param input The input command.
     * @return The response message indicating the removal of the task.
     */
    public String executeDeleteCommand(String input) {
        try {
            if (input.split(" ").length < 2) {
                throw new NoTaskIndexException();
            }
        } catch (NoTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        try {
            if (index < 0 || index >= taskList.getSize()) {
                throw new InvalidTaskIndexException();
            }
        } catch (InvalidTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        Task task = taskList.getTask(index);
        taskList.removeTask(index);
        return ui.printRemoveTask(task, taskList);
    }

    /**
     * Executes the "find" command to search for tasks based on a query.
     *
     * @param input The input command.
     * @return The response message containing the search results.
     */
    public String executeFindCommand(String input) {
        try {
            if (input.equals("find")) {
                throw new EmptyFindQueryException();
            }
        } catch (EmptyFindQueryException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        String taskQuery = input.split(" ")[1];
        TaskList tasksResult = new TaskList();
        for(Task task : taskList.getTaskList()) {
            if(task.toString().contains(taskQuery)) {
                tasksResult.addTask(task);
            }
        }
        return ui.printSearchResult(tasksResult);
    }

    /**
     * Executes the "add tag" command to add a new tag.
     *
     * @param input The input command.
     * @return The response message indicating the addition of the tag.
     */
    public String executeAddTagCommand(String input) {
        try {
            if(input.equals("tag") || input.equals("tag #")) {
                throw new EmptyTagException();
            }
        } catch(EmptyTagException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        String tagName = input.split("#")[1];
        Tag tag = new Tag(tagName);
        tagList.addTag(tag);
        return ui.printNewTag(tagList, tag);
    }

    /**
     * Executes the "delete tag" command to remove a tag.
     *
     * @param input The input command.
     * @return The response message indicating the removal of the tag.
     */
    public String executeRemoveTagCommand(String input) {
        try {
            if(input.split(" ").length != 3) {
                throw new NoTagIndexException();
            }
        } catch(NoTagIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        int index = Integer.parseInt(input.split(" ")[2]) - 1;

        try {
            if (index < 0 || index >= tagList.getSize()) {
                throw new InvalidTagIndexException();
            }
            Tag tag = tagList.getTag(index);
            tagList.removeTag(index);
            return ui.printRemoveTag(tag, tagList);
        } catch (InvalidTagIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Executes the "tags" command to list all available tags.
     *
     * @return The response message containing the list of tags.
     */
    public String executeTagListCommand() {
        return ui.printTagList(tagList);
    }

    /**
     * Executes the "tag" command to tag a task with a specified tag.
     *
     * @param input The input command.
     * @return The response message indicating the tagging of the task.
     */
    public String executeTagCommand(String input) {
        try {
            int taskIndex = extractTaskIndex(input);
            String tagName = input.split("#")[1];
            Tag tag = new Tag(tagName);
            Task task = taskList.getTask(taskIndex);
            if (!tagList.containTag(tagName)) {
                return ui.printErrorMessage("This tag does not exist in the tag list");
            }
            task.tagTask(tag);
            return ui.printTagTask(task);
        } catch (NoTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Executes the "untag" command to remove a tag from a task.
     *
     * @param input The input command.
     * @return The response message indicating the removal of the tag from the task.
     */
    public String executeUntagCommand(String input) {
        try {
            int taskIndex = extractTaskIndex(input);
            Task task = taskList.getTask(taskIndex);
            task.untagTask();
            return ui.printUntagTask(task);
        } catch (NoTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
