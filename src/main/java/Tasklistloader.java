
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class Tasklistloader {

  private ArrayList<Task> taskList;
  private ObjectMapper taskMapper;

  public Tasklistloader(ArrayList<Task> tasks) {
    this.taskList = tasks;
    this.taskMapper = new ObjectMapper();
    PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
//        .allowIfSubType("com.baeldung.jackson.inheritance")
        .allowIfSubType("java.util.ArrayList")
        .allowIfSubType("ReceivedTasks")
        .allowIfSubType("Task")
        .build();
    this.taskMapper.activateDefaultTyping(ptv, DefaultTyping.NON_FINAL);
  }

  public void loadTasks() {

    try {
      Path filePath = Paths.get("tasks.json");
      String content = new String(Files.readAllBytes(filePath));
      ReceivedTasks kek = this.taskMapper.readValue(content, ReceivedTasks.class);
      ArrayList<Task> wow = kek.tasks;

      for (int i = 0; i < wow.size(); i++) {
        Task curr = kek.tasks.get(i);
        System.out.println(curr.getDescription());
      }

//      System.out.println(content);

    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }


  }


  // given the original tasklist, save the tasks into a json file
  public void saveTasks() {

    try {

      Path filePath = Paths.get("tasks.json");
      ReceivedTasks test = new ReceivedTasks();
      test.tasks = this.taskList;
//      List<Task> test = this.taskList;
//
//      String output =
//          this.taskMapper.writeValueAsString(test);
//      String output =
//          this.taskMapper.writeValueAsString(this.taskList);

      String output =
          this.taskMapper.writeValueAsString(test);
      System.out.println(output);
      Files.write(filePath, output.getBytes());

    } catch (JsonProcessingException ex) {
      System.out.println("something went wrong");
    } catch (IOException ex) {
      System.out.println("Unable to save to file!");
    }

  }
}
