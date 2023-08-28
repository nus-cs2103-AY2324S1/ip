public class Field implements CommandElement {

  private String text;

  public Field(String text) {
    this.text = text;
  }

  @Override
  public String getName() {
    return this.text;
  }

  @Override
  public String getRegexForm() {
    return String.format("( %s )", this.text);
  }

  @Override
  public String toString() {
    return this.text;
  }
  
}
