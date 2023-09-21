package duke;

import place.FoodPlace;
import place.ShoppingPlace;
import place.StudyPlace;

public class Parser {
    TaskList taskList;
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Writes task into Local Storage.
     * @param promptText    Task from user's input.
     * @throws DukeException    Exception to be thrown when the input cannot be read.
     */
    public String createTask(String promptText) throws DukeException {
        String returnString;
        if (promptText.startsWith("todo")) {
            returnString = parseTodo(promptText);
        } else if (promptText.startsWith("deadline")) {
            returnString = parseDeadline(promptText);
        } else {
            try {
                String[] taskDetails = promptText.split("/", 2);
                String dateParts = taskDetails[1].substring(5);
                int splitIndex = dateParts.indexOf("/to");
                String startDate = dateParts.substring(0, splitIndex - 1);
                String endDate = dateParts.substring(splitIndex + 4);

                if (DateParser.isEitherDate(startDate) && DateParser.isEitherDate(endDate)) {
                    Task event = new Event(taskDetails[0].substring(6),
                            DateParser.formatDate(startDate), DateParser.formatDate(endDate));
                    returnString = taskList.add(event, true);
                } else {
                    Task event = new Event(taskDetails[0].substring(6), startDate, endDate);
                    returnString = taskList.add(event, true);
                }
                taskList.writeToFile();
            } catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of an event cannot be empty.");
            }
        }
        return returnString;
    }

    private String parseTodo(String promptText) throws DukeException {
        try {
            Task todo = new Todo(promptText.substring(5));
            String returnStatement = taskList.add(todo, true);
            taskList.writeToFile();
            return returnStatement;
        } catch (StringIndexOutOfBoundsException s) {
            throw new DukeException("OOPS!! The description of a todo cannot be empty.");
        }
    }

    private String parseDeadline(String promptText) throws DukeException {
        String returnStatement;
        try {
            String[] parts = promptText.split("/", 2);
            String date = parts[1].substring(3);
            if (DateParser.isEitherDate(date)) {
                Task deadline = new Deadline(parts[0].substring(9),
                        DateParser.formatDate(date));
                returnStatement = taskList.add(deadline, true);
            } else {
                Task deadline = new Deadline(parts[0].substring(9), parts[1].substring(2));
                returnStatement = taskList.add(deadline, true);
            }
            taskList.writeToFile();
            return returnStatement;
        } catch (StringIndexOutOfBoundsException s) {
            throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Marks task as done or undone.
     *
     * @param promptText User's input to mark or unmark a task.
     * @return The message to be printed.
     * @throws DukeException Exception that is thrown when the task does not exist.
     */
    public String markTask(String promptText) throws DukeException {
        String returnString;
        try {
            int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
            Task t = taskList.get(i-1);
            if (promptText.startsWith("unmark")) {
                returnString = t.unmark();
                taskList.writeToFile();
            } else {
                returnString = t.mark(true);
                taskList.writeToFile();
            }
            return returnString;
        } catch (NumberFormatException n) {
            throw new DukeException("OOPS!! You must mark/unmark an index.");
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("OOPS!! This index doesn't exist.");
        }
    }

    public String findTask(String promptText) {
        String findTask = promptText.substring(5);
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(findTask)) {
                returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
            }
        }
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(returnString);
        return "Here are the matching tasks in your list: \n" + returnString;
    }

    public String createPlace(String promptText) {
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
    }

    public String taskCommand(String promptText) throws DukeException {
        if (promptText.startsWith("todo") || promptText.startsWith("deadline") || promptText.startsWith("event")) {
            return createTask(promptText);
        } else if (promptText.startsWith("mark") || promptText.startsWith("unmark")) {
            return markTask(promptText);
        } else if (promptText.startsWith("find")) {
            return findTask(promptText);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
