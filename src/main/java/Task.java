/**
 * Encapsulates a Task in the Chat bot.
 *
 * @author Rayson
 */
public class Task {

    String task;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
