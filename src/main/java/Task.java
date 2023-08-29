public class Task {

    private String task;
    private boolean done;

    public Task(String task) throws DukeException {

        // Throws error if there is no task description.
        if (task.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.done = false;
    }

    /**
     * Function to print task, together with whether it is marked
     * done or not done.
     * @return String
     */
    public String printTask() {
        if (this.done) {
            return String.format("[X] %s", this.task);
        } else {
            return String.format("[] %s", this.task);
        }
    }

    /**
     * Function to toggle done or undone, depending on parameter.
     * @param keyword
     */
    public void toggleDone(String keyword) {
        if (keyword.equals("mark")) {
            this.done = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.printTask());
        } else {
            this.done = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.printTask());
        }
    }

    public void toggleDoneFromStorage(String keyword) {
        if (keyword.equals("mark")) {
            this.done = true;
        } else {
            this.done = false;
        }
    }

    public String addToStorage() {
        if (this.done) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }
}
