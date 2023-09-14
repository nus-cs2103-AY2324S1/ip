import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.tasks = taskList;
        this.storage = storage;
    }

    public void listAllTasks() {
        int count = 1;
        System.out.println(Ui.line + "\n" + "Here are the tasks in your list:");
        for (Task task: tasks) {
            String string = String.format("%d.%s", count, task);
            System.out.println(string);
            count++;
        }
        System.out.println(Ui.line);
    }

    public void deleteTask(String taskNumber) throws TaskListEmptyException {
        if (tasks.size() < 1) {
            throw new TaskListEmptyException("OOPS!!! You cannot delete an empty list.");
        }
        int number = Integer.parseInt(taskNumber);
        System.out.println(Ui.line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(number - 1));
        tasks.remove(number - 1);
        // Update the file
        storage.rewriteFile();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Ui.line);
    }



    public void markTaskDone(String taskNumber) {
        Task currentTask = tasks.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setIsDone();
        storage.rewriteFile();
        System.out.println(Ui.line);
        System.out.println("Nice! I've marked this task as done:\n" + currentTask);
        System.out.println(Ui.line);
    }

    public void unmarkTaskDone(String taskNumber) {
        Task currentTask = tasks.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setNotDone();
        System.out.println(Ui.line);
        storage.rewriteFile();
        System.out.println("Nice! I've unmarked this task as done:\n" + currentTask);
        System.out.println(Ui.line);
    }

    public void addNewTask(String userInput) throws DescriptionIncompleteException, IllegalCommandException{
        String[] splitMessage = userInput.split(" ", 2);
        String instruction = splitMessage[0];
        Task task = null;

        if (!(instruction.equals("todo")||instruction.equals("deadline")||instruction.equals("event"))) {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-()");
        }

        if (splitMessage.length < 2) {
            throw new DescriptionIncompleteException("OOPS!!! The description of an instruction cannot be empty.");
        }

        switch (instruction) {
            case "todo":
                task = Todo.createNewTodoTask(splitMessage[1]);
                break;
            case "deadline":
                task = Deadline.createNewDeadlineTask(splitMessage[1]);
                break;
            case "event":
                task = Event.createNewEventTask(splitMessage[1]);
                break;
        }

        tasks.add(task);
        try {
            storage.writeToFile(task.toFileString());
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        System.out.println(Ui.line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Ui.line);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
