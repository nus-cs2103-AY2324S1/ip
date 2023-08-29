import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + "." + taskList.get(i).toString();

            if (i != taskList.size() - 1) {
                tasks += "\n";
            }
        }

        return tasks;
    }

    public String toFileString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += taskList.get(i).fileString() + "\n";
        }
        return tasks;
    }

//    /**
//     * Creates a Task and adds it to the task list.
//     *
//     * @param input The input command from user.
//     * @throws InvalidCommandException     Handles missing or wrong input commands by user.
//     * @throws InvalidDescriptionException Handle empty task descriptions.
//     */
//    public void addTask(String[] input) throws DukeException {
//
//        String cmd = input[0];
//
//        switch (cmd) {
//        case "todo":
//            if (input.length == 1 || input[1].equals("")) {
//                throw new InvalidDescriptionException("todo");
//            }
//
//            taskList.add(new ToDo(input[1].trim()));
//            break;
//
//        case "deadline":
//            if (input.length == 1 || input[1].equals("") || input[1].trim().charAt(0) == '/') {
//                throw new InvalidDescriptionException("deadline");
//            }
//
//            String[] task = input[1].split("/by ", 2);
//            String taskDesc = task[0].trim();
//
//            if (task.length == 1 || task[1].equals("")) {
//                throw new InvalidCommandException("☹ OOPS!!! Need to include /by date for deadline.");
//            }
//
//            String dateTime = task[1].trim();
//
//            if (isIsoDateTime(dateTime)) {
//                taskList.add(new Deadline(taskDesc, printIsoDateTime(dateTime)));
//            } else {
//                throw new InvalidCommandException("Invalid date time format. Format is yyyy-mm-dd HH:mm");
//            }
//            break;
//
//        case "event":
//            if (input.length == 1 || input[1].equals("") || input[1].trim().charAt(0) == '/') {
//                throw new InvalidDescriptionException("description");
//            }
//
//            String[] event = input[1].split("/from ", 2);
//
//            if (event.length == 1 || event[1].equals("")) {
//                throw new InvalidCommandException("☹ OOPS!!! Need to include /from date for an event.");
//            }
//
//            String[] dates = event[1].split("/to ", 2);
//
//            if (dates.length == 1 || dates[1].equals("")) {
//                throw new InvalidCommandException("☹ OOPS!!! Need to include /to date for an event.");
//            }
//
//            String fromDate = dates[0].trim();
//            String toDate = dates[1].trim();
//
//            if (isIsoDateTime(fromDate) && isIsoDateTime(toDate)) {
//                taskList.add(new Event(event[0].trim(), printIsoDateTime(fromDate),
//                        printIsoDateTime(toDate)));
//            } else {
//                throw new InvalidCommandException("Invalid date time format. " +
//                        "Both /from and /to format is yyyy-mm-dd HH:mm");
//            }
//
//            break;
//
//        default:
//            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//
//        System.out.println("Got it. I've added this task:");
//        System.out.println(taskList.get(taskList.size() - 1).toString());
//        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
//    }
    public String addTask(String taskType, String[] args) {
        switch (taskType) {
        case "todo":
            taskList.add(new ToDo(args[0]));
            break;

        case "deadline":
            taskList.add(new Deadline(args[0], args[1]));
            break;

        case "event":
            taskList.add(new Event(args[0], args[1], args[2]));
            break;

        }

        return "Got it. I've added this task:\n" +
                taskList.get(taskList.size() - 1).toString() +
                "\nNow you have " + (taskList.size()) + " tasks in the list.";
    }

    public String editTask(String taskType, int ind) {

        String editDesc = "";

        switch (taskType) {
        case "mark":
            taskList.get(ind - 1).markTask();
            editDesc += "Nice! I've marked this task as done:\n" + taskList.get(ind - 1).toString();
            break;

        case "unmark":
            taskList.get(ind - 1).unmarkTask();
            editDesc += "OK, I've marked this task as not done yet:\n" + taskList.get(ind - 1).toString();
            break;

        case "delete":
            editDesc += "Noted. I've removed this task:\n" + taskList.get(ind - 1).toString();
            taskList.remove(ind - 1);
            editDesc += "\nNow you have " + taskList.size() + " tasks in the list.";
            break;
        }

        return editDesc;
    }

//    public void editTask (String[] cmd) throws InvalidCommandException, InvalidIndexException {
//        String regex = "-?\\d+";
//        if (cmd.length == 1) {
//            throw new InvalidCommandException("Need to include index for task marking!");
//        }
//
//        if (cmd[1] == " ") {
//            throw new InvalidCommandException("Please include index for task marking");
//        }
//
//        if (!cmd[1].matches(regex)) {
//            throw new InvalidCommandException("Can only use integers as index for marking!");
//        }
//
//        int pos = Integer.parseInt(cmd[1]);
//
//        if (pos > taskList.size() || pos <= 0) {
//            throw new InvalidIndexException();
//        }
//
//        if (cmd[0].equals("mark")) {
//            taskList.get(pos - 1).markTask();
//            System.out.println("Nice! I've marked this task as done:");
//        } else if (cmd[0].equals("unmark")){
//            taskList.get(pos - 1).unmarkTask();
//            System.out.println("OK, I've marked this task as not done yet:");
//        } else {
//            System.out.println("Noted. I've removed this task:");
//        }
//
//        System.out.println(taskList.get(pos - 1).toString());
//        if (cmd[0].equals("delete")) {
//            taskList.remove(pos - 1);
//            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//        }
//    }

//    /**
//     * Checks if the format of the string is the expected DateTime format.
//     * The expected format is yyyy-mm-dd HH:mm.
//     *
//     * @param input The dateTime string input from the user.
//     * @return The boolean value representing if string matches expected DateTime format.
//     */
//    private static boolean isIsoDateTime(String input) {
//        String isoDatePattern = "^\\d{4}-\\d{2}-\\d{2}\\s([01]\\d|2[0-3]):[0-5]\\d$";
//
//        Pattern pattern = Pattern.compile(isoDatePattern);
//        Matcher matcher = pattern.matcher(input);
//
//        return matcher.matches();
//    }

//    /**
//     * Return the string representing the dateTime input in MMM d yyyy h.mma format.
//     *
//     * @param input The dateTime string input from the user.
//     * @return The string of the formatted DateTime input.
//     */
//    private static String printIsoDateTime(String input) {
//        String[] dateTime = input.split(" ", 2);
//        String dateTimeFormat = dateTime[0] + "T" + dateTime[1] + ":00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h.mma", Locale.ENGLISH);
//
//        LocalDateTime deadline = LocalDateTime.parse(dateTimeFormat);
//        return deadline.format(formatter);
//    }
}
