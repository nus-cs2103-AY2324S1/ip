import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Gson gson = null;
    private File file = null;
    private String filePath = "";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws LinusException{
        try {
            Path path = Paths.get(filePath);
            String json = Files.readString(path);

            // Deserialize the Json String into an ArrayList of Tasks
            TaskDeserializer deserializer = new TaskDeserializer("type");
            deserializer.registerTaskType("ToDo", ToDo.class);
            deserializer.registerTaskType("Deadline", Deadline.class);
            deserializer.registerTaskType("Event", Event.class);

            gson = new GsonBuilder()
                    .registerTypeAdapter(Task.class, deserializer)
                    .create();
            List<Task> tasks = gson.fromJson(json, new TypeToken<ArrayList<Task>>(){}.getType());

            if(tasks == null) {
                return new ArrayList<>();
            }
            return tasks;
        } catch (IOException e) {
            throw new LinusException("The file system experienced an unexpected error.");
        }
    }

    public void store(List<Task> tasks) {
        String json = gson.toJson(tasks);
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            Ui.print("The file system experienced an unexpected error.");
        }
    }
}
