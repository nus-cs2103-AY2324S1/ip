import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Task {
    String list;
    boolean completed;
    TaskType type;

    public Task(String list, TaskType type) {
        this.list = list;
        this.type = type;
    }

    /**
     * Marks the task as completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    public String setMarked() throws DukeException{
        if(this.completed) {
            throw new DukeException("This task has already been marked as done!\n");
        }
        this.completed = true;
        return "";
    }

    /**
     * Marks the task as not completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    public String setUnmarked() throws DukeException{
        if(!this.completed) {
            throw new DukeException("This task has already been marked as not done!\n");
        }
        this.completed = false;
        return "";
    }

    @Override
    public String toString() {
        String typeSymbol = "";
        switch (type) {
            case TODO:
                typeSymbol = "[T]";
                break;
            case EVENT:
                typeSymbol = "[E]";
                break;
            case DEADLINE:
                typeSymbol = "[D]";
                break;
        }
        return typeSymbol + (this.completed ? "[X] " : "[ ] ") + this.list;
    }

}
