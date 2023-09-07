package brandon.chatbot.tasks;

import java.io.IOException;
import java.util.ArrayList;

import brandon.chatbot.common.DukeIndexOutOfBoundsException;
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int index) throws DukeIndexOutOfBoundsException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task temp = tasks.get(index - 1);
        tasks.remove(index - 1);
    }

    public void markAsDone(int index) throws DukeIndexOutOfBoundsException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task target = tasks.get(index - 1);
        target.setDone(true);
    }

    public void unmark(int index) throws DukeIndexOutOfBoundsException, IOException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task target = tasks.get(index - 1);
        target.setDone(false);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}

