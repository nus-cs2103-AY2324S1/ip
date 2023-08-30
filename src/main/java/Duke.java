import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        public String name;
        public boolean isComplete;

        public Task(String name) {
            this.name = name;
            this.isComplete = false;
        }

        public String getMark() {
            return (isComplete ? "X" : " ");
        }

        public String toString() {
            return "[" + getMark() + "] " + name;
        }

        public String taskToStringStore(Task task) {
            String isCompleteString = (getMark() == "X") ? "X" : "O";
            return isCompleteString + " " + task.name + " ";
        }

    }

    public static class Todo extends Task {
        public Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public String taskToStringStore(Task task) {
            return "T " + super.taskToStringStore(task);
        }
    }

    public static class Deadline extends Task {
        public String by;

        public Deadline(String name, String by) {
            super(name);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }

        @Override
        public String taskToStringStore(Task task) {
            return "D" + " " + super.taskToStringStore(task) + by;
        }
    }

    public static class Event extends Task {
        public String from;
        public String to;

        public Event(String name, String from, String to) {
            super(name);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + " )";
        }

        @Override
        public String taskToStringStore(Task task) {
            return "E" + " " + super.taskToStringStore(task) + from + " " + to ;
        }
    }

    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    Task[] taskList = new Task[100];
    ArrayList<Task> taskArrayList = new ArrayList<>();
    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";

    private static final String FILE_PATH_NAME = "./data/chadBot.txt";

        public void chadGreet() {
            System.out.println(line);
            System.out.println("Yo! This is CHADbot\n");
            System.out.println("Need sum help?\n");
            System.out.println(line);
        }
        public void chadBye() {
            System.out.println(line);
            System.out.println("Cya l8r~\n");
            System.out.println(line);
        }

        public void chadOutput(String input) {
            System.out.println(line);
            System.out.println(input + "\n");
            System.out.println(line);
        }
        public void chadAddList(Task input) {
            try {
                if (input == null) {
                    throw new DukeException("What are you on about? I do not understand...");
                }
//                taskList[taskCount] = input;
                taskArrayList.add(input);
                System.out.println(line);
                System.out.println(input.toString() + " has been added to yo list!\n");
                System.out.println(line);
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage() + "\n");
                System.out.println(line);
            }
        }

        public void chadRemoveList(int index){
            try {
                Task removed = taskArrayList.remove(index - 1);
                System.out.println("Okay! I have removed this task :\n" + removed);

                if (taskArrayList.size() == 0) {
                    System.out.println("Your list is currently empty! Good job :)");
                } else {
                    System.out.println("Your list is currently " + taskArrayList.size() + " long... Get back to work!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid task index! Try again!");
            }

        }
        public void chadListTask() {
            if (taskArrayList.size() == 0) {
                System.out.println("Your task list is EMPTY!");
            } else {
                System.out.println(line);
                System.out.println("Your outstanding tasks are...");
                for (int i = 0; i < taskArrayList.size(); i++) {
//                    System.out.println("Task " + (i + 1) + ") " + taskList[i].toString());
                    System.out.println("Task " + (i + 1) + ") " + taskArrayList.get(i)); //<<<<<
                }
                System.out.println("\n" + "Get to work NOW!\n");
                System.out.println(line);
            }
        }

        public void chadMarkTask(int index) {
//            taskList[index - 1].isComplete = true;
            taskArrayList.get(index - 1).isComplete = true;
            System.out.println(line);
            System.out.println("Good job! Task fulfilled!");
//            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(taskArrayList.get(index - 1).name + " [" + taskArrayList.get(index - 1).getMark() + "]\n");
            System.out.println(line);
        }

        public void chadUnmarkTask(int index) {
//            taskList[index - 1].isComplete = false;
            taskArrayList.get(index - 1).isComplete = false;
            System.out.println(line);
            System.out.println("Boooo! Task is not done!");
//            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(taskArrayList.get(index - 1).name + " [" + taskArrayList.get(index - 1).getMark() + "]\n");
            System.out.println(line);
        }

        public void makeNewDirectory() {
            File newDir = new File("./data");
            if (newDir.mkdirs()) {
                System.out.println("Data directory has been created successfully!");
            } else {
                System.out.println("Data directory was not created! (There may already exists a data directory)");
            }
        }

        public void makeNewFile() {
            try {
                File newFile = new File(FILE_PATH_NAME);
                if (newFile.createNewFile()) {
                    System.out.println("I have created this file for you: " + newFile.getName());
                } else {
                    System.out.println("You already have the file... Stop wasting my time");
                }
            } catch (IOException e) {
                System.out.println("An error has occurred when creating the file: " + e.getMessage());
            }
        }

        public void printFile() throws FileNotFoundException {
            File chadFile = new File(FILE_PATH_NAME);
            Scanner s = new Scanner(chadFile);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }

        public void writeFile() {
            try {
                FileWriter fw = new FileWriter(FILE_PATH_NAME);
                for (Task task : taskArrayList) {
                    fw.write(task.taskToStringStore(task) + System.lineSeparator());
                }
                fw.close();

            } catch (IOException e) {
                System.out.println("There was an error writing the file: " + e);;
            }
        }

        public Task stringToTask(String data) {

            String[] parts = data.split(" ", 0);


            String type = parts[0];

            boolean mark = parts[1] == "X";

            switch (type) {
                case "T":
                    Todo t = new Todo(parts[2]);
                    t.isComplete = mark;
                    System.out.println(t.toString());
                    return t;
                case "D":
                    Deadline d = new Deadline(parts[2], parts[3]);
                    d.isComplete = mark;
                    System.out.println(d.toString());
                    return d;
                case "E":
                    Event e = new Event(parts[2], parts[3], parts[4]);
                    e.isComplete = mark;
                    System.out.println(e.toString());
                    return e;
            }
            return null;
        }

        public void loadFile() {
            try {
                File chadFile = new File(FILE_PATH_NAME);
                Scanner s = new Scanner(chadFile);
                System.out.println("Here are the tasks from last time:");
                while (s.hasNext()) {
                    String nextTask = s.nextLine();
                    Task t = stringToTask(nextTask);
                    if (t != null) {
                        taskArrayList.add(t);
                    }
                }


            } catch (FileNotFoundException e) {
                System.out.println("File not found... Unable to load tasks");
            }
        }





    public static void main(String[] args) {
        Duke chad = new Duke();
        chad.chadGreet();
        chad.makeNewDirectory();
        chad.makeNewFile();
        chad.loadFile();


        Scanner scanObj = new Scanner(System.in);

        while(true) {
            String input = scanObj.nextLine();
            String[] inputArray = input.split(" ", 2);
            if (inputArray[0].equals("bye")) {
                chad.chadBye();
                break;

            } else if (inputArray[0].equals("list")) {
                chad.chadListTask();

            } else if (inputArray[0].equals("mark")) {
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadMarkTask(index);
                    chad.writeFile();

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");

                }
            } else if (inputArray[0].equals("unmark")) {
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadUnmarkTask(index);
                    chad.writeFile();

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");

                }

            } else if (inputArray[0].equals("todo")) {
                try {
                    if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    chad.chadAddList(new Todo(inputArray[1]));
                    chad.writeFile();
                } catch (DukeException e) {
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (inputArray[0].equals("deadline")) {
                try {
                    if (inputArray.length < 2 || inputArray[1].isEmpty()){
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    String[] details = inputArray[1].split(" /by ", 2);

                    if (details.length < 2) {
                        throw new DukeException("Umm you forgot the deadline! Remember to use /by before the deadline!");
                    }
                    chad.chadAddList(new Deadline(details[0], details[1]));
                    chad.writeFile();
                } catch (DukeException e){
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (inputArray[0].equals("event")) {
                try {
                    if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    String[] details = inputArray[1].split(" /from ", 2);
                    if (details.length < 2) {
                        throw new DukeException("Hey you are missing the start date! Remember to use /from before the deadline!");
                    }
                    String[] timings = details[1].split(" /to ", 0);
                    if (timings.length < 2) {
                        throw new DukeException("The end date is missing! Do better! Use /to!");
                    }
                    chad.chadAddList(new Event(details[0], timings[0], timings[1]));
                    chad.writeFile();
                } catch(DukeException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (inputArray[0].equals("delete")) {
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadRemoveList(index);
                    chad.writeFile();

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");
                }

            } else {
                chad.chadOutput("Hmm? You are not making sense!");
            }
        }
        scanObj.close();

    }
}
