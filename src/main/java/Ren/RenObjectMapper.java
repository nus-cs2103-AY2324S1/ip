package ren;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import ren.task.Deadline;
import ren.task.Event;
import ren.task.TaskList;
import ren.task.ToDo;

import java.io.*;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class RenObjectMapper {
    static ObjectMapper objectMapper = new ObjectMapper();
    private static String cacheFileAddress = "./src/main/resources/cacheTaskList.txt";

    public RenObjectMapper() {
        objectMapper.registerSubtypes(new NamedType(ToDo.class, "ren.task.ToDo"));
        objectMapper.registerSubtypes(new NamedType(Deadline.class, "ren.task.Deadline"));
        objectMapper.registerSubtypes(new NamedType(Event.class, "ren.task.Event"));
    }

    public static void storeIntoHarddisk(TaskList taskList) {
        try {
            String home = System.getProperty("user.home");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "resources", "cacheTaskList.txt");
            String json = objectMapper.writeValueAsString(taskList);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.createFile(path);
            }
            FileWriter myWriter = new FileWriter(path.toString());
            myWriter.write(json);
            myWriter.close();
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
            String home = System.getProperty("user.home");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "resources", "cacheTaskList.txt");
            boolean directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.createFile(path);
            }

            Path cachePath = Path.of(cacheFileAddress);
            File myObj = cachePath.toFile();
            Scanner sc = new Scanner(myObj);
            String json = sc.nextLine();
            taskList = objectMapper.readValue(json, TaskList.class);
            System.out.println("Successfully read from the file.");
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            try (InputStream inputStream = RenObjectMapper.class.getResourceAsStream("/cacheTaskList.txt");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String contents = reader.lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                taskList = objectMapper.readValue(contents, TaskList.class);
                System.out.println("Successfully read from the file.");
                return taskList;
            } catch (IOException ioException) {
                System.out.println("Can't read from cache file");
                ioException.printStackTrace();
            }
        } catch (JsonMappingException e) {
            System.out.println("Can't Map");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("Can't Process");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("No Cache");
            taskList = new TaskList();
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }

        return taskList;
    }
}
