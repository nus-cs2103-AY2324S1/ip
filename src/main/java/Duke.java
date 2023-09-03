//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Chatbot {
    public Chatbot() {
    }

    public static void main(String[] args) {
        Chatbot.TaskList taskList = new Chatbot.TaskList();
        new Chatbot.Ui();
        Chatbot.Storage.load(taskList);
        Chatbot.Ui.startMessage();
        Scanner userInput = new Scanner(System.in);

        while(true) {
            String userMessage = userInput.nextLine();
            Chatbot.Parser.parse(userMessage, taskList);
            Chatbot.Storage.saveTasks(taskList);
        }
    }

    public static class Storage {
        public Storage() {
        }

        public static void load(Chatbot.TaskList taskList) {
            try {
                String datePattern2 = "MMM-dd-yyyy HH:mm";
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(datePattern2);
                File taskFile = new File("./tasks.txt");
                Scanner taskReader = new Scanner(taskFile);

                while(taskReader.hasNextLine()) {
                    String taskFromFile = taskReader.nextLine();
                    if (taskFromFile.charAt(0) == 'T') {
                        Chatbot.Todo todo = new Chatbot.Todo(taskFromFile.substring(3));
                        if (taskFromFile.charAt(1) == 't') {
                            todo.markAsDone();
                        }

                        taskList.addTask(todo);
                    }

                    LocalDateTime start;
                    String startFromFile;
                    if (taskFromFile.charAt(0) == 'D') {
                        startFromFile = taskReader.nextLine();
                        start = LocalDateTime.parse(startFromFile, formatter2);
                        Chatbot.Deadline deadline = new Chatbot.Deadline(taskFromFile.substring(3), start);
                        if (taskFromFile.charAt(1) == 't') {
                            deadline.markAsDone();
                        }

                        taskList.addTask(deadline);
                    }

                    if (taskFromFile.charAt(0) == 'E') {
                        startFromFile = taskReader.nextLine();
                        start = LocalDateTime.parse(startFromFile, formatter2);
                        String endFromFile = taskReader.nextLine();
                        LocalDateTime end = LocalDateTime.parse(endFromFile, formatter2);
                        Chatbot.Event event = new Chatbot.Event(taskFromFile.substring(3), start, end);
                        if (taskFromFile.charAt(1) == 't') {
                            event.markAsDone();
                        }

                        taskList.addTask(event);
                    }
                }
            } catch (FileNotFoundException var12) {
                File taskFile = new File("./tasks.txt");

                try {
                    taskFile.createNewFile();
                } catch (IOException var11) {
                    System.out.println("Error!");
                }
            }

        }

        public static void saveTasks(Chatbot.TaskList taskList) {
            try {
                FileWriter taskWriter = new FileWriter("./tasks.txt", false);

                for(int taskNumber = 0; taskNumber <= taskList.getCount(); ++taskNumber) {
                    Chatbot.Task var10001;
                    if (taskList.getTask(taskNumber) instanceof Chatbot.Todo) {
                        taskWriter.write("T");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(var10001.getDescription() + "\n");
                    } else if (taskList.getTask(taskNumber) instanceof Chatbot.Deadline) {
                        taskWriter.write("D");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(((Chatbot.Deadline)var10001).getDescriptionWithoutTime() + "\n");
                    } else if (taskList.getTask(taskNumber) instanceof Chatbot.Event) {
                        taskWriter.write("E");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(((Chatbot.Event)var10001).getDescriptionWithoutTime() + "\n");
                    }

                    if (taskList.getTask(taskNumber) instanceof Chatbot.Deadline) {
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(((Chatbot.Deadline)var10001).getDeadline() + "\n");
                    }

                    if (taskList.getTask(taskNumber) instanceof Chatbot.Event) {
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(((Chatbot.Event)var10001).getStartTime() + "\n");
                        var10001 = taskList.getTask(taskNumber);
                        taskWriter.write(((Chatbot.Event)var10001).getEndTime() + "\n");
                    }

                    taskWriter.flush();
                }
            } catch (Exception var3) {
                System.out.println("Error!");
            }

        }
    }

    public static class TaskList {
        private Chatbot.Task[] tasks = new Chatbot.Task[100];
        private int taskCount = 0;

        public TaskList() {
        }

        public void addTask(Chatbot.Task task) {
            this.tasks[this.taskCount] = task;
            ++this.taskCount;
        }

        public void deleteTask(int task) {
            Chatbot.Task[] newTasks = new Chatbot.Task[100];

            for(int i = 0; i < this.taskCount - 1; ++i) {
                int j = i;
                if (i >= task) {
                    j = i + 1;
                }

                newTasks[i] = this.tasks[j];
            }

            this.tasks = newTasks;
            --this.taskCount;
        }

        public void markTask(int task) {
            this.tasks[task].markAsDone();
        }

        public int getCount() {
            return this.taskCount;
        }

        public Chatbot.Task getTask(int i) {
            return this.tasks[i];
        }
    }

    public static class Parser {
        public Parser() {
        }

        public static void parse(String userMessage, Chatbot.TaskList taskList) {
            String datePattern = "dd-MM-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            if (userMessage.equalsIgnoreCase("bye")) {
                Chatbot.Ui.endMessage();
            }

            if (userMessage.equalsIgnoreCase("list")) {
                Chatbot.Ui.listTasks(taskList);
            } else {
                try {
                    int index;
                    if (userMessage.substring(0, 6).equalsIgnoreCase("delete")) {
                        index = Integer.parseInt(userMessage.substring(7)) - 1;
                        Chatbot.Ui.deleteMessage(index, taskList);
                    } else if (userMessage.substring(0, 4).equalsIgnoreCase("mark")) {
                        index = Integer.parseInt(userMessage.substring(5)) - 1;
                        Chatbot.Ui.markMessage(index, taskList);
                    } else if (userMessage.substring(0, 4).equalsIgnoreCase("todo")) {
                        try {
                            Chatbot.Todo todo = new Chatbot.Todo(userMessage.substring(5));
                            Chatbot.Ui.todoMessage(todo, taskList);
                        } catch (Exception var14) {
                            Chatbot.Ui.formatErrorMessage("todo");
                        }
                    } else if (userMessage.substring(0, 8).equalsIgnoreCase("deadline")) {
                        try {
                            index = userMessage.indexOf("/by");
                            String description = userMessage.substring(9, index - 1);
                            LocalDateTime taskDeadline = LocalDateTime.parse(userMessage.substring(index + 4), formatter);
                            Chatbot.Deadline deadline = new Chatbot.Deadline(description, taskDeadline);
                            Chatbot.Ui.deadlineMessage(deadline, taskList);
                        } catch (Exception var13) {
                            Chatbot.Ui.formatErrorMessage("deadline");
                        }
                    } else if (userMessage.substring(0, 5).equalsIgnoreCase("event")) {
                        try {
                            index = userMessage.indexOf("/from");
                            int index2 = userMessage.indexOf("/to");
                            String description = userMessage.substring(6, index - 1);
                            String start = userMessage.substring(index + 6, index2 - 1);
                            String end = userMessage.substring(index2 + 4);
                            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                            Chatbot.Event event = new Chatbot.Event(description, startTime, endTime);
                            Chatbot.Ui.eventMessage(event, taskList);
                        } catch (Exception var12) {
                            Chatbot.Ui.formatErrorMessage("event");
                        }
                    } else {
                        Chatbot.Ui.inputErrorMessage();
                    }
                } catch (Exception var15) {
                    Chatbot.Ui.inputErrorMessage();
                }

            }
        }
    }

    public static class Ui {
        public Ui() {
        }

        public static void startMessage() {
            System.out.println("Hello! I'm Chatbot!");
            System.out.println("What can I do for you?");
        }

        public static void endMessage() {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }

        public static void inputErrorMessage() {
            System.out.println("I'm sorry, but I don't know what that means!");
        }

        public static void formatErrorMessage(String type) {
            byte var2 = -1;
            switch(type.hashCode()) {
                case -1335458389:
                    if (type.equals("delete")) {
                        var2 = 2;
                    }
                    break;
                case 3344077:
                    if (type.equals("mark")) {
                        var2 = 1;
                    }
                    break;
                case 3565638:
                    if (type.equals("todo")) {
                        var2 = 0;
                    }
                    break;
                case 96891546:
                    if (type.equals("event")) {
                        var2 = 4;
                    }
                    break;
                case 503634520:
                    if (type.equals("deadline")) {
                        var2 = 3;
                    }
            }

            switch(var2) {
                case 0:
                    System.out.println("Please use the format \"todo <task description>\"!");
                    break;
                case 1:
                    System.out.println("Please use the format \"mark <task number>\"!");
                    break;
                case 2:
                    System.out.println("Please use the format \"delete <task number>\"!");
                    break;
                case 3:
                    System.out.println("Please use the format \"deadline <task description> /by DD-MM-YYYY HH:MM\"!");
                    break;
                case 4:
                    System.out.println("Please use the format \"event <task description> /from DD-MM-YYYY HH:MM /to DD-MM-YYYY HH:MM\"!");
            }

        }

        public static void listTasks(Chatbot.TaskList taskList) {
            System.out.println("Here are the tasks in your list:");

            for(int i = 0; i < taskList.getCount(); ++i) {
                int listNumber = i + 1;
                System.out.print(listNumber + ". ");
                if (taskList.getTask(i) instanceof Chatbot.Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(i) instanceof Chatbot.Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(i) instanceof Chatbot.Event) {
                    System.out.print("[E]");
                }

                if (taskList.getTask(i).isDone()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }

                System.out.println(taskList.getTask(i).getDescription());
            }

        }

        public static void deleteMessage(int removeTask, Chatbot.TaskList taskList) {
            try {
                System.out.println("This task will be removed!");
                if (taskList.getTask(removeTask) instanceof Chatbot.Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(removeTask) instanceof Chatbot.Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(removeTask) instanceof Chatbot.Event) {
                    System.out.print("[E]");
                }

                if (taskList.getTask(removeTask).isDone()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }

                System.out.println(taskList.getTask(removeTask).getDescription());
                taskList.deleteTask(removeTask);
            } catch (Exception var3) {
                formatErrorMessage("delete");
            }

        }

        public static void markMessage(int doneTask, Chatbot.TaskList taskList) {
            try {
                System.out.println("Well done! This task has been marked as done.");
                if (taskList.getTask(doneTask) instanceof Chatbot.Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(doneTask) instanceof Chatbot.Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(doneTask) instanceof Chatbot.Event) {
                    System.out.print("[E]");
                }

                System.out.print("[X] ");
                System.out.println(taskList.getTask(doneTask).getDescription());
                taskList.getTask(doneTask).markAsDone();
            } catch (Exception var3) {
                formatErrorMessage("mark");
            }

        }

        public static void todoMessage(Chatbot.Todo todo, Chatbot.TaskList taskList) {
            taskList.addTask(todo);
            System.out.print("Added this task: [T] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }

        public static void deadlineMessage(Chatbot.Deadline deadline, Chatbot.TaskList taskList) {
            taskList.addTask(deadline);
            System.out.print("Added this task: [D] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }

        public static void eventMessage(Chatbot.Event event, Chatbot.TaskList taskList) {
            taskList.addTask(event);
            System.out.print("Added this task: [E] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }
    }

    public static class Event extends Chatbot.Task {
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        String datePattern = "MMM-dd-yyyy HH:mm";
        DateTimeFormatter formatter;

        public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
            super(description);
            this.formatter = DateTimeFormatter.ofPattern(this.datePattern);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getDescription() {
            String datePattern = "MMM-dd-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            String var10000 = super.description;
            return var10000 + " (from: " + this.startTime.format(formatter) + " to: " + this.endTime.format(formatter) + ")";
        }

        public String getStartTime() {
            return this.startTime.format(this.formatter);
        }

        public String getEndTime() {
            return this.endTime.format(this.formatter);
        }

        public String getDescriptionWithoutTime() {
            return super.description;
        }
    }

    public static class Deadline extends Chatbot.Task {
        private LocalDateTime deadline;
        String datePattern = "MMM-dd-yyyy HH:mm";
        DateTimeFormatter formatter;

        public Deadline(String description, LocalDateTime deadline) {
            super(description);
            this.formatter = DateTimeFormatter.ofPattern(this.datePattern);
            this.deadline = deadline;
        }

        public String getDescription() {
            String var10000 = super.description;
            return var10000 + " (by: " + this.deadline.format(this.formatter) + ")";
        }

        public String getDeadline() {
            return this.deadline.format(this.formatter);
        }

        public String getDescriptionWithoutTime() {
            return super.description;
        }
    }

    public static class Todo extends Chatbot.Task {
        public Todo(String description) {
            super(description);
        }
    }

    public static class Task {
        private String description;
        private boolean done;

        public Task(String description) {
            this.description = description;
            this.done = false;
        }

        public void markAsDone() {
            this.done = true;
        }

        public void markAsNotDone() {
            this.done = false;
        }

        public String getDescription() {
            return this.description;
        }

        public boolean isDone() {
            return this.done;
        }
    }
}
