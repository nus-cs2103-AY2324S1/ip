package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Used to add, delete, mark, show and find tasks in the list.
 *
 * @author Teo Kai Sheng
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create a TaskList.
     *
     * @param taskList The previously saved task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays the current list to the user.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String showList(String[] input) {
        try {
            if (!(input.length == 1 || input[1].strip().equals(""))) {
                throw new DukeException(Ui.showListErrorMessage());
            }
            String output = "Here are the tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                output = output + String.format("\n    %d.%s", i + 1, taskList.get(i).toString());
            }
            System.out.println(output);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks the indicated task as done.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String markTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsDone();
            String output = "Nice! I've marked this task as done:\n" + task.toString();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundErrorMessage();
        } catch (NumberFormatException e) {
            return Ui.markTaskErrorMessage();
        }
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String unmarkTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsUndone();
            String output = "OK, I've marked this task as not done yet:\n" + task.toString();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundErrorMessage();
        } catch (NumberFormatException e) {
            return Ui.unmarkTaskErrorMessage();
        }
    }

    /**
     * Deletes the indicated task.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String deleteTask(String[] input) {
        try {
            int toDelete = Integer.parseInt(input[1]);
            Task task = taskList.get(toDelete - 1);
            String deletedTask = task.toString();
            taskList.remove(toDelete - 1);
            String output = "Noted. I've removed this task:\n"
                    + deletedTask + "\n" + "Number of tasks: " + taskList.size();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundErrorMessage();
        } catch (NumberFormatException e) {
            return Ui.deleteTaskErrorMessage();
        }
    }

    /**
     * Adds an Event to the task list.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String addEvent(String[] input) {
        try {
            String[] s1 = input[1].split("/from", 2);
            String[] s2 = s1[1].split("/to", 2);
            String desc = s1[0].strip();
            String from = s2[0].strip();
            String to = s2[1].strip();
            if (desc.equals("") || from.equals("") || to.equals("")) {
                throw new DukeException(Ui.addEventErrorMessage());
            }
            Event e = new Event(desc, LocalDate.parse(from), LocalDate.parse(to));
            taskList.add(e);
            String output = "Got it. I've added this task:\n"
                    + e.toString() + "\n" + "Number of tasks: " + taskList.size();
            System.out.println(output);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return Ui.addEventErrorMessage();
        } catch (DateTimeParseException e) {
            return Ui.invalidDateErrorMessage();
        }
    }

    /**
     * Adds a Deadline to the task list.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String addDeadline(String[] input) {
        try {
            String[] s = input[1].split("/by", 2);
            String desc = s[0].strip();
            String deadline = s[1].strip();
            if (desc.equals("") || deadline.equals("")) {
                throw new DukeException(Ui.addDeadlineErrorMessage());
            }
            Deadline d = new Deadline(desc, LocalDate.parse(deadline));
            taskList.add(d);
            String output = "Got it. I've added this task:\n"
                    + d.toString() + "\n" + "Number of tasks: " + taskList.size();
            System.out.println(output);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return Ui.addDeadlineErrorMessage();
        } catch (DateTimeParseException e) {
            return Ui.invalidDateErrorMessage();
        }
    }

    /**
     * Adds a ToDo to the task list.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String addToDo(String[] input) {
        try {
            String desc = input[1];
            if (desc.strip().equals("")) {
                throw new DukeException(Ui.addToDoErrorMessage());
            }
            ToDo t = new ToDo(desc);
            taskList.add(t);
            String output = "Got it. I've added this task:\n"
                    + t.toString() + "\n" + "Number of tasks: " + taskList.size();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.addToDoErrorMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays all tasks in the list containing the keyword.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String findTasks(String[] input) {
        try {
            String keyword = input[1];
            if (keyword.strip().equals("")) {
                throw new DukeException(Ui.findTasksErrorMessage());
            }
            String output = "Here are the matching tasks in your list:";
            int counter = 1;
            for (int i = 0; i < taskList.size(); i++) {
                String s = taskList.get(i).toString();
                if (s.contains(keyword)) {
                    output = output + String.format("\n    %d.%s", counter, s);
                    counter++;
                }
            }
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.findTasksErrorMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Updates the indicated task.
     *
     * @param input User input.
     * @return The String representation of the output.
     */
    public String updateTask(String[] input) {
        try {
            String[] s1 = input[1].split("/", 2);
            String[] s2 = s1[1].split(" ", 2);
            String taskNumber = s1[0].strip();
            String updateType = s2[0].strip();
            String newValue = s2[1].strip();
            if (Integer.parseInt(taskNumber) > taskList.size()) {
                throw new DukeException(Ui.taskNotFoundErrorMessage());
            }
            if (taskNumber.equals("") || updateType.equals("") || newValue.equals("")) {
                throw new DukeException(Ui.updateTaskErrorMessage());
            }
            Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
            updateTaskValue(task, updateType, newValue);
            String output = "OK, I've updated this task:\n" + task.toString();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return Ui.updateTaskErrorMessage();
        } catch (NumberFormatException e) {
            return Ui.updateTaskErrorMessage();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.invalidDateErrorMessage();
        }
    }

    /**
     * Updates the task with the new value.
     *
     * @param task The task to be updated.
     * @param toUpdate The field to be updated.
     * @param newValue The new value.
     * @throws DukeException Throws DukeException if the task type is wrong.
     */
    private void updateTaskValue(Task task, String toUpdate, String newValue) throws DukeException {
        switch (toUpdate) {
        case "desc":
            task.updateDescription(newValue);
            break;
        case "by":
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                d.updateByDate(LocalDate.parse(newValue));
            } else {
                throw new DukeException(Ui.wrongTaskTypeErrorMessage());
            }
            break;
        case "from":
            if (task instanceof Event) {
                Event e = (Event) task;
                e.updateFromDate(LocalDate.parse(newValue));
            } else {
                throw new DukeException(Ui.wrongTaskTypeErrorMessage());
            }
            break;
        case "to":
            if (task instanceof Event) {
                Event e = (Event) task;
                e.updateToDate(LocalDate.parse(newValue));
            } else {
                throw new DukeException(Ui.wrongTaskTypeErrorMessage());
            }
            break;
        default:
            throw new DukeException(Ui.updateTaskErrorMessage());
        }
    }
}
