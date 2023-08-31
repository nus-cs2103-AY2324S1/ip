package JavAI;
public class Parser {

    public void parse(String input, TaskList tasks, Ui ui) throws JavAIException {
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
                    throw new JavAIException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                tasks.add(todo);
                ui.printAddTask(todo, tasks);
            } catch (JavAIException e) {
                ui.showLoadingError(e);
            }

        } else if (words[0].equals("deadline")) {
            String endDate = "";
            String endTime = "";
            try {
                if(!input.contains("/by")) {
                    throw new JavAIException("☹ OOPS!!! Please input a valid deadline using '/by'.");
                }
                while (!words[iterator].equals("/by")) {
                    description += words[iterator] + " ";
                    iterator++;
                }
                iterator++;
                if (iterator + 1 > words.length) {
                    throw new JavAIException("☹ OOPS!!! Please specify both date and time in following " +
                            "format after after /by: yyyy-mm-dd hh:mm'.");
                }
                endDate = words[iterator];
                endTime = words[iterator + 1];
                Deadline deadline = new Deadline(description, endDate, endTime);
                tasks.add(deadline);
                ui.printAddTask(deadline, tasks);
            } catch (JavAIException e) {
                ui.showLoadingError(e);
            }
        } else if (words[0].equals("event")) {
            try {
                if(!input.contains("/from") || !input.contains("/to")) {
                    throw new JavAIException("☹ OOPS!!! Please input a valid event using '/from' and '/to'.");
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
                ui.printAddTask(event, tasks);
            } catch (JavAIException e) {
                ui.print(e.getMessage());
            }
        } else if (words[0].equals("mark")) {
            try {
                int iden = Integer.parseInt(words[1]) - 1;
                tasks.get(iden).markAsDone();
                ui.printDone(tasks.get(iden));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            } catch (NullPointerException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            } catch (NumberFormatException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
            }
        } else if (words[0].equals("unmark")) {
            try {
                int iden = Integer.parseInt(words[1]) - 1;
                tasks.get(iden).markAsUndone();
                ui.printUndone(tasks.get(iden));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            } catch (NullPointerException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            } catch (NumberFormatException e) {
                throw new JavAIException("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
            }

        } else if (words[0].equals("delete")) {
            try {
                ui.printDelete(tasks.get(Integer.parseInt(words[1]) - 1), tasks);
                tasks.delete(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
            } catch (IndexOutOfBoundsException e) {
                ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
            } catch (NumberFormatException e) {
                ui.print("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
            }
        } else if (input.equals("list")) {
            ui.printList(tasks);
        } else {
            ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
