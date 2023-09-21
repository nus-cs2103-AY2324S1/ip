
# User Guide

Dre is a Command Line Interface (CLI) chatbot application for managing a task list. If you can type fast, Dre can get your tasks done faster than traditional todo managers like Google Keep or Notion.

## Quick Start

1. Ensure you have Java 11 installed in your computer. 
2. Download the latest Dre.jar from [here](. (add link)
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Dre.jar` command to run the application. A GUI similar to the below should appear in a few seconds.
![image](Ui.png)
4. Type the command in the command box and press Enter or click Send on the window.
Some example commands you can try:
   - `list`: Lists all tasks.
   - `bye`: Exits the app.
   - `todo new task`: Creates a new todo called 'new task'.

## Features

### Add New Task

Allows the user to add a new task to the task list. Depending on the type of task, the user can specify a description and, for certain tasks, one or more dates.

### Delete Task

Enables the user to remove a specific task from the task list using its index.

### Edit Task

Offers the user the flexibility to modify the details of a specific task without needing to delete and re-add it.

### Find Using Keyword

Helps the user search for specific tasks in the task list using a keyword.

### List All Tasks

Displays all the tasks in the task list, allowing the user to view and manage them effectively.

### Mark Task as Done

Allows the user to mark a specific task as completed, providing a sense of accomplishment.

### Mark Task as Undone

In case of errors or changes, this command lets the user revert a task's status back to "not done."

### Exit Application

Provides a simple command to gracefully close the chatbot application, at the same time saving the tasks.

## Usage

### `todo`, `deadline`, `event` - Add New Task

Add a new task of type ToDo, Deadline, or Event.

Format:

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by YYYY-MM-DD`
- `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Example of usage:

- `todo homework`  
- `deadline assignment /by 2022-01-01`  
- `event hackathon /from 2022-01-01 /to 2022-02-01`

Expected outcome:

3 new tasks are separately added to your list.

```
Got it. I've added this task:
[T][ ] homework
You now have 1 tasks in your list.

Got it. I've added this task:
[D][ ] assignment (by: Jan 01 2022)
You now have 2 tasks in your list.

Got it. I've added this task:
[E][ ] hackathon (from: Jan 01 2022 to: Feb 01 2022)
You now have 3 tasks in your list.
```

---

### `delete` - Delete Task

Delete a task from the list by its index.

Format:

- `delete TASK_INDEX`

Example of usage:

- `delete 1`

Expected outcome:

The task with index 1 is removed from the list.

```
Task deleted: 
[T][ ] homework
```

---

### `edit` - Edit Task

Edit the details of a specific task.

Format:

- `edit TASK_INDEX description NEW_DESCRIPTION` (any task's description can be edited)
- `edit TASK_INDEX fromDate YYYY-MM-DD` (only for Event tasks)
- `edit TASK_INDEX toDate YYYY-MM-DD` (only for Event tasks)
- `edit TASK_INDEX byDate YYYY-MM-DD` (only for Deadline tasks)

Example of usage:

- `edit 1 description Buy milk`
- `edit 2 byDate 2023-10-09`

Expected outcome:

The tasks 1 and 2 are separately updated.

```
The following task has been successfully updated:
[T][ ] Buy milk

The following task has been successfully updated:
[D][ ] 3230 assignment (by: Oct 09 2023)
```

---

### `find` - Find Using Keyword

Search for tasks using a keyword.

Format:`find KEYWORD`

Example of usage:
`find assignment`

Expected outcome:

Displays all tasks containing the keyword "assignment".

```
Here are the matching tasks in your list:
1. [D][ ] assignment (by: Jan 01 2022)
2. [T][X] 3230 assignments
```

---

### `list` - List All Tasks

Show all tasks in the list.

Format: `list`

Expected outcome:

Displays all tasks.

```
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] assignment (by: 2022-01-01)
```

---

### `mark` - Mark Task as Done

Mark a task as done by its index.

Format:
`mark TASK_INDEX`

Example of usage:
`mark 2`

Expected outcome:

The task with index 2 is marked as done.

```
Nice! I've marked this task as done:
[T][X] homework
```

---

### `unmark` - Mark Task as Undone

Unmark a task as done by its index.

Format: `unmark TASK_INDEX`

Example of usage: `unmark 2`

Expected outcome:

The task with index 2 is marked as undone.

```
Ok! I've marked this task as undone:
[T][ ] homework
```

---

### `bye` - Exit Application

Exit the chatbot application.

Format: `bye`

Expected outcome:
The chatbot application is closed.

```
Bye! Hope to see you again soon.
```

## Acknowledgements

Reused syntax for User Guide from AB3, written by NUS CS2103/T AY23/24 Team.
Special thanks to:
- Krista Yeo github.com/kristayeo for some bug fixes.
- Tembusu Coffeehouse for the much needed fuel to complate this project.
