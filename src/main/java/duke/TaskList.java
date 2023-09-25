package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ListIterator;

import dukeuielements.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * This class holds the ArrayList to load all the tasks. Contains important Task manipulation methods.
 */
public class TaskList {
    //stores all the tasks
    private ArrayList<Task> storeTask = new ArrayList<>(1);
    /**
     * Displays the entire list of tasks to user.
     */
    public String userListChoice() {
        ListIterator<Task> ls = storeTask.listIterator();
        return Ui.printList(ls);
    }

    /**
     * Mark or unmark the task.
     *
     * @param taskNumber The task number to be marked/unmarked.
     * @param userMarkerChoice User choice mark/unmark.
     */
    public String userMarkUnmark(String taskNumber, String userMarkerChoice) throws DukeException {
        Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
        return taskItem.changeStatus(userMarkerChoice);
    }

    /**
     * Adds task with given description to ArrayList.
     *
     * @param userDescription Description attached to task.
     */
    public String addToDo(String userDescription) {
        ToDo td = new ToDo(userDescription);
        storeTask.add(td);
        return Ui.printNumberOfEntries(td, this);
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userInputList list that contains the user input.
     */
    public String addDeadline(String[] userInputList) {
        try {
            String[] deadlineList = userInputList[1].split("/", 2);
            String userDescription = deadlineList[0];
            String deadlineBy = deadlineList[1];
            Deadline deadline = new Deadline(userDescription, deadlineBy);
            storeTask.add(deadline);
            return Ui.printNumberOfEntries(deadline, this);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return Ui.invalidDeadlineFormat();
        }
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userInputList list that contains the user input.
     */
    public String addEvent(String[] userInputList) {
        try {
            String[] eventList = userInputList[1].split("/", 3);
            String userDescription = eventList[0];
            String from = eventList[1];
            String to = eventList[2];
            Event event = new Event(userDescription, from, to);
            storeTask.add(event);
            return Ui.printNumberOfEntries(event, this);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return Ui.invalidEventFormat();
        }
    }

    /**
     * Deletes task from ArrayList.
     *
     * @param delUserChoice The task number to be deleted (based on number on list).
     * @throws DukeException  If TaskList is empty or invalid selection by user.
     */
    public String deleteTask(int delUserChoice) throws DukeException {
        if ((delUserChoice - 1) < 0) { //if number entered smaller than 1, array will go negative index.
            throw new DukeException("Invalid Task entered. Please try again...");
        } else if (storeTask.isEmpty()) {
            throw new DukeException("Task Scheduler is empty... Please try again!");
        } else {
            Task itemRemoved = storeTask.remove(delUserChoice - 1);
            return Ui.deleteTaskPrint(itemRemoved, this);
        }
    }

    /**
     * Find task string.
     *
     * @param findThis user input given with find function
     * @return filtered list
     */
    public String findTask(String findThis) {
        ArrayList<Task> filteredList = altFindFunctions(findThis);
        ListIterator<Task> iterFilteredList = filteredList.listIterator();
        if (filteredList.size() == 0) {
            return Ui.emptyList();
        } else {
            return Ui.findTaskPrint(iterFilteredList);
        }
    }
    private ArrayList<Task> altFindFunctions(String findThis) {
        String[] breakDownFindFunction = findThis.split(" ");
        ListIterator<Task> ls = storeTask.listIterator();
        ArrayList<Task> filteredList = new ArrayList<>();
        switch (breakDownFindFunction.length) {
        case 1:
            return normalFind(findThis, ls, filteredList);
        case 2:
            return taskFind(breakDownFindFunction, ls, filteredList);

        default:
            return filteredList;
        }
    }

    private static ArrayList<Task> normalFind(String findThis, ListIterator<Task> ls, ArrayList<Task> filteredList) {
        while (ls.hasNext()) {
            Task current = ls.next();
            String currentDescription = current.getDescription();
            if (currentDescription.contains(findThis)) {
                filteredList.add(current);
            }
        }
        return filteredList;
    }
    private static ArrayList<Task> taskFind(String[] breakDownFindFunction, ListIterator<Task> ls,
                                            ArrayList<Task> filteredList) {
        String findFunctionMainType = breakDownFindFunction[0];
        String findFunctionSubType = breakDownFindFunction[1];
        if (findFunctionMainType.equals("all")) {
            return getTasks(ls, filteredList, findFunctionSubType);
        } else {
            return filteredList;
        }
    }

    private static ArrayList<Task> getTasks(ListIterator<Task> ls, ArrayList<Task> filteredList,
                                            String findFunctionSubType) {
        while (ls.hasNext()) {
            Task current = ls.next();
            String currentTaskType = current.getClass().getSimpleName();
            if (currentTaskType.contains(findFunctionSubType)) {
                filteredList.add(current);
            }
        }
        return filteredList;
    }

    public ArrayList<Task> getStoreTask() {
        return storeTask;
    }
    public int getTaskSize() {
        return storeTask.size();
    }

    /**
     * Save data string.
     *
     * @return save msg
     */
    public String saveData() {
        try {
            //closes file and truncates it
            //@@author adhigop13-reused
            //Reused from ChatGPT
            //with minor modifications
            Files.write(Duke.PATH_OF_DIRECTORY, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            for (int i = 0; i < this.getTaskSize(); i++) {
                String taskToString = this.getStoreTask().get(i).storeToDiskFormat() + "\n";
                Files.write(Duke.PATH_OF_DIRECTORY, taskToString.getBytes(), StandardOpenOption.APPEND);
            }
            return Ui.saveDukeMsg();
            //@@author
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred...";
        }
    }
}
