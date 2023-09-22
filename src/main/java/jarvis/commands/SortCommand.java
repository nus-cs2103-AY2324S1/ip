//package jarvis.commands;
//
//import jarvis.exceptions.InvalidIndexException;
//import jarvis.exceptions.InvalidTaskFormatException;
//import jarvis.gui.Ui;
//import jarvis.parser.DeadlineComparator;
//import jarvis.storage.Storage;
//import jarvis.tasks.Deadline;
//import jarvis.tasks.TaskList;
//
///**
// * Represents a command to sort the tasks in the task list.
// */
//public class SortCommand implements Command {
//    @Override
//    public String execute(TaskList taskList, Ui ui, Storage storage)
//            throws InvalidIndexException, InvalidTaskFormatException {
//        // Sort the tasks using the DeadlineComparator.
//        taskList.sortTasks();
//
//        // Separate Todo tasks from the sorted list and add them at the end.
//        int index = 0;
//        while (index < taskList.getTaskCount() && !(taskList.getTask(index) instanceof Deadline)) {
//            index++;
//        }
//        if (index < taskList.getTaskCount()) {
//            // Found a Deadline task, so Todo tasks exist.
//            TaskList sortedList = new TaskList();
//            for (int i = 0; i < index; i++) {
//                sortedList.addTask(taskList.getTask(i));
//            }
//            for (int i = index; i < taskList.getTaskCount(); i++) {
//                sortedList.addTask(taskList.getTask(i));
//            }
//            taskList.setTasks(sortedList.getTaskList());
//        }
//
//        // Save the sorted tasks to storage.
//        storage.saveTasks(taskList.getTaskList());
//
//        // Return the sorted task list as a string.
//        return ui.printTasks(taskList.getTaskList());
//    }
//}
