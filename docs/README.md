# Simon Chatbot User Guide

## Features
### Interactive UI
Simon offers a clear and responsive chat-like interface, making task management intuitive and enjoyable.

### Task Management
Keep track of your to-dos, deadlines, and events seamlessly with Simon's chat interface.

### Persistent Storage
Your tasks aren't fleeting, and neither is Simon's memory. Tasks are saved in a file for future reference, so you won't lose track.

### Dark Mode
A pleasing and eye-friendly dark mode ensures you can work comfortably at any time of the day.

### `List`
Displays all your tasks in a structured manner.

### `Todo`
Adds a new to-do.

### `Deadline`
Adds a new deadline.

### `Event`
Adds a new event.

### `Mark`
Marks a task as done.

### `Unmark`
Marks a task as not done.

### `Delete`
Deletes a task.

### `Find`
Finds tasks that match the keyword.

### `Bye`
Exits the chatbot.

## Usage
### List all tasks
### `list`
Displays all your tasks in a structured manner.

**Example of usage:**

```
list
```

**Expected outcome:**
```
1. [T][ ] Buy groceries
2. [D][X] Submit assignment (by: 12 Dec 2023)
```
___
### Add a new to-do
### `todo <task>`
Describe how the todo command works.

**Example of usage:**

```
todo Buy groceries
```

**Expected outcome:**
```
Got it. I've added this task:
  [T][] Buy groceries
Now you have 1 task in the list.
```
___
### Add a new deadline
### `deadline <task> /by <date>`

Add tasks with a specific deadline to be met.

**Example of usage:**
```
deadline Submit project /by 2023-12-15
```

**Expected outcome:**

```
Got it. I've added this task:
  [D][] Submit project (by: 15 Dec 2023)
Now you have 2 tasks in the list.
```

---
### Add a new event
### `event <task> /from <date> /to <date>`

Organize events or schedule tasks to be done at a particular time or date.

**Example of usage:**

```
event Team meeting /from 2023-12-16 1400 /to 2023-12-16 1600
```

**Expected outcome:**

```
Got it. I've added this event:
[E][] Team meeting (from: 16 Dec 2023, 2:00 PM to: 16 Dec 2023, 4:00 PM)
Now you have 3 tasks in the list.
```

---
### Mark a task as done
### `mark <task_number>`

Celebrate your progress by marking tasks as completed.

**Example of usage:**

```
mark 1
```

**Expected outcome:**
```
Nice! I've marked this task as done:
[T][X] Buy groceries
```

---
### Mark a task as not done
### `unmark <task_number>`

Revert a task back to its incomplete status.

**Example of usage:**

```
unmark 1
```

**Expected outcome:**
```
I've marked this task as not done:
[T][] Buy groceries
```

---
### Delete a task
### `delete <task_number>`

Delete a task from your list.

**Example of usage:**
````
delete 1
````

**Expected outcome:**
```
Noted. I've removed this task:
  [T][X] Buy groceries
Now you have 2 tasks in the list.
```

---
### Search for tasks
### `find <keyword>`

Find and display tasks that match the keyword.

**Example of usage:**
```
find project
```

**Expected outcome:**
```
Here are the matching tasks in your list:
1. [D][] Submit project (by: 15 Dec 2023)
```

---
### Exit the chatbot
### `bye`

Say goodbye to Simon and close the application.

**Example of usage:**
```
bye
```

**Expected outcome:**
```
Goodbye! Hope to see you again soon!
```
