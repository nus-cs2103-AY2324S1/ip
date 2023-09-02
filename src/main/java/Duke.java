import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    public static final DateTimeFormatter QUERY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");

    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.createFileIfNotExists();

        Ui ui = new Ui();
        ui.printWelcome();

        TaskList taskList;

        try {
            taskList = storage.readTasksFromFile();
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
            String[] commandResult = ui.processCommand();
            try {
                if (commandResult.length == 0) {
                    System.out.println("Nothing happened!");
                } else {
                    String command = commandResult[0];
                    Command commandName = Command.commandEnum(command);
                    if(commandName == null){
                        System.out.println("Error: " + command + " is not a valid command!");
                    }
                    else {
                        if (command.equalsIgnoreCase("bye")) {
                            ui.printExit();
                            break;
                        }
                        String commandArgs = commandResult[1];
                        switch (commandName) {
                            case LIST:
                                if (!commandArgs.isEmpty()) {
                                    LocalDateTime queryDate = LocalDateTime.parse(
                                            commandArgs + " 0000", QUERY_DATE_TIME_FORMATTER);
                                    taskList.listTasks(ui, queryDate);
                                }
                                else{
                                    taskList.listTasks(ui);
                                }
                                break;
                            case MARK:
                                int markIndex = Parser.checkIndexArg(commandArgs, taskList.getTasksSize());
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
                                int unmarkIndex = Parser.checkIndexArg(commandArgs, taskList.getTasksSize());
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
                                int deleteIndex = Parser.checkIndexArg(commandArgs, taskList.getTasksSize());
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
            storage.writeTasksToFile(taskList);
        }
    }
}
