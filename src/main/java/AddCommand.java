/**
 * Represents a Command class that is responsible for adding a Task object to TaskList.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class AddCommand extends Command{
    /** Task to be added to the list of Tasks. */
    private Task task;

    /**
     * Constructor for the AddCommand class to add a Task to the list of Tasks.
     *
     * @param task Task object to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, false);
        ui.showAddedTask(task, tasks.getNumOfTasks());
        storage.saveFile(task.toStorageString());
    }

//    /**
//     * Adds event task to the list of tasks.
//     *
//     * @param input The user input with the description of the task.
//     */
//    public void addEvent(String input) {
//        try {
//            int startIndex = input.indexOf("/from");
//            if (startIndex == -1) {
//                throw new ChatterException("Please add a '/from' statement with the start time / date.");
//            }
//
//            int endIndex = input.indexOf("/to");
//            if (endIndex == -1) {
//                throw new ChatterException("Please add a '/to' statement with the end time / date.");
//            }
//
//            this.tasks.addTask(new Event(input.substring(6, startIndex - 1),
//                    input.substring(startIndex + 6, endIndex - 1),
//                    input.substring(endIndex + 4)), false);
//        } catch(ChatterException e) {
//            System.out.println(e.getMessage());
//        } catch(Exception e) {
//            System.out.println("Please enter a valid description and start / end time.");
//        }
//    }
//
//    /**
//     * Adds todo task to the list of tasks.
//     *
//     * @param input The user input with the description of the task.
//     */
//    private void addTodo(String input) {
//        try {
//            if (input.length() < 6) {
//                throw new ChatterException("☹ OOPS!!! The description of a todo cannot be empty!");
//            }
//            this.tasks.addTask(new ToDo(input.substring(5)), false);
//        } catch(ChatterException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    /**
//     * Adds deadline task to the list of tasks.
//     *
//     * @param input The user input with the description of the task.
//     */
//    private void addDeadline(String input) {
//        try {
//            int deadlineIndex = input.indexOf("/by");
//            if (deadlineIndex == -1) {
//                throw new ChatterException("Please add a '/by' statement with the deadline.");
//            }
//
//            this.tasks.addTask(new Deadline(input.substring(9, deadlineIndex - 1),
//                    input.substring(deadlineIndex + 4)), false);
//        } catch(ChatterException e) {
//            System.out.println(e.getMessage());
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("Please enter a valid description or deadline.");
//        }
//    }
}
