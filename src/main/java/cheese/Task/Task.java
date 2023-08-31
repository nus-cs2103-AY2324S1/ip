package cheese.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {
    private char type;
    private String description;
    private String dateTime1;
    private String dateTime2;
    private LocalDate dateTimeHr1;
    private boolean isDone;

    // Todo constructor
    public Task(char type, String description) {
      this.type = type;
      this.description = description;
      this.isDone = false;
    }

    // Deadline constructor
    public Task(char type, String description, LocalDate dateTime1) {
      this.type = type;
      this.description = description;
      this.dateTimeHr1 = dateTime1;
      this.isDone = false;
    }

    public Task(char type, String description, String dateTime1) {
      this.type = type;
      this.description = description;
      this.dateTime1 = dateTime1;
      this.isDone = false;
    }

    // Event constructor
    public Task(char type, String description, String dateTime1, String dateTime2) {
      this.type = type;
      this.description = description;
      this.dateTime1 = dateTime1;
      this.dateTime2 = dateTime2;
      this.isDone = false;
    }

    /**
         * Mark task as done
         */
    public void markAsDone() {
      this.isDone = true;
    }

    /**
         * Returns string representation of task
         * @return String representation of task
         */
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[").append(type).append("][").append(isDone ? "X" : " ").append("]|").append(description);
      
      if (type == 'T') {
        return sb.toString();
      } else if (type == 'D') {
        if (dateTimeHr1 != null) {
          String formattedDate = dateTimeHr1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
          sb.append(" (by: ").append(formattedDate).append(")");
        } else if (dateTime1 != null) {
          sb.append(" (by: ").append(dateTime1).append(")");
        }
      } else if (type == 'E') {
        if (dateTime1 != null) {
          sb.append(" (from: ").append(dateTime1);
          if (dateTime2 != null) {
            sb.append(" to: ").append(dateTime2);
          }
          sb.append(")");
        }
    }
    /*
    if (dateTimeHr1 != null) {
        String formattedDate = dateTimeHr1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        sb.append(" (by: ").append(formattedDate).append(")");
      } else if (dateTime2 == null) {
        sb.append(" (by: ").append(dateTime1).append(")");
      } else if (dateTime1 != null) {
        sb.append(" (from: ").append(dateTime1);
        if (dateTime2 != null) {
          sb.append(" to: ").append(dateTime2);
        }
        sb.append(")");
      }
    */
      return sb.toString();
    }
  }

