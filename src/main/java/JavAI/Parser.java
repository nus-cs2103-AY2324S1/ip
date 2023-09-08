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
        String[] words = input.split(" ");
        String description = "";
        int iterator = 1;

        if (words[0].equals("todo")) {
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

        } else if (words[0].equals("deadline")) {
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
        } else if (words[0].equals("event")) {
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
        } else if (words[0].equals("mark")) {
            try {
                int iden = Integer.parseInt(words[1]) - 1;
                tasks.get(iden).markAsDone();
                return ui.printDone(tasks.get(iden));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            } catch (NullPointerException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            } catch (NumberFormatException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            }
        } else if (words[0].equals("unmark")) {
            try {
                int iden = Integer.parseInt(words[1]) - 1;
                tasks.get(iden).markAsUndone();
                return ui.printUndone(tasks.get(iden));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            } catch (NullPointerException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            } catch (NumberFormatException e) {
                throw new JavAiException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            }

        } else if (words[0].equals("delete")) {
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
        } else if (words[0].equals("find")) {
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
        } else if (input.equals("list")) {
            return ui.printList(tasks);
        } else {
            return ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
