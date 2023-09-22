# Avalon User Guide

Avalon is a Graphical User Interface(GUI) based task management chatbot designed to help you stay organized and keep track of your tasks efficiently. It offers features like task management, priority setting, and task searching.

## Features

- **Adding Tasks:** You can add various types of tasks, including to-dos, deadlines, and events.
- **Listing Tasks:** View all your tasks in a neatly organized list.
- **Completing Tasks:** Mark or unmark tasks depending on completion.
- **Deleting Tasks:** Remove tasks from your list when you no longer need them.
- **Searching Tasks:** Easily find tasks by searching for keywords.
- **Prioritize Tasks:** Set priority levels for your tasks from 0-9 to help you focus on what's most important.

## Usage

### 1. Adding a To-Do Task: `todo`

> Add a to-do task to your list.

**Format:**

`todo [TASK]`

**Example of usage:**

- ```todo Buy groceries```
- ```todo Read book```

**Expected outcome:**

The to-do task "Buy groceries" is added to your task list.

---

### 2. Adding a Deadline Task: `deadline`

> Add a task with a deadline to your list.

**Format:**

`deadline [TASK] /by [YYYY-MM-DD HHmm]`

**Example of usage:**

`deadline Submit report /by 2023-12-31 2359`

**Expected outcome:**

The deadline task "Submit report" with specified due date is added to your task list.

---

### 3. Adding an Event Task: `event`

> Add an event task to your list.

**Format:**

`event [TASK] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]`

**Example of usage:**

`event Team meeting /from 2023-09-15 1400 /to 2023-09-15 1500`

**Expected outcome:**

The event task "Team meeting" with the specified date and time is added to your task list.

---

### 4. Listing All Tasks: `list`

> View all your tasks in a neatly organized list.

**Format:**

`list`

**Expected outcome:**

A list of all your tasks with their status and priority.

```
1. [T][3][ ] Buy groceries
2. [D][0][X] Submit report (by: 22 Sep 2023 20:00)
3. [E][1][ ] Team meeting (from: 15 Sep 2023 23:00 to: 16 Sep 2023 23:59)
```

---

### 5. Mark a Task: `mark`

> Mark a task as completed.

**Format:**

`mark [TASKNUMBER]`

**Example of usage:**

`mark 1`

**Expected outcome:**

The first task in your list is marked as completed.

---

### 6. Unmark a Task: `unmark`

> Mark a task as not completed.

**Format:**

`unmark [TASKNUMBER]`

**Example of usage:**

`unmark 3`

**Expected outcome:**

The third task in your list is marked as completed.

---

### 7. Deleting a Task: `delete`

> Remove a task from your list.

**Format:**

`delete [TASKNUMBER]`

**Example of usage:**

`delete 2`

**Expected outcome:**

The second task in your list is deleted.

---

### 8. Searching for Tasks: `find`

> Search for tasks that match your keywords.

**Format:**

`find [KEYWORD]`

**Example of usage:**

`find report`

**Expected outcome:**

A list of tasks containing the keyword "report."

`1.[T][0][ ] Submit report`

---

### 9. Ranking Task Priority: `priority`

> Marks a task with a number from 0-9 as a priority label.

**Format:**

`priority [TASKNUMBER] /[PRIORITYNUMBER]`

**Example of usage:**

`priority 1 /3`

**Expected outcome:**

The first task in the list is marked with a priority of 3.

`1.[T][3][ ] Submit report`

---

### 10. Exit program: `bye`

> Saves the task list into a file and closes the program.

**Format:**

`bye`

**Expected outcome:**

An exit message is shown and the program window closes.

---

**Note:** You can use the Avalon chatbot effectively by following these commands and examples. Stay organized and never forget your tasks again!