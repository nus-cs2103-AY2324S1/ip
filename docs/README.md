# User Guide

URChatBot (UCB) is your personal task managing chatbot.
## Features 

* ### Manage Tasks
    - Tasks are split into Todos, Deadlines and Events.

    - You can create, remove, and list all tasks with JED.

    - You can also make them after you have finished your tasks.

* ### Manage Time

   - Find your free time while you have events during your working hours.


## Usage

### Quick Navigation
1. Task-related commands [Link](#task-related)
2. Time-management-related commands [Link](#time-management-related)
> [!NOTE]
> All commands are not case-sensitive.
> Example: `list` and `LIST` and `List` will all be identified as `list` command.
---
### Task-related


### `list` - List all existing tasks

Example of usage:

`list`

Expected outcome:

* Lists all existing tasks
* Example:
    ```
      1. [T][X] read book
      2. [D][] CS2103T IP (by: 22 09 2023 23:59)
      3. [E][] career fair (from: 20 09 2023 16:00 to: 20 09 2023 18:00)
    ```

---

### `todo` - Add a Todo task.
Format: `todo DESCRIPTION`
* Todo task is a task without any time restrictions.
* The task added is unmarked by default, if you want to mark your task, please use command `mark`.

Example of usage: `todo read book`

Expected outcome: 
* Adds a new todo to the list of tasks.
* Example:
    ```
      Noted. I've added this task:
      [T] [] read book
      Now you have 1 task in the list.
    ```

---

### `deadline` - Add a Deadline task.
Format: `deadline DESCRIPTION /by DATE`
* Deadline task is a task with strict time deadline.
* DATE can be entered in these three format: `yyyy-MM-dd HH:mm` `yyyy-MM-dd` `HH:mm`.
> [!NOTE]
> It is possible to key in only the time, e.g. `/from HH:mm`
However, our chatbot will assume the date as the date your added the task.
Example of usage: `deadline CS2103T IP /by: 2023-09-22 2359`

Expected outcome: 
* Adds a new deadline to the list of tasks.
* Example:
    ```
    Noted. I've added this task:
    [D][] CS2103T IP (by: 22 09 2023 23:59)
    Now you have 2 tasks in the list.
    ```
---

### `event` - Add a Event task.

Format: `event DESCRIPTION /from DATE /to DATE`
* Event task is a task with a starting date and/or time and an ending date and/or time
* DATE can be entered in these three format: `yyyy-MM-dd HH:mm` `yyyy-MM-dd` `HH:mm`.
> [!NOTE]
> It is possible to key in only the time, e.g. `/from HH:mm`
           However, our chatbot will assume the date as the date your added the task.

Example of usage: `event career fair /from 2023-09-20 16:00 /to 2023-09-20 18:00`

Expected outcome: 
* Adds a new event to the list of tasks.
* Example:
  ```
  Noted. I've added this task:
  [E][] career fair (from: 20 09 2023 16:00 to: 20 09 2023 18:00)
  Now you have 3 tasks in the list.
  ```
---

### `mark` - Mark a task as completed.

Format: `mark INDEX`
* Your task will be marked as done with X.

Example of usage: `mark 1`

Expected outcome:
* Marks the task at the specified index as completed.
* Example:
    ```
      Nice! I've marked this task as done:
      [T] [X] read book
    ```
---

### `unmark` - Unmark a task as incomplete.

Format: `unmark INDEX`
* Your task will be unmarked and X will be removed.

Example of usage: `unmark 1`

Expected outcome:

* Un-marks the task at the specified index as incomplete.
* Example: 
    ```
      Nice! I've unmarked this task as done:
      [T] [] read book
    ```
---

### `delete` - Delete a task.

Format: `delete INDEX`

Example of usage: `delete 1`

Expected outcome:

* Deletes the task at the specified index.
* Example
    ```
      Noted. I've removed this task:
      [T] [] read book
      Now you have 2 task in the list.
    ```
---

### `find` - Find tasks by matching keyword.

Format: `find KEYWORD`
* Keywords will be used to match the task description in the existing list of tasks.

Example of usage: `find fair`

Expected outcome:

* Lists all tasks with matching keyword.
* Example:
    ```
  Here are the matching tasks in your list:
  1. [E][] career fair (from: 20 09 2023 16:00 to: 20 09 2023 18:00)
  ```
---
### `print` - Print tasks by matching dates.

Format: `print DATE`
* DATE will be used to match the task in the existing list of tasks.
* DATE can be entered in **only one** format: `yyyy-MM-dd`.

Example of usage: `print 2023-09-20`

Expected outcome:

* Lists all tasks with matching date.
* Example:
    ```
  There are a total of 1 task on 20 09 2023:
  1. [E][] career fair (from: 20 09 2023 16:00 to: 20 09 2023 18:00)
  ```
---
### `clear` - Clear all tasks in your task list.
Example of usage: `clear`
> [!WARNING]
> If you clear your tasks, there no way to recover. Be careful!

Expected outcome:

* Deletes the task at the specified index.
* Example
    ```
  All tasks are cleared.
    ```
---
### `bye` - Exit the chatbot
Example of usage: `bye`
* Don't worry about your tasks! Your task list will be stored in a txt file we created for you.

Expected outcome:

* Example
    ```
      Bye! Hope to see you again!
    ```
---
### Time-management-related

### `findFreeTime` - Find the date with free time duration during the working hours
Format: `findFreeTime TIME_DURATION /from DATE /to DATE`
* The chatbot only finds your free time based on the events.
* The chatbot only finds your free times within your working hours (8am to 6pm daily).
* TIME_DURATION should be pure number in **minutes**.
* DATE can be entered in **only one** format: `yyyy-MM-dd`.
* It is possible to enter the same DATE for `/from` and `/to` since DATEs are **inclusive**.

Example of usage: 

`findFreeTime 540 /from 2023-09-20 /to 2023-09-21`: find dates with free time of consecutive 9 hours from 2023-09-20 to 2023-09-21

Expected outcome:

```
Free Time date within the specified date range:
2023-09-21
```
---
