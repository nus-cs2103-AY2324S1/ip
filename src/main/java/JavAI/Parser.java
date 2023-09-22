package javai;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing user input and executing corresponding commands
 * to manage tasks in a task list.
 */
public class Parser {

    /**
     * Parses user input to execute commands for managing tasks in the task list.
     *
     * @param input The user input string to be parsed.
     * @param tasks The TaskList to which tasks are added or modified.
     * @param ui The Ui instance for displaying messages and user interface.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String parse(String input, TaskList tasks, Ui ui) throws JavAiException {
        assert input != null : "Input should not be null";

        String[] words = input.split(" ");
        String description = "";
        int iterator = 1;

        if (words[0].equals("todo")) {
            return createTodo(iterator, words, description, tasks, ui);
        } else if (words[0].equals("deadline")) {
            return createDeadline(iterator, words, description, tasks, ui, input);
        } else if (words[0].equals("event")) {
            return createEvent(iterator, words, description, tasks, ui, input);
        } else if (words[0].equals("mark")) {
            return markTask(words, tasks, ui);
        } else if (words[0].equals("unmark")) {
            return unmarkTask(words, tasks, ui);
        } else if (words[0].equals("delete")) {
            return deleteTask(words, tasks, ui);
        } else if (words[0].equals("find")) {
            return findTask(words, tasks, ui);
        } else if (input.equals("list")) {
            return ui.printList(tasks);
        } else if (input.equals("reminder")) {
            return getReminder(tasks, ui);
        } else {
            return ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Creates a new Todo task and adds it to the TaskList.
     *
     * @param iterator Iterator for processing input words.
     * @param words The array of input words.
     * @param description The description of the todo task.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the task has been added.
     */
    public String createTodo(int iterator, String[] words, String description, TaskList tasks, Ui ui) {
        try {
            while (iterator < words.length) {
                description += words[iterator] + " ";
                iterator++;
            }
            Todo todo = new Todo(description);
            if (description.equals("")) {
                throw new JavAiException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.add(todo);
            return ui.printAddTask(todo, tasks);
        } catch (JavAiException e) {
            return ui.showLoadingError(e);
        }
    }

    /**
     * Creates a new Deadline task and adds it to the TaskList.
     *
     * @param iterator Iterator for processing input words.
     * @param words The array of input words.
     * @param description The description of the deadline task.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @param input The user input string to be parsed.
     * @return A message indicating the task has been added.
     */
    public String createDeadline(int iterator, String[] words, String description, TaskList tasks,
                                 Ui ui, String input) {
        String endDate = "";
        String endTime = "";
        try {
            if (!input.contains("/by")) {
                throw new JavAiException("☹ OOPS!!! Please input a valid deadline using '/by'.");
            }
            while (!words[iterator].equals("/by")) {
                description += words[iterator] + " ";
                iterator++;
            }
            iterator++;
            if (iterator + 1 > words.length) {
                throw new JavAiException("☹ OOPS!!! Please specify both date and time in following "
                        + "format after after /by: yyyy-mm-dd hh:mm'.");
            }
            endDate = words[iterator];
            endTime = words[iterator + 1];
            try {
                LocalDate.parse(endDate);
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
                LocalTime.parse(endTime, inputFormatter);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JavAiException("☹ OOPS!!! Please specify valid date and time in following "
                        + "format after after /by: yyyy-mm-dd hh:mm'.");
            }
            Deadline deadline = new Deadline(description, endDate, endTime);
            tasks.add(deadline);
            return ui.printAddTask(deadline, tasks);
        } catch (JavAiException e) {
            return ui.showLoadingError(e);
        }
    }

    /**
     * Creates a new Event task and adds it to the TaskList.
     *
     * @param iterator Iterator for processing input words.
     * @param words The array of input words.
     * @param description The description of the event task.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @param input The user input string to be parsed.
     * @return A message indicating the task has been added.
     */
    public String createEvent(int iterator, String[] words, String description, TaskList tasks,
                              Ui ui, String input) {
        try {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new JavAiException("☹ OOPS!!! Please input a valid event using '/from' and '/to'.");
            }
            String from = "";
            String to = "";
            while (!words[iterator].equals("/from")) {
                description += words[iterator] + " ";
                iterator++;
            }
            iterator++;
            while (!words[iterator].equals("/to")) {
                from += words[iterator] + " ";
                iterator++;
            }
            iterator++;
            while (iterator < words.length) {
                to += words[iterator] + " ";
                iterator++;
            }
            Event event = new Event(description, from, to);
            tasks.add(event);
            return ui.printAddTask(event, tasks);
        } catch (JavAiException e) {
            return ui.print(e.getMessage());
        }
    }

    /**
     * Marks a task as done.
     * @param words The array of input words.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the task has been marked as done.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String markTask(String[] words, TaskList tasks, Ui ui) throws JavAiException {
        try {
            int identity = Integer.parseInt(words[1]) - 1;
            assert tasks.get(identity) != null : "Task should not be null";
            if (tasks.get(identity).isDone) {
                throw new JavAiException("☹ OOPS!!! This task is already marked complete.");
            }
            tasks.get(identity).markAsDone();
            return ui.printDone(tasks.get(identity));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
        } catch (NullPointerException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
        } catch (NumberFormatException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
        }
    }

    /**
     * Marks a task as undone.
     * @param words The array of input words.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the task has been marked as undone.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String unmarkTask(String[] words, TaskList tasks, Ui ui) throws JavAiException {
        try {
            int identity = Integer.parseInt(words[1]) - 1;
            assert tasks.get(identity) != null : "Task should not be null";
            if (!tasks.get(identity).isDone) {
                throw new JavAiException("☹ OOPS!!! This task is already unmarked.");
            }
            tasks.get(identity).markAsUndone();
            return ui.printUndone(tasks.get(identity));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
        } catch (NullPointerException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
        } catch (NumberFormatException e) {
            throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
        }
    }

    /**
     * Deletes a task from the TaskList.
     * @param words The array of input words.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the task has been deleted.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String deleteTask(String[] words, TaskList tasks, Ui ui) throws JavAiException {
        try {
            String result = ui.printDelete(tasks.get(Integer.parseInt(words[1]) - 1), tasks);
            tasks.delete(Integer.parseInt(words[1]) - 1);
            return result;
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
        } catch (IndexOutOfBoundsException e) {
            return ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
        } catch (NumberFormatException e) {
            return ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
        }
    }

    /**
     * Finds a task from the TaskList.
     * @param words The array of input words.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the task has been found.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String findTask(String[] words, TaskList tasks, Ui ui) throws JavAiException {
        if (words.length != 2) {
            throw new JavAiException("☹ OOPS!!! Please input agi valid keyword after 'find'.");
        } else {

            String keyword = words[1];
            String result = ui.print("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    result += "\n" + (i + 1) + "." + tasks.get(i).toString();
                }
            }
            return result;

        }
    }

    /**
     * Gets a reminder of tasks that are due within next 3 days.
     * @param tasks The TaskList to which the task is added.
     * @param ui The Ui instance for displaying messages.
     * @return A message indicating the tasks that are due within next 3 days.
     * @throws JavAiException If there's an error in parsing or executing the command.
     */
    public String getReminder(TaskList tasks, Ui ui) throws JavAiException {
        String result = ui.print("Here are the tasks that are due within next 3 days:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline && !tasks.get(i).isDone) {
                Deadline deadline = (Deadline) tasks.get(i);
                if (deadline.isDueWithinThreeDays()) {
                    result += "\n" + (i + 1) + "." + tasks.get(i).toString();
                }
            }
        }
        return result;
    }



}
