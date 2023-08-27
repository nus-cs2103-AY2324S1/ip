/**
 * Class to handle checked exceptions due to incorrect user input
 */
public class OscarException extends Exception{
  public OscarException(String errorMessage) {
    super(errorMessage);
  }
}
