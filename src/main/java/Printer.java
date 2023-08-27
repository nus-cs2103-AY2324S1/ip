public class Printer {
  public void print(Object... toPrint) {
    printLine();
    for (Object o : toPrint) {
      print(o);
    }
    printLine();
  }

  private void print(Object s) {
    System.out.printf("    %s\n", s);
  }

  private void printLine() {
    String line = "  ____________________________________________________________";
    System.out.println(line);
  }
}
