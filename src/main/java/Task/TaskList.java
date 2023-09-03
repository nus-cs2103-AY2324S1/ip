package Task;

import Exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList = new ArrayList<>();
    public TaskList() {}

    public void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }

    public void addEvent(String input) {

        String[] list = input.split("/");
        String title = list[0].substring(6);
        String start = list[1].substring(5).trim();
        String end = list[2].substring(3).trim();

        // returns 1 if correct datetime
        // returns 0 if random string input

        int startTimeFormat = this.computeDateTimeFormat(start);
        int endTimeFormat = this.computeDateTimeFormat(end);

        if (startTimeFormat != 1 || endTimeFormat != 1) {
            Event event = new Event(title, start, end);
            System.out.println(event.toString());
            taskList.add(event);
        } else {
            try {
                DateTimeFormatter inputFormatWithTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime startDateTime = LocalDateTime.parse(start, inputFormatWithTime);
                LocalDateTime endDateTime = LocalDateTime.parse(end, inputFormatWithTime);
                Event event = new Event(title, startDateTime, endDateTime);
                System.out.println(event.toString());
                taskList.add(event);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
            }
        }


    }

    public void addDeadline(String input) {
        String[] list = input.split("/");
        String title = list[0].substring(9);
        String time = list[1].substring(3).trim();

        // returns 1 if correct datetime
        // returns 0 if random string input

        int timeFormat = this.computeDateTimeFormat(time);

        if (timeFormat == 0) {
            System.out.println("Got it. I've added this task:");
            Deadline deadline = new Deadline(title, time);
            System.out.println(deadline.toString());
            taskList.add(deadline);
        } else {
            try {
                DateTimeFormatter inputFormatWithTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatWithTime);
                Deadline deadline = new Deadline(title, dateTime);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline.toString());
                taskList.add(deadline);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
            }
        }
    }

    public void addToDo(String input) throws DukeException {

        System.out.println("Got it. I've added this task:");
        ToDo toDo = new ToDo(input);
        System.out.println(toDo.toString());
        taskList.add(toDo);
    }

    // method to quickly load up tasks into the tasklist during loading
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void mark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask);
    }

    public void unmark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask);
    }

    public int size() {
        return taskList.size();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    int computeDateTimeFormat(String input) {

        // returns 1 if correct datetime
        // returns 2 if correct date
        // returns 0 if random string input

       // Assume that the date input is dd/MM/yyyy 16-08-1977 1800

        String[] blankArray = input.split(" ");

        // Check for datetime input
        if (blankArray.length == 2 && isNumeric(blankArray[1])) {

            String[] slashArray = blankArray[0].split("-");

            boolean isAllNumeric = true;
            for (String s : slashArray) {
                if (!isNumeric(s)) {
                    isAllNumeric = false;
                }
            }
            if (slashArray.length == 3 && isAllNumeric) {
                return 1;
            }

        }
        return 0;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskList;
    }
}
