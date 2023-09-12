package duke;

import place.*;

public class Ui {

    public String echo(String promptText, TaskList tasks) throws DukeException {
        Parser parser = new Parser(tasks);
        if (promptText.startsWith("place")) {
            String[] locationString = promptText.split("/", 3);
            String name = locationString[0].substring(5);
            String type = locationString[1].substring(5);
            String desc = locationString[2].substring(5);
            if (type.startsWith("food")) {
                FoodPlace newFoodPlace = new FoodPlace(name, desc);
                return newFoodPlace.addPlace();
            } else if (type.startsWith("shopping")) {
                ShoppingPlace newShoppingPlace = new ShoppingPlace(name, desc);
                return newShoppingPlace.addPlace();
            } else {
                StudyPlace newStudyPlace = new StudyPlace(name, desc);
                return newStudyPlace.addPlace();
            }
        } else if (promptText.startsWith("listplaces")) {
            return Place.list();
        } else if (promptText.equals("bye")) {
            return exit();
        }
        else if (promptText.equals("list")) {
            if (tasks.size() == 0) {
                System.out.println("Your task list is empty!");
                return "Your task list is empty!";
            } else {
                return list(tasks);
            }
        }
        else if (promptText.startsWith("todo") || promptText.startsWith("deadline") || promptText.startsWith("event")) {
            return parser.createTask(promptText);
        }
        else if (promptText.startsWith("mark") || promptText.startsWith("unmark")) {
            return parser.markTask(promptText);
        }
        else if (promptText.startsWith("delete")) {
            return deleteTask(tasks,Integer.valueOf(promptText.substring(7)) - 1);
        }
        else if (promptText.startsWith("find")) {
            return parser.findTask(promptText);
        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
