package duke;

import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.trivia.Trivia;
import duke.trivia.TriviaList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the Duke chat bot.
 */
public class Duke {
    private static final String DATA_PATH = "./data";
    private static final String DUKE_FILE_PATH = DATA_PATH + "/duke.txt";
    private static final String TRIVIA_FILE_PATH = DATA_PATH + "/trivia.txt";
    private final Storage storage = new Storage(DUKE_FILE_PATH, TRIVIA_FILE_PATH);
    private final TaskList tasks;
    private final TriviaList triviaList;

    /**
     * Creates a Duke chat bot.
     */
    public Duke() {
        ArrayList<Task> loadedTasks;
        ArrayList<Trivia> loadedTrivia;
        try {
            loadedTasks = storage.loadTasks();
            loadedTrivia = storage.loadTrivia();
        } catch (IOException e) {
            Ui ui = new Ui();
            ui.showError(e.getMessage());
            loadedTasks = new ArrayList<>();
            loadedTrivia = new ArrayList<>();
        }

        this.tasks = new TaskList(loadedTasks);
        this.triviaList = new TriviaList(loadedTrivia);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String deleteTask(String taskIndex) throws IOException {
        int index = Integer.parseInt(taskIndex) - 1;
        assert index >= 0 && index < tasks.getSize() : "Invalid task index!";
        Task removedTask = tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        return generateTaskRemovedMessage(removedTask);
    }

    /**
     * Generates the message to be displayed when a task is removed.
     *
     * @param task The task that is removed.
     * @return The message to be displayed when a task is removed.
     */
    private String generateTaskRemovedMessage(Task task) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n",
                task, tasks.getSize());
    }

    /**
     * Adds a todo task to the list.
     *
     * @param task The description of the todo task.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addTodo(String task) throws IOException {
        assert task != null && !task.trim().isEmpty() : "Task description cannot be empty or null!";
        Task newTask = new Todo(task);
        tasks.addTask(newTask);
        storage.saveTasks(tasks.getTasks());
        return generateTaskAddedMessage(newTask);
    }

    /**
     * Generates the message to be displayed when a task is added.
     *
     * @param task The task that is added.
     * @return The message to be displayed when a task is added.
     */
    private String generateTaskAddedMessage(Task task) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                task, tasks.getSize());
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param task The description of the deadline task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addDeadline(String task) throws IllegalArgumentException, DateTimeParseException, IOException {
        assert task != null && !task.trim().isEmpty() : "Task description cannot be empty or null!";
        String[] parts = task.split(" /by ");

        guardForValidDeadlineFormat(parts);

        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.addTask(newTask);
        storage.saveTasks(tasks.getTasks());

        return generateTaskAddedMessage(newTask);
    }

    /**
     * Guards for a valid deadline format.
     *
     * @param parts The parts of the deadline task.
     * @throws IllegalArgumentException If the deadline format is invalid.
     * @throws DateTimeParseException If the date format is invalid.
     */
    private void guardForValidDeadlineFormat(String[] parts) throws IllegalArgumentException, DateTimeParseException{
        if (parts.length < 2) {
            throw new IllegalArgumentException("Please use the format 'deadline <task description> /by yyyy-MM-dd'");
        }

        LocalDate.parse(parts[1]); // Validates the date
    }

    /**
     * Adds an event task to the list.
     *
     * @param task The description of the event task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addEvent(String task) throws IllegalArgumentException, DateTimeParseException, IOException {
        assert task != null && !task.trim().isEmpty() : "Task description cannot be empty or null!";
        String[] parts = validateEventFormat(task);

        Task newTask = new Event(parts[0], parts[1], parts[2]);
        tasks.addTask(newTask);
        storage.saveTasks(tasks.getTasks());

        return generateTaskAddedMessage(newTask);
    }

    /**
     * Validates the event format.
     *
     * @param task The description of the event task.
     * @throws IllegalArgumentException If the event format is invalid.
     * @throws DateTimeParseException If the date format is invalid.
     * @return The parts of the event task.
     */
    private String[] validateEventFormat(String task) throws IllegalArgumentException, DateTimeParseException{
        String[] parts = task.split(" /from "); // second part will consist of the timings

        if (parts.length < 2) {
            throw new IllegalArgumentException("Please use the format 'event <event description> " +
                    "/from yyyy-MM-dd /to yyyy-MM-dd'");
        }

        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            throw new IllegalArgumentException("Please use the format 'event <event description> " +
                    "/from yyyy-MM-dd /to yyyy-MM-dd'");
        }

        // Validates the date formats
        LocalDate.parse(times[0]);
        LocalDate.parse(times[1]);

        return new String[] {parts[0], times[0], times[1]};
    }

    /**
     * Lists all the tasks in the list.
     *
     * @return The response to the user.
     */
    public String listTasks() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            response.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param task The index of the task to be marked as done.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String markTaskDone(String task) throws IOException {
        return modifyTaskStatus(task, true);
    }

    /**
     * Marks a task as not done.
     *
     * @param task The index of the task to be marked as not done.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String unmarkTaskDone(String task) throws IOException {
        return modifyTaskStatus(task, false);
    }

    /**
     * Modifies the status of a task.
     *
     * @param task The index of the task to be modified.
     * @param markAsDone Whether to mark the task as done or not done.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    private String modifyTaskStatus(String task, boolean markAsDone) throws IOException {
        int index = Integer.parseInt(task) - 1;
        assert index >= 0 && index < tasks.getSize() : "Invalid task index for unmark task!";

        Task modifiedTask = markAsDone ? tasks.markDone(index) : tasks.unmarkDone(index);
        String statusMessage = markAsDone ? "Nice! I've marked this task as done:\n  "
                : "OK, I've marked this task as not done yet:\n  ";

        storage.saveTasks(tasks.getTasks());
        return statusMessage + modifiedTask + "\n";
    }

    /**
     * Finds and lists all the tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The response to the user.
     */
    public String findAndListTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Search keyword cannot be empty or null!";
        StringBuilder response = new StringBuilder();

        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.isEmpty()) {
            response.append("No tasks found with the keyword: ").append(keyword).append("\n");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                response.append(i + 1).append(".").append(matchedTasks.get(i)).append("\n");
            }
        }

        return response.toString();
    }

    /**
     * Parses the trivia index from the user input.
     *
     * @param input The user input.
     * @return The trivia index.
     */
    private int parseTriviaIndex(String input) {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please provide a valid trivia index.");
        }
    }

    /**
     * Adds a trivia to the list.
     *
     * @param input The user input.
     * @throws IOException If there is an error saving the trivia to the file.
     * @return The response to the user.
     */
    public String addTrivia(String input) throws IOException {
        String[] triviaWords = input.split(" /answer ");
        if (triviaWords.length < 2) {
            return "Invalid trivia format. Use 'addtrivia <question> /answer <answer>'";
        }

        String question = triviaWords[0].trim();
        String answer = triviaWords[1].trim();

        if (question.isEmpty() || answer.isEmpty()) {
            return "Question or answer cannot be empty!";
        }

        Trivia trivia = new Trivia(question, answer);
        triviaList.addTrivia(trivia);

        storage.saveTrivia(triviaList.getTrivias());
        return "Trivia added!\n" + trivia;
    }

    /**
     * Lists all the trivia in the list.
     *
     * @return The response to the user.
     */
    public String listAllTrivia() {
        if (triviaList.getTrivias().isEmpty()) {
            return "There are no trivia in your list.";
        }

        StringBuilder response = new StringBuilder("Here are the trivia in your list:\n");
        for (int i = 0; i < triviaList.getSize(); i++) {
            response.append(i + 1).append(". ").append(triviaList.getTrivia(i).getQuestion()).append("\n");
        }
        return response.toString();
    }

    /**
     * Tests a trivia.
     *
     * @param input The user input.
     * @return The response to the user.
     */
    public String testTrivia(String input) {
        String[] words = input.split(" ", 2);

        if (words.length < 2) {
            return "Please provide an answer to test. Use 'testtrivia [trivia index] [your answer]'.";
        }

        int index = parseTriviaIndex(words[0]);
        String userAnswer = words[1].trim();

        Trivia trivia = triviaList.getTrivia(index);
        if (trivia.checkAnswer(userAnswer)) {
            return "Correct! The answer is: " + trivia.getAnswer();
        } else {
            return "Incorrect. The correct answer is: " + trivia.getAnswer();
        }
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input The user input.
     * @return The response to the user.
     */
    public String processInput(String input) {
        StringBuilder response = new StringBuilder();

        try {
            String[] words = Parser.parseCommand(input);
            switch (words[0]) {
                case "bye":
                    return "SHUTDOWN";
                case "delete":
                    response.append(deleteTask(words[1]));
                    break;
                case "help":
                    response.append("\nCommands:\n"
                            + "- To add a todo: 'todo [description]'\n"
                            + "- To add a deadline: 'deadline [description] /by [date in format yyyy-MM-dd]'\n"
                            + "- To add an event: 'event [description] /from [start date in format yyyy-MM-dd] /to "
                            + "[end date in format yyyy-MM-dd]'\n"
                            + "- To add trivia: 'addtrivia [question] /answer [answer]'\n"
                            + "- To list all trivia: 'listtrivia'\n"
                            + "- To test a trivia: 'testtrivia [trivia index] [your answer]'\n");
                    break;
                case "todo":
                    response.append(addTodo(words[1]));
                    break;
                case "deadline":
                    response.append(addDeadline(words[1]));
                    break;
                case "event":
                    response.append(addEvent(words[1]));
                    break;
                case "list":
                    response.append(listTasks());
                    break;
                case "mark":
                    response.append(markTaskDone(words[1]));
                    break;
                case "unmark":
                    response.append(unmarkTaskDone(words[1]));
                    break;
                case "find":
                    response.append(findAndListTasks(words[1]));
                    break;
                case "addtrivia":
                    response.append(addTrivia(words[1]));
                    break;
                case "listtrivia":
                    response.append(listAllTrivia());
                    break;
                case "testtrivia":
                    response.append(testTrivia(words[1]));
                    break;
                default:
                    response.append("Sorry, I don't understand that command.");
                    break;
            }
        } catch (Exception e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }
}