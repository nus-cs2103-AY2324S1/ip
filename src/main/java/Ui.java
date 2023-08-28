import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ui {

  Scanner sc;

  public Ui(Scanner sc) {
    this.sc = sc;
  }

  public abstract void print(String msg);

  public abstract void greet();

  public static String getTaskCount(int n) {
    return String.format("You have %d task%s in the list now.", n, n == 1 ? "" : "s");
  }

  public static <T> String stringifyList(List<T> arr) {
    List<String> enumArr = new ArrayList<>();
    int i = 1;
    for (T e : arr) {
      enumArr.add(String.format("%d. %s", i++, e.toString()));
    }
    return String.join("\n", enumArr);
  }

  public static String stringifyDate(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
  }

  public String readCommand() {
    return sc.nextLine().replaceAll("\n", "").trim();
  }

  public boolean hasNext() {
    return sc.hasNext();
  }

  public void close() {
    sc.close();
  }

}
