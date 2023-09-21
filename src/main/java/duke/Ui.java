package duke;


import place.Place;

public class Ui {

    public String echo(String promptText, TaskList tasks) throws DukeException {
        assert tasks != null : "taskList must be initialized properly";
        Parser parser = new Parser(tasks);
        if (promptText.startsWith("place")) {
            return parser.createPlace(promptText);
        } else if (promptText.startsWith("listplaces")) {
            return Place.list();
        } else if (promptText.equals("bye")) {
            return exit();
        } else if (promptText.equals("list")) {
            if (tasks.size() == 0) {
                System.out.println("Your task list is empty!");
                return "Your task list is empty!";
            } else {
                return list(tasks);
            }
        } else if (promptText.startsWith("delete")) {
            return deleteTask(tasks,Integer.valueOf(promptText.substring(7)) - 1);
        } else {
            return parser.taskCommand(promptText);
        }
    }

    public String deleteTask(TaskList tasks, int i) throws DukeException {
        try {
            return tasks.delete(tasks.get(i));
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! This task doesn't exist!");
        }
    }

    public String list(TaskList tasks) {
        return tasks.list();
    }

    public String exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
        return "Bye. Hope to see you again soon!";
    }

    public void showLoadingError() {
        System.out.println("OOPS! Loading error.");
    }
}
