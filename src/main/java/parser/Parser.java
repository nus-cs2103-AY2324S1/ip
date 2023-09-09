package parser;

import tasks.*;
import ui.Ui;
import main.Duke;
import storage.Storage;
import actions.TaskList;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    private Duke chad;
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Parser(Duke chad, ArrayList<Task> taskArrayList) {
        this.chad = chad;
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(taskArrayList);
    }

    /**
     * Takes in a String array of the instructions from user
     * Runs the applicable methods based on the instruction from user
     *
     * @param inputArray array of string from the input of user
     * @return false if user gives command to stop bot, else returns true
     */
    public boolean inputParse(String[] inputArray) {

        if (inputArray[0].equals("bye")) {
            ui.chadBye();
            return false;

        } else if (inputArray[0].equals("list")) {
            ui.chadListTask(chad.taskArrayList);

        } else if (inputArray[0].equals("mark")) {
            try {
                Integer index = Integer.valueOf(inputArray[1]);
                tasklist.chadMarkTask(index);
                ui.chadMarkTaskOutput(chad.taskArrayList.get(index - 1).name,
                        chad.taskArrayList.get(index - 1).getMark());
                storage.writeFile(chad.taskArrayList);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");
            }

        } else if (inputArray[0].equals("unmark")) {
            try {

                Integer index = Integer.valueOf(inputArray[1]);
                tasklist.chadUnmarkTask(index);
                ui.chadUnmarkTaskOutput(chad.taskArrayList.get(index - 1).name,
                        chad.taskArrayList.get(index - 1).getMark());

                storage.writeFile(chad.taskArrayList);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("The task index is invalid! Try again!");
            }

        } else if (inputArray[0].equals("find")) {
            String name = inputArray[1];
            tasklist.chadFindTask(name);

        } else if (inputArray[0].equals("todo")) {
            try {
                if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }
                Todo newTodo = new Todo(inputArray[1]);
                tasklist.chadAddList(newTodo);
                ui.chadAddListOutput(newTodo.toString());
                storage.writeFile(chad.taskArrayList);
            } catch (Duke.DukeException e) {
                System.out.println(e.getMessage() + "\n");
            }

        } else if (inputArray[0].equals("deadline")) {
            try {
                if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }

                String[] details = inputArray[1].split(" /by ", 2);

                if (details.length < 2) {
                    throw new Duke.DukeException("Umm you forgot the deadline! Remember to use /by before the deadline!");
                }
                Deadline newDeadline = new Deadline(details[0], details[1]);
                tasklist.chadAddList(newDeadline);
                ui.chadAddListOutput(newDeadline.toString());
                storage.writeFile(chad.taskArrayList);
            } catch (Duke.DukeException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (DateTimeParseException e) {
                System.out.println("Make sure the date format is: d MMM yyyy");
            }

        } else if (inputArray[0].equals("event")) {
            try {
                if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }
                String[] details = inputArray[1].split(" /from ", 2);
                if (details.length < 2) {
                    throw new Duke.DukeException("Hey you are missing the start date! Remember to use /from before the deadline!");
                }
                String[] timings = details[1].split(" /to ", 0);
                if (timings.length < 2) {
                    throw new Duke.DukeException("The end date is missing! Do better! Use /to!");
                }
                System.out.println(timings[0]);
                System.out.println(timings[1]);
                Event newEvent = new Event(details[0], timings[0], timings[1]);
                tasklist.chadAddList(newEvent);
                ui.chadAddListOutput(newEvent.toString());
                storage.writeFile(chad.taskArrayList);
            } catch (Duke.DukeException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (DateTimeParseException e) {
                System.out.println("Make sure the date format is: d MMM yyyy");
            }
        } else if (inputArray[0].equals("delete")) {
            try {
                Integer index = Integer.valueOf(inputArray[1]);
                String name = tasklist.chadRemoveList(index);
                ui.chadRemoveOutput(name, chad.taskArrayList.size());
                storage.writeFile(chad.taskArrayList);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("The task index is invalid! Try again!");
            }

        } else {
            ui.chadOutput("Hmm? You are not making sense!");
        }
        return true;
    }

    public String displayInputParse(String[] inputArray) {

        String output = "";

        if (inputArray[0].equals("bye")) {
            output = ui.displayChadBye();

        } else if (inputArray[0].equals("list")) {
            output = ui.displaychadListTask(chad.taskArrayList);

        } else if (inputArray[0].equals("mark")) {
            try {
                Integer index = Integer.valueOf(inputArray[1]);
                tasklist.chadMarkTask(index);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadMarkTaskOutput(chad.taskArrayList.get(index - 1).name,
                        chad.taskArrayList.get(index - 1).getMark());


            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                output = "The task index is invalid! Try again!";
            }

        } else if (inputArray[0].equals("unmark")) {
            try {

                Integer index = Integer.valueOf(inputArray[1]);
                tasklist.chadUnmarkTask(index);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadUnmarkTaskOutput(chad.taskArrayList.get(index - 1).name,
                        chad.taskArrayList.get(index - 1).getMark());

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                output = "The task index is invalid! Try again!";
            }

        } else if (inputArray[0].equals("find")) {
            String name = inputArray[1];
            output = tasklist.displayChadFindTask(name);

        } else if (inputArray[0].equals("todo")) {
            try {
                if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }
                Todo newTodo = new Todo(inputArray[1]);
                tasklist.chadAddList(newTodo);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadAddListOutput(newTodo.toString());

            } catch (Duke.DukeException e) {
                output =  e.getMessage() + "\n";
            }

        } else if (inputArray[0].equals("deadline")) {
            try {
                if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }

                String[] details = inputArray[1].split(" /by ", 2);

                if (details.length < 2) {
                    throw new Duke.DukeException("Umm you forgot the deadline! Remember to use /by before the deadline!");
                }
                Deadline newDeadline = new Deadline(details[0], details[1]);
                tasklist.chadAddList(newDeadline);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadAddListOutput(newDeadline.toString());

            } catch (Duke.DukeException e) {
                output =  e.getMessage() + "\n";
            } catch (DateTimeParseException e) {
                output =  "Make sure the date format is: d MMM yyyy";
            }

        } else if (inputArray[0].equals("event")) {
            try {
                if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                    throw new Duke.DukeException("Hey! You forgot what you needed to do?");
                }
                String[] details = inputArray[1].split(" /from ", 2);
                if (details.length < 2) {
                    throw new Duke.DukeException("Hey you are missing the start date! Remember to use /from before the deadline!");
                }
                String[] timings = details[1].split(" /to ", 0);
                if (timings.length < 2) {
                    throw new Duke.DukeException("The end date is missing! Do better! Use /to!");
                }
                Event newEvent = new Event(details[0], timings[0], timings[1]);
                tasklist.chadAddList(newEvent);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadAddListOutput(newEvent.toString());

            } catch (Duke.DukeException e) {
                return e.getMessage() + "\n";
            } catch (DateTimeParseException e) {
                return "Make sure the date format is: d MMM yyyy";
            }
        } else if (inputArray[0].equals("delete")) {
            try {
                Integer index = Integer.valueOf(inputArray[1]);
                String name = tasklist.chadRemoveList(index);
                storage.writeFile(chad.taskArrayList);
                output = ui.displayChadRemoveOutput(name, chad.taskArrayList.size());

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                output =  "The task index is invalid! Try again!";
            }

        } else {
            output = ui.displayChadOutput("Hmm? You are not making sense!");
        }
        return output;
    }
}




