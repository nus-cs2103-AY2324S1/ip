public class RepeatedFieldException extends DukeException {
  public RepeatedFieldException(String field) {
    super(String.format("☹ OOPS!!! %s field is repeated.", field));
  }
}