package sisyphus;

import sisyphus.task.Deadline;
import sisyphus.task.Event;
import sisyphus.task.TaskList;
import sisyphus.task.ToDo;

import java.time.LocalDate;

public class Parser {
    private boolean isChatting;

    public Parser() {
        isChatting = true;
    }

    public boolean getActiveStatus() {
        return this.isChatting;
    }

    public void run(String fullCommand, TaskList taskList, Storage storage, Ui ui)  throws SisyphusException {
        String[] inputArray;
        String command, params = "";

        inputArray = fullCommand.split(" ", 2);
        command = inputArray[0];

        if (inputArray.length > 1) {
            params = inputArray[1];
        }

        switch (command) {
        case ("bye"): {
            isChatting = false;
            break;
        }
        case ("list"): {
            ui.printTasks(taskList);
            break;
        }
        case ("mark"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                taskList.markTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            ui.printMarkTask(taskList, index);
            break;
        }
        case ("unmark"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                taskList.unmarkTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            ui.printUnmarkTask(taskList, index);
            break;
        }
        case ("delete"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                taskList.deleteTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            ui.printDeleteTask(taskList, index);
            break;
        }
        case ("todo"): {
            if (params == "" || params == null) {
                throw new SisyphusException("Include a description for the sisyphus.task.ToDo. \nHere is an example: " +
                        "todo Roll Boulder");
            }
            ToDo todoTask = new ToDo(params);
            taskList.addTask(todoTask);
            storage.writeFile(taskList);
            ui.printAddTodo(taskList);
            break;
        }
        case ("deadline"): {
            String description, deadline;
            LocalDate deadlineDate;
            try {
                description = params.split(" /by ")[0];
                deadline = params.split(" /by ")[1];
                deadlineDate = LocalDate.parse(deadline);
            } catch (Exception e) {
                throw new SisyphusException("Include both description and deadline for a deadline. \nHere " +
                        "is an example: deadline roll boulder /by 2023-10-15");
            }

            Deadline deadlineTask = new Deadline(description, deadlineDate);
            taskList.addTask(deadlineTask);
            storage.writeFile(taskList);
            ui.printAddDeadline(taskList);
            break;
        }
        case ("event"): {
            String description, fromAndToTime, from, to;
            try {
                description = params.split(" /from ")[0];
                fromAndToTime = params.split(" /from ")[1];
                from = fromAndToTime.split(" /to ")[0];
                to = fromAndToTime.split(" /to ")[1];
            } catch (Exception e) {
                throw new SisyphusException("Include the description, from and to time for an event. \nHere is" +
                        " an example: event roll boulder /from past /to eternity");
            }

            Event eventTask = new Event(description, from, to);
            taskList.addTask(eventTask);
            storage.writeFile(taskList);
            ui.printAddEvent(taskList);
            break;
        }
        default: {
            throw new SisyphusException("Enter a valid command. Available comments are " +
                    "bye, list, event, deadline, todo, mark, unmark.");
        }
        }

    }
}
