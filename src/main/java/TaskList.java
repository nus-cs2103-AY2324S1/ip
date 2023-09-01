import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static final String line = "___________________________________________";
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to handle TODOs
     * @param descr the task description
     */
    public void handleTodo(String descr) {
        try {
            ToDo newTodo = new ToDo(descr);
            newTodo.checkValidity();
            taskList.add(newTodo);
            System.out.println("Okie! I've added this ToDo to your task list!");
            System.out.println(newTodo);
            System.out.println("Now you've got " + taskList.size() + " tasks in your list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle Events
     * @param descr the task description
     */
    public void handleEvent(String descr) {
        try {
            Event newEvent = new Event(descr);
            newEvent.checkValidity();
            taskList.add(newEvent);
            System.out.println("Okie! I've added this Event to your task list!");
            System.out.println(newEvent);
            System.out.println("Now you've got " + taskList.size() + " tasks in your list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle Deadlines
     * @param descr the task description
     */
    public void handleDeadline(String descr) {
        try {
            Deadline newDeadline = new Deadline(descr);
            newDeadline.checkValidity();
            taskList.add(newDeadline);
            System.out.println("Okie! I've added this Deadline to your task list!");
            System.out.println(newDeadline);
            System.out.println("Now you've got " + taskList.size() + "  tasks in your list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to mark task
     * @param task
     */
    public void mark(String task) throws DukeException {
        String[] parts = task.split(" ");
        if (parts.length < 2) {
            throw new DukeException("Which task do you want to mark as done?");
        }
        String index = parts[1];
        int taskIndex = 0;
        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid index.");
        }
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException("Please enter a valid index.");
        }
        Task taskChanged = taskList.get(taskIndex);
        String action = parts[0];
        try {
            if (action.equals("mark")) {
                taskChanged.markDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                taskChanged.markUndone();
                System.out.println("Nice! I've marked this task as undone:");
            }
            System.out.println(taskChanged);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method deletes task from taskList.
     * @param task The instructions containing index of task to be deleted.
     */
    public void delete(String task) throws DukeException {
        String[] segments = task.split(" ");
        if (segments.length < 2) {
            throw new DukeException("Which task do you want to delete?");
        }
        String index = segments[1];
        int taskIndex = 0;
        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid index."); //e.g. delete hi
        }
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        Task deletedTask = taskList.get(Integer.parseInt(index) - 1);
        taskList.remove(deletedTask);

        System.out.println(line);
        System.out.println("Deleted the following task: ");
        System.out.println(deletedTask);
        System.out.println(line);
    }

    /**
     * Method to print taskList.
     */
    public void printList() throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("You have no tasks in your list! Yay!");
        } else {
            int index = 1;
            for (Task task : taskList) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }

    /**
     * Method to handle inputs.
     * Entry point linking terminal to system.
     */
    public void handleInput() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        KeywordEnum keywordEnum = KeywordEnum.assign(task);

        while (true) {
            switch(keywordEnum) {
                case LIST:
                    try {
                        printList();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case BYE:
                    sc.close();
                    //Ui.outro();
                    return;
                case TODO:
                    handleTodo(task);
                    break;
                case DEADLINE:
                    handleDeadline(task);
                    break;
                case EVENT:
                    handleEvent(task);
                    break;
                case DELETE:
                    try {
                        delete(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case MARK:
                case UNMARK:
                    try {
                        this.mark(task);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("This is not a valid task.");
            }
            task = sc.nextLine();
            keywordEnum = KeywordEnum.assign(task);
        }
    }
}
