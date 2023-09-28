package duke.tasks;

import duke.helper.Ui;

public class Task {
    String task;
    boolean isDone;

    /**
    * constructs the task class
    *
    * @param task the description of the task
    * @param isDone a boolean value that indicate whether the task is done or not
    */
    public Task(String task){
        this.task = task;
        this.isDone = false;
    }
    
    /**
    * getter method that return the description of the task
    *
    * @return the description of the task
    */
    public String getTask() {
        return this.task;
    }

    /**
    * returns the status of the task
    *
    * @return a formatted string of the status of the task
    */
    public String getStatus() {
        String status = "[" + (isDone ? " Completed " : "In Progress") + "]";
        return status + " " + this.getTask();
    }

    public String getTime() {
        return null;
    }

    /**
    * setter method for the task status
    *
    * @param isDone new status of the tasks
    * @return the bot response
    */
    public String markItem(Boolean isDone) {
        this.isDone = isDone;
        if (this.isDone) {
            String[] messageList = {("Nice! I've marked this task as complete:"), 
                                    (this.getStatus()), 
                                    ("Here's a lollipop.")};
            return Ui.print(messageList);
        } else {
            String[] messageList = {("OK, I've marked this task as incomplete yet:"), 
                                    (this.getStatus()), 
                                    ("Keep up with the good work.")};
            return Ui.print(messageList);
        }
    }

    public String toFile() {
        return task;
    }

    /**
    * translate text input to task instance
    *
    * @param text record from the txt file
    * @return a Task instance in accordance to the input text
    */
    public static Task convertStringToTask(String text) {
        String[] tasks = text.split("\\|");
        String type = tasks[0].trim();
        boolean isCompleted = tasks[1].trim().equals("1");
        String task = tasks[2].trim();

        if (type.equals("T")) {
            ToDo temp = new ToDo(task);
            temp.markItem(isCompleted);
            return temp;
        } else if (type.equals("D")) {
            String ddl = tasks[3].trim();
            Deadline temp =  new Deadline(task, ddl);
            temp.markItem(isCompleted);
            return temp;
        } else if (type.equals("E")) {
            String[] timeDuration = tasks[3].trim().split("-");
            String from = timeDuration[0].trim();
            String to = timeDuration[1].trim();
            Event temp =  new Event(task, from, to);
            temp.markItem(isCompleted);
            return temp;
        } else {
            return null;
        }
    }
}
