import java.util.ArrayList;

public class TaskList {
    private static final String INDENTATION = "    ";
    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    public String formatList() {
        String str = "";
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            str = str + (i + 1) + ". " + taskList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    public void handleMarking(String commandNum, String status) throws DukeException {

        try {
            int index = Integer.parseInt(commandNum) - 1;

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid Task Index provided!");
            }
            Task selectedTask = taskList.get(index);
            if (status.equals("mark")) {
                selectedTask.markTask();
                System.out.println(formatOutput("Nice! I've marked the task as done:\n   " +
                        INDENTATION + selectedTask));
            } else if (status.equals("unmark")) {
                selectedTask.unmarkTask();
                System.out.println(formatOutput("OK, I've marked this task as not done yet:\n   " +
                        INDENTATION + selectedTask));
            }
            this.saveList();
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Task Index provided!");
        }
    }

    public void handleDelete(String commandNum) throws DukeException {
        try {
            int index = Integer.parseInt(commandNum) - 1;

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid Task Index provided!");
            }
            Task selectedTask = taskList.remove(index);
            this.saveList();
            System.out.println(formatOutput("Noted. I've removed this task:\n   " +
                    INDENTATION + selectedTask + "\n " + INDENTATION + "Now you have " +
                    taskList.size() + " tasks in the list."));

        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Task Index provided!");
        }
    }

    public void handleToDo(String task) {
        ToDo item = new ToDo(task);
        taskList.add(item);
        this.saveList();
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list."));
    }

    public void handleDeadline(String desc, String by) throws DukeException {

        Deadline item = new Deadline(desc, by);
        taskList.add(item);
        this.saveList();
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list."));
    }

    public void handleEvent(String desc, String start, String end) throws DukeException {
        Event item = new Event(desc, start, end);
        taskList.add(item);
        this.saveList();
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list."));
    }

    public String exportList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.exportData()).append("\n");
        }
        return sb.toString();
    }

    public void saveList() {
        String data = this.exportList();
        storage.saveData(data);
    }


}
