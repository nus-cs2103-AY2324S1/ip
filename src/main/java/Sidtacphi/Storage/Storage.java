package Sidtacphi.Storage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import Sidtacphi.Task.Task;
import Sidtacphi.Task.TaskList;

public class Storage {
    private Storage() {
    }

    /**
     * Saves current taskList as a json file.
     */
    public static void saveAsJson(TaskList taskList, String location) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = 
           new SimpleModule("TaskSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Task.class, new TaskSerializer());
        mapper.registerModule(module);
        try {
            mapper.writeValue(new File(location), taskList);
        } catch (IOException e) {
            new File(location);
        }
    }

    /**
     * Reads json file and save to taskList.
     */
    public static TaskList readJson(String location) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
            new SimpleModule("TaskDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Task.class, new TaskDeserializer());
        mapper.registerModule(module);

        TaskList resultList = new TaskList();

        try {
            resultList.addAll(mapper.readValue(new File(location), new TypeReference<ArrayList<Task>>(){}));
        } catch (IOException e) {
            // If no such file exists
            new File(location);
        }

        return resultList;
    }
}
