import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TaskManager {
    private List<Task> tasks;
    private final String FILEPATH;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
        FILEPATH = "./data/chatbot.txt";

        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error initializing file: " + e.getMessage());
            }
        }

    }
    public void addTodo(String t) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todos(t);
        tasks.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
    public void addDeadlines(String t, String date) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a deadlines cannot be empty.");
        }
        else if (date == null || date.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The date of a deadlines cannot be empty.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            Task task = new Deadlines(t, dateTime);
            tasks.add(task);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + task.toString());
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.");
        }
    }

    public void addEvents(String t, String start, String end) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        else if (start == null || start.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The starting date of a event cannot be empty.");
        } else if (end == null || end.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The end time of a event cannot be empty.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);

            Events newEvent = new Events(t, startDateTime, endDateTime);
            tasks.add(newEvent);

            System.out.println("    Got it. I've added this event:");
            System.out.println("      " + newEvent);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.");
        }
    }





    public Task getTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            return tasks.get(index - 1);
        }
        return null;
    }

    public void taskDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
    }

    public void unMarktask(int index){
        Task task = getTask(index);
        if (task != null) {
            task.unMark();
        }
    }
    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTask(int index) throws ChatbotException {
        try {
            Task removedTask = tasks.remove(index - 1); // Subtracting 1 because ArrayList is 0-based.
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("Please provide a valid task number to delete.");
        }
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.toFileFormat());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    // Handle corrupted line here for stretch goal
                    System.out.println("Warning: Corrupted line skipped in file");
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void printTasksOnDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(date, formatter);
        for (Task task : tasks) {
            if (task instanceof Deadlines) {
                Deadlines deadlineTask = (Deadlines) task;
                if (targetDate.equals(deadlineTask.getDateTime().toLocalDate())) {
                    System.out.println(task);
                }
            }
        }
    }

}
