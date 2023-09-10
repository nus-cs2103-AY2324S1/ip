package bob;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import bob.exception.MissingDatesException;
import bob.exception.MissingTaskException;
import bob.exception.WrongInputException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskType;
import bob.task.Todo;

/**
 * Contains list of tasks, and operations that alter it.
 */
public class TaskList {

    public ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Generates the appropriate type of Task based on user input
     * Throws exceptions due to incorrect user input
     * @param description is of the form e.g. "event read /from 2pm /to 4pm"
     * @return the relevant Task
     * @throws WrongInputException for unrecognised input.
     * @throws MissingTaskException when task name is missing.
     * @throws MissingDatesException when start and end of Event is missing.
     */
    public Task generateTask(String description)
            throws WrongInputException, MissingTaskException,
            MissingDatesException, DateTimeParseException {

        // Split by the first " " into type, and task details
        String[] task = description.split(" ", 2);
        TaskType taskType;

        try {
            taskType = Enum.valueOf(TaskType.class, task[0]);
        } catch (Exception e) {
            throw new WrongInputException();
        }

        if (task.length == 1) {
            throw new MissingTaskException();
        }

        String taskDetails = task[1];

        if (taskType.equals(TaskType.deadline)) {
            return new Deadline(taskDetails);
        } else if (taskType.equals(TaskType.event)) {
            return new Event(taskDetails);
        } else {
            return new Todo(taskDetails);
        }
    }

    /**
     * Adds a Task to lst. Writes modified lst to bob.txt.
     * Handles exceptions that occur due to wrong input/ missing requirements
     * @param description is of the form e.g. "event read /from 2pm /to 4pm"
     * @return message for adding a Task
     */
    public String[] addToList(String description) {
        try {
            Task taskObj = generateTask(description);
            lst.add(taskObj);
            return new String[]{"New task added: ", "\t" + taskObj.toString(),
                "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")};
        } catch (WrongInputException e) {
            return new String[]{e.message};
        } catch (MissingTaskException e) {
            return new String[]{e.message};
        } catch (MissingDatesException e) {
            return new String[]{e.message};
        } catch (DateTimeParseException e) {
            return new String[]{"Please input valid date!"};
        }
    }

    /**
     * Prints out the Tasks on lst.
     * @return display of lst
     */
    public String[] displayList() {

        if (lst.isEmpty()) {
            return new String[] { "You currently have no tasks." };
        }

        List<String> tasks = IntStream.range(1, lst.size() + 1)
                .mapToObj(i -> i + ". " + lst.get(--i).toString())
                .collect(Collectors.toList());

        tasks.add(0, "Here are your tasks!");

        return tasks.toArray(new String[0]);
    }

    /**
     * Marks Task as done or undone at a specified index.
     * Writes modified lst to bob.txt.
     * @param index of Task to be marked
     * @param doneOrNot states whether the Task is done or not
     * @return message for marking a Task
     */
    public String[] markDoneOrNot(int index, boolean doneOrNot) {
        lst.get(index - 1).setDoneOrNot(doneOrNot);
        String statement = doneOrNot ? "Nice! You completed a task!" : "... This is now undone.";
        return new String[]{statement, "\t" + lst.get(index - 1).toString()};
    }

    /**
     * Deletes Task at specified index from list.
     * Writes modified lst to bob.txt.
     * @param index of Task to be deleted
     * @return message for deleting a Task
     */
    public String[] deleteTask(int index) {
        String taskStr = lst.get(index - 1).toString();
        lst.remove(index - 1);
        return new String[]{"I've removed this task from list: ", "\t" + taskStr,
            "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")};
    }

    public String[] findTasks(String keyword) {

        List<String> tasksStream = lst.stream()
                .filter(tsk -> tsk.getName()
                .contains(keyword))
                .map(Object::toString)
                .collect(Collectors.toList());

        Stream<Integer> indexStream = IntStream.range(0, tasksStream.size()).boxed();

        List<String> tasksFound = indexStream
                .map(i -> (++i) + ". " + tasksStream.get(--i))
                .collect(Collectors.toList());

        tasksFound.add(0, "Here are the matching tasks: ");

        return tasksFound.size() > 1
                ? tasksFound.toArray(new String[0])
                : new String[]{"There are no matching tasks..."};
    }
}
