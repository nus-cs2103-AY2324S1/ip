package bee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to interact with the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param tasks The initial list of tasks to populate the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList without displaying any messages.
     *
     * @param task The task to be added.
     */
    public void quietlyAddTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Adds a task to the TaskList and displays a confirmation message.
     *
     * @param task The task to be added.
     * @throws BeeException If there's an issue with adding the task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task: ~Bzzz~");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list. ~Bzzz~");
    }

    /**
     * Lists all tasks in the TaskList along with their indices.
     */
    public void listAllTasks() {
        System.out.println("Here are the tasks in your list: ~Bzzz~");
        for (int i = 0; i < this.tasks.size() ; i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
        }
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to mark as done.
     * @throws BeeException If there's an issue with marking the task as done.
     */
    public void setTaskDone(int taskIndex) throws BeeException {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        this.tasks.get(taskIndex - 1).setDone();
        System.out.println("Nice! I've marked task " + taskIndex +  " as done: ~Bzzz~");
        System.out.println(this.tasks.get(taskIndex - 1).toString());
    }

    /**
     * Marks a task as not done based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to mark as not done.
     * @throws BeeException If there's an issue with marking the task as not done.
     */
    public void setTaskNotDone(int taskIndex) throws BeeException {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        this.tasks.get(taskIndex - 1).setNotDone();
        System.out.println("OK, I've marked task " + taskIndex +  " as not done yet: ~Bzzzz");
        System.out.println(this.tasks.get(taskIndex - 1).toString());
    }

    /**
     * Deletes a task based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to delete.
     * @throws BeeException If there's an issue with deleting the task.
     */
    public void deleteTask(int taskIndex) throws BeeException {
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        Task deletedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        System.out.println("Okies~. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
     * Creates a task of the specified type based on user input and adds it to the TaskList.
     *
     * @param task      The type of task to create.
     * @param userInput The user input specifying the task details.
     * @throws BeeException If there's an issue with creating or adding the task.
     */
    public void createTask(Parser.TaskClass task, String userInput) throws BeeException {
        switch (task) {
            case TODO:
                try {
                    String editedInput = userInput.substring(5);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(editedInput);
                    this.addTask(todo);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                }
                break;
            case DEADLINE:
                try {
                    String editedInput = userInput.substring(9);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    String[] splitEditedInput = editedInput.split(" /by ");
                    String deadlineDescription = splitEditedInput[0];
                    String deadlineDateString = splitEditedInput[1];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, formatter);

                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                    this.addTask(deadlineTask);
                } catch (DateTimeParseException e) {
                    throw new BeeException("OOPS!! Invalid deadline date format. Please use yyyy-MM-dd HHmm");
                }
                catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of the deadline cannot be empty.");
                }
                break;
            case EVENT:
                try {
                    String editedInput = userInput.substring(6);
                    if (editedInput.isEmpty()) {
                        throw new BeeException("OOPS!! The description of an event cannot be empty.");
                    }
                    String[] splitEditedInput = editedInput.split(" /from ");
                    String[] splitEditedInput2 = splitEditedInput[1].split(" /to ");
                    String eventDescription = splitEditedInput[0];
                    String eventStartDate = splitEditedInput2[0];
                    String eventEndDate = splitEditedInput2[1];
                    Event event = new Event(eventDescription, eventStartDate, eventEndDate);
                    this.addTask(event);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of an event cannot be empty.");
                }
                break;

            default:
                this.addTask(new Task(userInput));
        }
    }

    /**
     * Updates a task based on the specified action and user input.
     *
     * @param action    The action to perform on the task (e.g., mark, unmark, delete).
     * @param userInput The user input specifying the task and action.
     * @throws BeeException If there's an issue with performing the task action.
     */
    public void updateTask(Parser.TaskAction action, String userInput) throws BeeException {
        String[] splitInput = userInput.split(" ");
        switch (action) {
            case MARK:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    this.setTaskDone(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The task number cannot be empty.");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPS!! You must have entered an invalid task number.");
                }
                break;
            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    this.setTaskNotDone(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The task number cannot be empty.");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                }
                break;
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(splitInput[1]);
                    this.deleteTask(taskIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPSS!! Please enter a task number");
                } catch (NumberFormatException e) {
                    throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                }
                break;
            default:
                throw new BeeException("OOPSS!! I can't do that!!!");
        }
    }

    public void findTasksByKeyword(String userInput) {
        String keyword = userInput.substring(5).trim();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list: ~Bzzz~");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
            }
        }
    }
}
