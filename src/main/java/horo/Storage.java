package horo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import horo.data.expenses.Expense;
import horo.data.expenses.ExpenseList;
import horo.data.tasks.Task;
import horo.data.tasks.TaskList;

public class Storage {

  private static final String DEFAULT_TASK_STORAGE_FILEPATH = "./data/tasks.txt";
  private static final String DEFAULT_EXPENSE_STORAGE_FILEPATH = "./data/expenses.txt";

  private TaskList taskList;
  private ExpenseList expenseList;

  public Storage() {
  }

  /**
   * Load data files
   */
  public void load() {
    taskList = new TaskList();
    expenseList = new ExpenseList();
    try {
      File taskFile = new File(DEFAULT_TASK_STORAGE_FILEPATH);
      File expenseFile = new File(DEFAULT_EXPENSE_STORAGE_FILEPATH);

      if (!taskFile.exists()) {
        taskFile.mkdirs();
      }

      if (taskFile.createNewFile()) {
        System.out.println("File created: " + taskFile.getName());
      }

      if (expenseFile.createNewFile()) {
        System.out.println("File created: " + expenseFile.getName());
      }

      Scanner scanner = new Scanner(taskFile);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        taskList.add(Parser.parseTaskDataString(data));
      }

      scanner.close();

      scanner = new Scanner(expenseFile);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        expenseList.add((Expense) Parser.parseExpenseDataString(data));
      }

      scanner.close();
    } catch (IOException e) {
      System.out.println("Error writing to file");
      e.printStackTrace(System.out);
    } catch (HoroException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Update task data file with given taskList
   * 
   * @param taskList TaskList to be stored in the data file
   */
  public void updateTaskData(TaskList taskList) {
    this.taskList = taskList;
    try {
      FileWriter writer = new FileWriter(DEFAULT_TASK_STORAGE_FILEPATH, false);
      for (Task t : taskList.getTasks()) {
        writer.write(t.getDataString() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace(System.out);
    }
  }

  /**
   * Update expense data file with given expenseList
   * 
   * @param expenseList ExpenseList to be stored in the data file
   */
  public void updateExpenseData(ExpenseList expenseList) {
    this.expenseList = expenseList;
    try {
      FileWriter writer = new FileWriter(DEFAULT_EXPENSE_STORAGE_FILEPATH, false);
      for (Expense e : expenseList.getExpenses()) {
        writer.write(e.getDataString() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace(System.out);
    }
  }

  public TaskList getTaskList() {
    return taskList;
  }

  public ExpenseList getExpenseList() {
    return expenseList;
  }
}
