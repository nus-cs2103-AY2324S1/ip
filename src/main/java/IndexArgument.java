public class IndexArgument extends Argument {
  
  public IndexArgument(String text) {
    super(text);
  }

  @Override
  public Object formatInput(String input) throws DukeException {
    int idx;
    try {
      idx = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new InvalidIndexException();
    }
    if (idx <= 0) {
      throw new InvalidIndexException();
    }
    return idx;
  }

  @Override
  public String formatOutput(Object val) {
    return String.valueOf((int) ((Integer) val));
  }

}
