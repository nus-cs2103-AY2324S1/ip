# Chatbot "Alain" User Guide

Chatbot Alain provides users with a helpful assistant to manage tasks efficiently. This User Guide will help you get familiarized with its functionality.

## Table of Contents

- [Features](#features)
    - [X] Text based and easy to use
    - [x] Manage tasks
    - [X] Save into file: ./data/notebook.txt
- [Usage](#usage)
    - [`list`](#list)
    - [`mark`](#mark-task-number)
    - [`unmark`](#unmark-task-number)
    - [`todo`](#todo-task-description)
    - [`deadline`](#deadline-task-description-by-datetime)
    - [`event`](#event-task-description-from-start-datetime-to-end-datetime)
    - [`delete`](#delete-task-number)
    - [`find`](#find-key-word)

## Features

### 1. **List Tasks**:
Displays all the tasks added by the user in a list format.

### 2. **Mark as Done**:
Users can mark specific tasks as completed.

### 3. **Unmark a Task**:
Change a task's status back to not done.

### 4. **Task Types: ToDos, Deadlines, and Events**:
Supports three types of tasks:
- **ToDos** - tasks without any specific date/time.
- **Deadlines** - tasks that have a completion date/time.
- **Events** - tasks that have both start and end date/time.

### 5. **Error Handling**:
The chatbot provides feedback for incorrect or unrecognizable inputs.

### 6. **Delete Task**:
Users can remove tasks from their list.

### 7. **Task Sorting**:
User can see tasks already sorted by deadlines(only applicable for those with specific dates) when applying "list" command

### 8. **Task saved into txt**:
User can see saved their changes into notebook.txt.

## Usage

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
