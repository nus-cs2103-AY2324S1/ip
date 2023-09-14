package taskmanager;

import taskutility.TaskMatcher;

/**
 * The tasks of type "To-Do."
 */
public class ToDo extends Task {

    private String taskDesc;

    /**
     * Constructs a new To-Do task with the specified user input.
     *
     * @param taskDesc The description of the to-do task.
     */
    public ToDo(String taskDesc) {
        assert taskDesc != null : "taskDesc must not be null";
        this.taskDesc = taskDesc;
    }

    /**
     * Constructs a new To-Do task with the specified description and completion status.
     *
     * @param taskDesc  The description of the to-do task.
     * @param completion The completion status (1 for done, 0 for not done).
     */
    public ToDo(String taskDesc, String completion) {
        assert taskDesc != null : "taskDesc must not be null";
        assert completion != null : "completion must not be null";

        try {
            if (completion.equals("1")) {
                this.taskDesc = taskDesc.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid todo task in the task list!");
            this.taskDesc = null;
        }
    }

    /**
     * Checks if the To-Do task is valid.
     *
     * @return True if the task is valid (not null); otherwise, false.
     */
    public boolean isValid() {
        return taskDesc != null;
    }

    /**
     * Returns a string format of the To-Do task that can be written to the .txt file.
     *
     * @return A string format of the To-Do task to store it into the .txt file.
     */
    @Override
    public String toFileString() {
        String x;
        if (this.isDone) {
            x = "1";
        } else {
            x = "0";
        }
        String str1 = String.format("%s", x);
        return "T | " + str1 + " | " + this.taskDesc;
    }

    /**
     * Generates a string representation of the To-Do task.
     *
     * @return A formatted string representation for displaying to the user.
     */
    @Override
    public String toString() {
        String x;
        if (this.isDone) {
            x = "X";
        } else {
            x = " ";
        }
        String str1 = String.format("[%s] ", x);
        return "[T]" + str1 + this.taskDesc;
    }

    /**
     * Compares this To-Do task to another object for equality.
     *
     * @param o The object for comparison.
     * @return True if the objects are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object o) {
        ToDo toDo = (ToDo) o;
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            return taskDesc.equals(toDo.taskDesc);
        }
    }

    /**
     * Matches the taskDesc with keyword for query.
     *
     * @param keyword The keyword for matching.
     * @return True if there is matching keyword; otherwise, false.
     */
    public boolean isMatch(String keyword) {
        return TaskMatcher.isMatch(keyword, taskDesc);
    }

}
