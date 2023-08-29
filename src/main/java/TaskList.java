import java.util.ArrayList;

public class TaskList {
    public static final ArrayList<Task>  taskList = new ArrayList<>();
    // number tracker
    public static int numOfTasks = 0;
    // adds task into task list
    public static void addTask(String cmd) throws InvalidCommand, EmptyTaskException {
        String type = cmd.split("\\s+")[0]; // grab the first word
        Task newTask;
        switch (type) {
            case "todo":
                newTask = ToDo.interpret(cmd);
                break;
            case "deadline":
                newTask = Deadline.interpret(cmd);
                break;
            case "event":
                newTask = Event.interpret(cmd);
                break;
            default:
                throw new InvalidCommand(cmd);
        }
        taskList.add(newTask);
        numOfTasks++;
        // print statement
        Haste.printLine();
        System.out.println(Haste.INDENT + "Got it. I've added this task:");
        System.out.println(Haste.INDENT + newTask);
        System.out.println(Haste.INDENT + "Now you have " + getNumOfTasks() + " tasks in the list.");
        Haste.printLine();
    }

    public static void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void printList() {
        Haste.printLine();
        for (Task a : taskList) {
            System.out.println(Haste.INDENT + (taskList.indexOf(a) + 1) + ". " + a);
        }
        Haste.printLine();
    }
}
