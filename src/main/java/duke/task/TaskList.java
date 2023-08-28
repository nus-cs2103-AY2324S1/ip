package duke.task;

import duke.Duke;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void newTodo(ArrayList<String> parsedInput) throws DukeMissingArgumentException {
        try {
            Task newTask = new Todo(parsedInput.get(1));
            this.taskList.add(newTask);
            Ui.showTodoMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void newDeadline(ArrayList<String> parsedInput) throws DukeMissingArgumentException,
            DukeInvalidDateFormatException {
        try {
            Task newTask = new Deadline(parsedInput.get(1), LocalDate.parse(parsedInput.get(2)));
            this.taskList.add(newTask);
            Ui.showDeadlineMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    public void newEvent(ArrayList<String> parsedInput) throws DukeMissingArgumentException,
            DukeInvalidDateFormatException, DukeEndDateBeforeStartDateException {
        try {
            Task newTask = new Event(parsedInput.get(1), LocalDate.parse(parsedInput.get(2)),
                    LocalDate.parse(parsedInput.get(3)));
            this.taskList.add(newTask);
            Ui.showEventMessage(newTask, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }
    public void list() {
        Ui.showListMessage(this.taskList);
    }

    public void markAsDone(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task task = this.taskList.get(i - 1);
            task.markAsDone();
            Ui.showMarkMessage(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void markAsUndone(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task task = this.taskList.get(i - 1);
            task.markAsUndone();
            Ui.showUnmarkMessage(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void delete(ArrayList<String> parsedInput) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(parsedInput.get(1));
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task removedTask = this.taskList.get(i - 1);
            this.taskList.remove(i - 1);
            Ui.showDeleteMessage(removedTask, this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void find(ArrayList<String> parsedInput) throws DukeMissingArgumentException {
        try {
            String target = parsedInput.get(1);
            ArrayList<Task> filteredTasks = new ArrayList<>();
            for (Task task : this.taskList) {
                if (task.getDescription().contains(target)) {
                    filteredTasks.add(task);
                }
            }
            Ui.showFilteredTasks(filteredTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public ArrayList<String> stringify() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Task task : this.taskList) {
            stringList.add(task.toString());
        }
        return stringList;
    }
}
