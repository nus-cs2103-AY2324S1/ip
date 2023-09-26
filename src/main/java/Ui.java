/**
 * Class handling the output.
 */
public class Ui {
    public static void startMessage() {
        System.out.println("Hello! I'm Chatbot!");
        System.out.println("What can I do for you?");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void inputErrorMessage() {
        System.out.println("I'm sorry, but I don't know what that means!");
    }

    /**
     * Displays an error message arising from mistakes in format.
     *
     * @param type Type of command that is not properly formatted.
     */
    public static void formatErrorMessage(String type) {
        switch(type) {
            case "todo":
                System.out.println("Please use the format \"todo <task description>\"!");
                break;
            case "mark":
                System.out.println("Please use the format \"mark <task number>\"!");
                break;
            case "delete":
                System.out.println("Please use the format \"delete <task number>\"!");
                break;
            case "deadline":
                System.out.println("Please use the format \"deadline <task description> /by DD-MM-YYYY HH:MM\"!");
                break;
            case "event":
                System.out.println("Please use the format \"event <task description> /from DD-MM-YYYY HH:MM " + "/to DD-MM-YYYY HH:MM\"!");
                break;
        }
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public static void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getCount(); i++) {
            int listNumber = i + 1;
            System.out.print(listNumber + ". ");
            if (taskList.getTask(i) instanceof Todo) {
                System.out.print("[T]");
            } else if (taskList.getTask(i) instanceof Deadline) {
                System.out.print("[D]");
            } else if (taskList.getTask(i) instanceof Event) {
                System.out.print("[E]");
            }
            if (taskList.getTask(i).isDone()) System.out.print("[X] ");
            else System.out.print("[ ] ");
            System.out.println(taskList.getTask(i).getDescription());
        }
    }

    /**
     * Handles the output of a delete command.
     *
     * @param removeTask Index of the task to be removed from the list.
     * @param taskList The TaskList object from which the task is to be deleted.
     */
    public static void deleteMessage(int removeTask, TaskList taskList) {
        try {
            if (removeTask < 0 || removeTask >= taskList.getCount()) {
                throw new RuntimeException();
            }
            System.out.println("This task will be removed!");
            if (taskList.getTask(removeTask) instanceof Todo) {
                System.out.print("[T]");
            } else if (taskList.getTask(removeTask) instanceof Deadline) {
                System.out.print("[D]");
            } else if (taskList.getTask(removeTask) instanceof Event) {
                System.out.print("[E]");
            }
            if (taskList.getTask(removeTask).isDone()) System.out.print("[X] ");
            else System.out.print("[ ] ");
            System.out.println(taskList.getTask(removeTask).getDescription());
            taskList.deleteTask(removeTask);
        } catch (Exception e) {
            Ui.formatErrorMessage("delete");
        }
    }

    /**
     * Handles the output of a mark command.
     *
     * @param doneTask Index of the task to be marked as done.
     * @param taskList The TaskList object in which the task is to be marked as done.
     */
    public static void markMessage(int doneTask, TaskList taskList) {
        try {
            if (doneTask < 0 || doneTask >= taskList.getCount()) {
                throw new RuntimeException();
            }
            System.out.println("Well done! This task has been marked as done.");
            if (taskList.getTask(doneTask) instanceof Todo) {
                System.out.print("[T]");
            } else if (taskList.getTask(doneTask) instanceof Deadline) {
                System.out.print("[D]");
            } else if (taskList.getTask(doneTask) instanceof Event) {
                System.out.print("[E]");
            }
            System.out.print("[X] ");
            System.out.println(taskList.getTask(doneTask).getDescription());
            taskList.getTask(doneTask).markAsDone();
        } catch (Exception e) {
            Ui.formatErrorMessage("mark");
        }
    }

    public static void todoMessage(Todo todo, TaskList taskList) {
        taskList.addTask(todo);
        System.out.print("Added this task: [T] ");
        System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
    }

    public static void deadlineMessage(Deadline deadline, TaskList taskList) {
        taskList.addTask(deadline);
        System.out.print("Added this task: [D] ");
        System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
    }

    public static void eventMessage(Event event, TaskList taskList) {
        taskList.addTask(event);
        System.out.print("Added this task: [E] ");
        System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
    }
}