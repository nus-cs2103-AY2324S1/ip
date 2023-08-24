/**
 * Class to handle checked exceptions due to incorrect user input
 */
public class DukeException extends Exception{
  public DukeException(String errorMessage) {
    super(errorMessage);
  }
}
