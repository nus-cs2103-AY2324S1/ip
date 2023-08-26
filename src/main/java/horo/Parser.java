package horo;

import horo.data.Deadline;
import horo.data.Event;
import horo.data.Task;
import horo.data.Todo;

public class Parser {
  public Parser() {
  }

  /**
   * Returns a task by parsing string from data file
   * 
   * @param s Input string from data file
   * @return Task
   * @throws HoroException
   */
  public static Task parseDataString(String s) throws HoroException {
    String[] arguments = s.split(",");
    Task t;
    switch (arguments[0]) {
      case "T":
        t = new Todo(arguments[2]);
        break;
      case "D":
        t = new Deadline(arguments[2], arguments[3]);
        break;
      case "E":
        t = new Event(arguments[2], arguments[3], arguments[4]);
        break;
      default:
        throw new HoroException("Bad Command");
    }
    if (arguments[1].equals("1")) {
      t.markDone();
    }
    return t;
  }
}
