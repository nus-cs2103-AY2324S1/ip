public class StringArgument extends Argument {

  public StringArgument(String text) {
    super(text);
  }

  @Override
  public Object formatInput(String input) throws DukeException {
    if (input == null || input.equals("")) {
      throw new EmptyArgException(this.toString());
    }
    return input;
  }

  @Override
  public String formatOutput(Object val) {
    return val.toString();
  }
  
}