import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Duke {

    /**
     * Instance handling all the user interface
     */
    private final Ui ui;

    /**
     * Path to the storage, default is ./data/data.txt
     */
    private Storage storage;

    /**
     * Instance handling the tasks state
     */
    private final TaskList taskList;

    /**
     * Construct a new Duke object which uses filePath as the storage
     *
     * @param filePath - path to the storage file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.welcomeMessage();
        this.taskList = new TaskList(null);

        // try to establish a connection to the file
        // set this.storage to null if not possible
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            this.ui.errorMessage("has some internal problem and is unable to help you today, please contact quacks mum");
            this.ui.println(e.getMessage());
            this.storage = null;
        } catch (DukeBadInputException e) {
            this.ui.errorMessage(filePath + " is not a text file, please provide a file!");
            this.storage = null;
        }
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }


    /**
     * Entry point of the software
     */
    private void run() {
        // prints out a preview of current tasks
        this.handleList();
        this.ui.lineBreak();

        if (this.storage != null) {
            this.loadStorage();
            this.collectCommand();
            this.storage.close();
        }
        // Goodbye Message
        this.ui.goodbyeMessage();
    }

    // TODO
    private void loadStorage() {
        List<String> storedInput;

        // retrieve data from storage class
        try {
            storedInput = this.storage.readFile();
        } catch (IOException e) {
            this.ui.unexpectedError(e.getMessage());
            return;
        }
        // parse and store data while looking out for data corruption
        boolean corrupted = false;
        for (String s : storedInput) {
            try {
                this.createTask(new Parser(s), s);
            } catch (DukeBadInputException e) {
                corrupted = true;
                this.ui.println(e.getMessage());
            }
        }

        if (corrupted) {
            // refresh memory to only include non-corrupted tasks
            try {
                if (!this.storage.rewriteAll(this.taskList.getAllTask())) {
                    this.ui.unexpectedError("not all tasks were successfully written, please contact my mother :( ");
                }
            } catch (IOException e) {
                this.ui.unexpectedError("error when rewriting to storage: " + e.getMessage());
            }
            this.ui.unexpectedError("some of quack's memory are corrupted, please contact quack's mum if this continuously happens ");

        }

        // prints to the user an overview of stored tasks
        this.handleList();
        this.ui.lineBreak();
    }

    /**
     * Handles the collection and execution of the command
     */
    private void collectCommand() {
        String input = this.ui.readCommand();
        while (!input.equalsIgnoreCase("bye")) {
            this.ui.lineBreak();
            try {
                Parser command = new Parser(input);
                switch (command.getCommand()) {
                    case LIST:
                        this.handleList();
                        break;
                    case MARK:
                    case UNMARK:
                        this.handleMark(command.getCommand() == Commands.MARK,
                                this.validateIndex(command.getIndex() - 1));
                        break;
                    case DELETE:
                        this.handleDeletion(this.validateIndex(command.getIndex() - 1));
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        this.handleTask(command, input);
                        break;
                    case UNRECOGNISED:
                        this.ui.println("Quack does not understand your command!!");
                        this.ui.println(
                                "Quack only understands these commands: list, mark, unmark, delete, todo, deadline, event");
                        break;

                }
            } catch (DukeBadInputException e) {
                this.ui.errorMessage(e.getMessage());
            } catch (NumberFormatException e) {
                this.ui.errorMessage(e.getMessage()
                        + ", quack only understand numbers, please input a numeric value!");
            } catch (DateTimeParseException e) {
                this.ui.errorMessage(e.getMessage());
                this.ui.println("Quack only understands date in this format: YYYY-MM-DD HH:MM, do give the hours in 24hours format");
            } finally {
                this.ui.lineBreak();
                input = this.ui.readCommand();
            }

        }
        this.ui.close();
    }

    /**
     * Handles the execution of list
     */
    private void handleList() {
        if (this.taskList.length() == 0) {
            this.ui.println("Quack Quack, you have not entered any tasks yet!");
            this.ui.println("Create new tasks with the todo, deadline or event command");
            return;
        }
        this.ui.println("Quack Quack, here are the tasks in quack's memory:");
        for (int i = 0; i < this.taskList.length(); i++) {
            this.ui.println((i + 1) + "." + this.taskList.get(i));
        }
    }

    /**
     * validate the index to ensure it is within range
     *
     * @param index - the index of the task in question
     * @return the validated index
     * @throws DukeBadInputException if the index given is not within range
     */
    private int validateIndex(int index) throws DukeBadInputException {

        // validate input
        if (this.taskList.length() == 0) {
            throw new DukeBadInputException(
                    "Quack currently has no task remembered and cannot execute your command, add one now??");
        }

        // validate input
        if (index >= this.taskList.length()) {
            throw new DukeBadInputException("Quack does not remember having a task: " + (index + 1) +
                    " Quack only remember till task " + (this.taskList.length()));
        }
        return index;
    }

    /**
     * Handles the deletion command
     *
     * @param index the index of the task being deleted
     */
    private void handleDeletion(int index) {
        Task removed = this.taskList.remove(index);
        try {
            if (!this.storage.rewriteAll(this.taskList.getAllTask())) {
                this.ui.unexpectedError("not all tasks were successfully written, please contact my mother :( ");
            }
        } catch (IOException e) {
            this.ui.unexpectedError("error when writing to storage: " + e.getMessage());
        }
        this.ui.println("Quack! I have removed this task:");
        this.ui.println(removed.toString());
        this.ui.println("Quack! Quack is currently remembering " + this.taskList.length() + " tasks.");

    }

    /**
     * Handles the mark/unmark command
     *
     * @param mark  - true if it is a mark command else false
     * @param index - index of the task in question
     */
    private void handleMark(boolean mark, int index) {
        Task task = this.taskList.get(index);
        // only toggle if mark != completed as if they are the same then there is no
        // effect
        String resp;
        if (mark != task.isCompleted()) {
            task.toggleCompleted();
            resp = mark ? "Quack! Congrats for finishing the task!" : "Quack, I've marked this task as not done yet :(";
        } else {
            resp = mark ? "Quack! This task is already done QUACK!"
                    : "Quack! you cant unmark something that isn't done yet!!";
        }
        this.ui.println(resp);
        this.ui.println(task.toString());
    }

    /**
     * Creates a new task with the given param
     *
     * @param param - parser object containing information on the new task.
     * @param input - user input used to generate param
     */
    public Task createTask(Parser param, String input) throws DukeBadInputException {
        Commands type = param.getCommand();
        Task newTask;
        if (type == Commands.UNRECOGNISED) {
            throw new DukeBadInputException("Quack must have some memory issue, unable to recall this task: " + input);
        }
        if (type == Commands.TODO) {
            newTask = new Todo(param.getParam(), input);
        } else if (type == Commands.DEADLINE) {
            newTask = new Deadline(param.getFlag("/by"), param.getParam(), input);
        } else {
            newTask = new Event(param.getFlag("/from"), param.getFlag("/to"), param.getParam(), input);
        }
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Handles the creation of new tasks
     *
     * @param param - parser object containing information on the new task.
     * @param input - user input used to generate param
     * @throws DukeBadInputException if quack cannot remember anymore tasks
     */
    private void handleTask(Parser param, String input) throws DukeBadInputException {
        if (this.taskList.length() >= 100) {
            throw new DukeBadInputException("quack cannot remember any more tasks!!");
        }

        Task newTask = this.createTask(param, input);


        if (!this.storage.writeToFile(input)) {
            this.ui.unexpectedError("unable to write to storage");
            return;
        }
        this.ui.println("Quack! I have added this task:");
        this.ui.println(newTask.toString());
        this.ui.println("Quack! Quack is currently remembering " + this.taskList.length() + " tasks.");

    }
}
