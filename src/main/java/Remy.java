import java.nio.file.Path;
import java.nio.file.Paths;

// CS2103T Website Increment description-reused
// Reused the example code from the website.
public class Remy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Remy(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatbotException e) {
            Ui.printLongSandwich(e.toString());
            tasks = new TaskList();
        }
    }


    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatbotException e) {
                Ui.printError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {

        Path filePath = Paths.get(".", "data", "remy.ser");
        new Remy(filePath).run();

        /*
        Storage storage = new Storage(filePath);
        TaskList taskList = storage.load();
        Ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String taskType = parseTaskType(input);
            try {
                if (taskType.equals("bye")) {
                    Ui.printExitMessage();
                    break;
                } else if (taskType.equals("list")) {
                    Ui.printShortSandwich(taskList.toString());
                } else if (taskType.equals("mark")) {
                    // Marks item as done
                    if (input.length() < 6) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsDone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        storage.save(taskList);
                        Ui.printShortSandwich(content);

                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("unmark")) {
                    // Marks item as undone
                    if (input.length() < 8) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsUndone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        // saveTasksToFile(filePath, taskList);
                        storage.save(taskList);
                        Ui.printShortSandwich(content);
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("delete")) {
                    if (input.length() < 8) throw new ChatbotException("missing info lah.");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        String task = taskList.get(index).toString();
                        taskList.remove(index);
                        String content = "Done. Can you don't be so troublesome?\n" + task;
                        // saveTasksToFile(filePath, taskList);
                        storage.save(taskList);
                        Ui.printShortSandwich(content);
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("todo")) {
                    if (input.length() < 6) throw new ChatbotException("missing info lah.");
                    String description = input.substring(5);
                    Todo temp = new Todo(description);
                    taskList.add(temp);
                    storage.save(taskList);
                    addTask(temp, taskList.size());
                } else if (taskType.equals("deadline")) {
                    if (input.length() < 10) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        Deadline temp = new Deadline(parts[0], parts[1]);
                        taskList.add(temp);
                        storage.save(taskList);
                        addTask(temp, taskList.size());
                    } else {
                        throw new ChatbotException("wrong format lah.");
                    }
                } else if (taskType.equals("event")) {
                    if (input.length() < 7) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length == 3) {
                        Event temp = new Event(parts[0], parts[1], parts[2]);
                        taskList.add(temp);
                        storage.save(taskList);
                        addTask(temp, taskList.size());
                    } else {
                        throw new ChatbotException("wrong format lah.");
                    }
                } else {
                    throw new ChatbotException("that's not a command.");
                }
            } catch (ChatbotException e) {
                Ui.printLongSandwich(e.toString());
            } catch (DateTimeParseException e) {
                System.out.println("Don't you know how to write a date?: " + e.getMessage());
            }
        }
        */
    }

}
