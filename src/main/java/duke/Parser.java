package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /*
     * Method that handles input with the command "mark" or "unmark".
     * 
     * @param command the command that the user inputs: either "mark" or "unmark"
     * @param input   what the user inputs 
     * @return        the task number that the user wants to mark or unmark
     */
    public static int getTaskNumber(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        // remove leading and trailing whitespaces
        String trimmedTaskNum = taskNum.trim();
        return Integer.valueOf(trimmedTaskNum);
    }

    public boolean parse(String input) {
        int n = this.taskList.getSize();
        boolean toBreak = false;

        if (input.equals("bye")) {
            toBreak = true;
        } else if (input.equals("")) {
            DukeExceptionHandler.handleEmptyInput();
        } else if (input.equals("list")) {
            this.taskList.displayList();
        } else if (input.contains("unmark")) {
            // mark is in the word unmark so this case has to go first
            int taskNum = getTaskNumber("unmark", input);
            this.taskList.markTaskAsUndone(taskNum);
            this.storage.saveFile(this.taskList);
        } else if (input.contains("mark")) {
            int taskNum = getTaskNumber("mark", input);
            this.taskList.markTaskAsDone(taskNum);
            this.storage.saveFile(this.taskList);
        } else if (input.contains("todo")) {
            try {
                ToDo newToDo = new ToDo(input);
                this.storage.saveWhenAddTask(newToDo, this.taskList);
                this.ui.printAddedTask(newToDo, n + 1);
            } catch (IllegalArgumentException e) {
                DukeExceptionHandler.printErrorMsg(e.getMessage());
            }
        } else if (input.contains("deadline")) {
            try {
                Deadline newDeadline = new Deadline(input);
                this.storage.saveWhenAddTask(newDeadline, this.taskList);
                this.ui.printAddedTask(newDeadline, n + 1);
            } catch (IllegalArgumentException e) {
                DukeExceptionHandler.printErrorMsg(e.getMessage());
            } 
        } else if (input.contains("event")) {
            try {
                Event newEvent = new Event(input);
                this.storage.saveWhenAddTask(newEvent, this.taskList);
                this.ui.printAddedTask(newEvent, n + 1);
            } catch (IllegalArgumentException e) {
                DukeExceptionHandler.printErrorMsg(e.getMessage());
            } 
        } else if (input.contains("delete")) {
            try {
                int taskNum = getTaskNumber("delete", input);
                Task task = taskList.deleteTask(taskNum - 1);
                this.storage.saveFile(this.taskList);
                this.ui.printDeletedTask(task, n - 1);
            } catch (IndexOutOfBoundsException e) {
                int taskNum = getTaskNumber("delete", input);
                DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            }
            
        } else {
            DukeExceptionHandler.handleUnseenInput();
        }

        return toBreak;
    }
}
