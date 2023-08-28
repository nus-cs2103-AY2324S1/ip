package Ren;

import Ren.task.Deadline;
import Ren.task.Event;
import Ren.task.TaskList;
import Ren.task.ToDo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class RenObjectMapper {
    static ObjectMapper objectMapper = new ObjectMapper();
    private static String cacheFileAddress = ".\\data\\cacheTaskList.txt";

    public RenObjectMapper() {
        objectMapper.registerSubtypes(new NamedType(ToDo.class, "Ren.task.ToDo"));
        objectMapper.registerSubtypes(new NamedType(Deadline.class, "Ren.task.Deadline"));
        objectMapper.registerSubtypes(new NamedType(Event.class, "Ren.task.Event"));
    }

    public static void storeIntoHarddisk(TaskList taskList) {
        try {
            String json = objectMapper.writeValueAsString(taskList);
            FileWriter myWriter = new FileWriter(cacheFileAddress);
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (JsonProcessingException e) {
            System.out.println("An JSON error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }

    public static TaskList retrieveFromHarddisk() {
        TaskList taskList = null;
        try {
            Scanner myReader = new Scanner(new File(cacheFileAddress));
            String json = myReader.nextLine();
            taskList = objectMapper.readValue(json, TaskList.class);
            System.out.println("Successfully read from the file.");
            myReader.close();
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Cache file not found");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Can't Map");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("Can't Process");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("No Cache");
            taskList = new TaskList();
        }

        return taskList;
    }
}
