import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    static final String PATHNAME = "data/linus.txt";

    List<Task> tasks = null;
    Gson gson = null;
    File file = null;

    public Storage() {
        Path filePath = Paths.get(PATHNAME);
        try {
            String json = Files.readString(filePath);

            // Deserialize the Json String into an ArrayList of Tasks
            TaskDeserializer deserializer = new TaskDeserializer("type");
            deserializer.registerTaskType("ToDo", ToDo.class);
            deserializer.registerTaskType("Deadline", Deadline.class);
            deserializer.registerTaskType("Event", Event.class);

            gson = new GsonBuilder()
                    .registerTypeAdapter(Task.class, deserializer)
                    .create();
            tasks = gson.fromJson(json, new TypeToken<ArrayList<Task>>(){}.getType());

            if(tasks == null) {
                tasks = new ArrayList<>();
            }
        } catch (IOException e) {
            MessagePrinter.print("The file system experienced an unexpected error.");
        }
    }

    public void store() {
        String json = gson.toJson(tasks);
        try {
            FileWriter fileWriter = new FileWriter(PATHNAME);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            MessagePrinter.print("The file system experienced an unexpected error.");
        }
    }

    public void list() {
        String listOfItems = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            listOfItems += (i + 1) + "."
                    + tasks.get(i).toString() + "\n";
        }
        MessagePrinter.print(listOfItems);
    }

    public void add(Task task) {
        tasks.add(task);
        store();
        int numOfTasks = tasks.size();
        MessagePrinter.print("Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void delete(int index) throws LinusException{
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot delete task. Please provide a valid index.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        store();
        int numOfTasks = tasks.size();
        MessagePrinter.print("Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void mark(int index) throws LinusException{
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot mark task. Please provide a valid index.");
        }
        tasks.get(index).mark();
        store();

    }
    public void unmark(int index) throws LinusException{
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot unmark task. Please provide a valid index.");
        }
        tasks.get(index).unmark();
        store();
    }
}
