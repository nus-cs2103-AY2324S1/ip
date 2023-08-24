/**
 * The class with the main method where all the functionality begins.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
        String prompt = Duke.inputText();
        try {
            while (!prompt.equals("bye")) {
                if (prompt.equals("list")) {
                    System.out.println(line + "Here are the tasks in your list:\n"
                            + getTasks(tasks) + line);
                } else if (prompt.contains("mark") || prompt.contains("unmark") ||
                prompt.contains("delete")) {
                    int index = Integer.parseInt(prompt.charAt(prompt.length() - 1) + "");
                    if(index > tasks.size()) {
                        throw new InvalidSerialException("☹ OOPS!!! I think you have added" +
                                "an incorrect serial number greater than " + (tasks.size() - 1));
                    }
                    if (prompt.contains("unmark")) {

                        tasks.get(index - 1).unmarkTask();
                        System.out.println(line +
                                "OK, I've marked this task as not done yet:\n" +
                                tasks.get(index - 1).toString() + "\n" + line);
                    } else if (prompt.contains("delete")) {
                        System.out.print(line +
                                "Noted. I've removed this task:\n" + "  " +
                                tasks.get(index - 1).toString() + "\n");
                        tasks.remove(index - 1);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list\n" + line);
                    } else {
                        tasks.get(index - 1).markTask();
                        System.out.println(line +
                                "Nice! I've marked this task as done:\n" +
                                tasks.get(index - 1).toString() + "\n" + line);
                    }
                } else {
                    if (prompt.contains("deadline")) {
                        String deadLine[] = Duke.extractDeadline(Duke.textAfter(prompt));
                        tasks.add(new Deadline(deadLine[0], deadLine[1]));

                    } else if (prompt.contains("event")) {
                        String eventData[] = Duke.extractEvent(Duke.textAfter(prompt));
                        tasks.add(new Event(eventData[2], eventData[0], eventData[1]));
                    } else if (prompt.contains("todo")) {
                        tasks.add(new ToDo(Duke.textAfter(prompt)));
                    } else {
                        throw new InvalidTaskException(
                                "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                        );
                    }
                    System.out.println(line + "Got it. I've added this task:\n "
                            + tasks.get(tasks.size() - 1) + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list\n"
                            + line);
                }
                prompt = Duke.inputText();
            }
        } catch (InvalidTaskException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        } catch (InvalidArgumentException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        } catch(InvalidSerialException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        }
        System.out.println(line +
                " Bye. Hope to see you again soon!\n" +
                line);
    }
    /**
     * Method to extract the content of the command
     * @param sent The String that contains the command content after
     *             the specified command.
     * @throws InvalidArgumentException if there is not content in the command
     * @return Sentinel object of type R.
     */
    public static String textAfter(String sent) throws InvalidArgumentException {
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
        if(reText == "") {
            throw new InvalidArgumentException("☹ OOPS!!! The description of a "
                    + command + " cannot be empty.");
        }
        return reText;
    }

    /**
     * Extracts the end timing of the deadline.
     * @param text The input prompt
     * @return An array containing the command content and end timing of
     * the Deadline.
     */
    public static String[] extractDeadline(String text) {
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
        String deadArray[] = new String[2];
        deadArray[0] = str.trim();
        deadArray[1] = text.substring(i + 1);
        return deadArray;
    }

    /**
     * Method to extract the start and end timings of a deadline
     * @param text The input text
     * @return Returns and array containing the command content,
     * the start and the end times
     */
    public static String[] extractEvent(String text) {
        String str[] = new String[3];
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

    /**
     * The method evaluates the list of the passed tasks.
     * @param tasks The ArrayList containing all the passed commands
     * @return String of all the passed tasks
     */
    public static String getTasks(ArrayList<? extends Task> tasks) {
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return listedTasks;
    }

    /**
     * Takes input of the passed text
     * @return String containing the passed input
     */
    public static String inputText() {
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }
}
