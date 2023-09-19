# Chatbot Alain User Guide

Chatbot Alain provides users with a helpful assistant to manage tasks efficiently. This User Guide will help you get familiarized with its functionality.

# Alain frees your mind of having to remember things you need to do. It's,
* text-based
* easy to learn
* FAST SUPER FAST to use
* allows input with txt files and/or with mannual key-in

## Table of Contents

-  [Features](#features)
    - [x] Managing tasks 
-  [Usage](#usage)
    - [`list`](#list)
    - [`mark`](#mark-task-number)
    - [`unmark`](#unmark-task-number)
    - [`todo`](#todo-task-description)
    - [`deadline`](#deadline-task-description-by-datetime)
    - [`event`](#event-task-description-from-start-datetime-to-end-datetime)
    - [`delete`](#delete-task-number)
    - [`find`](#find-key-word)


### `list`

- **Action:** Shows all tasks added by the user.
- **Expected Outcome:**
  read book
  return book
  markdown
  Copy code

### `mark [task number]`

- **Action:** Marks the specified task as completed.
- **Example:**
  mark 2

markdown
Copy code
- **Expected Outcome:**
  Nice! I've marked this task as done:
  [X] return book

markdown
Copy code

### `unmark [task number]`

- **Action:** Changes the task's status back to not done.
- **Example:**
  unmark 2

markdown
Copy code
- **Expected Outcome:**
  OK, I've marked this task as not done yet:
  [ ] return book

markdown
Copy code

### `todo [task description]`

- **Action:** Add a ToDo task with the provided description.
- **Example:**
- todo borrow book
- **Expected Outcome:** 
- Got it. I've added this task:
  [T][ ] borrow book
  Now you have 5 tasks in the list.

### `deadline [task description] /by [date/time]`

- **Action:** Add a Deadline task with a specific due date/time.
- **Example:** 
- deadline return book /by Sunday
- **Expected Outcome:** 
- Got it. I've added this task:
  [D][ ] return book (by: Sunday)
  Now you have 6 tasks in the list.

### `event [task description] /from [start date/time] /to [end date/time]`

- **Action:** Add an Event with a specific start and end date/time.
- **Example:** 
- event project meeting /from Mon 2pm /to 4pm
- **Expected Outcome:** 
- Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
  Now you have 7 tasks in the list.
- 
- **Example:**
- event project meeting /from 2019-10-11 18:00 /to 2019-10-12 18:00
- **Expected Outcome:**
- Got it. I've added this task:
  [E][ ] project meeting (from: October 11 2019 18:00 to: October 12 2019 18:00)
  Now you have 8 tasks in the list.

### `delete [task number]`

- **Action:** Deletes the specified task from the list.
- **Example:** 
- delete 3
- **Expected Outcome:** 
- Noted. I've removed this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
  Now you have 4 tasks in the list.

### `find [key word]`

- **Action: ** search for tasks which contains key word in description.
- **Example:**
- find meet
- **Expected Outcome:**
- Here are the matching tasks in your list:
- 1.[E][] project meeting (from:Mon 2pm to: 4pm)
- 2.[E][ ] project meeting(from:October 11 2019 18:00 to: October 12 2019 18:00)

> **Note:** For all commands that require a task number, refer to the task number shown when using the `list` command.
