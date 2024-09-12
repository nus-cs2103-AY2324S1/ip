# Max Chatbot User Guide
Max is a desktop app to track all kinds of tasks, ranging from todos, events and deadlines!
It is optimised for use via a command line interface (CLI), while also having a Graphical User Interface (GUI)
that's easy on the eyes.

## Features
* Viewing help: `help`
* Adding tasks:
    * Todo: `todo`
    * Deadline: `deadline`
    * Event: `event`
* Deleting tasks: `delete`
* Marking tasks: `mark`
* Unmarking tasks: `unmark`
* Finding tasks: `find`
* Viewing tasks: `list`
* Exiting the program: `exit`

## Usage

> **NOTE**
> * Words in `UPPER_CASE` are parameters supplied by the user.
> * Parameters must be in the given order

---

### `help` - Viewing help
Shows a message listing commands that can be used, and their syntax.

**Example of usage:** `help`

**Expected output:**

A full list of commands.

```
Here are some commands you can try:
  list -- show full task list
  mark <int> -- mark task as completed
  unmark <int> -- mark task as not done yet
  find xx xx -- filter specific task descriptions
  todo <item> -- create new todo
  deadline <item> /by <yyyy-mm-dd> -- create new deadline task
  event <item> /from <yyyy-mm-dd> /to <yyyy-mm-dd> -- create new event task
  help -- bruh need say more?
```

---

### `todo` - Adding todo tasks
Adds a todo task to the user's list of tasks. 

**Command format:** `todo TASK_NAME`

**Example of usage:** `todo pack bag`

**Expected output:**

A confirmation message showing the todo task that has been added, and the number of tasks in the list.

```
I gotchu. I've added this task:
  [T][ ] pack bag
Now you have 1 task(s) in the list.
```

**Notes:**

* `TASK_NAME` can be any non-empty sequence of characters.

---

### `deadline` - Adding deadline tasks
Adds a deadline task with a due date to the user's list of tasks.

**Command format:** `deadline TASK_NAME /by DUE_DATE`

**Example of usage:** `deadline prepare for quiz /by 2023-10-01`

**Expected output:**

A confirmation message showing the deadline task that has been added, and the number of tasks in the list.

```
I gotchu. I've added this task:
  [D][ ] prepare for quiz (by: 2023-10-01)
Now you have 2 task(s) in the list.
```

**Notes:**

* `TASK_NAME` can be any non-empty sequence of characters.
* `DUE_DATE` must be in YYYY-MM-DD format.

---

### `event` - Adding event tasks
Adds an event task with a start and end date to the user's list of tasks.

**Command format:** `event TASK_NAME /from START_DATE /to END_DATE`

**Example of usage:** `event recess week /from 2023-09-23 /to 2023-10-01`

**Expected output:**

A confirmation message showing the event tasks that has been added, and the number of tasks in the list.

```
I gotchu. I've added this task:
  [E][ ] recess week (from: 2023-09-23 to: 2023-10-01)
Now you have 3 task(s) in the list.
```

**Notes:**

* `TASK_NAME` can be any non-empty sequence of characters.
* `START_DATE` and `END_DATE` must be in YYYY-MM-DD format.

---

### `delete` - Deleting tasks
Deletes a task from the user's list, specified by a given index number.

**Command format:** `delete INDEX_NUMBER`

**Example of usage:** `delete 2`

**Expected output:**

A confirmation message specifying which task was deleted.

```
Aights mate. I've killed this task:
  [D][ ] prepare for quiz (by: 2023-10-01)
Now you have 2 task(s) in the list.
```

**Notes:**

* `INDEX_NUMBER` must be a non-negative integer that is not bigger than the size of the list.

---

### `mark` - Marking tasks
Marks a task as completed, specified by a given index number.

**Command format:** `mark INDEX_NUMBER`

**Example of usage:** `mark 1`

**Expected output:**

A confirmation message specifying which task was marked.

```
Good job on completing your task!
 [T][X] pack bag
```

**Notes:**

* `INDEX_NUMBER` must be a non-negative integer that is not bigger than the size of the list.

---

###  `unmark` - Unmarking tasks
Unmarks a task, specified by a given index number. 

**Command format:** `unmark INDEX_NUMBER`

**Example of usage:** `unmark 1`

**Expected output:**

A confirmation message specifying which task was unmarked.

```
Okay, I've marked this as not done yet:
 [T][ ] pack bag
```

**Notes:**

* `INDEX_NUMBER` must be a non-negative integer that is not bigger than the size of the list.

---

###  `find` - Finding tasks
Shows users a list of tasks that matches their query.

**Example of usage:** `find A_FEW_KEYWORDS`

**Example of usage:** `find bag`

**Expected output:**

A list tasks whose names match the keywords.

```
Here are all your tasks:
 [T][ ] pack bag
```

**Notes:**

* `A_FEW_KEYWORDS` can be any number of characters. Search is delimited by spaces.

---

### `list` - Viewing tasks
Shows the user's list of tasks.

**Example of usage:** `list`

**Expected output:**

A list of all the user's tasks.

```
Here are all your tasks:
[T][ ] pack bag
[E][ ] recess week (from: 2023-09-23 to: 2023-10-01)
```

---

###  Exiting the program: `bye`
Exits Max application.

**Example of usage:** `bye`

**Expected output:**

A salutation message from Max.

```
Bye! Please come again!
```

