package DukePackage;

import java.util.Objects;

public class ChatGUI {
    private final String HORIZONTAL_LINE = " ------------------------------------------------------------\n";
    private final String INTRO = "    Hello! I'm iPbot \n" +
            "    What can I do for you?";
    private final String noDescError = "    OOPS!!! The description of a todo cannot be empty.";
    private final String noCommandError = "    OOPS!!! I'm sorry, but I don't know what that means :-(";


    private final String OUTRO = "    Bye. Hope to see you again soon!\n";

    protected Storage storage = new Storage();

    public ChatGUI() {
        // Constructor body (if needed)
    }

    public String process(String input) {
        String result = HORIZONTAL_LINE;
        String[] parts = input.split(" ");
        switch (parts[0]) {
            case "bye":
                result += OUTRO;
                // write the changes into the file duke.txt
                storage.writeTasksToFile();
                break;
            case "list":
                result += storage.printTaskList();
                break;
            case "mark":
                int id = Integer.parseInt(parts[1]) - 1;
                try {
                    storage.changeTaskMarking(id, true);
                    result += storage.printTaskMarking(id);
                } catch (Exception e) {
                    result += e.getMessage();
                }
                break;
            case "unmark":
                int id2 = Integer.parseInt(parts[1]) - 1;
                try {
                    storage.changeTaskMarking(id2, false);
                    result += storage.printTaskMarking(id2);
                } catch (Exception e) {
                    result += e.getMessage();
                }
                break;
            case "delete":
                int id3 = Integer.parseInt(parts[1]) - 1;
                result += storage.deleteTask(id3);
                break;
            case "find":
                int indexOfFind = input.indexOf("find");
                String toFindString = input.substring(indexOfFind + 5);
                result += storage.printMatchingList(toFindString);
                break;
            case "todo":
                int indexOfTodo = input.indexOf("todo");
                String taskDesc = input.substring(indexOfTodo + 5);
                if (Objects.equals(taskDesc, "")) {
                    result += noDescError;
                    break;
                }
                Task task = new Task(taskDesc, TaskType.TODO, "", "");
                storage.addList(task);
                result += storage.printTaskEntry(task);
                break;
            case "deadline":
                int indexOfDeadline = input.indexOf("deadline");
                int indexOfBy = input.indexOf("/by");
                taskDesc = input.substring(indexOfDeadline + 9, indexOfBy);
                String deadlinePart = "";
                deadlinePart = input.substring(indexOfBy + 3).trim();
                task = new Task(taskDesc, TaskType.DEADLINE, deadlinePart, "");
                storage.addList(task);
                result += storage.printTaskEntry(task);
                break;
            case "event":
                int indexOfEvent = input.indexOf("event");
                int indexOfFrom = input.indexOf("/from");
                int indexOfTo = input.indexOf("/to");
                taskDesc = input.substring(indexOfEvent + 6, indexOfFrom);
                String fromPart = "";
                fromPart = input.substring(indexOfFrom + 5, indexOfTo).trim();
                String toPart = "";
                toPart = input.substring(indexOfTo + 3).trim();
                task = new Task(taskDesc, TaskType.EVENT, fromPart, toPart);
                storage.addList(task);
                result += storage.printTaskEntry(task);
                break;
            default:
                result += noCommandError;
        }
        result += HORIZONTAL_LINE;
        return result;
    }
}
