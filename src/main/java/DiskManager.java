import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

public class DiskManager {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerSubtypes(new NamedType(Todo.class, "Todo"));
        MAPPER.registerSubtypes(new NamedType(Deadline.class, "Deadline"));
        MAPPER.registerSubtypes(new NamedType(Event.class, "Event"));
        MAPPER.registerModule(new JavaTimeModule());
    }

    private static File getFile() {
        String directoryPath = "data";
        String fileName = "tasks.json";
        String filePath = directoryPath + "/" + fileName;
        try {
            String currentWorkingDir = System.getProperty("user.dir");

            // create a file object for the directory
            File directory = new File(currentWorkingDir, directoryPath);

            // If the directory does not exist, create it
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a File object for the file within the directory
            File file = new File(currentWorkingDir, filePath);

            // If the file doesn't exist, create a new one
            if (!file.exists()) {
                file.createNewFile();
            }

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveToDisk(TaskManager taskManager) {
        try {
            String json = MAPPER.writeValueAsString(taskManager);
            File file = getFile();
            if (file != null) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(json);
                fileWriter.close();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TaskManager loadFromDisk() {
        try {
            File file = getFile();
            if (file == null) {
                return new TaskManager();
            }

            // read the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            String json = jsonData.toString();

            TaskManager taskManager = MAPPER.readValue(json, TaskManager.class);
            return taskManager;
        } catch (JsonProcessingException e) {
            // e.printStackTrace();
            return new TaskManager();
        } catch (IOException e) {
            // e.printStackTrace();
            return new TaskManager();
        }
    }
}
