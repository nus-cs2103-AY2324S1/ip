import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = 
                            "\t░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n" +
                            "\t██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n" +
                            "\t██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n" +
                            "\t██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n" +
                            "\t╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n" +
                            "\t░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";
    private ArrayList<Task> taskList = new ArrayList<Task>();
    // private String[] taskList = new String[100];
    // private boolean[] taskListCompletion = new boolean[100];
    // private int taskList.size() = 0;

    private class Task{
        private String taskName;
        private boolean isDone;

        public Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String getTaskName() {
            return this.taskName;
        }

        public boolean getIsDone() {
            return this.isDone;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
        }

        public String saveString() {
            return "";
        }
    }

    // Use inheritance
    private class ToDo extends Task {
        public ToDo (String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public String saveString() {
            return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName();
        }
    }

    private class Deadline extends Task {
        private String deadlineString;

        public Deadline (String taskName, String deadline) {
            super(taskName);
            this.deadlineString = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.deadlineString + ")";
        }

        @Override
        public String saveString() {
            return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + this.deadlineString;
        }
    }

    private class Event extends Task {
        private String fromString;
        private String toString;

        public Event (String taskName, String from, String to) {
            super(taskName);
            this.fromString = from;
            this.toString = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.fromString + " to: " + this.toString + ")";
        }
        @Override
        public String saveString() {
            return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + this.fromString + " | " + this.toString;
        }
    }

    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke();

        // load task list
        duke.loadTaskList();

        // introduction
        System.out.println("\n\tWelcome to Chatty.\n" + duke.LOGO);
        duke.sendGreeting();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println();
            String input = sc.nextLine().trim();

            while (!input.equals("bye")) {
                duke.handleTextInput(input);
                System.out.println();
                input = sc.nextLine().trim();
            }
        }
        // say bye
        duke.sendFarewell();
    }

    private void addToTaskList(Task task) {
        if (taskList.size() < 100) {
            taskList.add(task);
        } else {
            System.out.println("Error: List is full.");
        }
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    private void handleTextInput(String inputString) {
        System.out.println(DIVIDER);
        // handle key logic here.
        if (inputString.equals("list")) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
            }
        } else if (inputString.startsWith("mark")) {
            int taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            // taskList.get(taskIndex).markAsDone();
            taskList.get(taskIndex).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + taskList.get(taskIndex));
        } else if (inputString.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            taskList.get(taskIndex).unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + taskList.get(taskIndex));
        } else if (inputString.startsWith("todo")) {
            try {
                // this excludes the space after todo as well
                String taskName = inputString.substring(5);
                Task newTask = new ToDo(taskName);
                addTaskOutputText(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                // stop before /by
                String taskName = inputString.substring(9, inputString.indexOf("/by") - 1);
                // get day
                String deadline = inputString.substring(inputString.indexOf("/by") + 4);
                Task newTask = new Deadline(taskName, deadline);
                addTaskOutputText(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (inputString.startsWith("event")) {
            try {
                String taskName = inputString.substring(6, inputString.indexOf("/from") - 1);
                String from = inputString.substring(inputString.indexOf("/from") + 6, inputString.indexOf("/to") - 1);
                String to = inputString.substring(inputString.indexOf("/to") + 4);
                Task newTask = new Event(taskName, from, to);
                addTaskOutputText(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else if (inputString.startsWith("delete")) {
            String indexToDelete = inputString.split(" ")[1];
            int taskIndexToDelete = Integer.parseInt(indexToDelete) - 1;
            System.out.println("\tNoted. I've removed task " + indexToDelete + ":");
            System.out.println("\t\t" + taskList.get(taskIndexToDelete));
            // have to place here before it's removed for the output to be correct
            taskList.remove(taskIndexToDelete);
            String taskWord = taskList.size() == 1 ? "task" : "tasks";
            System.out.println("\tNow you have " + taskList.size() + " " + taskWord + " in your list.");
        } else {
            System.out.println("\tI'm not quite sure what that means. Try again using either mark <index>, unmark <index>, todo <task>, deadline <task /by ..>, event <task /from .. /to ..>, or bye.");
        }
        System.out.println(DIVIDER);
        saveTaskListHelper();
    }

    private void addTaskOutputText(Task newTask) {
        addToTaskList(newTask);
        System.out.println("\tGot it. I've added this task:");
        // can use -1 because we just added it
        System.out.println("\t\t" + taskList.get(taskList.size() - 1));
        String taskWord = taskList.size() == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskList.size() + " " + taskWord + " in your list.");
    }

    private void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }

    private void saveTaskListHelper() {
        try {
            saveTaskList();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // save the task list to file
    private void saveTaskList() throws IOException {
        // loop through the task list
        // for each task, write to file
        String filePath = "./data/duke.txt";
        // if directory doesn't exist, create it
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            // write to file
            // format liek so
            /* 
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
             */
            fWriter.write(taskList.get(i).saveString() + "\n");
        }
        fWriter.close();
    }

    private void loadTaskList() {
        // read from file if it exists
        String filePath = "./data/duke.txt";
        File directory = new File("./data");
        // We'll skip if directory or file doesn't exist
        if (!directory.exists()) {
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        // load the data into taskList
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                // save to taskList
                taskList.add(parseTask(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private Task parseTask(String data) {
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th | Aug 8th
        String[] dataArr = data.split(" \\| ");
        for (int i = 0; i < dataArr.length; i++) {
            System.out.println(dataArr[i]);
        }
        String taskType = dataArr[0];
        boolean isDone = dataArr[1].equals("1");
        // each task has at least 3 elements
        String taskName = dataArr[2];
        Task newTask;
        // todo
        if (taskType.equals("T")) {
            newTask = new ToDo(taskName);
        // deadline
        } else if (taskType.equals("D")) {
            newTask = new Deadline(taskName, dataArr[3]);
        // event
        } else {
            newTask = new Event(taskName, dataArr[3], dataArr[4]);
        }
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }
}
