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
- **list** all current tasks
  ```
  list
  ```
- **mark** marks the selected task as done (specify its index in the list)
  ```
  mark 1
  ```
- **unmark** marks the selected task as un-done (specify its index in the list)
  ```
  unmark 1
  ```
- **todo** Add a todo task to the bot. (specify the task name after the command)
  ```
  todo some code
  ```
- **delete** a specific task (specify its index in the list)
  ```
  delete 1
  ```
- **event** add a event (specify its start and end date time)
  ```
  event code stuff /from 2012-01-01T01:03 /to 2012-01-01T04:30
  ```
- **deadline** add a deadline (specify the dead date)
  ```
  deadline code /by 02/11/1999
  ```
- **find** Find a tasks whose names loosely matches the given name 
  ```
  find wow
  ```
- **free** Find the earliest timeslot with x number of hours
  ```
  free 4
  ```
  
  
  
