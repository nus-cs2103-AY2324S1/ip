public class Parser {

    private enum Commands {
        todo, deadline, event, mark, unmark, list, delete, bye;
    }

    private TaskList taskList;
    private Ui ui;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public boolean parseCommand(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            ui.byeMessage();
            return false;
        } else if (userInput.equalsIgnoreCase("list")) {
            ui.showTasks(taskList);
            return true;
        } else if (userInput.startsWith("mark")) {
            try {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                taskList.markTaskAsDone(id - 1);
                ui.markedMessage(taskList.getTask(id - 1));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To mark a task you need to include the index");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                taskList.markTaskAsUnDone(id - 1);
                ui.unmarkedMessage(taskList.getTask(id - 1));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To unmark a task you need to include the index");
            }
        } else if (userInput.startsWith("todo")) {
            try {
                String description = userInput.substring(5);
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                ui.addTaskMessage(todo, taskList.numOfTasks());
                //writer.write(todo.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (userInput.startsWith("deadline")) {
            try {
                int index = userInput.indexOf("/");
                String description = userInput.substring(9, index - 1);
                String by = userInput.substring(index + 3);
                Deadline deadline = new Deadline(description, by.trim());
                taskList.addTask(deadline);
                ui.addTaskMessage(deadline, taskList.numOfTasks());
                //writer.write(deadline.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (userInput.startsWith("event")) {
            try {
                int indexOfFrom = userInput.indexOf("/");
                String description = userInput.substring( 6, indexOfFrom - 1);
                String duration = userInput.substring(indexOfFrom + 4);
                int indexOfTo = duration.indexOf("/");
                String from = duration.substring(1, indexOfTo - 1);
                String to = duration.substring(indexOfTo + 3);
                Event event = new Event(description, from, to);
                taskList.addTask(event);
                ui.addTaskMessage(event, taskList.numOfTasks());
                //writer.write(event.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                taskList.deleteTask(id - 1);
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To delete a task you have to include the index");
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
