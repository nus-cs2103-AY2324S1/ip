# Pixel User Guide

Pixel is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, Pixel can get your task management done faster than
traditional GUI apps.

## Features

- Adding todo tasks with only a description
- Adding deadlines with a description and a deadline
- Adding events with a description and a start and end time
- Adding timed tasks with a description and a duration
- Marking tasks as done

## Usage

### `todo {description}` - Add a todo task

Adds a todo task with the given description to the list of tasks.

Example of usage:

`todo read book`

Expected outcome: A todo task with the description "read book" is added to the list of tasks.

```
Got it. I've added this task:
  [T][ ] read book
 Now you have 1 task in the list.
```

### `deadline {description} /by {deadline}` - Add a deadline

Adds a deadline with the given description and deadline to the list of tasks.
Deadline should be in the format `dd/MM/yyyy HHmm`.

Example of usage:

`deadline return book /by 21/9/2023 2359`

Expected outcome: A deadline with the description "return book" with deadline on 21 Sep 2023, 23:59pm is added to the
list of
tasks.

```
Got it. I've added this task:
  [D][ ] return book (by: 21 Sep 2023, 23:59)
 Now you have 2 tasks in the list.
```

### `event {description} /from {startTime} /to {endTime}` - Add an event

Adds an event with the given description, start time and end time to the list of tasks.
Start time and end time should be in the format `dd/MM/yyyy HHmm`.

Example of usage:

`event project meeting /from 21/9/2023 1400 /to 21/9/2023 1500`

Expected outcome: An event with the description "project meeting" starting on 21 Sep 2023, 2:00pm and ending on 21
Sep 2023, 3:00pm, is added to the list of tasks.

```
Got it. I've added this task:
  [E][ ] project meeting (from: 21 Sep 2023, 14:00 | to 21 Sep 2023, 15:00)
 Now you have 3 tasks in the list.
```

### `timed {description} /duration {duration}` - Add a timed task

Adds a timed task with the given description and duration to the list of tasks.
Duration should be a number in hours.

Example of usage:

`timed project meeting /duration 2`

Expected outcome: A timed task with the description "project meeting" with duration of 2 hours is added to the list of
tasks.

```
Got it. I've added this task:
  [I][ ] project meeting (duration: 2.0 hours)
 Now you have 4 tasks in the list.
```

### `mark {index}` - Mark a task as done

Marks the task at the given index as done.

Example of usage:

`mark 1`

Expected outcome: The first task in the list is marked as done.

```
Nice! I've marked this task as done:
  [T][X] read book
```

### `unmark {index}` - Mark a task as not done

Marks the task at the given index as not done.

Example of usage:

`unmark 1`

Expected outcome: The first task in the list is marked as not done.

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### `list` - List all tasks

Lists all tasks stored.

Example of usage:

`list`

Expected outcome: All tasks stored are listed.

```
Here are the tasks in your list:
  1. [T][X] read book
  2. [D][ ] return book (by: 21 Sep 2023, 23:59)
  3. [E][ ] project meeting (from: 21 Sep 2023, 14:00 | to 21 Sep 2023, 15:00)
  4. [I][ ] project meeting (duration: 2.0 hours)
```

### `delete {index}` - Delete a task

Deletes the task at the given index.

Example of usage:

`delete 1`

Expected outcome: The first task in the list is deleted.

```
Noted. I've removed this task:
  [T][X] read book
 Now you have 3 tasks in the list.
```

### `find {keyword}` - Find tasks

Finds all tasks with the given keyword in their description.

Example of usage:

`find book`

Expected outcome: All tasks with the keyword "book" in their description are listed.

```
Here are the matching tasks in your list:
  1. [D][ ] return book (by: 21 Sep 2023, 23:59)
```

### `bye` - Exit the program

Example of usage:

`bye`

Expected outcome: The program exits.