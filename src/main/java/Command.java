public class Command {
    private Backend backend;
    private SystemText systemText;
    private TaskList taskList;

    public Command(Backend backend, SystemText systemText, TaskList taskList) {
        this.backend = backend;
        this.systemText = systemText;
        this.taskList = taskList;
    }

    // Add to do task
    public String handleToDo(String input) {
        String[] keyword = input.split(" ", 2);
        // Create new To Do task from input
        ToDo task = new ToDo(false, keyword[1]);
        // Add new To Do into task list
        this.taskList.addTask(task);
        // Save new task into backend
        this.backend.saveTask(task);
        // Return system message to inform action
        return this.systemText.printAddTask(task);
    }

    // Add deadline task
    public String handleDeadline(String input) {
        // Break input down into variables
        String[] firstKeyword = input.split("/by");
        String deadline = firstKeyword[1];
        String[] secondKeyword = firstKeyword[0].split(" ", 2);
        String name = secondKeyword[1];
        // Create new Deadline task from variables
        Deadline task = new Deadline(false, name, deadline);
        // Add new Deadline into task list
        this.taskList.addTask(task);
        // Save new task into backend
        this.backend.saveTask(task);
        // Return system message to inform action
        return this.systemText.printAddTask(task);
    }

    // Add event task
    public String handleEvent(String input) {
        // Break input down into variables
        String[] firstSplit = input.split("/from");
        String[] secondSplit = firstSplit[0].split(" ", 2);
        String name = secondSplit[1];
        String[] thirdSplit = firstSplit[1].split("/to");
        String start = thirdSplit[0];
        String end = thirdSplit[1];
        // Create new Event task from variables
        Event task = new Event(false, name, start, end);
        // Add new Event into task list
        this.taskList.addTask(task);
        // Save new task into backend
        this.backend.saveTask(task);
        // Return system message to inform action
        return this.systemText.printAddTask(task);
    }

    // Mark task
    public String handleMark(String input) {
        String[] keyword = input.split(" ");
        int taskNumber = Integer.parseInt(keyword[1]);
        Task task = this.taskList.getTask(taskNumber);
        // Update backend, important to update backend first!
        this.backend.updateTask(task, 1);
        // Mark task as done
        task.mark();
        // Return system message to inform action
        return this.systemText.printUpdateTask(task);
    }

    // Un-mark task
    public String handleUnMark(String input) {
        String[] keyword = input.split(" ");
        int taskNumber = Integer.parseInt(keyword[1]);
        Task task = this.taskList.getTask(taskNumber);
        // Update backend
        this.backend.updateTask(task, 0);
        // un-mark task
        task.unMark();
        // Return system message to inform action
        return this.systemText.printUpdateTask(task);
    }
}
