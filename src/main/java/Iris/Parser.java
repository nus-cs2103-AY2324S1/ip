package Iris;

public class Parser {

    public void parseCommand(Storage taskStorage, ToDoList toDoList, String input) {
        String[] sections = input.split(" ", 2);
        String command = sections[0];
        String rest = sections.length > 1 ? sections[1] : "";
        switch (command) {
            case "bye": {
                Ui.exitMsg();
                System.exit(1);
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
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format.");
                }
                break;
            case "mark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                toDoList.mark(index);
                Task task = toDoList.get(index);
                Ui.markTaskMsg(task);
                break;
            }
            case "unmark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                toDoList.unmark(index);
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
