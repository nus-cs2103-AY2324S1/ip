import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    enum Month {
        Jan, Feb, Mar, Apr, May, June, July, Aug, Sept, Oct, Nov, Dec
    }
    public static void main(String[] args) {
        printIntro();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            try {
                String type = cmd.split(" ", 2)[0];

                // If cmd is "list", list items and wait for next command
                if (cmd.equals("list")) {
                    printListItems();
                } else if (type.equals("todo")) {
                    // Check if description is empty
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("todo");
                    }
                    String taskName = cmd.split(" ", 2)[1];
                    Task todo = new ToDo(taskName);
                    list.add(todo);
                    printAddTaskMessage(todo);
                } else if (type.equals("deadline")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("deadline");
                    }
                    String taskWithDeadline = cmd.split(" ", 2)[1];

                    if (hasNoDeadline(taskWithDeadline)) {
                        throw new NoDeadlineException();
                    }

                    String taskName = taskWithDeadline.split("/", 2)[0];
                    String deadlineDescription = taskWithDeadline.split("/", 2)[1];
                    String deadlineDay = checkedDeadline(deadlineDescription);
                    if (deadlineDay == "") {
                        throw new InvalidDeadlineException(deadlineDescription);
                    }

                    Task deadline = new Deadline(taskName, deadlineDay);
                    list.add(deadline);
                    printAddTaskMessage(deadline);
                } else if (type.equals("event")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("event");
                    }
                    String task = cmd.split(" ", 2)[1];
                    String[] time = task.split("/");

                    if (time.length != 3) {
                        throw new IncompleteDurationException();
                    }
                    String taskName = time[0];
                    String starting = time[1];
                    String ending = time[2];
//                    String start = starting.split(" ", 2)[1];
//                    String end = ending.split(" ", 2)[2];

//                    if (checkedDurationStart(starting).equals("") || checkedDurationEnd(ending).equals("")) {
//                        throw new InvalidDurationException(task.split("/", 2)[1]);
//                    }

                    Task event = new Event(taskName, starting.split(" ", 2)[1], ending.split(" ", 2)[1]);
                    list.add(event);
                    printAddTaskMessage(event);
                } else if (type.equals("delete")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("delete");
                    }

                    String arr[] = cmd.split(" ", 2);
                    int taskNumber = Integer.parseInt(arr[1]);

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }
                    Task task = list.get(taskNumber - 1);
                    list.remove(task);
                    printDeleteTaskMessage(task);
                } else if (type.equals("mark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("mark");
                    }
                    String arr[] = cmd.split(" ", 2);
                    int taskNumber = Integer.parseInt(arr[1]);

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

                    Task task = list.get(taskNumber - 1);
                    task.markTask();
                    printMarkedTaskMessage(task);
                } else if (type.equals("unmark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("unmark");
                    }
                    String arr[] = cmd.split(" ", 2);
                    int taskNumber = Integer.parseInt(arr[1]);

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

                    Task task = list.get(taskNumber - 1);
                    task.unMarkTask();
                    printUnmarkedTaskMessage(task);
                } else {  // If the inputted command is not valid, throw TaskTypeException
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (NoDeadlineException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDeadlineException e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskNumberException e) {
                System.out.println(e.getMessage());
            } catch (IncompleteDurationException e) {
                System.out.println(e.getMessage());
            }
//            catch (InvalidDurationException e) {
//                System.out.println(e.getMessage());
//            }
            finally {
                cmd = sc.nextLine();
            }
        }

        printExit();
    }

    public static void printListItems() {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println(i+1 + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name);
        }
    }

    public static void printIntro() {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);
    }

    public static void printAddTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " Got it. I've added this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + list.size() + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printDeleteTaskMessage(Task task){
        String message = "____________________________________________________________\n" +
                " Noted. I've removed this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + list.size() + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printExit() {
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }

    public static void printMarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + task.getStatusIcon() + " " + task.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printUnmarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " OK. I've marked this task as not done yet:\n" +
                "   " + task.getStatusIcon() + " " + task.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static boolean descriptionIsEmpty(String cmd) {
        return cmd.split(" ").length == 1;
    }

    public static String checkedDeadline(String deadline) {
        String by = deadline.split(" ", 2)[0];

        // Check if the first word of the string is "by"
        if (!by.equals("by")) {
            return "";
        }
        if (deadline.split(" ").length == 2) {
            String day = deadline.split(" ", 2)[1];
            String capitalisedDay = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase().trim();
            return isValidDay(capitalisedDay) ? capitalisedDay : "";
        } else if (deadline.split(" ").length == 3) {
            String month = deadline.split(" ")[1];
            String capitalisedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase().trim();
            String date = deadline.split(" ")[2].split("")[0];

            if (isValidDate(capitalisedMonth, Integer.parseInt(date))) {
                return getDate(capitalisedMonth, Integer.parseInt(date));
            }
        }
        return "";
    }

//    public static String checkedDurationStart(String start) {
//        String from = start.split(" ", 2)[0];
//        if (!from.equals("from")) {
//            return "";
//        }
//
//        if (start.split(" ").length == 2) {
//            String day = start.split(" ", 2)[1];
//            String capitalisedDay = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();
//            return isValidDay(capitalisedDay.trim()) ? capitalisedDay : "";
//        } else if (start.split(" ").length == 3) {
//            // For month date case
//            String month = start.split(" ")[1];
//            String capitalisedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase().trim();
//            String date = start.split(" ")[2].split("")[0];
//
//            //For day time case
//
//            if (isValidDate(capitalisedMonth, Integer.parseInt(date))) {
//                return getDate(capitalisedMonth, Integer.parseInt(date));
//            }
//        } else if (start.split(" ").length == 4)
//        return "";
//    }
//
//    public static String checkedDurationEnd(String end) {
//        String to = end.split(" ", 2) [0];
//
//        if (!to.equals("to")) {
//            return "";
//        }
//
//        if (end.split(" ").length == 2) {
//            String day = end.split(" ", 2)[1];
//            String capitalisedDay = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase().trim();
//            return isValidDay(capitalisedDay) ? capitalisedDay : "";
//        } else if (end.split(" ").length == 3) {
//            String month = end.split(" ")[1];
//            String capitalisedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase().trim();
//            String date = end.split(" ")[2].split("")[0];
//
//            if (isValidDate(capitalisedMonth, Integer.parseInt(date))) {
//                return getDate(capitalisedMonth, Integer.parseInt(date));
//            }
//        }
//        return "";
//    }

    public static boolean isValidDay(String day) {
        for (Day dayy : Day.values()) {
            if (dayy.name().equals(day)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidDate(String month, int day) {
        if (month.equals("Jan") || month.equals("March") || month.equals("May") || month.equals("July")
                || month.equals("August") || month.equals("October") || month.equals("December")) {
            return day >= 1 && day <= 31;
        } else if (month.equals("Apr") || month.equals("June") || month.equals("Sept") || month.equals("Nov")) {
            return day >=1 && day <= 31;
        } else if (month.equals("Feb")) {
            return day >= 1 && day <= 28;
        } else {
            return false;
        }
    }

    public static String getDate(String month, int day) {
        String prefix = "";
        int ones = -1;
        if (day % 10 == 0) {
            ones = day;
        } else {
            ones = day % 10;
        }
        if (ones == 1) {
            prefix = "st";
        } else if (ones == 2) {
            prefix = "nd";
        } else if (ones == 3) {
            prefix = "rd";
        } else {
            prefix = "th";
        }

        return month + " " + day + prefix;
    }

    public static boolean hasNoDeadline(String taskWithDeadline) {
        return taskWithDeadline.split("/").length == 1;
    }

    public static boolean isValidTaskNumber(int number) {
        return number > 0 && number <= list.size();
    }
}
