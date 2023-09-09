package alcazar;

/**
 * Class to parse the input commands
 */
public class Parser {

    /**
     * Method to interpret the command input.
     * @param prompt The input text
     * @param tasks The TaskList object containing current tasks
     * @param ui Object to present different UI features
     * @param storage Storage object containing methods to read and write to files
     * @throws InvalidArgumentException In the situation when an InvalidArgument is passed
     * @throws InvalidTaskException In the situation when an unrecognized task was passed
     * @throws InvalidSerialException In the situation where an invalid serial number is passed
     */
    public String parse(String prompt, TaskList tasks, Ui ui,
                        Storage storage) throws InvalidArgumentException, InvalidTaskException, InvalidSerialException {
        if (prompt.equals("list")) {
            //ui.showLine();
            return "Here are the tasks in your list:\n"
                    + tasks.getTasks();
        } else if (prompt.contains("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (prompt.contains("find")) {
            String search = this.textAfter(prompt);
            return "Here are the matching tasks in your list:\n" + tasks.printEquals(search);

        } else if (prompt.contains("mark") || prompt.contains("unmark")
                || prompt.contains("delete")) {
            int index = Integer.parseInt(prompt.charAt(prompt.length() - 1) + "");
            if (index > tasks.size()) {
                throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                        + "an incorrect serial number greater than " + (tasks.size()));
            }
            if (prompt.contains("unmark")) {

                tasks.unmarkElement(index - 1);
                storage.writeUp(tasks);
                return "OK, I've marked this task as not done yet:\n"
                        + tasks.elementAt(index - 1) + "\n";
            } else if (prompt.contains("delete")) {
                ui.showLine();
                Task t = tasks.elementAt(index - 1);
                tasks.delete(index - 1);
                storage.writeUp(tasks);
                return "Noted. I've removed this task:\n" + "  "
                                + t + "\nNow you have " + tasks.size() + " tasks in the list\n";
            } else {
                tasks.markElement(index - 1);
                storage.writeUp(tasks);
                ui.showLine();
                return "Nice! I've marked this task as done:\n"
                        + tasks.elementAt(index - 1) + "\n";
            }
        } else {
            if (prompt.contains("deadline")) {
                String[] deadLine = this.extractDeadline(this.textAfter(prompt));
                tasks.add(new Deadline(deadLine[0], deadLine[1]));
                storage.writeUp(tasks);
            } else if (prompt.contains("event")) {
                String[] eventData = this.extractEvent(this.textAfter(prompt));
                tasks.add(new Event(eventData[2], eventData[0], eventData[1]));
                storage.writeUp(tasks);
            } else if (prompt.contains("todo")) {
                tasks.add(new ToDo(this.textAfter(prompt)));
                storage.writeUp(tasks);
            } else {
                throw new InvalidTaskException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                );
            }
            return "Got it. I've added this task:\n "
                    + tasks.elementAt(tasks.size() - 1) + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list\n";
        }
    }

    /**
     * To check if the input passed is an exit request.
     * @param prompt Text input to the class's functions
     * @return Returns a boolean
     */

    public boolean isExit(String prompt) {
        return prompt.contains("bye");
    }

    /**
     * Method to extract the content of the command
     * @param sent The String that contains the command content after
     *             the specified command.
     * @return String description of the Task
     * @throws InvalidArgumentException if there is no content in the command
     */
    public String textAfter(String sent) throws InvalidArgumentException {
        String reText = "";
        String command = "";
        boolean flag = false;
        for (int i = 0; i < sent.length(); i++) {
            char ch = sent.charAt(i);
            if (flag) {
                reText += ch;
            } else if (ch == ' ') {
                command += ch;
                flag = true;
            }
        }
        if (reText == "") {
            throw new InvalidArgumentException("☹ OOPS!!! The description of a "
                    + command + " cannot be empty.");
        }
        return reText;
    }

    /**
     * Extracts the end timing of the deadline.
     * @param text The input prompt
     * @return An array containing the command content and end timing of
     *      the Deadline
     */
    public String[] extractDeadline(String text) {
        String wrd = "";
        String str = "";
        int i;
        for (i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("/by")) {
                    break;
                }
                str += wrd + " ";
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        String[] deadArray = new String[2];
        deadArray[0] = str.trim();
        deadArray[1] = text.substring(i + 1);
        return deadArray;
    }

    /**
     * Method to extract the start and end timings of a deadline
     * @param text The input text
     * @return Returns and array containing the command content,
     *      the start and the end times
     */
    public String[] extractEvent(String text) {
        String[] str = new String[3];
        str[0] = "";
        str[1] = "";
        str[2] = "";
        String wrd = "";
        text = text + " ";
        boolean fromFlag = false;
        boolean toFlag = false;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("/from")) {
                    fromFlag = true;
                } else if (wrd.equals("/to")) {
                    toFlag = true;
                    fromFlag = false;
                } else if (fromFlag) {
                    str[0] += wrd + " ";
                } else if (toFlag) {
                    str[1] += wrd + " ";
                } else {
                    str[2] += wrd + " ";
                }
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        str[2] = str[2].trim();
        return str;
    }

}
