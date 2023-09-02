import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Duke {
    public static final DateTimeFormatter QUERY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
    private static final String SAVE_FILE = "data/saved_tasks.csv";

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage(SAVE_FILE);
        this.ui = new Ui();
    }

    public void run() {
        this.ui.printWelcome();

        try {
            this.taskList = this.storage.readTasksFromFile();
        } catch (TaskFormatException e) {
            System.out.println(e.getMessage());
            return;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        while(true) {
            ui.readCommand();
            ui.printDivider();
            Pair<String, Map<String, String>> commandResult = ui.processCommand();
            try {
                if (commandResult.getFirst().length() == 0) {
                    System.out.println("Nothing happened!");
                } else {
                    String command = commandResult.getFirst();
                    Command commandName = Command.commandEnum(command);
                    if(commandName == null){
                        System.out.println("Error: " + command + " is not a valid command!");
                    }
                    else {
                        if (command.equalsIgnoreCase("bye")) {
                            ui.printExit();
                            break;
                        }
                        Map<String, String> commandArgs = commandResult.getSecond();
                        switch (commandName) {
                            case LIST:
                                if (!commandArgs.get("").isEmpty()) {
                                    LocalDateTime queryDate = LocalDateTime.parse(
                                            commandArgs.get("") + " 0000", QUERY_DATE_TIME_FORMATTER);
                                    taskList.listTasks(ui, queryDate);
                                }
                                else{
                                    taskList.listTasks(ui);
                                }
                                break;
                            case MARK:
                                int markIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                                if (markIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to mark: " + commandArgs);
                                }
                                Pair<Task, Boolean> taskMark = taskList.markTask(markIndex);
                                if (taskMark.getSecond()) {
                                    ui.printMarkTask(taskMark.getFirst(), true);
                                } else {
                                    ui.printAlreadyMarkedTask(taskMark.getFirst(), true);
                                }
                                break;
                            case UNMARK:
                                int unmarkIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                                if (unmarkIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to unmark: " + commandArgs);
                                }
                                Pair<Task, Boolean> taskUnmark = taskList.unmarkTask(unmarkIndex);
                                if (taskUnmark.getSecond()) {
                                    ui.printMarkTask(taskUnmark.getFirst(), false);
                                } else {
                                    ui.printAlreadyMarkedTask(taskUnmark.getFirst(), false);
                                }
                                break;
                            case TODO:
                                if (commandArgs.isEmpty()) {
                                    throw new CommandArgumentException("Task description cannot be empty!");
                                }
                                ToDo toDo = taskList.addToDoWithArgs(commandArgs);
                                ui.printAddedItem(toDo, "todo");
                                break;
                            case DEADLINE:
                                Deadline deadline = taskList.addDeadlineWithArgs(commandArgs);
                                ui.printAddedItem(deadline, "deadline");
                                break;
                            case EVENT:
                                Event event = taskList.addEventWithArgs(commandArgs);
                                ui.printAddedItem(event, "event");
                                break;
                            case DELETE:
                                int deleteIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                                if (deleteIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to delete: " + commandArgs);
                                }
                                Task task = taskList.deleteTask(deleteIndex);
                                ui.printDeletedItem(task);
                                break;
                        }
                    }
                }
            }
            catch(CommandArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            ui.printDivider();
            this.storage.writeTasksToFile(taskList);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
