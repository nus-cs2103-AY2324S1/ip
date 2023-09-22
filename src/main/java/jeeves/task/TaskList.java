package jeeves.task;

import java.util.ArrayList;

/**
 * Contains logic related to the task list
 */
public class TaskList {

    /**
     * The arraylist used to track tasks.
     * Due to how the taskCount variable is used as the id and
     * array access position, index 0 will always be unused.
     * taskList is effectively 1-indexed
     */
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public void addTaskAtIndex(int index, Task t) {
        taskList.add(index, t);
    }

    public void setTask(int index, Task t) {
        taskList.set(index, t);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        taskList.get(index).setStatus(true);
    }

    public void unmarkTask(int index) {
        taskList.get(index).setStatus(false);
    }

    public String getTaskListDataAsString() {
        // Starts by creating the text to write to the output file
        StringBuilder sb = new StringBuilder();
        for (Task currTask : taskList) {
            // If the task is already deleted from the list, (represented as null object)
            // don't write it to the file
            if (currTask != null) {
                // Determines what type of Task is being handled currently for printing purposes
                if (currTask instanceof Todo) {
                    sb.append("T|");
                } else if (currTask instanceof Deadline) {
                    sb.append("D|");
                } else {
                    sb.append("E|");
                }

                // Writes the status of the task
                if (currTask.isDone()) {
                    sb.append("1|");
                } else {
                    sb.append("0|");
                }

                // Writes the description and other tracked data.
                if (currTask instanceof Todo) {
                    sb.append(currTask.getDesc())
                            .append("\n");
                } else if (currTask instanceof Deadline) {
                    sb.append(currTask.getDesc())
                            .append("|")
                            .append(((Deadline) currTask).getDeadline())
                            .append("\n");
                } else {
                    sb.append(currTask.getDesc())
                            .append("|")
                            .append(((Event) currTask).getStartTime())
                            .append("|")
                            .append(((Event) currTask).getEndTime())
                            .append("\n");
                }
            }
        }
        return sb.toString();
    }

    public String searchFor(String searchTerm) {
        StringBuilder sb = new StringBuilder();
        boolean hasMatch = false;
        for (Task currTask: taskList) {
            // Process the object as a string if it is not null, else skip over it
            if (currTask != null) {
                String currDesc = currTask.getDesc();
                if (currDesc.contains(searchTerm) && !hasMatch) {
                    // For the first match, append a success message to the output string
                    sb.append("Here are the matching tasks Master: \n");
                    sb.append(currTask.toString());
                    sb.append("\n");
                    hasMatch = true;
                } else if (currDesc.contains(searchTerm)) {
                    // For every match, append it to the output string
                    sb.append(currTask.toString());
                    sb.append("\n");
                }
            }
        }
        if (!hasMatch) {
            // If no matches are found at all, simply returns this error message
            sb.append("I could not find anything matching '").append(searchTerm).append("' Master");
        }
        return sb.toString();
    }
}
