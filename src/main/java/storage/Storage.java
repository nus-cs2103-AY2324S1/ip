package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import tasks.Task;
import tasks.TaskList;

public class Storage {

  private TaskList taskList;
  private ObjectMapper taskMapper;

  public Storage(TaskList tasks) {
    this.taskList = tasks;
    this.taskMapper = new ObjectMapper();
    this.taskMapper.registerModule(new JavaTimeModule());
    PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
        .allowIfSubType("java.util.ArrayList")
        .allowIfSubType("ReceivedTasks")
        .allowIfSubType("tasks.Task")
        .build();
    this.taskMapper.activateDefaultTyping(ptv, DefaultTyping.NON_FINAL);
    this.taskMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
  }

  public void loadTasks() {

    try {
      Path filePath = Paths.get("tasks.json");
      String content = new String(Files.readAllBytes(filePath));
      TaskList plist = this.taskMapper.readValue(content, TaskList.class);
      for (int i = 0; i < plist.size(); i++) {
        Task wow = plist.get(i);
        taskList.add(wow);
      }

    } catch (IOException ex) {
      System.out.println(ex.getMessage() + " not found! No list loaded!");
    }

  }


  // given the original tasklist, save the tasks into a json file
  public void saveTasks() {

    try {

      Path filePath = Paths.get("tasks.json");
      String output =
          this.taskMapper.writeValueAsString(taskList);
      Files.write(filePath, output.getBytes());

    } catch (JsonProcessingException ex) {
      System.out.println("something went wrong");
    } catch (IOException ex) {
      System.out.println("Unable to save to file!");
    }

  }
}
