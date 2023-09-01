import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

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
            Deadline deadline = new Deadline(title, time);
            System.out.println(deadline.toString());
            taskList.add(deadline);
        } else {
            try {
                DateTimeFormatter inputFormatWithTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatWithTime);
                Deadline deadline = new Deadline(title, dateTime);
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

    public void list() {
        Duke.line();
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                Task t = taskList.get(i);
                System.out.println(index + "." + t.toString());
            }
        }
        Duke.line();
    }

    public void mark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(true);
    }

    public void unmark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(false);
    }

    public BufferedWriter printStoreFormat(BufferedWriter writer) throws IOException {
        for (Task t : taskList) {
            writer.append(t.storeFormat()).append("\n");

        }
        return writer;
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

    private int computeDateTimeFormat(String input) {

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

//        } else if (blankArray.length == 1) {
//
//            String[] slashArray = blankArray[0].split("/");
//            boolean isAllNumeric = true;
//            for (String s : slashArray) {
//                if (!isNumeric(s)) {
//                    isAllNumeric = false;
//                }
//            }
//
//            if (slashArray.length == 3 && isAllNumeric) {
//                return 2;
//            }

        }

        return 0;
    }
}
