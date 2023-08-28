
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

    Map<Integer, Task> tasks = new HashMap<>();
    for (int i = 0; i < this.taskList.size(); i++) {
      Integer index = i + 1;
      Task currTask = taskList.get(i);
      tasks.put(index, currTask);
      try {
        taskMapper.writeValue(new File("tasks.json"), tasks);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
