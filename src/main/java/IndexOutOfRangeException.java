public class EmptyDescriptionException extends AjException{
  EmptyDescriptionException(String cmd, String helpMsg) {
    super("☹ OOPS!!! The description of a " + cmd + " cannot be empty.\n" +
            "Try:\n" +
            helpMsg);
  }
}
