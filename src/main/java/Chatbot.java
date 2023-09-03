import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Chatbot {

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
            return description;
        }

        public boolean isDone() {
            return done;
        }
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }
    }

    public static class Deadline extends Task {
        private LocalDateTime deadline;

        String datePattern = "MMM-dd-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        public Deadline(String description, LocalDateTime deadline) {
            super(description);
            this.deadline = deadline;
        }

        public String getDescription() {
            return super.description + " (by: " + deadline.format(formatter) + ")";
        }

        public String getDeadline() {
            return deadline.format(formatter);
        }

        public String getDescriptionWithoutTime() {
            return super.description;
        }
    }

    public static class Event extends Task {
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        String datePattern = "MMM-dd-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
            super(description);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getDescription() {
            String datePattern = "MMM-dd-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return super.description + " (from: " + startTime.format(formatter) + " to: "
                    + endTime.format(formatter) + ")";
        }

        public String getStartTime() {
            return startTime.format(formatter);
        }

        public String getEndTime() {
            return endTime.format(formatter);
        }

        public String getDescriptionWithoutTime() {
            return super.description;
        }
    }

    public static class Ui {
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
            switch(type) {
                case "todo":
                    System.out.println("Please use the format \"todo <task description>\"!");
                    break;
                case "mark":
                    System.out.println("Please use the format \"mark <task number>\"!");
                    break;
                case "delete":
                    System.out.println("Please use the format \"delete <task number>\"!");
                    break;
                case "deadline":
                    System.out.println("Please use the format \"deadline <task description> /by DD-MM-YYYY HH:MM\"!");
                    break;
                case "event":
                    System.out.println("Please use the format \"event <task description> /from DD-MM-YYYY HH:MM " + "/to DD-MM-YYYY HH:MM\"!");
                    break;
            }
        }

        public static void listTasks(TaskList taskList) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getCount(); i++) {
                int listNumber = i + 1;
                System.out.print(listNumber + ". ");
                if (taskList.getTask(i) instanceof Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(i) instanceof Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(i) instanceof Event) {
                    System.out.print("[E]");
                }
                if (taskList.getTask(i).isDone()) System.out.print("[X] ");
                else System.out.print("[ ] ");
                System.out.println(taskList.getTask(i).getDescription());
            }
        }

        public static void deleteMessage(int removeTask, TaskList taskList) {
            try {
                System.out.println("This task will be removed!");
                if (taskList.getTask(removeTask) instanceof Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(removeTask) instanceof Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(removeTask) instanceof Event) {
                    System.out.print("[E]");
                }
                if (taskList.getTask(removeTask).isDone()) System.out.print("[X] ");
                else System.out.print("[ ] ");
                System.out.println(taskList.getTask(removeTask).getDescription());
                taskList.deleteTask(removeTask);
            } catch (Exception e) {
                Ui.formatErrorMessage("delete");
            }
        }

        public static void markMessage(int doneTask, TaskList taskList) {
            try {
                System.out.println("Well done! This task has been marked as done.");
                if (taskList.getTask(doneTask) instanceof Todo) {
                    System.out.print("[T]");
                } else if (taskList.getTask(doneTask) instanceof Deadline) {
                    System.out.print("[D]");
                } else if (taskList.getTask(doneTask) instanceof Event) {
                    System.out.print("[E]");
                }
                System.out.print("[X] ");
                System.out.println(taskList.getTask(doneTask).getDescription());
                taskList.getTask(doneTask).markAsDone();
            } catch (Exception e) {
                Ui.formatErrorMessage("mark");
            }
        }

        public static void todoMessage(Todo todo, TaskList taskList) {
            taskList.addTask(todo);
            System.out.print("Added this task: [T] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }

        public static void deadlineMessage(Deadline deadline, TaskList taskList) {
            taskList.addTask(deadline);
            System.out.print("Added this task: [D] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }

        public static void eventMessage(Event event, TaskList taskList) {
            taskList.addTask(event);
            System.out.print("Added this task: [E] ");
            System.out.println(taskList.getTask(taskList.getCount() - 1).getDescription());
        }
    }

    public static class Parser {
        public static void parse (String userMessage, TaskList taskList) {
            String datePattern = "dd-MM-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            if (userMessage.equalsIgnoreCase("bye")) {
                Ui.endMessage();
            }
            if (userMessage.equalsIgnoreCase("list")) {
                Ui.listTasks(taskList);
                return;
            }
            try {
                if (userMessage.substring(0, 6).equalsIgnoreCase("delete")) {
                    int removeTask = Integer.parseInt(userMessage.substring(7)) - 1;
                    Ui.deleteMessage(removeTask, taskList);
                } else if (userMessage.substring(0, 4).equalsIgnoreCase("mark")) {
                    int doneTask = Integer.parseInt(userMessage.substring(5)) - 1;
                    Ui.markMessage(doneTask, taskList);
                } else if (userMessage.substring(0, 4).equalsIgnoreCase("todo")) {
                    try {
                        Todo todo = new Todo(userMessage.substring(5));
                        Ui.todoMessage(todo, taskList);
                    } catch (Exception e) {
                        Ui.formatErrorMessage("todo");
                    }
                } else if (userMessage.substring(0, 8).equalsIgnoreCase("deadline")) {
                    try {
                        int index = userMessage.indexOf("/by");
                        String description = userMessage.substring(9, index - 1);
                        LocalDateTime taskDeadline = LocalDateTime.parse(userMessage.substring(index + 4), formatter);
                        Deadline deadline = new Deadline(description, taskDeadline);
                        Ui.deadlineMessage(deadline, taskList);
                    } catch (Exception e) {
                        Ui.formatErrorMessage("deadline");
                    }
                } else if (userMessage.substring(0, 5).equalsIgnoreCase("event")) {
                    try {
                        int index = userMessage.indexOf("/from");
                        int index2 = userMessage.indexOf("/to");
                        String description = userMessage.substring(6, index - 1);
                        String start = userMessage.substring(index + 6, index2 - 1);
                        String end = userMessage.substring(index2 + 4);
                        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                        Event event = new Event(description, startTime, endTime);
                        Ui.eventMessage(event, taskList);
                    } catch (Exception e) {
                        Ui.formatErrorMessage("event");
                    }
                } else {
                    Ui.inputErrorMessage();
                }
            } catch (Exception e) {
                Ui.inputErrorMessage();
            }
        }
    }

    public static class TaskList {
        private Task[] tasks;
        private int taskCount;

        public TaskList() {
            tasks = new Task[100];
            taskCount = 0;
        }

        public void addTask(Task task) {
            tasks[taskCount] = task;
            taskCount++;
        }

        public void deleteTask(int task) {
            Task[] newTasks = new Task[100];
            for (int i = 0; i < taskCount - 1; i++) {
                int j = i;
                if (i >= task) j = i + 1;
                newTasks[i] = tasks[j];
            }
            tasks = newTasks;
            taskCount--;
        }

        public void markTask(int task) {
            tasks[task].markAsDone();
        }

        public int getCount() {
            return taskCount;
        }

        public Task getTask(int i) {
            return tasks[i];
        }
    }

    public static class Storage {
        public static void load(TaskList taskList) {
            try {
                String datePattern2 = "MMM-dd-yyyy HH:mm";
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(datePattern2);
                File taskFile = new File("./tasks.txt");
                Scanner taskReader = new Scanner(taskFile);
                while (taskReader.hasNextLine()) {
                    String taskFromFile = taskReader.nextLine();
                    if (taskFromFile.charAt(0) == 'T') {
                        Todo todo = new Todo(taskFromFile.substring(3));
                        if (taskFromFile.charAt(1) == 't') {
                            todo.markAsDone();
                        }
                        taskList.addTask(todo);
                    }
                    if (taskFromFile.charAt(0) == 'D') {
                        String deadlineFromFile = taskReader.nextLine();
                        LocalDateTime taskDeadline = LocalDateTime.parse(deadlineFromFile, formatter2);
                        Deadline deadline = new Deadline(taskFromFile.substring(3), taskDeadline);
                        if (taskFromFile.charAt(1) == 't') {
                            deadline.markAsDone();
                        }
                        taskList.addTask(deadline);
                    }
                    if (taskFromFile.charAt(0) == 'E') {
                        String startFromFile = taskReader.nextLine();
                        LocalDateTime start = LocalDateTime.parse(startFromFile, formatter2);
                        String endFromFile = taskReader.nextLine();
                        LocalDateTime end = LocalDateTime.parse(endFromFile, formatter2);
                        Event event = new Event(taskFromFile.substring(3), start, end);
                        if (taskFromFile.charAt(1) == 't') {
                            event.markAsDone();
                        }
                        taskList.addTask(event);
                    }
                }
            } catch (FileNotFoundException e) {
                File taskFile = new File("./tasks.txt");
                try {
                    taskFile.createNewFile();
                } catch (IOException e2) {
                    System.out.println("Error!");
                }
            }
        }

        public static void saveTasks(TaskList taskList) {
            try {
                FileWriter taskWriter = new FileWriter("./tasks.txt", false);
                for (int taskNumber = 0; taskNumber <= taskList.getCount(); taskNumber++) {
                    if (taskList.getTask(taskNumber) instanceof Todo) {
                        taskWriter.write("T");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        taskWriter.write(taskList.getTask(taskNumber).getDescription() + "\n");
                    } else if (taskList.getTask(taskNumber) instanceof Deadline) {
                        taskWriter.write("D");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        taskWriter.write(((Deadline)taskList.getTask(taskNumber)).getDescriptionWithoutTime() + "\n");
                    } else if (taskList.getTask(taskNumber) instanceof Event) {
                        taskWriter.write("E");
                        taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                        taskWriter.write(((Event)taskList.getTask(taskNumber)).getDescriptionWithoutTime() + "\n");
                    }
                    if (taskList.getTask(taskNumber) instanceof Deadline) {
                        taskWriter.write(((Deadline) taskList.getTask(taskNumber)).getDeadline() + "\n");
                    }
                    if (taskList.getTask(taskNumber) instanceof Event) {
                        taskWriter.write(((Event) taskList.getTask(taskNumber)).getStartTime() + "\n");
                        taskWriter.write(((Event) taskList.getTask(taskNumber)).getEndTime() + "\n");
                    }
                    taskWriter.flush();
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        Storage.load(taskList);
        Ui.startMessage();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String userMessage = userInput.nextLine();
            Parser.parse(userMessage, taskList);
            Storage.saveTasks(taskList);
        }
    }
}
