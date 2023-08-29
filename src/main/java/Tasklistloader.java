
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Tasklistloader {

  private ArrayList<Task> taskList;
  private ObjectMapper taskMapper;

  public Tasklistloader(ArrayList<Task> tasks) {
    this.taskList = tasks;
    this.taskMapper = new ObjectMapper();
    PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
        .allowIfSubType("java.util.ArrayList")
        .allowIfSubType("ReceivedTasks")
        .allowIfSubType("Task")
        .build();
    this.taskMapper.activateDefaultTyping(ptv, DefaultTyping.NON_FINAL);
    this.taskMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
  }

  public void loadTasks() {

    try {
      Path filePath = Paths.get("tasks.json");
      String content = new String(Files.readAllBytes(filePath));
      ReceivedTasks received = this.taskMapper.readValue(content, ReceivedTasks.class);
      ArrayList<Task> tasks = received.tasks;

      this.taskList.addAll(tasks);
    } catch (IOException ex) {
      System.out.println(ex.getMessage()+ " not found! No list loaded!");
    }

  }


  // given the original tasklist, save the tasks into a json file
  public void saveTasks() {

    try {

      Path filePath = Paths.get("tasks.json");
      ReceivedTasks test = new ReceivedTasks();
      test.tasks = this.taskList;

      String output =
          this.taskMapper.writeValueAsString(test);
      Files.write(filePath, output.getBytes());

    } catch (JsonProcessingException ex) {
      System.out.println("something went wrong");
    } catch (IOException ex) {
      System.out.println("Unable to save to file!");
    }

  }
}
