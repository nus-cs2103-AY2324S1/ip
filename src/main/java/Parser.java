public class Parser {

  private Parser() {
  }

  public static String getTask(String text) {
    return text.split(" ")[0];
  }

  public static String getToDoName(String text) {
    return text.replaceFirst("todo ", "");
  }

  public static String getDeadlineName(String text) {
    text = text.replaceFirst("deadline ", "");
    int indexOfLastBy = text.lastIndexOf("/by");
    return text.substring(0, indexOfLastBy - 1);
  }

  public static String getDeadlineEnd(String text) {
    text = text.replaceFirst("deadline ", "");
    int indexOfLastBy = text.lastIndexOf("/by");
    return text.substring(indexOfLastBy + 4);
  }

  public static String getEventName(String text) {
    text = text.replaceFirst("event ", "");
    int indexOfLastFrom = text.lastIndexOf("/from");
    return text.substring(0, indexOfLastFrom - 1);
  }

  public static String getEventStart(String text) {
    text = text.replaceFirst("event ", "");
    int indexOfLastFrom = text.lastIndexOf("/from");
    int indexOfLastTo = text.lastIndexOf("/to");
    return text.substring(indexOfLastFrom + 6, indexOfLastTo - 1);
  }

  public static String getEventEnd(String text) {
    text = text.replaceFirst("event ", "");
    int indexOfLastTo = text.lastIndexOf("/to");
    return text.substring(indexOfLastTo + 4);
  }
}
