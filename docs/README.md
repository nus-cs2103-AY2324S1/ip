# Jarvis User Guide

```
Hi Master! I'm your personal assistant: Jarvis!
How can I serve you today? - Jarvis
```

![Robot Gif](../docs/robot.gif)

## Table of Contents

1. [Adding Tasks](#adding-tasks)
    1. [Todo Task](#adding-a-todo-task-todo)
    2. [Deadline Task](#adding-a-deadline-task-deadline)
    3. [Event Task](#adding-an-event-task-event)
2. [Managing Tasks](#listing-tasks-list)
    1. [Listing Tasks](#listing-tasks-list)
    2. [Marking a Task as Completed](#marking-a-task-as-completed-mark)
    3. [Marking a Task as Incompleted](#marking-a-task-as-incompleted-unmark)
    4. [Deleting a Task](#deleting-a-task-delete)
3. [Searching and Sorting](#finding-tasks-find)
    1. [Finding Tasks](#finding-tasks-find)
    2. [Sorting Tasks by Deadline](#sorting-tasks-sort-deadline)
4. [Exiting the Application](#exiting-jarvis-bye)
5. [Command Summary](#command-summary)

## Features

> - Words in **UPPER_CASE** are the parameters to be supplied by the user.
> - eg. todo **TASK**, **TASK** is a parameter which can be used as todo **Complete report**.

> - Parameters must be in the correct order
> - eg. if the command specifies deadline **TASK_DESCRIPTION** by **DUE_DATE**,
    deadline **Sep 20 2023 1200** by **Submit ip** will not be a successful command

---

## Adding Tasks

In Jarvis, there are three task types:

### 1. Todo Tasks

- Purpose: Basic tasks with no specific deadline.

### 2. Deadline Tasks

- Purpose: Tasks with specific due dates and times.

### 3. Event Tasks

- Purpose: Tasks representing events with start and end times.

Learn how manage these tasks using commands in the following.

---

## Adding a Todo task: `todo`

Adds a "Todo" task to the task list in the Jarvis app.

### Command Format: `todo TASK_DESCRIPTION`

- `TASK_DESCRIPTION`: The description of the "Todo" task you want to add.
- No other parameter is required, all information after `todo` command will be taken as `TASK_DESCRIPTION`

### Example:

`todo Buy groceries` will add a "Todo" task with the description "Buy groceries" to the task list.

```
Task:
1. T | 0 | Buy groceries
```

### Usage Tips:

- Use meaningful and descriptive text for the `TASK_DESCRIPTION` to easily identify the task.
- Do not include unnecessary details in `TASK_DESCRIPTION` eg. due date which can be added as other task
  type (`Deadline`).

---

## Adding a Deadline task: `deadline`

Adds a "Deadline" task to the task list in the Jarvis app.

### Command Format: `deadline TASK_DESCRIPTION by DUE_DATE`

- `TASK_DESCRIPTION`: The description of the "Deadline" task you want to add.
- `DUE_DATE`: The due date and time for the task in the format `dd/MM/yyyy HHmm`.

### Example:

`deadline Submit report by Sep 22 2023 1400` will add a "Deadline" task with the description "Submit report" and a due
date of "15th October 2023, 2:00 PM" to the task list.

```
Task:
1. D | 0 | Submit report | Sep 22 2023 1400
```

### Usage Tips:

- Ensure you provide a clear and descriptive `TASK_DESCRIPTION`.
- Remember to include the `by` keyword after `TASK_DESCRIPTION` and before `DUE_DATE`
- Use the acceptable format for `DUE_DATE` to avoid date and time format errors.
    - `MMM dd yyyy HHmm` eg. Sep 22 2023 1400
    - `dd MMM yyyy HHmm` eg. 22 Sep 2023 1400
    - `yyyy-MM-dd HHmm` eg. 2023-08-22 1400
    - `dd/MM/yyyy HHmm` eg. 15/10/2023 1400

---

## Adding an Event task: `event`

Adds an "Event" task to the task list in the Jarvis app.

### Command Format: `event TASK_DESCRIPTION from START_DATE_TIME to END_DATE_TIME`

- `TASK_DESCRIPTION`: The description of the "Event" task you want to add.
- `START_DATE_TIME`: The start date and time of the event in the format `dd/MM/yyyy HHmm`.
- `END_DATE_TIME`: The end date and time of the event in the format `dd/MM/yyyy HHmm`.

### Example:

`event Attend conference from 15/10/2023 1400 to 16/10/2023 1600` will add an "Event" task with the description "Attend
conference" and a start date of "15th October 2023, 2:00 PM" and an end date of "16th October 2023, 4:00 PM" to the task
list.

```
Task:
1. E | 0 | Attend conference | Oct 15 2023 1400 | Oct 16 2023 1600
```

### Usage Tips:

- Provide a clear and descriptive `TASK_DESCRIPTION`.
- Use the `from` and `to` keywords to specify the event's start and end date and time.
- Ensure that `START_DATE_TIME` and `END_DATE_TIME` are in the acceptable format.
    - Accepted formats:
        - `MMM dd yyyy HHmm` e.g., Sep 22 2023 1400
        - `dd MMM yyyy HHmm` e.g., 22 Sep 2023 1400
        - `yyyy-MM-dd HHmm` e.g., 2023-08-22 1400
        - `dd/MM/yyyy HHmm` e.g., 15/10/2023 1400

---

## Listing tasks: `list`

Lists all tasks currently present in the task list.

### Command Format: `list`

- This command does not require any additional parameters.
- Adding other parameters will affect the command.
    - eg. `list tasks` will not run the list function properly

### Example:

Executing the `list` command will display all the tasks in your task list.

- It provides you with the task details including
    - Task type (`T` for Todo, `D` for Deadline, `E` for Event)
    - Completion status (`0` for incomplete, `1` for completed) and the
      task description.
    - Due date if application

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 1 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

### Usage Tips:

- The `list` command is useful for getting an overview of all tasks currently in your list.
- Use this command to review your tasks and their status.

---

## Marking a task as completed: `mark`

Marks a task as completed in the task list.

### Command Format: `mark INDEX`

- `INDEX`: The index of the task to mark as completed.
    - The index must be a positive integer ranging from 1 to the total number of
      tasks in the list.

### Example:

`mark 2` will mark the task "tp submission" as completed in the following list:

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

After executing this command, your updated task list will look like this:

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 1 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

where `1` for task "tp submission" indicates that the task has been completed.

### Usage Tips:

- Use the `list` command to see the list of tasks and their corresponding indices.
- Once a task is marked as completed,
    - it will be indicated with `1` in the task status column.

---

## Marking a task as incompleted: `unmark`

### Command Format: `unmark INDEX`

- `INDEX`: The index of the task to mark as completed.
    - The index must be a positive integer ranging from 1 to the total number of
      tasks in the list.

### Example:

`unmark 2` will mark the task "tp submission" as completed in the following list:

```
Task:
1. D | 1 | Finish report | Sep 18 2023 1000
2. D | 1 | tp submission | Sep 19 2023 1200
3. D | 1 | Complete ip | Sep 20 2023 1100
```

After executing this command, your updated task list will look like this:

```
Task:
1. D | 1 | Finish report | Sep 18 2023 1000
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 1 | Complete ip | Sep 20 2023 1100
```

where `0` for task "tp submission" indicates that the task is uncomplete.

### Usage Tips:

- Use the `list` command to see the list of tasks and their corresponding indices.
- Once a task is marked as uncompleted,
    - it will be indicated with `0` in the task status column.

---

## Deleting a task: `delete`

Deletes a task from the Jarvis app.

### Command Format: `delete INDEX`

- `INDEX`: The index of the task to delete.
    - The index must be a positive integer ranging from 1 to the total number of
      tasks in the list.

### Example:

`delete 2` will delete the task "tp submission" in the following list

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

After executing this command, your updated task list will look like this

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 0 | Complete ip | Sep 20 2023 1100
```

### Usage Tips:

- Use the `list` command to see the list of tasks and their corresponding indices.
- Be cautious when using the `delete` command
    - As it permanently removes a task from your list, and this action cannot be undone.

---

## Finding tasks: `find`

Searches for tasks containing a specific keyword in their titles and displays the matching tasks.

### Command Format: `find KEYWORD`

- `KEYWORD`: The keyword to search for within task titles.
    - The search is case-insensitive.

### Example:

Executing `find report` for the following list:

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

The result will show the tasks matching the keyword:

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
```

### Usage Tips:

- Use the `find` command to quickly locate tasks containing specific keywords in their titles.
- If no matching tasks are found, a message indicating this will be displayed.

---

## Sorting Tasks: `sort deadline`

Sorts the deadline tasks in the task list by their due dates.
> Note: This command does not affect other types of tasks, such as "Todo" or "Event" tasks.
> Tasks of other task type will be shifted to the back of the list.

### Command Format: `sort deadline`

- This command does not require any additional parameters.
- Adding other parameters will affect the command.
    - eg. `sort deadline by due date` will not execute the command.

### Example:

Executing `sort deadline` on the following list:

```
Task:
1. D | 0 | Complete ip | Sep 20 2023 1100
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 0 | Finish report | Sep 18 2023 1000
```

will update the task list to this:

```
Task:
1. D | 0 | Finish report | Sep 18 2023 1000
2. D | 0 | tp submission | Sep 19 2023 1200
3. D | 0 | Complete ip | Sep 20 2023 1100
```

### Usage Tips:

- Use the `sort deadline` command to organize your **Deadline** tasks based on their due dates.
- It helps you prioritize tasks with earlier deadlines.

---

## Exiting Jarvis: `bye`

Exits the Jarvis application, saving any changes to tasks and displaying a farewell message.

### Command Format: `bye`

- This command does not require any additional parameters.
- Adding other parameters will affect the command.
    - eg. `bye jarvis` will not exit the application.

### Example:

Executing the `bye` command will exit the Jarvis application after saving any changes to tasks. This command does not
have specific examples or outcomes beyond closing the application.

### Usage Tips:

- Use the `bye` command when you want to exit the Jarvis application.
- All changes to your tasks are automatically saved before exiting.

---

## Command Summary

| Action                  | Format, Examples                                                                                                                          |
|-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| Add Todo Task           | `todo TASK_DESCRIPTION`<br>e.g., `todo Buy groceries`                                                                                     |
| Add Deadline Task       | `deadline TASK_DESCRIPTION by DUE_DATE`<br>e.g., `deadline Submit report by Sep 22 2023 1400`                                             |
| Add Event Task          | `event TASK_DESCRIPTION from START_DATE_TIME to END_DATE_TIME`<br>e.g., `event Attend conference from 15/10/2023 1400 to 16/10/2023 1600` |
| List Tasks              | `list`                                                                                                                                    |
| Mark Task as Completed  | `mark INDEX`<br>e.g., `mark 2`                                                                                                            |
| Mark Task as Incomplete | `unmark INDEX`<br>e.g., `unmark 2`                                                                                                        |
| Delete Task             | `delete INDEX`<br>e.g., `delete 2`                                                                                                        |
| Find Tasks              | `find KEYWORD [MORE_KEYWORDS]`<br>e.g., `find report`                                                                                     |
| Sort Deadline Tasks     | `sort deadline`                                                                                                                           |
| Exit Jarvis             | `bye`                                                                                                                                     |

