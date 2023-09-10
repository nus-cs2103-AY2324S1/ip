package avalon;

/**
 * The main class for the Duke ChatBot (Avalon).
 */
public class Avalon {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an Avalon instance with the specified file path for task storage.
     */
    public Avalon() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/data/Avalon.txt");
        this.tasks = new TaskList();
        storage.loadTasks(this.tasks);
    }

    /**
     * Runs the Avalon application, allowing users to manage tasks using a command-line interface.
     */
    public void run() {
        int taskIndex;
        String description;
        String[] parts;

        ui.greetMessage();

        while (true) {
            String userInput = ui.getUserInput();
            String[] inputStr = userInput.split(" ");
            String command = inputStr[0];

            try {

                // Handle various commands
                switch (command) {
                case "bye":
                    storage.saveTasks(tasks);
                    ui.byeMessage();
                    return;
                case "list":
                    ui.showTasksList(tasks);
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markDone();
                        ui.showMarkMessage(tasks, taskIndex);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be marked.");
                    }
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markNotDone();
                        ui.showUnmarkMessage(tasks, taskIndex);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be unmarked.");
                    }
                    break;
                case "todo":
                    description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }

                    ToDo todo = new ToDo(description);
                    tasks.addTask(todo);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "deadline":
                    parts = userInput.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Please provide a description and a deadline "
                                + "(use /by).");
                    }
                    description = parts[0];
                    String by = parts[1];

                    Deadline deadline = new Deadline(description, by);
                    tasks.addTask(deadline);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "event":
                    parts = userInput.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Please provide a description, a starting time, "
                                + "and an ending time (use /from and /to).");
                    }
                    description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    Event event = new Event(description, from, to);
                    tasks.addTask(event);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task deletedTask = tasks.get(taskIndex);
                        tasks.removeTask(taskIndex);
                        ui.showDeleteTaskMessage(tasks, deletedTask);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be deleted.");
                    }
                    break;
                case "find":
                    String keyword = userInput.substring(5).trim();
                    findTasksByKeyword(keyword);
                    break;
                default:
                    throw new IllegalArgumentException("I humbly apologize, but thy words remain a mystery to me...");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }

    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions
     * and displays the matching tasks.
     *
     * @param keywords The keywords to search for in task descriptions.
     */
    private void findTasksByKeyword(String... keywords) {
        TaskList matchingTasks = new TaskList();

        for (String keyword : keywords) {
            for (Task task : tasks.tasks()) {
                if (task.description.contains(keyword)) {
                    matchingTasks.addTask(task);
                }
            }
        }

        ui.showFindMessage(matchingTasks);
    }

    /**
     * The entry point of the Avalon application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of Avalon and start the application
        new Avalon().run();
    }

    public String getResponse(String input) {
        return "Arthur reiterates: " + input;
    }
}
