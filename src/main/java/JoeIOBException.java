public class JoeIOBException extends JoeException {
  public JoeIOBException(int idx) {
    super("Task " + idx + " does not exist");
  }
}
