package deterministicparrot;


import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Deterministic Parrot task management application.
 */
public class DeterministicParrot {
    private Ui ui = new Ui();
    private TaskList taskList;
    private final Parser parser = new Parser();
    private final Storage storage = new Storage();
    private boolean endParrot = false;

    /**
     * Constructs a DeterministicParrot object.
     * Initializes the task list and sets up command handlers.
     */
    public DeterministicParrot() {
        this.taskList = new TaskList();
        try {
            this.taskList = TaskList.deserialize(storage.load());
        } catch (Exception e) {
            System.out.println("     " + "No saved task list found. Starting with empty task list.");
            this.taskList = new TaskList();
        }
        this.initCommandHandlers();
    }

    /**
     * Constructs a DeterministicParrot object.
     * Initializes the task list and sets up command handlers.
     */
    public DeterministicParrot(Scanner s, StringWriter sw) {
        this.ui = new Ui(s, sw);
        try {
            this.taskList = TaskList.deserialize(storage.load());
        } catch (Exception e) {
            System.out.println("     " + "No saved task list found. Starting with empty task list.");
            this.taskList = new TaskList();
        }
        this.initCommandHandlers();
    }

    /**
     * Main method to start the Deterministic Parrot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DeterministicParrot parrot = new DeterministicParrot();
        System.out.println(parrot.getResponse("list"));
        parrot.poll();
    }

    /**
     * Initializes command handlers for various user commands.
     */
    private void initCommandHandlers() {
        parser.registerHandler("list", args -> printList());
        parser.registerHandler("bye", args -> bye());
        parser.registerHandler("mark", args -> {
            markAsDone(args);
        });
        parser.registerHandler("unmark", args -> {
            markAsUndone(args);
        });
        parser.registerHandler("todo", args -> {
            addToDo(args);
        });
        parser.registerHandler("deadline", args -> {
            addDeadline(args);
        });
        parser.registerHandler("event", args -> {
            addEvent(args);
        });
        parser.registerHandler("delete", args -> {
            deleteTask(args);
        });
        parser.registerHandler("find", args -> {
            findTask(args);
        });
        parser.registerHandler("upcoming", args -> {
            getUpcomingTasks(args);
        });
    }

    /**
     * Saves the task list to a file.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private void dumpTaskListToFile() throws FileNotFoundException {
        assert this.taskList != null : "TaskList should not be null when saving to file.";
        storage.save(this.taskList.serialize());
    }

    /**
     * Adds a task to the list and updates the UI.
     *
     * @param t The task to be added.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addToList(Task t) throws Exception {
        this.taskList.addTask(t);
        this.ui.println("     " + "Got it. I've added this task:");
        this.ui.println("       " + t);
        this.ui.println("     " + "Now you have " + this.taskList.getSize() + " tasks in the list.");
        dumpTaskListToFile();
    }

    /**
     * Marks a task as done and updates the UI.
     *
     * @param args The arguments passed to the mark command.
     * @throws Exception If an error occurs while marking the task.
     */
    private void markAsDone(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.markAsDone(i);
        this.ui.println("    " + "Nice! I've marked this task as done:");
        this.ui.println("       " + t);
        dumpTaskListToFile();
    }

    /**
     * Marks a task as undone and updates the UI.
     *
     * @param toks The arguments passed to the unmark command.
     * @throws Exception If an error occurs while marking the task as undone.
     */
    private void markAsUndone(String[] toks) throws Exception {
        if (toks.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(toks[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.markAsUndone(i);
        this.ui.println("    " + "OK, I've marked this task as not done yet:\n");
        this.ui.println("       " + t);
        dumpTaskListToFile();
    }

    /**
     * Adds a "ToDo" task to the list and updates the UI.
     *
     * @param args The arguments passed to the todo command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addToDo(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ToDo t = new ToDo(taskDescription);
        addToList(t);
    }

    /**
     * Adds a "Deadline" task to the list and updates the UI.
     *
     * @param args The arguments passed to the deadline command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addDeadline(String[] args) throws Exception {
        int byIndex = Arrays.asList(args).indexOf("/by");
        if (byIndex == -1 || byIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid deadline format. Use /by to specify deadline time.");
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        Deadline t = new Deadline(taskName, deadline);
        addToList(t);
    }

    /**
     * Adds an "Event" task to the list and updates the UI.
     *
     * @param args The arguments passed to the event command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addEvent(String[] args) throws Exception {
        int fromIndex = Arrays.asList(args).indexOf("/from");
        int toIndex = Arrays.asList(args).indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex || fromIndex == args.length - 1 ||
            toIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid event format. Use /from and /to to specify event time.");
        }
        String eventName = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex));
        String startTime = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length));
        Event t = new Event(eventName, startTime, endTime);
        addToList(t);
    }

    /**
     * Deletes a task from the list and updates the UI.
     *
     * @param args The arguments passed to the delete command.
     * @throws Exception If an error occurs while deleting the task.
     */
    private void deleteTask(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.deleteTask(i);
        this.ui.println("    " + "Noted. I've removed this task:");
        this.ui.println("       " + t);
        this.ui.println("     " + "Now you have " + this.taskList.getSize() + " tasks in the list.");
        dumpTaskListToFile();
    }

    /**
     * Displays the list of tasks in the UI.
     */
    private void printList() {
        this.ui.println("     " + "Here are the tasks in your list:");
        this.ui.println(this.taskList.formatAsString());
    }

    /**
     * Performs necessary actions before exiting the application.
     *
     * @throws Exception If an error occurs during the exit process.
     */

    private void bye() throws Exception {
        dumpTaskListToFile();
        this.endParrot = true;
        this.ui.bye();
    }

    /**
     * Polls for user input and handles commands until the application is exited.
     */
    private void poll() {
        this.ui.greet();
        try {
            this.taskList = TaskList.deserialize(storage.load());
            assert this.taskList != null :
                "TaskList should be initialized after loading from storage or creating a new one.";
        } catch (Exception e) {
            this.ui.println("     " + "No saved task list found. Starting with empty task list.");
            this.taskList = new TaskList();
        }

        while (true) {
            if (this.endParrot) {
                break;
            }
            String input = this.ui.readCommand();
            if (input.isEmpty()) {
                continue; // Skip empty input
            }
            this.ui.printDash();
            try {
                this.parser.handleCommand(input);
            } catch (Exception e) {
                this.ui.printError(e);
            }
            this.ui.printDash();
        }
    }

    private void findTask(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a search term.");
        }
        String searchTerm = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        List<TaskList.SearchResult> results = this.taskList.findTask(searchTerm);
        this.ui.println("     " + "Here are the matching tasks in your list:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            sb.append("     " + results.get(i).index + ". " + results.get(i).task + "\n");
        }
        this.ui.println(sb.toString());
    }

    private void getUpcomingTasks(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a time.");
        }
        String timeString = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        LocalDateTime beforeTime = DPUtils.dPTryParseDateTime(timeString);
        List<TaskList.SearchResult> results = this.taskList.getUpcomingTasks(beforeTime);
        this.ui.println("     " + "Here are the upcoming deadlines in your list:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            sb.append("     " + results.get(i).index + ". " + results.get(i).task + "\n");
        }
        this.ui.println(sb.toString());
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        //this.ui.printDash();
        try {
            this.parser.handleCommand(input);
        } catch (Exception e) {
            this.ui.printError(e);
        }
        //this.ui.printDash();
        String output = this.ui.flushWriter();
        System.out.println(output);
        return output;
    }
}
