# Martin Chatbot User Guide

## Features 

1. [Date - Find tasks by date](#date---find-tasks-by-date)
2. [Deadline - Create a deadline task](#deadline---create-a-deadline-task)
3. [Event - Create an event task](#event---create-an-event-task)
4. [Todo - Create a todo task](#todo---create-a-todo-task)
5. [Delete - Remove a task from the list](#delete---remove-a-task-from-the-list)
6. [Find - Search for tasks by keyword](#find---search-for-tasks-by-keyword)
7. [List - Display all tasks](#list---display-all-tasks)
8. [Mark - Mark a task as done](#mark---mark-a-task-as-done)
9. [Unmark - Unmark a task as done](#unmark---unmark-a-task-as-done)
10. [Snooze - Delay a task](#snooze---delay-a-task)
11. [Sort - Sort tasks chronologically](#sort---sort-tasks-chronologically)

## Usage

### `date` - Find tasks by date

Given a specific date, it displays all tasks (deadlines or events) associated with that date.

**Example of usage:**

`date d/M/yyyy`

**Expected outcome:**

`Tasks on [date]`
A list of all deadlines or events for the given date is shown. If there are no tasks on the provided date, the output will display "No tasks on this date."

**Notes:**
- Use the format 'd/M/yyyy' for the date.
- The list will display tasks on the date or events spanning the date.

**Example output:**
```
Tasks on 1 1 2023:

[D][] Homework (by: Jan 01 2023 2359)
[E][] Test (from: Jan 01 2023 1500 to: Jan 01 2023 1700)
```

---

### `deadline` - Create a deadline task

Create a new deadline task and add it to the list. Deadlines have a description and a specific due date-time.

**Example of usage:**

\`deadline [description] /by d/M/yyyy HHmm\`

**Expected outcome:**

A confirmation message indicating that the deadline has been added.

**Example output:**
```
Got it. I've added this task:
[D][] Homework (by: Jan 01 2023 2359)
Now you have [total tasks] tasks in the list.
```

---

### `event` - Create an event task

Create a new event task and add it to the list. Events have a description and a start and end date-time.

**Example of usage:**

\`event [description] /from d/M/yyyy HHmm /to d/M/yyyy HHmm\`

**Expected outcome:**

A confirmation message indicating that the event has been added.

**Example output:**

```
Got it. I've added this task:
[E][] Meeting (from: Jan 01 2023 1500 to: Jan 01 2023 1700)
Now you have [total tasks] tasks in the list.
```

---

### `todo` - Create a todo task

Create a new todo task and add it to the list. Todos only have a description.

**Example of usage:**

\`todo [description]\`

**Expected outcome:**

A confirmation message indicating that the todo task has been added.

**Example output:**

```
Got it. I've added this task:
[T][] Read a book
Now you have [total tasks] tasks in the list.
```

---

### `delete` - Remove a task from the list

Delete a task based on its index in the list.

**Example of usage:**

\`delete [index]\`

**Expected outcome:**

A confirmation message indicating that the task has been removed.

**Notes:**
- Provide an integer index for the task's position in the list.
- Index starts from 1 (e.g., `delete 1` for the first task).
- Invalid or out-of-range indices will result in an error message.

**Example output:**
```
Noted. I've removed this task:
[T][] Read a book
Now you have [remaining tasks] tasks in the list.
```

---

### `find` - Search for tasks by keyword

Search for tasks that contain a specified keyword in their descriptions.

**Example of usage:**

\`find [keyword]\`

**Expected outcome:**

A list of tasks whose descriptions contain the specified keyword is displayed.

**Notes:**
- Matches tasks with the keyword as a substring in their description.

**Example output:**
```
Here are the matching tasks in your list:

[T][] CS lecture
[D][] Submit CS assignment (by: Mar 01 2023 2359)
```

---

### `list` - Display all tasks

Shows a list of all tasks currently in storage.

**Example of usage:**

\`list\`

**Expected outcome:**

A comprehensive list of all your tasks is displayed.

**Example output:**

```
Here are the tasks in your list:

[T][] Read a book
[D][] CS2103T ip submission (by: Sep 17 2023 2359)
[E][] Team meeting (from: Sep 20 2023 1500 to: Sep 20 2023 1700)
```

---

### `mark` - Mark a task as done

Marks a task at the specified index as completed.

**Example of usage:**

\`mark [index]\`

**Expected outcome:**

The specified task is marked as done and a confirmation message is displayed.

**Example output:**

```
Nice! I've marked this task as done:
[T][X] Read a book
```

---

### `unmark` - Unmark a task as done

Unmarks a task at the specified index, setting it back to its incomplete state.

**Example of usage:**

\`unmark [index]\`

**Expected outcome:**

The specified task is unmarked as done and a confirmation message is displayed.

**Example output:**
```
OK, I've marked this task as not done yet:
[T][] Read a book
```

---

### `snooze` - Delay a task

Snoozes a task at the specified index by a given number of minutes.

**Example of usage:**

\`snooze [index] [minutes]\`

**Expected outcome:**

The specified task's deadline or event time is delayed by the given number of minutes, and a confirmation message is shown.

**Notes:**
- Todos do not support snoozing, so ensure you pick tasks that can be snoozed.

**Example output:**
```
Got it. I've snoozed the task:
[D][] Homework (by: Jan 02 2023 0019)
```

---

### `sort` - Sort tasks chronologically

Sorts the tasks in the list chronologically, where deadlines are sorted by their due dates, events are sorted by their start dates, and tasks without dates (like ToDos) are placed at the end.

**Example of usage:**

\`sort\`

**Expected outcome:**

The tasks in the list will be rearranged in a chronological order based on their associated dates and times. A confirmation message will be displayed.

**Notes:**
- Tasks without associated dates (e.g., Todo tasks) will be placed at the end of the list.
- If the task list is empty, a notification will be given.

**Example output:**

```
Tasks have been sorted!
```