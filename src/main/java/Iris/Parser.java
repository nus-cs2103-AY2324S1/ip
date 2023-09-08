package iris;

/**
 * The class responsible for parsing user commands in the Iris application.
 */
public class Parser {

    /**
     * Parses and executes user commands.
     *
     * @param taskStorage The storage object for tasks.
     * @param toDoList    The to-do list containing tasks.
     * @param input       The user input command to be parsed.
     */
    public void parseCommand(Storage taskStorage, ToDoList toDoList, String input) {
        String[] sections = input.split(" ", 2);
        String command = sections[0];
        String rest = sections.length > 1 ? sections[1] : "";
        switch (command) {
            case "bye": {
                Ui.exitMsg();
                System.exit(0);
            }

            case "list": {
                Ui.printTasks(toDoList);
                break;
            }
            case "todo":
            case "deadline":
            case "event":
                try {
                    ToDoList.addTask(toDoList, command, rest);
                } catch (EmptyTaskDescriptorsException e) {
                    System.out.println(e.toString());
                }
                break;
            case "mark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                toDoList.markTask(index);
                Task task = toDoList.get(index);
                Ui.markTaskMsg(task);
                break;
            }
            case "unmark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                toDoList.unmarkTask(index);
                Task task = toDoList.get(index);
                Ui.unmarkTaskMsg(task);
                break;
            }
            case "delete": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                ToDoList.deleteTask(toDoList, index);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown command.");
            }
        }
        taskStorage.writeTask(toDoList);
    }
}
