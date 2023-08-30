import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.Writer;
import java.io.FileWriter;

public class Duke {

    ArrayList<Task> list = new ArrayList<>();

    String divider = "------------------------------------\n";

    String greet = divider +
            "Hello! I'm Khaleelur!\n" +
            "What can I do for you?\n " +
            divider;

    String exit = divider +
            "Bye. Hope to see you again soon!\n" +
            divider;


    /**
     * Retrieves a formatted string showing the number of tasks left in the list.
     *
     * @return A string indicating the number of tasks remaining.
     */
    public String getTaskLeft() {
        return "Now you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Retrieves a formatted string containing all tasks in the list.
     *
     * @return A string listing all tasks in the task list.
     * @throws DukeException If there are no tasks in the list.
     */
    public String getAllToDo() throws DukeException {
        StringBuilder res = new StringBuilder();

        if (list.size() == 0) {
            throw new DukeException("Oh no! No tasks for now! Add more tasks :)\n");
        }

        res.append("Here are the tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            
            res.append(i + 1).append(".")
                    .append(list.get(i).toString());
            if (i != list.size() - 1) res.append("\n");
        }
        return res.toString();
    }

    /**
     * Retrieves a formatted string indicating the success of marking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String markTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to mark task!\n");
        }

        if (parts.length > 2 ) {
            throw new DukeException("Enter mark command properly!\n");
        }

        //No task to mark
        if (list.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to mark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Enter mark command with positive index lesser than " + (list.size() + 1) + "\n");
                }

                res = list.get(index - 1).setMarked();
                handleChangesInFile();

            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your markcommand!\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    /**
     * Reads tasks from a file and processes them based on their types.
     * The tasks are parsed and passed to respective handler methods.
     * Handles Todo, Deadline, and Event tasks stored in the file.
     */
    public void handleReadAllTasksFromFile() {

        try {
            File obj = new File("./src/main/storage/duke.txt");
        } catch(Exception e) {
            System.out.println("Please create a folder at the specified folder : src/main/storage/duke.txt");
        }

        try {
            File obj = new File("./src/main/storage/duke.txt");
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {

                String str = reader.nextLine();

                String taskType = str.substring(3, 4);
                String taskDescription = str.substring(9);
                switch (taskType) {
                    case "T": {
                        handleTodoTask("todo " + taskDescription, "file");
                        break;
                    }
                    case "D": {
                        handleDeadlineTask("deadline " + taskDescription, "file");
                        break;
                    }
                    case "E": {
                        handleEventTask("event " + taskDescription, "file");
                    }
                    default:
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Please create a folder at the specified folder : src/main/storage/duke.txt");
        }
        catch(IOException | DukeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes changes in the task list to the file.
     * Each task is written as a formatted string in the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void handleChangesInFile() throws IOException {
        try {
            Writer writer = new FileWriter("./src/main/storage/duke.txt", false);

                for(int i = 0;i < list.size();i++) {
                    writer.append("" + (i+1)).append(".").append(list.get(i).toString()).append("\n");
                }

                writer.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Retrieves a formatted string indicating the success of unmarking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String unmarkTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to unmark task!\n");
        }

        if (parts.length > 2 ) {
            throw new DukeException("Enter unmark command properly!\n");
        }

        //No task to mark
        if (list.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to unmark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Enter umark command with positive index lesser than " + (list.size() + 1) + "\n");
                }
                res = list.get(index - 1).setUnmarked();
                handleChangesInFile();

            } catch (NumberFormatException | IOException e) {
                throw new DukeException("Enter a valid positive integer after your unmark command!\n");
            }
        }
        return res;
    }


    /**
     * Deletes a task from the task list based on the provided index.
     *
     * @param input The input of the task to be deleted.
     * @return A message indicating the success of the deletion.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String deleteTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to delete
        if (input.equals("delete") && parts.length == 1) {
            throw new DukeException("Specify index to delete task!\n");
        }

        //No task to delete
        if (list.size() == 0) {
            throw new DukeException("No tasks to delete! Add more tasks to delete!\n");
        }

        if ((parts[0].equals("delete")) && parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                String removedTask = list.get(index - 1).toString();
                list.remove(index - 1);
                res = "Noted. I've removed this task: \n " + "  " + removedTask + "\n" + getTaskLeft();
                handleChangesInFile();
            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your mark/unmark command!\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return res;
    }

    /**
     * Adds a ToDo task to the task list based on the provided input.
     *
     * @param input The user input containing the task description.
     * @return A message indicating the success of adding the ToDo task.
     * @throws DukeException If there's an issue with the input or task description.
     */
    public String handleTodoTask(String input, String from) throws DukeException, IOException {
        String task = "";

        String[] parts = input.split(" ");

        for (int i = 1; i < parts.length; i++) {
            task += parts[i] + " ";
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said?! Enter again!\n");
        }

        list.add(new ToDo(task, TaskType.TODO));

        String str = list.get(list.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();

        if(from.equals("user")) {
            handleChangesInFile();
        }

        return res;
    }

    /**
     * Adds a Deadline task to the task list based on the provided input.
     *
     * @param input The user input containing the task description and deadline.
     * @return A message indicating the success of adding the Deadline task.
     * @throws DukeException If there's an issue with the input, task description, or deadline.
     */
    public String handleDeadlineTask(String input,String from) throws DukeException, IOException {
        String task = "";
        String by = "";
        String endTime = "";

        if (from.equals("user")) {
            String[] parts = input.split("/by ");
            String[] taskArray = parts[0].split(" ");
            if (parts.length != 2) {
                throw new DukeException("Specify by date and time!");
            }
            String[] deadlineInfo = parts[1].split(" ");

            if (deadlineInfo.length != 2) {
                throw new DukeException("Specify both date and time in the following manner : yyyy-mm-dd hh:mm");
            }
            by = deadlineInfo[0];
            endTime = deadlineInfo[1];

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }
        }

         else if (from.equals("file")) {
            String [] parts = input.split("by: ");
            String[] taskArray = parts[0].split(" ");
            String[] deadlineInfo = parts[1].split(" ");

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                by += deadlineInfo[i] + " ";
            }

            by = by.substring(0,11);

            endTime = deadlineInfo[3];
            endTime = endTime.substring(0, endTime.length() - 1);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(by, inputFormatter);
            by = localDate.format(outputFormatter);
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! Enter again!\n");
        }

        if (by.isEmpty()) {
            throw new DukeException("deadline task must have /by date and time\n");
        }

        list.add(new Deadline(task, by, endTime+":00", TaskType.DEADLINE));

        String str = list.get(list.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();

        if(from.equals("user")) {
            handleChangesInFile();
        }

        return res;
    }

    /**
     * Adds an Event task to the task list based on the provided input.
     *
     * @param input The user input containing the task description and event timings.
     * @return A message indicating the success of adding the Event task.
     * @throws DukeException If there's an issue with the input, task description, or event timings.
     */
    public String handleEventTask(String input,String from) throws DukeException, IOException {
        String task = "";
        String startDate = "";
        String endDate = "";
        String startTime = "";
        String endTime = "";

        if (from.equals("user")) {
            String[] parts = input.split("/from ");
            if (parts.length != 2) {
                throw new DukeException("Specify from and to date and time!");
            }
            String[] taskArray = parts[0].split(" ");
            String[] taskInfo = parts[1].split("/to ");

            if (taskInfo.length != 2) {
                throw new DukeException("Specify both date and time for /from and /to in the following manner : yyyy-mm-dd hh:mm");
            }

            String[] fromInfo = taskInfo[0].split(" ");
            String[] toInfo = taskInfo[1].split(" ");

            if (fromInfo.length != 2) {
                throw new DukeException("Specify both date and time for /from in the following manner : yyyy-mm-dd hh:mm");
            }

            if (toInfo.length != 2) {
                throw new DukeException("Specify both date and time for /to in the following manner : yyyy-mm-dd hh:mm");
            }

            startDate = fromInfo[0];
            startTime = fromInfo[1];

            endDate = toInfo[0];
            endTime = toInfo[1];


            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }
        }

        if (from.equals("file")) {

            String[] parts = input.split("from: ");
            String[] taskArray = parts[0].split(" ");
            String[] taskInfo = parts[1].split("to: ");

            String[] fromInfo = taskInfo[0].split(" ");
            String[] toInfo = taskInfo[1].split(" ");

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                startDate += fromInfo[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                endDate += toInfo[i] + " ";
            }

            startDate = startDate.substring(0,11);
            endDate = endDate.substring(0,11);
            startTime = fromInfo[3];
            endTime = toInfo[3];
            endTime = endTime.substring(0, endTime.length() - 1);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateStart = LocalDate.parse(startDate, inputFormatter);
            LocalDate localDateEnd = LocalDate.parse(endDate, inputFormatter);
            startDate = localDateStart.format(outputFormatter);
            endDate = localDateEnd.format(outputFormatter);
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! Enter again!\n");
        }

        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new DukeException("event task must have both /from and /to times\n");
        }

        list.add(new Event(task, startDate, endDate, startTime+":00", endTime+":00", TaskType.EVENT));

        String str = list.get(list.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();

        if(from.equals("user")) {
            handleChangesInFile();
        }

        return res;
    }

    /**
     * Handles user input and performs the relevant actions based on the commands.
     * Continuously listens for user input until the "bye" command is entered.
     */
    public void handleUserInput() {
        Scanner obj = new Scanner(System.in);
        while (true) {
            String userInput = obj.nextLine();
            try {
                if (userInput.equals("bye")) {
                    return;
                }

                if (userInput.startsWith("mark")) {
                    System.out.println(divider + markTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("unmark")) {
                    System.out.println(divider + unmarkTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("delete")) {
                    System.out.println(divider + deleteTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("list")) {
                    System.out.println(divider + getAllToDo() + "\n" + divider);
                } else if (userInput.startsWith("todo")) {
                    System.out.println(divider + handleTodoTask(userInput, "user") + "\n" + divider);
                } else if (userInput.startsWith("deadline")) {
                    System.out.println(divider + handleDeadlineTask(userInput, "user") + "\n" + divider);
                } else if (userInput.startsWith("event")) {
                    System.out.println(divider + handleEventTask(userInput, "user") + "\n" + divider);
                } else {
                    throw new InvalidInputExpression("Invalid input!! Specify commands as list, mark, unmark, or deadline, event and todo followed by the task please la dei!\n");
                }
            } catch (DukeException | InvalidInputExpression | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();

        System.out.println(duke.greet);
        duke.handleReadAllTasksFromFile();
        duke.handleUserInput();
        System.out.println(duke.exit);
    }
}

