# [BauBauBot Guide](https://github.com/et-irl/ip)

BauBauBot is a Java-based chatbot designed to help you manage your tasks effectively. This user guide will walk you through its features and how to use them.

## Features

### 📅 Add a Deadline

You can add a deadline task using the following command or its alias:

```
deadline [NAME] /by [TIME]
```

Alias: `d`

### 📆 Add an Event

To add an event task, use the following command or its alias:

```
event [NAME] /from [START TIME] /to [END TIME]
```

Alias: `e`

### ✅ Add a To-Do

Add a simple to-do task with this command or its alias:

```
todo [NAME]
```

Alias: `t`

### 🗑️ Delete a Task

Remove a task from your list using this command or its alias:

```
delete [TASK NUMBER]
```

Alias: `rm`

### 🔍 Find Tasks

To search for tasks that match a partial search term in their names, use this command or its alias:

```
find [PARTIAL SEARCH TERM]
```

Alias: `f`

### 📋 List Tasks

View all your tasks with this command or its alias:

```
list
```

Alias: `ls`

### ✅ Mark Task as Done

Mark a task as completed with this command or its alias:

```
mark [TASK NUMBER]
```

Alias: `m`

### ❌ Mark Task as Undone

Reverse a completed task to its undone status with this command or its alias:

```
unmark [TASK NUMBER]
```

Alias: `um`

## Usage

Each feature has a specific command associated with it. You can use these commands to interact with BauBauBot and manage your tasks efficiently.

Example of usage:

```
deadline Finish Report /by 2023-12-31
```

Expected outcome:

BauBauBot will add a new deadline task named "Finish Report" with the due date of December 31, 2023.

```
Expected output:
Got it. I've added this task:
[D][ ] Finish Report (by: Dec 31, 2023)
Now you have X tasks in the list.
```

Follow similar patterns for other commands, and BauBauBot will help you organize your tasks seamlessly.

This user guide should help you get started with BauBauBot, making task management a breeze!
