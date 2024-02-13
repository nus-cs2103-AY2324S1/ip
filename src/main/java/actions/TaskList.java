package actions;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;


import java.util.ArrayList;

public class TaskList {
    private Ui ui;
    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
        this.ui = new Ui();
    }

    /**
     * Adds a task to the current ArrayList
     *
     * @param input the task that is to be added
     */
    public void chadAddList(Task input) {
            taskArrayList.add(input);
    }

    /**
     * Removes a list from the current ArrayList
     *
     * @param index of the task to be removed
     * @return the name of the removed task
     */
    public String chadRemoveList(int index){
        try {
            Task removed = taskArrayList.remove(index - 1);
            return removed.getName();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index! Try again!");
        }
        return null;
    }


    /**
     * Marks a task as completed
     *
     * @param index of task to be marked
     */
    public void chadMarkTask(int index) {
        taskArrayList.get(index - 1).markTask();
    }

    /**
     * Unmarks a task that was previously marked
     *
     * @param index of task to be unmarked
     */
    public void chadUnmarkTask(int index) {
        taskArrayList.get(index - 1).unmarkTask();
    }

    /**
     * Finds tasks that matches the keyword
     *
     * @param keyword string to match the name of tasks
     */
    public void chadFindTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getName().contains(keyword)) {
                matched.add(task);
            }
        }
        if (matched.isEmpty()) {
            ui.chadMatchNotFoundOutput();
        } else {
            ui.chadMatchFoundOutput(matched);
        }
        assert false: "Should always return an output";
    }

    /**
     * Returns string of tasks that matches the keyword
     *
     * @param keyword string to match the name of tasks
     * @return output with string of matched tasks
     */
    public String displayChadFindTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getName().contains(keyword)) {
                matched.add(task);
            }
        }
        if (matched.isEmpty()) {
            return ui.displayChadMatchNotFoundOutput();
        } else {
            return ui.displayChadMatchFoundOutput(matched);
        }
    }

    /**
     * Updates a detail of a task
     *
     * @param details input from user
     * @return output string after updating task
     */
    public String updateTask(String[] details) {
        int index = Integer.valueOf(details[0]) - 1;
        String type = details[1];
        String command = details[2];
        String update = details[3];

        switch(type) {
        case "T":
            if (!(taskArrayList.get(index) instanceof Todo)) {
                return ui.displayWrongInstanceTask("Todo");
        }

            if (command.equals("/name")) {
                System.out.println("1");
                System.out.println(taskArrayList.get(index));
                taskArrayList.get(index).updateName(update);
                System.out.println(taskArrayList.get(index));
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else {
                System.out.println("2");
                return ui.displayWrongCommand();
            }
        case "D":
            if (!(taskArrayList.get(index) instanceof Deadline)) {
                return ui.displayWrongInstanceTask("Deadline");
            }

            if (command.equals("/name")) {
                taskArrayList.get(index).updateName(update);
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else if (command.equals("/by")) {
                Deadline d = (Deadline) taskArrayList.get(index);
                d.updateDeadlineDate(update);
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else {
                return ui.displayWrongCommand();
            }
        case "E":
            if (!(taskArrayList.get(index) instanceof Event)) {
                return ui.displayWrongInstanceTask("Event");
            }
            if (command.equals("/name")) {
                taskArrayList.get(index).updateName(update);
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else if (command.equals("/from")) {
                Event e = (Event) taskArrayList.get(index);
                e.updateEventFrom(update);
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else if (command.equals("/to")) {
                Event e = (Event) taskArrayList.get(index);
                e.updateEventTo(update);
                return ui.displayUpdatedTask(taskArrayList.get(index));
            } else {
                return ui.displayWrongCommand();
            }
        default:
            return "Check your command! The command is 'edit (index of task) (type of task: T D E) (command: /name /by /from /to) (update)'";
        }
    }
}
