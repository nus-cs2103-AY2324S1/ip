package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parser class parses user input and executes corresponding commands.
 * Handles adding, list and marking tasks
 */
public class Parser {
    static boolean result = false;
    /**
     *
     * Process user's input and performs the corresponding action.
     *
     * @param input User's input string.
     * @param taskList task list to be updated
     * @param storage stores the object for it to be saved.
     * @return  true if program exits, false if still persist.
     */
    public static String inputType(String input, TaskList taskList, Storage storage) {
         Parser.result = false;
        String response = "";
        if (input.startsWith("bye")) {
            response ="Bye. Hope to see you again soon!";
            Parser.result= true;
            return response;
        } else if (input.startsWith("delete")) {
            int num3 = Integer.parseInt(input.substring(7).trim());
            String action2 = TaskList.helper(TaskList.actions[num3 - 1], TaskList.type[num3 - 1], TaskList.isDone[num3 - 1]);
            for (int j = num3 - 1; j < TaskList.counter - 1; j++) {
                TaskList.actions[j] = TaskList.actions[j + 1];
                TaskList.type[j] = TaskList.type[j + 1];
                TaskList.isDone[j] = TaskList.isDone[j + 1];
            }
            TaskList.counter = TaskList.counter - 1;
            response = "Noted. I've removed this task:\n" + action2+ "\n Now you have" + TaskList.counter + " tasks in the list. ";
            Storage.save("data/duke.txt", TaskList.actions, TaskList.type, TaskList.isDone, TaskList.dueString, TaskList.startTime, TaskList.endTime, TaskList.counter);
            Parser.result = false;
           return response;
        } else if (input.startsWith("list")) {
          //  System.out.println("Here are the tasks in your list:");
           // for (int i = 0; i < TaskList.counter; i++) {
//                String DMYString = "";
//                if (TaskList.type[i].equals("D")) {
//                    DMYString = "by " + TaskList.dueString[i];
//                } else if (TaskList.type[i].equals("E")) {
//                    LocalDateTime startTime = TaskList.startTime[i];
//                    LocalDateTime endTime = TaskList.endTime[i];
//                    DMYString = "from " + TaskList.startTime[i]
//                            + " to " + TaskList.endTime[i];
//                }
               response = TaskList.list(taskList);
                Parser.result = false;
            //}
            return response;
        }else if (input.startsWith("todo")) {
            if (input.length() <= 4) {
                response = "☹ OOPS!!! The description of a todo cannot be empty.";
            } else {
                String action = input.substring(5).trim();
                return TaskList.todo(action);
            }
        } else if (input.startsWith("deadline")) {
            String action = input.substring(9, input.indexOf("/by")).trim();
            // System.out.println(action + "action");
            String by = input.substring(input.indexOf("/by") + 4).trim();
            LocalDateTime timeDeadline = dateTask(by);
            //  System.out.println(by);
            return TaskList.deadline(action, by, timeDeadline);
        } else if (input.startsWith("event")) {
            String action = input.substring(6, input.indexOf("/from")).trim();
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
            String to = input.substring(input.indexOf("/to") + 4).trim();;
            return TaskList.event(action, from, to);

        } else if (input.startsWith("mark")) {
            int num = Integer.parseInt(input.substring(5).trim());
            if (num - 1 < TaskList.counter) {
                TaskList.isDone[num - 1] = true;
                response = "ok, i marked this as done.";
                Parser.result = false;
               return response;
            }
        } else if (input.startsWith("unmark")) {
            int num2 = Integer.parseInt(input.substring(7).trim());
            if (num2 - 1 < TaskList.counter) {
                TaskList.isDone[num2 - 1] = false;
               response= "OK, I've marked this task as not done yet:"+ "  " + TaskList.helper(TaskList.actions[num2 - 1], TaskList.type[num2 - 1], TaskList.isDone[num2 - 1]);
                Parser.result = false;
                return response;
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else if (input.startsWith("find")) {
                return find(input, taskList);
            }
      return "error";
        }

    /**
     * Parse date and time and convert into LocalDateTime for storing.
     * @param dateTimeStr String to be formatted to LocalDateTime.
     * @return LocalDateTime of the parsed date and time.
     */
    private static LocalDateTime dateTask(String dateTimeStr) {
        DateTimeFormatter DMYhelper = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // HHmm for the hour and minutes
        return LocalDateTime.parse(dateTimeStr, DMYhelper);
    }
    public static String find(String input, TaskList taskList) {
        String keyword = input.substring(5).trim();
        StringBuilder response = new StringBuilder();

        ArrayList<String> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.counter; i++) {
            if (taskList.actions[i].contains(keyword)) {
                matchingTasks.add(taskList.helper(taskList.actions[i], taskList.type[i], taskList.isDone[i]));
            }
        }

        if (matchingTasks.isEmpty()) {
            response.append("No matching tasks found.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1) + "." + matchingTasks.get(i));

                // Append a newline character after each task except the last one
                if (i < matchingTasks.size() - 1) {
                    response.append("\n");
                }
            }
        }

        return response.toString();
    }




}