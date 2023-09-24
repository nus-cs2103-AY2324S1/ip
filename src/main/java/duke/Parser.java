package duke;

/**
 * Parses all input commands given by the user.
 */
public class Parser {

    //The TaskList to store all tasks given to the Duke bot.
    private TaskList tasks;

    //The TriviaList used to store all trivia given to the Duke bot.
    private TriviaList trivia;


    /**
     * Instantiates a new Parser class
     * @param list the TaskList that contains all tasks stored.
     * @param trivia the TriviaList that contains all trivia stored.
     */
    public Parser(TaskList list, TriviaList trivia) {
        this.tasks = list;
        this.trivia = trivia;
    }

    /**
     * Parses and manages the user's inputs.
     * @param tally takes in the input string.
     * @return the Command to be executed.
     */
    public Command messageHandler(String tally) {
        String[] words = tally.split(" ", 2);
        final String comd = words[0];
        final String restOfCommand = words.length > 1 ? words[1] : "";

        switch (comd) {
        case "list":
            return new TaskLister(this.tasks);
        case "search":
            return new TaskSearcher(restOfCommand, this.tasks);
        case "bye":
            return new Bye(tasks, trivia);
        case "todo":
        case "deadline":
        case "event":
            return this.addTask(comd, restOfCommand);
        case "mark":
        case "unmark":
        case "delete":
            return this.editTask(comd, restOfCommand);
        case "addtrivia":
        case "edittrivia":
        case "removetrivia":
        case "ask":
            return this.handleTrivia(comd, restOfCommand);
        default:
            return new TaskError("other");
        }
    }


    /**
     * Returns the specific command for adding a specific task
     * @param taskType The type of task to be added.
     * @param details The information of the given task.
     * @return The command to add inputted task.
     */
    private Command addTask(String taskType, String details) {
        switch (taskType) {
        case "todo":
            return new ToDoAdder(details, this.tasks);
        case "deadline":
            try {
                String[] deadline = details.split(" /by ", 2);
                String deadlineName = deadline[0];
                String deadlineTime = deadline[1];

                return new DeadlineAdder(deadlineName, deadlineTime, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new TaskError("deadline");
            }
        case "event":
            try {
                String[] event = details.split(" /from ", 2);
                String eventName = event[0];
                String eventTime = event[1];

                String[] times = eventTime.split(" /to ", 2);
                String eventStart = times[0];
                String eventEnd = times[1];

                return new EventAdder(eventName, eventStart, eventEnd, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new TaskError("event");
            }
        default :
            return new TaskError("other");
        }
    }

    /**
     * Returns a command to edit a specific task.
     * @param editType The type of the edit to be made.
     * @param index The index of the task to be edited.
     * @return The command to edit a task.
     */
    private Command editTask(String editType, String index) {
        switch (editType) {
        case "mark":
            if (index.isEmpty()) {
                return new TaskError("mark");
            }
            int indexNumber = Integer.parseInt(index);
            assert indexNumber >= 0 : "Ehh? Make sure it's a non-negative index!!!";
            return new TaskMarker(indexNumber, tasks);
        case "unmark":
            if (index.isEmpty()) {
                return new TaskError("unmark");
            }
            int number = Integer.parseInt(index);
            assert number >= 0 : "Ehh? Make sure it's a non-negative index!!!";
            return new TaskUnmarker(number, tasks);
        case "delete":
            if (index.isEmpty()) {
                return new TaskError("delete");
            }
            return new TaskDeleter(Integer.parseInt(index), tasks);

        default:
            return new TaskError("other");
        }
    }

    /**
     * Handles all trivia commands
     * @param type The type of trivia commands
     * @param triviafacts The contents of the given trivia commands
     * @return The trivia Command to be executed
     */
    private Command handleTrivia(String type, String triviafacts) {
        switch (type) {
        case "addtrivia":
            try {
                String[] facts = triviafacts.split(" /answer ", 2);
                String question = facts[0];
                String answer = facts[1];

                return new TriviaAdd(question, answer, trivia);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new TaskError("addtrivia");
            }
        case "edittrivia":
            try {
                String[] newFacts = triviafacts.split(" /answer ", 2);
                String newQuestion = newFacts[0];
                String newAnswer = newFacts[1];

                return new TriviaEdit(newQuestion, newAnswer, trivia);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new TaskError("edittrivia");
            }
        case "removetrivia":
            return new TriviaDelete(triviafacts, trivia);
        case "ask":
            return new Ask(triviafacts, trivia);
        default:
            return new TaskError("");
        }
    }
}