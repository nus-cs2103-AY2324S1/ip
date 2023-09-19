# Kimochi Usagi きもちうさぎ bot

## Description
The kimochi Usagi bot is a cute little rabbit, who wants to help you to remember tasks

## Types of tasks
- **todo** tasks are tasks with no time limit. They just have to be done.
- **deadline** tasks are tasks with a deadline (date). Ensure that the date is formatted as dd/mm/YYYY
- **event** tasks are tasks with a start and end datetime. Ensure that the date time is in the format of YYYY-MM-DDTHH:MM

## Features

- **bye** Saves tasks to a json file, and exists the chat bot
  ```
  bye
  ```
  **outcome:** After two seconds, the bot would shut-down
- **list** all current tasks
  ```
  list
  ```
  **outcome** A list of all tasks would be displayed. eg:
    ```
    1 [T][ ] work tomorrow
    ```
- **mark** marks the selected task as done (specify its index in the list)
  ```
  mark 1
  ```
  **outcome:** The right checkbox would be marked
    ```
    1 [T][X] work tomorrow
    ```
- **unmark** marks the selected task as un-done (specify its index in the list)
  ```
  unmark 1
  ```
  **outcome** The right checkbox would be unmarked 
    ```
    1 [T][ ] work tomorrow
    ```
- **todo** Add a todo task to the bot. (specify the task name after the command)
  ```
  todo some code
  ```
  **outcome** The bot will print the todo task
    ```
    Added: [T][ ] some code
    ```
- **delete** a specific task (specify its index in the list)
  ```
  delete 1
  ```
  **outcome** The specific task would be deleted
     ```
      Deleting selected task!
      [T][ ] some code
     ```
- **event** add a event (specify its start and end date time)
  ```
  event code stuff /from 2012-01-01T01:03 /to 2012-01-01T04:30
  ```
  **outcome**
     ```
    added: [E][ ] wow (2012-01-01T01:03 to 2012-01-01T04:30)
     ```

- **deadline** add a deadline (specify the dead date)
  ```
  deadline code /by 02/11/1999
  ```
  **outcome**
    ```
    added: [D][] code (1999-11-02)
    ```
- **find** Find a tasks whose names loosely matches the given name
  ```
  find wow
  ```
  **outcome**
    ```
    finding task!
    Found:
    1 [E][ ] wow (2012-01-01T01:03 to 2012-01-01T04:30)
    ```
- **free** Find the earliest timeslot with x number of hours
  ```
  free 4
  ```
  **outcome**
    ```
    The earliest timeslot is: 2012-01-01T04:30
    ```
  
  



