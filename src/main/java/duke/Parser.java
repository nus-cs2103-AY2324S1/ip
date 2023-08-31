package duke;

/**
 * The Parser class handles the analysis of user inputs and triggers commands accordingly.
 * It interprets user commands and interacts with the UI, storage, tasks and commands.
 */
public class Parser {

    private Commands command;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser instance.
     *
     * @param ui The UI component for displaying messages to the user.
     * @param storage The storage component for loading and saving task data.
     * @param tasks The task list containing existing tasks.
     * @param command The commands component for executing actions on tasks.
     */
    public Parser(Ui ui, Storage storage, TaskList tasks, Commands command) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
        this.command = command;
    }

    /**
     * Analyzes the user input and triggers the appropriate actions.
     * Analyze inputs based on what inputs begin with.
     * If input is related to creating a new task, call next analyzer.
     * If input is related to modifying existing task, call relevant commands.
     * If is none of the above, throw MYBotExceptions accordingly.
     *
     * @param input The user input to be analysed
     */
    public void analyseInput(String input) {
        try {
            if (input.equals("list")) {
                command.listTasks(tasks);
            } else if (input.startsWith("mark ")) {
                int task_index = Integer.parseInt(input.substring(5));
                command.markTasks(task_index);
            } else if (input.startsWith("unmark ")) {
                int task_index = Integer.parseInt(input.substring(7));
                command.unmarkTasks(task_index);
            } else if (input.startsWith("delete ")) {
                int task_index = Integer.parseInt(input.substring(7));
                command.removeTasks(task_index);
            } else if(input.startsWith("find ")) {
                String keyword = input.substring(5);
                command.findTasks(keyword);
            } else if (input.isEmpty()) {
                throw new MYBotExceptions.UnknownCommandException();
            } else if (!input.startsWith("todo ") && !input.startsWith("deadline ") && !input.startsWith("event ")) {
                throw new MYBotExceptions.InvalidTaskException();
            } else {
                analyseAddTaskInput(input);
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Analyzes an input that is related to creating a new Task.
     * Determines type of task according to what inputs begin with.
     * Checks if details of tasks keyed in based on format.
     * Calls respective commands if input is keyed in correct format,
     * or else throw an exception to tell users what's wrong.
     *
     * @param input The details of the Task
     */
    public void analyseAddTaskInput(String input) {
        try {
            if(command == null) {
                throw new NullPointerException();
            }
            if (input.startsWith("todo ")) {

                String description = input.substring(5);
                if (!description.isBlank()) {
                    command.addTodoTask(description);
                } else {
                    throw new MYBotExceptions.EmptyDetailsException("description", "todo");
                }

            } else if (input.startsWith("deadline ")) {

                if(!input.contains(" /by")) {
                    throw new MYBotExceptions.InvalidInputException("deadline", "duedate");
                }

                String description = input.substring(9, input.indexOf(" /by "));
                String by = (input.substring(input.indexOf(" /by ") + 4)).substring(1);

                if (description.isBlank()) {
                    throw new MYBotExceptions.EmptyDetailsException("description", "deadline");
                } else if (by.isBlank()) {
                    throw new MYBotExceptions.EmptyDetailsException("duedate", "deadline");
                } else {
                    command.addDeadlineTask(description, by);
                }

            } else if ((input.startsWith("event "))) {

                if(!input.contains(" /from")) {
                    throw new MYBotExceptions.InvalidInputException("event", "start time");
                } else if (!input.contains(" /to")) {
                    throw new MYBotExceptions.InvalidInputException("event", "end time");
                }

                String description = input.substring(6, input.indexOf(" /from "));
                String from = (input.substring(input.indexOf(" /from ") + 6, input.indexOf(" /to"))).substring(1);
                String to = (input.substring(input.indexOf(" /to ") + 4)).substring(1);

                if (description.isBlank()) {
                    throw new MYBotExceptions.EmptyDetailsException("description", "event");
                } else if (from.isBlank()) {
                    throw new MYBotExceptions.EmptyDetailsException("start time", "event");
                } else if (to.isBlank()) {
                    throw new MYBotExceptions.EmptyDetailsException("end time", "event");
                } else {
                    command.addEventTask(description, from, to);
                }
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
