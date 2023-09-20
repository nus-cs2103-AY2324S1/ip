package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parser {
    /**
     * Parses the response given by the user.
     *
     * @param s The response of the user.
     * @param tasks The current Tasklist.
     */
    public static String parseResponse(String s, Tasklist tasks, Storage storage) {
        if (s.equals("list")) {
            return Ui.listTasks(tasks);
        }

        if (s.equals("bye")) {
            storage.updateTasks(tasks.getTasks());
            return Ui.goodbye();
        }

        try {
            String[] splitStr = s.split(" ");

            if (splitStr.length == 2 && (splitStr[0].equals("mark") || splitStr[0].equals("unmark"))) {
                try {
                    int itemNumber = Integer.parseInt(splitStr[1]);
                    if (splitStr[0].equals("mark")) {
                        tasks.markDone(itemNumber - 1);
                        return Ui.informTaskDone(tasks.getTask(itemNumber - 1));
                    } else {
                        tasks.markUndone(itemNumber - 1);
                        return Ui.informTaskUndone(tasks.getTask(itemNumber - 1));
                    }
                } catch (NumberFormatException e) {
                    throw new WrongInputException();
                }
            }

            if (splitStr.length == 2 && splitStr[0].equals("delete")) {
                if (tasks.getSize() == 0) {
                    return "No items already, what you want to delete?\n";
                }

                try {
                    int itemNumber = Integer.parseInt(splitStr[1]);
                    Task deletedTask = tasks.getTask(itemNumber - 1);
                    tasks.deleteItem(itemNumber - 1);
                    return Ui.informTaskDeleted(deletedTask, tasks.getSize());
                } catch (NumberFormatException e) {
                    throw new WrongInputException();
                }
            }

            if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")) {
                Task addedTask;
                switch (splitStr[0]) {
                case "todo":
                    String[] todoSplit = s.split("todo");
                    if (todoSplit.length <= 1 || todoSplit[1].trim().equals("")) {
                        throw new EmptyTodoException();
                    }
                    addedTask = new Todo(todoSplit[1].trim());
                    break;
                case "deadline":
                    String splitStr1 = s.split("deadline ")[1];
                    String[] splitStr2 = splitStr1.split(" /by ");
                    String[] splitStr3 = splitStr2[1].split(" ");
                    LocalDateTime dateTime = null;
                    if (splitStr3.length == 2) {
                        dateTime = DateManager.parseDateString(splitStr3[0], splitStr3[1]);
                    }
                    addedTask = dateTime == null ? new Deadline(splitStr2[0], splitStr2[1])
                            : new Deadline(splitStr2[0], dateTime);
                    break;
                case "event":
                    String eSplitStr1 = s.split("event ")[1];
                    String[] eSplitStr2 = eSplitStr1.split(" /");
                    addedTask = new Event(eSplitStr2[0], eSplitStr2[1].substring(5), eSplitStr2[2].substring(3));
                    break;
                default:
                    return "";
                }

                if (tasks.isDuplicate(addedTask)) {
                    return Ui.informDuplicate();
                }

                tasks.addTask(addedTask);
                assert addedTask != null: "Task added should not be null";
                return Ui.informTaskAdded(addedTask, tasks.getSize());
            }

            if (splitStr[0].equals("find") && splitStr.length > 1 && !splitStr[1].equals("")) {
                String subString = s.substring(5);
                ArrayList<Task> filteredList = tasks.filterBySubstring(subString);
                Tasklist newTasklist = new Tasklist(filteredList);
                return Ui.listTasks(newTasklist);
            }

            throw new WrongInputException();
        } catch (Exception e) {
            return "Error: " + e + "\n";
        }

    }
}
