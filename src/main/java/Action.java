import java.util.ArrayList;

public class Action {

    private TaskList taskList;
    private Ui ui;

    public Action (TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void listTasks() {
        ArrayList<Task> tasks = taskList.getTask();
        if (tasks.isEmpty()) {
            ui.printResponse("Congratulations Master! There is no task at the moment!");
        } else {
            ui.printTasks(tasks);
        }
    }
    
    public void updateTask(int index, boolean isCompleted) {
        ArrayList<Task> tasks = taskList.getTask();
         if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            
            if (isCompleted) {
                task.markCompleted();
            } else {
                task.unmarkCompleted();
            }
            
            ui.printTaskStatus(task);
        } else {
            ui.printResponse("Invalid index.");
        }
    }

    public void addTask(String taskDetails, String taskType) {

        if (taskType.equalsIgnoreCase("todo")) {
            String taskTitle = taskDetails.substring(5).trim();
            Todo todo = new Todo(taskTitle);
            taskList.addTask(todo);
            ui.printResponse("Yes Master! I've added this task: \n" + "\t" + todo.toString() + "\n" +
                                "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
        } else if (taskType.equalsIgnoreCase("deadline")) {
            int indexOfBy = taskDetails.indexOf("by");

            if (indexOfBy != 1 && indexOfBy <= taskDetails.length()) {
                String taskTitle = taskDetails.substring(9, indexOfBy).trim();
                String dueDate = taskDetails.substring(indexOfBy + 2).trim();
                Deadline deadline = new Deadline(taskTitle, dueDate);
                taskList.addTask(deadline);
                ui.printResponse("Yes Master! I've added this task: \n" + deadline.toString() + "\n" +
                                "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            int indexOfFrom = taskDetails.indexOf("from");
            int indexOfTo = taskDetails.indexOf("to");

            if (indexOfFrom != 1 && indexOfFrom <= taskDetails.length() &&
                indexOfTo != 1 && indexOfTo <= taskDetails.length()) {
                String taskTitle = taskDetails.substring(6, indexOfFrom).trim();
                String startTime = taskDetails.substring(indexOfFrom + 4, indexOfTo).trim();
                String endTime = taskDetails.substring(indexOfTo + 2).trim();

                Event event = new Event(taskTitle, startTime, endTime);
                taskList.addTask(event);
                ui.printResponse("Yes Master! I've added this task: \n" + event.toString() + "\n" +
                                "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            }
        }  
    }
}
