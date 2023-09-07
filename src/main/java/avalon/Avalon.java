package avalon;

public class Avalon {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Avalon(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        storage.loadTasks(this.tasks);
    }

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
                        throw new IllegalArgumentException("Please provide a description and a deadline       (use /by).");
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
                        throw new IllegalArgumentException("Please provide a description, a starting time,     and an ending time (use /from and /to).");
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
                default:
                    throw new IllegalArgumentException("I humbly apologize, but thy words remain a mystery     to me...");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Avalon("src/main/data/Avalon.txt").run();
    }
}
