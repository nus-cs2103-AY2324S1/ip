
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Tasklistloader {

  private ArrayList<Task> taskList;
  private ObjectMapper taskMapper;

  public Tasklistloader(ArrayList<Task> tasks) {
    this.taskList = tasks;
    this.taskMapper = new ObjectMapper();
    PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
        .allowIfSubType("com.baeldung.jackson.inheritance")
        .allowIfSubType("java.util.ArrayList")
        .build();
    this.taskMapper.activateDefaultTyping(ptv, DefaultTyping.NON_FINAL);
  }


  // given the original tasklist, save the tasks into a json file
  public void saveTasks() {

    try {

      Path filePath = Paths.get("tasks.json");
      String output =
          this.taskMapper.writeValueAsString(this.taskList);
      Files.write(filePath, output.getBytes());

    } catch (JsonProcessingException ex) {
      System.out.println("something went wrong");
    } catch (IOException ex) {
      System.out.println("Unable to save to file!");
    }

  }
}
