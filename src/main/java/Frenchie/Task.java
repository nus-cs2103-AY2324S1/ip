package Frenchie;

/**
 * Represents a Task that is stored in the Task list of the Frenchie chatbot.
 * <p>
 * The task class has 2 attributes, isCompleted and taskName.
 * isCompleted is a boolean attribute, returning true if the task has been completed
 * and false if the task is incomplete.
 * taskName is a String that stores the name of the task, such as 'Read Book'.
 * <p>
 */
public class Task {
    public boolean isCompleted;
    public String taskName;


    public Task() {
        isCompleted = false;
    }
    /**
     *  Constructs a new Task object, with a default false value for
     *  isCompleted as tasks inputted into the task list are incomplete.
     *  Takes in a String which is the name of the task.
     */
    public Task(String name) {
        this.taskName = name;
        isCompleted = false;
    }

    /**
     *  Marks Task as complete by setting the isCompleted attribute to true.
     */
    public void setIsCompleted() {
        isCompleted = true;
    }

    /**
     *  Marks Task as incomplete by setting the isCompleted attribute to false.
     */
    public void setIsIncomplete() {
        isCompleted = false;
    }

    /**
     *  Returns the isCompleted attribute to check if Task is completed.
     */
    public boolean checkCompletion() {
        return isCompleted;
    }
    @Override
    public String toString() {
        String indicator = " ";
        if (isCompleted) {
            indicator = "X";
        }
        return "[" + indicator + "] " + taskName;
    }
}
