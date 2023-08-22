public class Task {
    /* The name of this task */
    String name;
    /* The state whether the task is done or not*/
    boolean state;

    /** The constructor of tasks
     *  The state is not done by default
     *
     * @param name The name of this Task
     * **/
    public Task(String name) {
        this.name = name;
        this.state = false;
    }

    /** The method to mark the state of this task as done
     *  If the task has already been marked, throw an exception
     */
    public void mark() throws DukeException{
        if (state) {
            throw new DukeException("☹ OOPS!!! This task has already be marked!\n");
        }else {
            System.out.println("Nice! I've marked this task as done:");
            this.state = true;
            System.out.println(this + "\n");
        }
    }

    /** The method to unmark the state of this task as not done
     * If the task has not been marked yet, throw an exception
     */
    public void unmark() throws DukeException{
        if (!state) {
            throw new DukeException("☹ OOPS!!! This task hasn't be marked yet!\n");
        }else {
            System.out.println("OK, I've marked this task as not done yet:");
            this.state = false;
            System.out.println(this + "\n");
        }
    }

    /** A private method to show out the state of this task
     *
     * @return The visible state of this task
     */
    private String getState() {
        return state ? "[X] " : "[ ] ";
    }

    /** The method to display this task with its state
     *
     * @return The method to display this task with its state
     */
    @Override
    public String toString() {
        return getState() + name;
    }
}
