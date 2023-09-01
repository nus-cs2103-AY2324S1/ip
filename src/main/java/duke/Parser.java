package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static boolean inputType(String input, TaskList taskList, Storage storage) {
        if (input.startsWith("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else if (input.startsWith("delete")) {
            int num3 = Integer.parseInt(input.substring(7).trim());
            String action2 = TaskList.helper(TaskList.actions[num3 - 1], TaskList.type[num3 - 1], TaskList.isDone[num3 - 1]);
            for (int j = num3 - 1; j < TaskList.counter - 1; j++) {
                TaskList.actions[j] = TaskList.actions[j + 1];
                TaskList.type[j] = TaskList.type[j + 1];
                TaskList.isDone[j] = TaskList.isDone[j + 1];
            }
            TaskList.counter = TaskList.counter - 1;
            System.out.println("Noted. I've removed this task:\n" + action2);
            System.out.println("Now you have " + TaskList.counter + " tasks in the list.");
            Storage.save("data/tasks.txt", TaskList.actions, TaskList.type, TaskList.isDone, TaskList.dueString, TaskList.startTime, TaskList.endTime, TaskList.counter);
            return false;
        } else if (input.startsWith("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.counter; i++) {
                String DMYString = "";
                if (TaskList.type[i].equals("D")) {
                    DMYString = "by " + TaskList.dueString[i];
                } else if (TaskList.type[i].equals("E")) {
                    LocalDateTime startTime = TaskList.startTime[i];
                    LocalDateTime endTime = TaskList.endTime[i];
                    DMYString = "from " + TaskList.startTime[i]
                            + " to " + TaskList.endTime[i];
                }
                System.out.println((i + 1) + "." + TaskList.helper(TaskList.actions[i], TaskList.type[i], TaskList.isDone[i]) + " " + DMYString);
            }
            return false;
        }else if (input.startsWith("todo")) {
            if (input.length() <= 4) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                String action = input.substring(5).trim();
                TaskList.todo(action);
                return false;
            }
        } else if (input.startsWith("deadline")) {
            String action = input.substring(9, input.indexOf("/by")).trim();
            // System.out.println(action + "action");
            String by = input.substring(input.indexOf("/by") + 4).trim();
            LocalDateTime timeDeadline = dateTask(by);
            //  System.out.println(by);
            TaskList.deadline(action, by, timeDeadline);
            return false;
        } else if (input.startsWith("event")) {
            String action = input.substring(6, input.indexOf("/from")).trim();
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
            String to = input.substring(input.indexOf("/to") + 4).trim();;
            TaskList.event(action, from, to);
            return false;
        } else if (input.startsWith("mark")) {
            int num = Integer.parseInt(input.substring(5).trim());
            if (num - 1 < TaskList.counter) {
                TaskList.isDone[num - 1] = true;
                return false;
            }
        } else if (input.startsWith("unmark")) {
            int num2 = Integer.parseInt(input.substring(7).trim());
            if (num2 - 1 < TaskList.counter) {
                TaskList.isDone[num2 - 1] = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + TaskList.helper(TaskList.actions[num2 - 1], TaskList.type[num2 - 1], TaskList.isDone[num2 - 1]));
                return false;
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return true;
    }

    private static LocalDateTime dateTask(String dateTimeStr) {
        DateTimeFormatter DMYhelper = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // HHmm for the hour and minutes
        return LocalDateTime.parse(dateTimeStr, DMYhelper);
    }
}