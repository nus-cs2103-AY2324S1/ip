package bee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import bee.ui.Ui;

/**
 * Represents a list of tasks and provides methods to interact with the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

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
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
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
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        storage.saveTasksToFile();
        return Ui.addTask(task, this.tasks.size());
    }

    /**
     * Lists all tasks in the TaskList along with their indices.
     */
    public String listAllTasks() {
        return Ui.listAllTasks(this.tasks);
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
    public String setTaskDone(int taskIndex) {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            return Ui.returnErrorString(new BeeException("OOPS!! Please enter a valid task number."));
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.setDone();
        storage.saveTasksToFile();
        return Ui.setTaskDone(task);
    }

    /**
     * Marks a task as not done based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to mark as not done.
     * @throws BeeException If there's an issue with marking the task as not done.
     */
    public String setTaskNotDone(int taskIndex) {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            return Ui.returnErrorString(new BeeException("OOPS!! Please enter a valid task number."));
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.setNotDone();
        storage.saveTasksToFile();
        return Ui.setTaskNotDone(task);
    }

    /**
     * Deletes a task based on its index in the TaskList.
     *
     * @param taskIndex The index of the task to delete.
     * @throws BeeException If there's an issue with deleting the task.
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex > tasks.size() || taskIndex < 1) {
            return Ui.returnErrorString(new BeeException("OOPS!! Please enter a valid task number."));
        }
        Task deletedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        storage.saveTasksToFile();
        return Ui.deleteTask(deletedTask, this.tasks.size());
    }

    /**
     * Creates a task of the specified type based on user input and adds it to the TaskList.
     *
     * @param task      The type of task to create.
     * @param userInput The user input specifying the task details.
     * @throws BeeException If there's an issue with creating or adding the task.
     */
    public String createTask(Parser.TaskClass task, String userInput) throws BeeException {
        switch (task) {
        case TODO:
            try {
                String editedInput = userInput.substring(5);
                if (editedInput.isEmpty()) {
                    return Ui.returnErrorString(new BeeException("OOPS!! The description of a todo cannot be empty."));
                }
                Todo todo = new Todo(editedInput);
                return this.addTask(todo);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The description of a todo cannot be empty."));
            }
        case DEADLINE:
            try {
                String editedInput = userInput.substring(9);
                if (editedInput.isEmpty()) {
                    return Ui.returnErrorString(new BeeException("OOPS!! The description of a deadline cannot be empty."));
                }
                String[] splitEditedInput = editedInput.split(" /by ");
                String deadlineDescription = splitEditedInput[0];
                String deadlineDateString = splitEditedInput[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, formatter);

                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                return this.addTask(deadlineTask);
            } catch (DateTimeParseException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! Invalid deadline date format. Please use yyyy-MM-dd HHmm"));
            }
            catch (StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The description of a deadline cannot be empty."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The date of the deadline cannot be empty."));
            }
        case EVENT:
            try {
                String editedInput = userInput.substring(6);
                if (editedInput.isEmpty()) {
                    return Ui.returnErrorString(new BeeException("OOPS!! The description of an event cannot be empty."));
                }
                String[] splitEditedInput = editedInput.split(" /from ");
                String[] splitEditedInput2 = splitEditedInput[1].split(" /to ");
                String eventDescription = splitEditedInput[0];
                String eventStartDate = splitEditedInput2[0];
                String eventEndDate = splitEditedInput2[1];
                Event event = new Event(eventDescription, eventStartDate, eventEndDate);
                return this.addTask(event);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The description of an event cannot be empty."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The date of an event cannot be empty."));
            }
        default:
            return Ui.returnErrorString(new BeeException("I'm sorry, but I don't know what that means."));
        }
    }

    /**
     * Updates a task based on the specified action and user input.
     *
     * @param action    The action to perform on the task (e.g., mark, unmark, delete).
     * @param userInput The user input specifying the task and action.
     * @throws BeeException If there's an issue with performing the task action.
     */
    public String updateTask(Parser.TaskAction action, String userInput) throws BeeException {
        String[] splitInput = userInput.split(" ");
        switch (action) {
        case MARK:
            try {
                int taskIndex = Integer.parseInt(splitInput[1]);
                return this.setTaskDone(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The task number cannot be empty."));
            } catch (NumberFormatException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! You must have entered an invalid task number."));
            }
        case UNMARK:
            try {
                int taskIndex = Integer.parseInt(splitInput[1]);
                return this.setTaskNotDone(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPS!! The task number cannot be empty."));
            } catch (NumberFormatException e) {
                return Ui.returnErrorString(new BeeException("OOPSS!! You must have entered an invalid task number."));
            }
        case DELETE:
            try {
                int taskIndex = Integer.parseInt(splitInput[1]);
                return this.deleteTask(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString(new BeeException("OOPSS!! Please enter a task number"));
            } catch (NumberFormatException e) {
                return Ui.returnErrorString(new BeeException("OOPSS!! You must have entered an invalid task number."));
            }
        default:
            return Ui.returnErrorString(new BeeException("OOPSS!! I can't do that!!!"));
        }
    }

    /**
     * Finds a task that contains a keyword
     * @param userInput
     */
    public String findTasksByKeyword(String userInput) {
        String keyword = userInput.substring(5).trim();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            return Ui.findTasks(matchingTasks);
        }
    }
}
