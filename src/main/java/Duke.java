import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected TaskType type;
        protected LocalDateTime start;
        protected LocalDateTime end;
        String markString = "    Nice! I've marked this task as done:";
        String unmarkString = "     OK, I've marked this task as not done yet:";

        public Task(String description, TaskType type, String start, String end) {
            this.description = description;
            this.isDone = false;
            // set to-do as the default type
            this.type = type;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.start = Objects.equals(start, "")
                ? null
                : LocalDateTime.parse(start, formatter);
            this.end = Objects.equals(end, "")
                    ? null
                    :LocalDateTime.parse(end, formatter);

        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTypeIcon() {
            switch (this.type) {
                case TODO:
                    return "T";
                case DEADLINE:
                    return "D";
                case EVENT:
                    return "E";
            }
            return null;
        }

        public void marking(Boolean checked) {
            this.isDone = checked;
        }

        public void descriptionString() {
            String initStatement = "     Got it. I've added this task:";
            System.out.println(initStatement);
            this.printMarking(false);
        }

        public void printMarking(boolean mark) {
            if (mark) {
                if (this.isDone) {
                    System.out.println(markString);
                } else {
                    System.out.println(unmarkString);
                }
            }

            System.out.printf("       [%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.description);

            if (!Objects.isNull(this.start) && !Objects.isNull(this.end)) {
                System.out.printf(" (from: %s to: %s)", this.start.toString().replace("T", " "), this.end.toString().replace("T", " "));
            } else if  (!Objects.isNull(this.start)) {
                System.out.printf(" (by: %s)", this.start.toString().replace("T", " "));
            } else {
                return;
            }
        }
        //...
    }

    public static class Storage {
        protected ArrayList<Task> taskList;

        public Storage() {
            this.taskList = new ArrayList<Task>();
        }

        public void addList(Task t) {
            this.taskList.add(t);
        }

        public void delete(int id) {
            System.out.println("     Noted. I've removed this task:");
            Task t = this.taskList.get(id);
            t.printMarking(false);
            this.taskList.remove(id);
            int size = this.taskList.size();
            System.out.printf("\n     Now you have %d tasks in the list.\n", size);
        }

        public void listPrinter() {
            for (int i = 0; i < this.taskList.size(); i++) {
                int index = i + 1;
                Task t = this.taskList.get(i);
                System.out.printf("     %d.[%s][%s] %s", index, t.getTypeIcon(), t.getStatusIcon(), t.description);
                if (!Objects.isNull(t.start) && !Objects.isNull(t.end)) {
                    System.out.printf(" (from: %s to: %s)%n", t.start.toString().replace("T", " "), t.end.toString().replace("T", " "));
                } else if  (!Objects.isNull(t.start)) {
                    System.out.printf(" (by: %s)%n", t.start.toString().replace("T", " "));
                } else {
                    System.out.print("\n");
                }
            }
        }

        public void printMarking(int i) throws DukeException{
            try {
                Task t = this.taskList.get(i);
                t.printMarking(true);
            }
            catch (Exception e) {
                throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        public void changeMarking(int i, boolean isDone) throws DukeException{
            try {
                Task t = this.taskList.get(i);
                t.marking(isDone);
            }
            catch (Exception e) {
                throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        public void printEntry(Task t) {
            t.descriptionString();
            int size = this.taskList.size();
            System.out.printf("\n     Now you have %d tasks in the list.\n", size);
        }
    }

    //function to retrieve string that the user input
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        //to mimic chatBot
        System.out.print(" ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {

        // standard response
        String horizontalLine = "   ------------------------------------------------------------------------";
        String intro = "    Hello! I'm iPbot \n    What can I do for you?";
        String outro = "    Bye. Hope to see you again soon!";
        String noDescError = "     ☹ OOPS!!! The description of a todo cannot be empty.";
        String noCommandError = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        // initialise
        Storage storage = new Storage();
        // read from txt file and create tasks and put into storage
        try (BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"))) {

            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file); // append mode
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                // Assuming your line contains comma-separated values
                String[] values = line.split("\\|");
                // Create your Java object based on the parsed values
                Duke.TaskType type = Objects.equals(values[0], "T")
                        ? TaskType.TODO
                        : Objects.equals(values[0], "D")
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;
                String start = "", end = "";
                try {
                    start = values[3];
                } catch (Exception e) {
                    start = "";
                }
                try {
                    end = values[4];
                } catch (Exception e) {
                    end = "";
                }
                Task obj = new Task(values[2], type, start, end); // Instantiate with appropriate arguments
                obj.marking(!Objects.equals(values[1], "0"));
                // Store the object in your storage instance
                storage.addList(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);

        while (true) {
            String input = getInput();
            String[] parts = input.split(" ");
            switch (parts[0]) {
                case "bye":
                    System.out.println(horizontalLine);
                    System.out.println(outro);
                    System.out.println(horizontalLine);
                    // write the changes into the file duke.txt
                    try {
                        FileWriter fileWriter = new FileWriter("data/duke.txt");
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        for (Task tasking : storage.taskList) {
                            // format the string

                            String formattedString = "";
                            Integer priority = tasking.isDone
                                    ? 1
                                    : 0;
                            switch (tasking.type) {
                                case TODO:
                                    formattedString = String.format("%c|%d|%s",
                                            'T', priority, tasking.description);
                                    break;
                                case DEADLINE:
                                    formattedString = String.format("%c|%d|%s|%s",
                                            'D', priority, tasking.description,
                                            tasking.start.toString().replace("T", " "));
                                    break;
                                case EVENT:
                                    formattedString = String.format("%c|%d|%s|%s|%s",
                                            'E', priority, tasking.description,
                                            tasking.start.toString().replace("T", " "),
                                            tasking.end.toString().replace("T", " "));
                                    break;
                            }
                            bufferedWriter.write(formattedString);
                            bufferedWriter.newLine(); // Move to the next line
                        }

                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                case "list":
                    System.out.println(horizontalLine);
                    storage.listPrinter();
                    System.out.println(horizontalLine);
                    break;
                case "mark" :
                    System.out.println(horizontalLine);
                    int id = Integer.parseInt(parts[1]) - 1;
                    try {
                        storage.changeMarking(id, true);
                        storage.printMarking(id);
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.println("\n" + horizontalLine);
                    break;
                case "unmark" :
                    int id2 = Integer.parseInt(parts[1]) - 1;
                    System.out.println(horizontalLine);
                    try {
                        storage.changeMarking(id2, false);
                        storage.printMarking(id2);
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.println("\n" + horizontalLine);
                    break;
                case "delete" :
                    int id3 = Integer.parseInt(parts[1]) - 1;
                    System.out.println(horizontalLine);
                    storage.delete(id3);
                    System.out.println(horizontalLine);
                    break;
                case "todo" :
                    int indexOfTodo = input.indexOf("todo");
                    String taskDesc = input.substring(indexOfTodo + 5);
                    if (Objects.equals(taskDesc, "")) {
                        System.out.println(horizontalLine);
                        System.out.println(noDescError);
                        System.out.println(horizontalLine);
                        break;
                    }
                    Task task = new Task(taskDesc, TaskType.TODO, "", "");
                    System.out.println(horizontalLine);
                    storage.addList(task);
                    storage.printEntry(task);
                    System.out.println(horizontalLine);
                    break;
                case "deadline" :
                    int indexOfDeadline = input.indexOf("deadline");
                    int indexOfBy = input.indexOf("/by");
                    taskDesc = input.substring(indexOfDeadline + 9, indexOfBy);
                    String deadlinePart = "";
                    deadlinePart = input.substring(indexOfBy + 3).trim();
                    System.out.println(horizontalLine);
                    task = new Task(taskDesc, TaskType.DEADLINE, deadlinePart, "");
                    storage.addList(task);
                    storage.printEntry(task);

                    System.out.println(horizontalLine);
                    break;
                case "event" :
                    int indexOfEvent = input.indexOf("event");
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    taskDesc = input.substring(indexOfEvent + 6, indexOfFrom);
                    String fromPart = "";
                    fromPart = input.substring(indexOfFrom + 5, indexOfTo).trim();
                    String toPart = "";
                    toPart = input.substring(indexOfTo +3).trim();
                    System.out.println(horizontalLine);
                    task = new Task(taskDesc, TaskType.EVENT, fromPart, toPart);
                    storage.addList(task);
                    storage.printEntry(task);

                    System.out.println(horizontalLine);
                    break;
                default:
                    System.out.println(horizontalLine);
                    System.out.println(noCommandError);
                    System.out.println(horizontalLine);
            }
        }
    }
}