# Avalon User Guide

Avalon is a Graphical User Interface(GUI) based task management chatbot designed to help you stay organized and keep track of your tasks efficiently. It offers features like task management, priority setting, and task searching.

## Features

### Task Management

- **Adding Tasks:** You can add various types of tasks, including to-dos, deadlines, and events.
- **Listing Tasks:** View all your tasks in a neatly organized list.
- **Completing Tasks:** Mark tasks as done when you've completed them.
- **Deleting Tasks:** Remove tasks from your list when you no longer need them.
- **Searching Tasks:** Easily find tasks by searching for keywords.
- **Prioritize Tasks:** Set priority levels for your tasks as high, medium, or low to help you focus on what's most important.

## Usage

### Adding a To-Do Task: `todo`

Add a to-do task to your list.

**Example of usage:**

todo Buy groceries /priority LOW

**Expected outcome:**

The to-do task "Buy groceries" with low priority is added to your task list.

### Adding a Deadline Task: `deadline`

Add a task with a deadline to your list.

**Example of usage:**

deadline Submit report /by 2023-12-31 2359 /priority HIGH

**Expected outcome:**

The deadline task "Submit report" with high priority and the specified due date is added to your task list.

### Adding an Event Task: `event`

Add an event task to your list.

**Example of usage:**

event Team meeting /at 2023-09-15 1400 to 1600 /priority MEDIUM

**Expected outcome:**

The event task "Team meeting" with medium priority and the specified date and time is added to your task list.

### Listing All Tasks: `list`

View all your tasks in a neatly organized list.

**Example of usage:**

list

**Expected outcome:**

A list of all your tasks with their status and priority.

1. [ ] Buy groceries (LOW)
2. [ ] Submit report (HIGH)
3. [ ] Team meeting (MEDIUM)

### Completing a Task: `mark`

Mark a task as completed.

**Example of usage:**

mark 1

**Expected outcome:**

The first task in your list is marked as completed.

### Deleting a Task: `delete`

Remove a task from your list.

**Example of usage:**

delete 2

**Expected outcome:**

The second task in your list is deleted.

### Searching for Tasks: `find`

Search for tasks that match your keywords.

**Example of usage:**

find report

**Expected outcome:**

A list of tasks containing the keyword "report."

1. [ ] Submit report (HIGH)

---

**Note:** You can use the Avalon chatbot effectively by following these commands and examples. Stay organized and never forget your tasks again!
