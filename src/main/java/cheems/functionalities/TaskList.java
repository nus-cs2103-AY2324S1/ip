package cheems.functionalities;

import cheems.tasks.Task;
import cheems.tasks.Event;
import cheems.tasks.Todo;
import cheems.tasks.Deadline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 * Represents a task list directly interacts with the user.
 * Interacts with Storage class as well to retrieve and update information to the text database.
 */
public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();
    private int total = 0; // total also indicates the first free slot
    private final Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    private void checkIndexOutOfBoundsHelper(int index) throws IndexOutOfBoundsException {
        if (index >= total || index < 0) {
            String errMsg = String.format("Sorry you do not have task #%d, "
                    + "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }
    }

    /**
     * Creates a task object based on the string parameters given.
     * @param isFromDatabase Represents whether the method is used for creating an object based on string input
     *                       from the database or the end user, since they have different formats.
     *                       If from the database, the isDone status needs to be reflected in the task.
     * @param params Represents the string parameters used to create the new task.
     * @return A new task object.
     */
    private Task identifyCreateTask(boolean isFromDatabase, String... params) throws RuntimeException {
        // If the params variable arguments is from the database
        // There is a single digit 0/1 to represent isDone status
        // Needs to move the start index to read arguments for creating the task 1 position back
        int startIndex = 0;
        boolean isDone = false;
        if (isFromDatabase) {
            startIndex++;
            isDone = params[0].equals("1");
        }

        // Parse the arguments and create the new task
        Keyword keyword = Keyword.valueOf(params[startIndex]);
        Task task = new Todo(params[startIndex + 1]);
        switch (keyword) {
        case DEADLINE:
            assert params.length == 3;
            task = new Deadline(params[startIndex + 1], params[startIndex + 2]);
            break;
        case EVENT:
            assert params.length == 4;
            task = new Event(params[startIndex + 1], params[startIndex + 2], params[startIndex + 3]);
            break;
        default:
            assert false : "Should not reach here!";
        }

        if (isFromDatabase && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Loads the task from the database to the task list.
     */
    public void loadTaskFromDatabase() {
        ArrayList<String[]> dataList = storage.loadData();
        for (String[] params : dataList) {
            Task newTask = identifyCreateTask(true, params);
            list.add(newTask);
            total++;
        }
    }

    /**
     * Adds a new task the user specified to the task list.
     * Updates the database to include this task.
     * @param params An array of strings used to create a new task.
     */
    public String addTaskToDatabase(String... params) {
        Task newTask = identifyCreateTask(false, params);
        list.add(newTask);
        total++;
        storage.saveNewTask(newTask.toStorage());

        String resp = "I have added this task for you!\n" + newTask;
        resp += String.format("\nNow you have %d task(s) in your list!", total);
        return resp;
    }


    public Task getTaskAt(int i) {
        return list.get(i);
    }

    public int getTotal() {
        return total;
    }
    /**
     * Prints all tasks in the current task list.
     */
    public String getTaskList() {
        String resp = "";
        if (total == 0) {
            resp = "You have no task right now:) Happy happy!";
        } else {
            for (int i = 0; i < total; i++) {
                resp += String.format("%d.%s\n", i + 1, list.get(i));
            }
        }
        return resp;
    }

    /**
     * Marks the task at index in the task list as done.
     * @param index The index of task to be marked done.
     * @throws IndexOutOfBoundsException when the index entered is out of range.
     */
    public String markAsDone(int index) throws IndexOutOfBoundsException {
        checkIndexOutOfBoundsHelper(index);
        assert index >= 0 && index < total;
        list.get(index).markAsDone();
        taskListToStorage();

        String resp = "Good job! I've marked this task as done:\n" + list.get(index);
        return resp;
    }

    /**
     * Marks the task at index in the task list as undone.
     * @param index The index of task to be marked undone.
     * @throws IndexOutOfBoundsException when the index entered is out of range.
     */
    public String markAsNotDone(int index) throws IndexOutOfBoundsException {
        checkIndexOutOfBoundsHelper(index);
        assert index >= 0 && index < total;
        list.get(index).markAsNotDone();
        taskListToStorage();

        String resp = "Okie dokie! I've unmarked it for you:\n" + list.get(index);
        return resp;
    }

    /**
     * Deletes the task at index in the task list and updates the database.
     * @param index The index of task to be deleted.
     * @throws IndexOutOfBoundsException when the index entered is out of range.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        checkIndexOutOfBoundsHelper(index);
        assert index >= 0 && index < total;
        Task t = list.get(index);
        list.remove(index);
        total--;
        taskListToStorage();

        String resp = "Noted. I've removed this task:\n"
                + t
                + String.format("\nNow you have %d tasks in your list!", total);
        return resp;
    }

    /**
     * Fuzzy match the input with current tasks.
     * Levenshtein algorithm is used.
     * @param search The array of strings that represents the keyword we need to search for.
     */
    public String findTasks(String search) {
        List<String> descriptions = list.stream()
                .map(Task::getDescription)
                .collect(Collectors.toList());

        // Atomic types are used to ensure thread safety in streams
        AtomicReference<String> resp = new AtomicReference<>("");
        AtomicInteger a = new AtomicInteger(1);

        FuzzySearch.extractSorted(search, descriptions, 60)
                .stream()
                .forEach(x -> {
                    int indexInList = x.getIndex();
                    int currentIndex = a.getAndAdd(1);
                    resp.getAndUpdate(prev -> prev + String.format("%d.%s\n", a.get(), list.get(indexInList)));
                });

        if (resp.get() == "") {
            return "There is no matching tasks in your list! Type 'list' to see what tasks you have:)";
        }

        return "We have found the following similar tasks:\n" + resp.get();
    }

    /**
     * Converts the tasks in list to Storage compatible string format.
     */
    public void taskListToStorage() {
        String resp = "";
        for (int i = 0; i < total; i++) {
            resp += list.get(i).toStorage() + "\n";
        }
        storage.updateWholeDatabase(resp);
    }
}
