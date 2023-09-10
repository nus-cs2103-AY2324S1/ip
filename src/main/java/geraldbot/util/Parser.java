package geraldbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import geraldbot.exception.DukeEmptyParametersException;
import geraldbot.exception.DukeException;
import geraldbot.exception.DukeInvalidCommandException;
import geraldbot.exception.DukeInvalidDateException;
import geraldbot.exception.DukeInvalidIndexException;
import geraldbot.person.Person;
import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

/**
 * The Parser class handles the parsing of user input and the execution of corresponding actions
 * based on the parsed commands. It interacts with the Storage and TaskList classes to manage tasks.
 */
public class Parser {
    private final TaskStorage taskStorage;
    private final ContactStorage contactStorage;
    private final TaskList taskList;

    private final ContactList contactList;

    private final Ui ui;

    private enum Command {
        LIST,
        BYE,
        FIND,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        CONTACT,
        INVALID
    }

    /**
     * Initializes a new Parser instance with the provided storage and task list.
     *
     * @param taskStorage   The storage instance used for reading and writing tasks.
     * @param contactStorage The storage instance used for reading and writing contacts.
     * @param taskList  The task list containing the tasks to be processed.
     * @param contactList The contact list containing the contacts to be processed.
     */
    public Parser(TaskStorage taskStorage, ContactStorage contactStorage,
                  ArrayList<Task> taskList, ArrayList<Person> contactList) {
        this.taskStorage = taskStorage;
        this.contactStorage = contactStorage;
        this.taskList = new TaskList(taskList);
        this.contactList = new ContactList(contactList);
        this.ui = new Ui();
    }

    /**
     * Parses the user input and executes the corresponding actions for both tasks and contacts.
     *
     * @param input The user input to be parsed and processed.
     * @return A String message containing the result of the executed action.
     * @throws DukeException If an error occurs during parsing or execution.
     */
    public String parse(String input) throws DukeException {
        Command command = getCommand(input);

        switch (command) {
        case LIST:
            return this.printList();
        case BYE:
            return this.ui.bye();
        case FIND:
            return findTasks(input.substring(5).trim());
        case MARK:
            return handleMarkCommand(input);
        case UNMARK:
            return handleUnmarkCommand(input);
        case DELETE:
            return handleDeleteCommand(input);
        case TODO:
            return handleTodoCommand(input);
        case DEADLINE:
            return handleDeadlineCommand(input);
        case EVENT:
            return handleEventCommand(input);
        case CONTACT:
            return handleContactCommand(input);
        case INVALID:
            throw new DukeInvalidCommandException();
        default:
            throw new DukeInvalidCommandException();
        }
    }

    /**
     * Determines the command type based on the user input.
     *
     * @param input The user input to be analyzed.
     * @return The Command enum representing the detected command type.
     */
    private Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("contact")) {
            return Command.CONTACT;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * Handles the "contact" command to perform contact-related actions.
     *
     * @param input The user input for the "contact" command.
     * @return A message indicating the result of the contact-related action.
     * @throws DukeException If there is an issue with the input or contact command.
     */
    private String handleContactCommand(String input) throws DukeException {
        if (input.startsWith("contact add")) {
            return handleAddPerson(input);
        } else if (input.startsWith("contact remove")) {
            return handleRemovePerson(input);
        } else if (input.equals("contact list")) {
            return handleListContacts();
        } else {
            throw new DukeInvalidCommandException();
        }
    }

    /**
     * Parses the "contact add" command to add a new person to the contact list and storage.
     *
     * @param input The user input for the "contact add" command.
     * @return A message indicating the success of adding the person.
     * @throws DukeException If there is an issue with the input or person information.
     */
    private String handleAddPerson(String input) throws DukeException {
        String[] parsedString = input.split(" ", 3);

        if (parsedString.length != 3 || !parsedString[1].equals("add")) {
            throw new DukeInvalidCommandException("contact add");
        }

        String personInfo = parsedString[2];
        String[] personData = personInfo.split("/");

        if (personData.length != 3) {
            throw new DukeInvalidCommandException("contact add");
        }

        String name = personData[0].trim();
        String phone = personData[1].trim();
        String email = personData[2].trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            throw new DukeInvalidCommandException("contact add");
        }

        return this.addPerson(name, phone, email);
    }

    /**
     * Parses the "contact remove" command to remove a person from the contact list and storage.
     *
     * @param input The user input for the "contact remove" command.
     * @return A message indicating the success of removing the person.
     * @throws DukeException If there is an issue with the input or person index.
     */
    private String handleRemovePerson(String input) throws DukeException {
        String[] parsedString = input.split(" ", 3);

        if (parsedString.length != 3 || !parsedString[1].equals("remove")) {
            throw new DukeInvalidCommandException("contact remove");
        }

        try {
            int index = Integer.parseInt(parsedString[2].trim());

            if (index <= 0 || index > contactList.size()) {
                throw new DukeInvalidIndexException(contactList.size());
            }

            return this.removePerson(index);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(contactList.size());
        }
    }

    /**
     * Adds a new person (contact) to the contact list and storage.
     *
     * @param name  The name of the person (contact).
     * @param phone The phone number of the person (contact).
     * @param email The email address of the person (contact).
     * @return A message indicating the success of adding the person.
     */
    public String addPerson(String name, String phone, String email) {
        assert name != null && !name.trim().isEmpty() : "Person name cannot be empty";
        assert phone != null && !phone.trim().isEmpty() : "Person phone cannot be empty";
        assert email != null && !email.trim().isEmpty() : "Person email cannot be empty";

        Person newPerson = new Person(name, phone, email);
        String newPersonString = newPerson.fileFormat();

        String message = "Got it. I've added this person to your contacts:\n";
        message += newPerson;

        contactList.add(newPerson);
        contactStorage.addPerson(newPersonString);

        message += "\nNow you have " + contactList.size() + " contacts in the list.";
        return message;
    }

    /**
     * Removes a person (contact) from the contact list and storage.
     *
     * @param index The index of the person (contact) to be removed.
     * @return A message indicating the success of removing the person.
     */
    public String removePerson(int index) {
        String message = "Noted. I've removed this person from your contacts:\n";
        Person removedPerson = contactList.remove(index - 1);
        contactStorage.updatePerson(index - 1, null);

        message += removedPerson;
        message += "\nNow you have " + contactList.size() + " contacts in the list.";
        return message;
    }

    /**
     * Handles the "contact list" command to list all contacts in the contact list.
     *
     * @return A message containing the list of contacts.
     */
    private String handleListContacts() {
        StringBuilder listString = new StringBuilder("Here are your contacts:\n");
        for (int i = 0; i < contactList.size(); i++) {
            listString.append(i + 1).append(". ").append(contactList.get(i)).append("\n");
        }
        return listString.toString();
    }

    /**
     * Parses the "mark" command to mark a task as done and updates the storage.
     *
     * @param input The user input for the "mark" command.
     * @return A message indicating the success of marking the task as done.
     * @throws DukeException If there is an issue with the input or task index.
     */
    private String handleMarkCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");
        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("mark");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > taskList.size() || num <= 0) {
                throw new DukeInvalidIndexException(taskList.size());
            }
            Task selectedTask = taskList.get(num - 1);
            return this.markCompletion(selectedTask, num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(taskList.size());
        }
    }

    /**
     * Parses the "unmark" command to remove the completion status of a task and updates the storage.
     *
     * @param input The user input for the "unmark" command.
     * @return A message indicating the success of marking the task as not done.
     * @throws DukeException If there is an issue with the input or task index.
     */
    private String handleUnmarkCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");
        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("unmark");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > taskList.size() || num <= 0) {
                throw new DukeInvalidIndexException(taskList.size());
            }
            Task selectedTask = taskList.get(num - 1);
            return this.unmarkCompletion(selectedTask, num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(taskList.size());
        }
    }

    /**
     * Parses the "delete" command to delete a task from the task list and updates the storage.
     *
     * @param input The user input for the "delete" command.
     * @return A message indicating the success of deleting the task.
     * @throws DukeException If there is an issue with the input or task index.
     */
    private String handleDeleteCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");

        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("delete");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > taskList.size() || num <= 0) {
                throw new DukeInvalidIndexException(taskList.size());
            }
            return this.deleteTask(num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(taskList.size());
        }
    }

    /**
     * Parses the "todo" command to add a new todo task to the task list and storage.
     *
     * @param input   The user input for the "todo" command.
     * @return A message indicating the success of adding the todo task.
     * @throws DukeException If there is an issue with the input or task description.
     */
    private String handleTodoCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("todo");
        }

        String command = "todo";
        String description = input.substring(input.indexOf(' ') + 1).trim();

        if (description.equals("")) {
            throw new DukeInvalidCommandException(command);
        }

        return this.addTodo(description, false);
    }

    /**
     * Parses the "deadline" command to add a new deadline task to the task list and storage.
     *
     * @param input   The user input for the "deadline" command.
     * @return A message indicating the success of adding the deadline task.
     * @throws DukeException If there is an issue with the input, task description, or date format.
     */
    private String handleDeadlineCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("deadline");
        }

        String command = "deadline";
        String task = input.substring(input.indexOf(' ') + 1);
        String[] parsedTask = task.split("/", 2);
        String description = parsedTask[0].trim();

        if (parsedTask.length < 2) {
            throw new DukeEmptyParametersException();
        }

        String by = parsedTask[1].trim();
        LocalDateTime deadlineDate = parseDate(by);

        if (description.equals("")) {
            throw new DukeInvalidCommandException(command);
        } else if (deadlineDate == null) {
            throw new DukeInvalidDateException();
        } else {
            return this.addDeadline(description, false, deadlineDate);
        }
    }

    /**
     * Parses the "event" command to add a new event task to the task list and storage.
     *
     * @param input   The user input for the "event" command.
     * @return A message indicating the success of adding the event task.
     * @throws DukeException If there is an issue with the input or task parameters.
     */
    private String handleEventCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("event");
        }

        String task = input.substring(input.indexOf(' ') + 1);
        String[] parsedTask = task.split("/");

        if (parsedTask.length < 3) {
            throw new DukeEmptyParametersException();
        }

        String description = parsedTask[0].trim();
        String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
        String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();

        if (description.equals("") || start.equals("") || by.equals("")) {
            throw new DukeEmptyParametersException();
        } else {
            return this.addEvent(description, false, start, by);
        }
    }

    /**
     * Parses a date string and returns a LocalDateTime object.
     *
     * @param dateStr The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     */
    private LocalDateTime parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("\\s+", 2);
            String dateString = parts.length > 1 ? parts[1] : parts[0]; // Use the second part if available

            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Adds a new todo task to the task list and storage.
     *
     * @param input   The description of the todo task.
     * @param isDone  The completion status of the task.
     * @return A message indicating the success of adding the todo task.
     */
    public String addTodo(String input, boolean isDone) {
        assert input != null && !input.trim().isEmpty() : "Task description cannot be empty";
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        taskList.add(newTask);
        taskStorage.addTask(newTaskString);

        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new deadline task to the task list and storage.
     *
     * @param input   The description of the deadline task.
     * @param isDone  The completion status of the task.
     * @param endTime The deadline of the task.
     * @return A message indicating the success of adding the deadline task.
     */
    public String addDeadline(String input, boolean isDone, LocalDateTime endTime) {
        assert input != null && !input.trim().isEmpty() : "Task description cannot be empty";
        Deadline newTask = new Deadline(input, isDone, endTime);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        taskList.add(newTask);
        taskStorage.addTask(newTaskString);

        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new event task to the task list and storage.
     *
     * @param input   The description of the event task.
     * @param isDone  The completion status of the task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @return A message indicating the success of adding the event task.
     */
    public String addEvent(String input, boolean isDone, String startTime, String endTime) {
        assert input != null && !input.trim().isEmpty() : "Task description cannot be empty";
        Event newTask = new Event(input, isDone, startTime, endTime);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        taskList.add(newTask);
        taskStorage.addTask(newTaskString);

        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints the list of tasks in the task list.
     *
     * @return A String containing the list of tasks.
     */
    public String printList() {
        String listString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            listString += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return listString;
    }

    /**
     * Marks a task as done and updates the storage.
     *
     * @param task The task to be marked as done.
     * @param taskIdx  The index of the task in the list.
     * @return A message indicating the success of marking the task as done.
     */
    public String markCompletion(Task task, int taskIdx) {
        if (task.getStatusIcon().equals("X")) {
            String message = "Nice! I've marked this task as done:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "Nice! I've marked this task as done:\n";

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.taskStorage.updateTask(taskIdx - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Removes the completion status of a task and updates the storage.
     *
     * @param task The task to be marked as not done.
     * @param taskIdx  The index of the task in the list.
     * @return A message indicating the success of marking the task as not done.
     */
    public String unmarkCompletion(Task task, int taskIdx) {
        if (task.getStatusIcon().equals(" ")) {
            String message = "OK, I've marked this task as not done yet:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "OK, I've marked this task as not done yet:\n";
            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.taskStorage.updateTask(taskIdx - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Deletes a task from the task list and updates the storage.
     *
     * @param taskIdx The index of the task in the task list to be deleted.
     * @return A message indicating the success of deleting the task.
     */
    public String deleteTask(Integer taskIdx) {
        String message = "Noted. I've removed this task:\n";
        Task selectedTask = taskList.remove(taskIdx - 1);
        this.taskStorage.updateTask(taskIdx - 1, null);

        message += "\t" + selectedTask;
        message += "\nNow you have " + taskList.size() + " tasks in the list.";
        return message;
    }

    /**
     * Finds tasks that match a specified keyword and displays them.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A String containing the list of matching tasks.
     */
    private String findTasks(String keyword) {
        List<Task> matchingTasks = taskList.findTasksByKeyword(keyword);

        String listString = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            listString += (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
        }
        return listString;
    }
}
