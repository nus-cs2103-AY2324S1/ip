/**
 * Class handling the output.
 */
public class Ui {
    public static String startMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Hello! I'm Chatbot!");
        stringBuilder.append("What can I do for you?");

        return stringBuilder.toString();
    }

    public static String endMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Bye. Hope to see you again soon!");

        return stringBuilder.toString();
    }

    public static String inputErrorMessage() {
        return "I'm sorry, but I don't know what that means!";
    }

    /**
     * Displays an error message arising from mistakes in format.
     *
     * @param type Type of command that is not properly formatted.
     */
    public static String formatErrorMessage(String type) {
        StringBuilder stringBuilder = new StringBuilder();

        switch(type) {
            case "todo":
                stringBuilder.append("Please use the format \"todo <task description>\"!");
                break;
            case "mark":
                stringBuilder.append("Please use the format \"mark <task number>\"!");
                break;
            case "delete":
                stringBuilder.append("Please use the format \"delete <task number>\"!");
                break;
            case "deadline":
                stringBuilder.append("Please use the format \"deadline <task description> /by DD-MM-YYYY HH:MM\"!");
                break;
            case "event":
                stringBuilder.append("Please use the format \"event <task description> /from DD-MM-YYYY HH:MM " + "/to DD-MM-YYYY HH:MM\"!");
                break;
        }

        return stringBuilder.toString();
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public static String listTasks(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Here is the list of relevant tasks:\n");
        for (int i = 0; i < taskList.getCount(); i++) {
            int listNumber = i + 1;
            stringBuilder.append(listNumber + ". ");
            if (taskList.getTask(i) instanceof Todo) {
                stringBuilder.append("[T]");
            } else if (taskList.getTask(i) instanceof Deadline) {
                stringBuilder.append("[D]");
            } else if (taskList.getTask(i) instanceof Event) {
                stringBuilder.append("[E]");
            }
            if (taskList.getTask(i).isDone()) stringBuilder.append("[X] ");
            else stringBuilder.append("[ ] ");
            stringBuilder.append(taskList.getTask(i).getDescription());
        }

        return stringBuilder.toString();
    }

    /**
     * Handles the output of a delete command.
     *
     * @param removeTask Index of the task to be removed from the list.
     * @param taskList The TaskList object from which the task is to be deleted.
     */
    public static String deleteMessage(int removeTask, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (removeTask < 0 || removeTask >= taskList.getCount()) {
                throw new RuntimeException();
            }
            stringBuilder.append("This task will be removed!\n");
            if (taskList.getTask(removeTask) instanceof Todo) {
                stringBuilder.append("[T]");
            } else if (taskList.getTask(removeTask) instanceof Deadline) {
                stringBuilder.append("[D]");
            } else if (taskList.getTask(removeTask) instanceof Event) {
                stringBuilder.append("[E]");
            }
            if (taskList.getTask(removeTask).isDone()) stringBuilder.append("[X] ");
            else stringBuilder.append("[ ] ");
            stringBuilder.append(taskList.getTask(removeTask).getDescription());
            taskList.deleteTask(removeTask);
        } catch (Exception e) {
            return formatErrorMessage("delete");
        }

        return stringBuilder.toString();
    }

    /**
     * Handles the output of a mark command.
     *
     * @param doneTask Index of the task to be marked as done.
     * @param taskList The TaskList object in which the task is to be marked as done.
     */
    public static String markMessage(int doneTask, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (doneTask < 0 || doneTask >= taskList.getCount()) {
                throw new RuntimeException();
            }
            stringBuilder.append("Well done! This task has been marked as done.\n");
            if (taskList.getTask(doneTask) instanceof Todo) {
                stringBuilder.append("[T]");
            } else if (taskList.getTask(doneTask) instanceof Deadline) {
                stringBuilder.append("[D]");
            } else if (taskList.getTask(doneTask) instanceof Event) {
                stringBuilder.append("[E]");
            }
            stringBuilder.append("[X] ");
            stringBuilder.append(taskList.getTask(doneTask).getDescription());
            taskList.getTask(doneTask).markAsDone();
        } catch (Exception e) {
            return formatErrorMessage("mark");
        }

        return stringBuilder.toString();
    }

    public static String todoMessage(Todo todo, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        taskList.addTask(todo);
        stringBuilder.append("Added this task: [T] ");
        stringBuilder.append(taskList.getTask(taskList.getCount() - 1).getDescription());

        return stringBuilder.toString();
    }

    public static String deadlineMessage(Deadline deadline, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        taskList.addTask(deadline);
        stringBuilder.append("Added this task: [D] ");
        stringBuilder.append(taskList.getTask(taskList.getCount() - 1).getDescription());

        return stringBuilder.toString();
    }

    public static String eventMessage(Event event, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        taskList.addTask(event);
        stringBuilder.append("Added this task: [E] ");
        stringBuilder.append(taskList.getTask(taskList.getCount() - 1).getDescription());

        return stringBuilder.toString();
    }

    public static String findMessage(String searchTerm, TaskList taskList) {
        TaskList resultList = new TaskList();
        for (int i = 0; i < taskList.getCount(); i++) {
            String taskDescription = taskList.getTask(i).getDescription();
            for (int j = 0; j <= taskDescription.length() - searchTerm.length(); j++) {
                if (taskDescription.substring(j, j+ searchTerm.length()).equals(searchTerm)) {
                    resultList.addTask(taskList.getTask(i));
                    break;
                }
            }
        }
        return listTasks(resultList);
    }
}