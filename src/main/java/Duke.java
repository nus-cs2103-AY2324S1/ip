import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String line = "___________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Method to print outro.
     */
    public void outro() {
        System.out.println(line);
        System.out.println("Buh-bye! See you soon!");
        System.out.println(line);
    }

    /**
     * Method that prints chatbot intro
     */
    public void intro() {
        System.out.println(line);
        System.out.println("Hey, I'm joyayaya! What's the move today?");
        System.out.println(line);
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
            if (taskIndex > taskList.size() || taskIndex < 0) {
                throw new IndexOutOfBoundsException("Please enter a valid index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid index.");
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
                    outro();
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

    /**
     * Mehod to write tasks frm ArrayList data structure into .txt file.
     * @param taskList
     */
    public void write(ArrayList<Task> taskList) {

    }

    /**
     * Method to read tasks from .txt file to ArrayList data structure.
     */
    public void read() {

    }



    public static void main(String[] args) throws DukeException {
        Duke dukie = new Duke();
        dukie.intro();
        dukie.handleInput();

    }
}
