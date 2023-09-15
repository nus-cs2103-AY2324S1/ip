import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Storage {
    public Storage() {
    }

    /**
     * Saves current taskList as a json file.
     */
    public static void saveAsJson(List<Task> taskList) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = 
           new SimpleModule("TaskSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Task.class, new TaskSerializer());
        mapper.registerModule(module);
        try {
            System.out.println("saving json...");
            mapper.writeValue(new File("tasks.json"), taskList);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    /**
     * Reads json file and save to taskList.
     */
    public static List<Task> readJson(List<Task> taskList) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
            new SimpleModule("TaskDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Task.class, new TaskDeserializer());
        mapper.registerModule(module);

        List<Task> resultList = new ArrayList<>();

        try {
            resultList = mapper.readValue(new File("tasks.json"), new TypeReference<ArrayList<Task>>(){});
        } catch (IOException e) {
            // If no such file exists
            new File("tasks.json");
        }

        return resultList;
    }
}
