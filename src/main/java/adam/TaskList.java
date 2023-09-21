package adam;

import adam.exception.NoTagException;
import adam.exception.NumberException;
import adam.exception.OutOfBoundException;
import adam.tasks.Deadline;
import adam.tasks.Event;
import adam.tasks.Task;
import adam.tasks.ToDo;

import java.util.ArrayList;

/**
 * This class holds all the methods that is realted to the task list such as adding and deleting tasks.
 */
public class TaskList {
    private Ui ui = new Ui();
    private boolean isActive = true;
    private ArrayList<Task> tasks;

    /**
     * Initializes the array needed to run this program from the hard disk.
     *
     * @param list ArrayList obtained from reading the file from the hard disk.
     */
    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Deletes the Task object from the ArrayList according to its index.
     *
     * @return String message saying whixh item was deleted.
     */
    public String deleteTask( String[] tokens) {
        if (tokens.length != 2) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        if (number > getSize()) {
            throw new OutOfBoundException();
        }
        Task curr = tasks.get(number - 1);
        tasks.remove(number - 1);
        return ui.delete(curr, tasks.size());
    }

    /**
     * Adds a Todo Object to the ArrayList.
     *
     * @param text Text inside the todo.
     */
    public String addTodo(String text) {
        ToDo curr = new ToDo(text);
        tasks.add(curr);
        return ui.addTodo(curr, tasks.size());
    }

    /**
     * Adds a Deadline Object to the ArrayList.
     *
     * @param text Text inside the deadline.
     * @param by By what time the deadline is due.
     */
    public String addDeadline(String text, String by) {
        Deadline curr = new Deadline(text, by);
        tasks.add(curr);
        return ui.addDeadline(curr, tasks.size());
    }

    /**
     * Adds an Event Object to the ArrayList.
     *
     * @param text Text inside the event.
     * @param from From what time does the event start.
     * @param to To what time does the event ends.
     */
    public String addEvent(String text, String from, String to) {
        Event curr = new Event(text, from, to);
        tasks.add(curr);
        return ui.addEvent(curr, tasks.size());
    }

    /**
     * Lists all the existing tasks inside the task list as a String.
     */
    public String list() {
        String sentence =  ui.list() + "\n";
        int count = 1;
        for (Task item: tasks) {
            String task = count + ". " + item.toString()+"\n";
            sentence = sentence + task;
            count++;
        }
        return sentence;
    }

    /**
     * Marks the intended Tasks as complete according to their index.
     *
     * @return String message saying which item was marked.
     */
    public String markAsDone(String[] tokens) {
        if (tokens.length != 2) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        if (number > getSize()) {
            throw new OutOfBoundException();
        }
        Task curr = tasks.get(number - 1);
        curr.markAsDone();
        assert curr.getStatusIcon().equals("X") : "This task should be unmarked";
        return ui.mark();
    }

    /**
     * Unmarks the intended Tasks as uncomplete according to their index.
     *
     * @return String message saying which item was unmarked.
     */
    public String unmarkAsDone(String[] tokens) {
        if (tokens.length != 2) {
            throw new NumberException();
        }
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (number > getSize()) {
            throw new OutOfBoundException();
        }
        Task curr = tasks.get(number - 1);
        curr.unmarkAsDone();
        assert curr.getStatusIcon().equals(" ") : "This task should be unmarked";
        return ui.unmark();
    }

    /**
     * Returns a boolean that indicates whether the program is still runnning or not.
     *
     * @return Boolean.
     */
    public boolean isRunning() {
        return isActive;
    }

    /**
     * Ends the program.
     */
    public String bye() {
        isActive = false;
        return ui.bye();
    }

    /**
     * Saves the array to the storage.
     *
     * @param storage Storage Object.
     */
    public void save (Storage storage) {
        storage.write(tasks);
    }

    /**
     * Returns the size of the array.
     *
     * @return Size of the array.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds the task that contains the same word as the param.
     *
     * @param item String that is being searched.
     * @return  String listing out all the items that match the find input.
     */
    public String find(String item) {
        ArrayList<Task> matches = new ArrayList<>();
        int count = 1;
        for (Task task : tasks) {
           if (task.search(item)) {
               matches.add(task);
           }
        }
        if (matches.size() == 0) {
            return ui.apologize();
        } else {
            String sentence = ui.search() + "\n";
            for (Task match : matches) {
                String task = count + ". " + match.toString() + "\n";
                sentence = sentence + task;
                count++;
            }
            return sentence;
        }
    }

    /**
     * Tags the specified item to have the tag of the input.
     *
     * @param tokens an array of Strings from the user input separated by empty space.
     * @return String message saying which item was tagged what it was tagged with.
     */
    public String tagTask(String[] tokens) {
        if(tokens.length < 3) {
            throw new NoTagException();
        }
        if (!tokens[1].matches("[0-9]+")) {
            throw new NumberException();
        }
        int number = Integer.valueOf(tokens[1]);
        if (number > getSize()) {
            throw new OutOfBoundException();
        }
        String description = "";
        for(int i = 2 ; i < tokens.length; i++) {
            description = tokens[i] + " ";
        }
        Task curr = tasks.get(number - 1);
        curr.tag(description);
        return ui.tag(description);
    }
}
