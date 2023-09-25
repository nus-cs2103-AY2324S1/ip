package sidtacphi.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import sidtacphi.task.Task;
import sidtacphi.task.TaskList;
import sidtacphi.contact.Contact;
import sidtacphi.contact.ContactList;

/**
 * Storage is the class that loads and saves tasks into a file.
 */
public class Storage {
    private Storage() {
    }

    /**
     * Saves taskList parameter as a json file.
     *
     * @param taskList
     * @param location Location of the file to be saved
     */
    public static void saveAsJson(TaskList taskList, String location) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
            new SimpleModule("TaskSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Task.class, new TaskSerializer());
        mapper.registerModule(module);
        try {
            String makeDirPath = location.substring(0, location.lastIndexOf("/"));
            Path path = Paths.get(makeDirPath);
            Files.createDirectories(path);
            mapper.writeValue(new File(location), taskList);
        } catch (IOException e) {
            new File(location);
        }
    }

    /**
     * Saves contactList parameter as a json file.
     *
     * @param taskList
     * @param location Location of the file to be saved
     */
    public static void saveAsJson(ContactList contactList, String location) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
            new SimpleModule("ContactSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Contact.class, new ContactSerializer());
        mapper.registerModule(module);
        try {
            String makeDirPath = location.substring(0, location.lastIndexOf("/"));
            Path path = Paths.get(makeDirPath);
            Files.createDirectories(path);
            mapper.writeValue(new File(location), contactList);
        } catch (IOException e) {
            new File(location);
        }
    }

    /**
     * Reads json file storing a TaskList object.
     *
     * @param location Location of the file to be read
     * @return TaskList object represented by file read
     */
    public static TaskList readTaskListJson(String location) {
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

    /**
     * Reads json file storing a ContactList object.
     *
     * @param location Location of the file to be read
     * @return ContactList object represented by file read
     */
    public static ContactList readContactListJson(String location) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
            new SimpleModule("ContactDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Contact.class, new ContactDeserializer());
        mapper.registerModule(module);

        ContactList resultList = new ContactList();

        try {
            resultList.addAll(mapper.readValue(new File(location), new TypeReference<ArrayList<Contact>>(){}));
        } catch (IOException e) {
            // If no such file exists
            new File(location);
        }

        return resultList;
    }
}
