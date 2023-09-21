# Capt. Price Bot - User Guide

*Created by: Raman Gupta*

![Capt. Price Ready for Mission](https://www.google.com.sg/url?sa=i&url=https%3A%2F%2Fwww.greenmangaming.com%2Fblog%2Fthe-history-of-call-of-dutys-captain-price%2F&psig=AOvVaw2eEoOBM1SZwLmhamV4oH00&ust=1695418292903000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCKCYxLrTvIEDFQAAAAAdAAAAABAE)

## Introduction

Welcome to Capt. Price Bot - Your Command Line Task Management Companion! Seamlessly manage, organize, and plan your tasks through the power of commands. Let Capt. Price assist you in your journey towards peak productivity.

_"On your command, Sergeant! (literally)"_

## Getting Started

1. Go to GitHub Releases, and download the JAR file onto your computer.
2. Run the JAR file titled `priceBot-v1.0-final`.
3. Follow the instructions below to harness the full potential of Capt. Price!

## Features Overview

### Create Tasks

- **Todo Tasks:** Create todo tasks with descriptions to keep track of things to do.
- **Deadline Tasks:** Manage and prioritize with deadline tasks that include descriptions and due dates.
- **Event Tasks:** Keep your calendar organized with event tasks including descriptions, start, and end times.

### List Tasks the Way You Want

- List all tasks to get a comprehensive overview of your to-do list.
- List tasks due within one week or month to stay on top of your schedule.

### Delete and Mark/Unmark Tasks

- Easily delete tasks when they're no longer relevant, and edit your task list as needed.
- Mark tasks as done to keep track of your progress and accomplishments.

### Tag Tasks

- Categorize tasks with tags like #sport or #fun for better organization and easy retrieval.

### Find Tasks

- Search for tasks using keywords to quickly locate what you need within your task list.

### Set Parent Tasks or Dependencies

- Define task dependencies to manage the order in which tasks should be completed or to signify prerequisites.

### Get Motivated

- Let Capt. Price inspire you with randomized motivational quotes to help you power through your tasks!

## Commands

### 1. `todo`

Create a todo task with a description. Todo tasks are for general tasks that don't have specific deadlines.

**Entering**

```markdown
todo quiz
```

**would return something like:**
```markdown
added: [T][ ] quiz  | DoAfter : NONE |
Now you have 7 tasks in the list.
```

### 2. `deadline`

Create a deadline task with a description and a due date. Deadline tasks help you manage tasks with specific deadlines.

**Entering**

```markdown
deadline geo quiz /by 2023-10-04 16:00:00
```

**would return something like:**
```markdown
added: [D][ ] geo quiz  | DoAfter : NONE |  (by: 16:00, 04 October 2023)
Now you have 8 tasks in the list.
```

### 3. `event`

Create an event task with a description, start time, and end time. Event tasks are ideal for managing calendar events.

**Entering**

```markdown
event team meeting /from 2023-10-04 16:00:00 /to 2023-10-04 17:00:00
```

**would return something like:**
```markdown
added: [E][ ] team meeting  | DoAfter : NONE |  (from: 16:00, 04 October 2023 to: 17:00, 04 October 2023)
Now you have 9 tasks in the list.
```

### 4. `list`

List all tasks in your task list for a comprehensive overview.

**Entering**

```markdown
list
```

**would return something like:**
```markdown
Here are the tasks in your list:
1. [T][X] read book  | DoAfter : NONE |
2. [D][ ] return book  | DoAfter : NONE |  (by: 18:30, 06 September 2023)
3. [D][ ] submit quiz  | DoAfter : NONE |  (by: 23:59, 08 September 2023)
4. [D][ ] return book  | DoAfter : NONE |  (by: 23:59, 02 October 2023)
5. [E][ ] project meeting  | DoAfter : NONE |  (from: 18:30, 13 September 2023 to: 20:30, 13 September 2023)
6. [T][X] join sports club  | DoAfter : NONE |
```

### 5. `list week`

List tasks due within one week from the current date.

**Entering**

```markdown
list week
```

**would return something like:**
```markdown
Here are the tasks in your list that start/due within one week:
1. [T][X] read book  | DoAfter : NONE |
2. [T][X] join sports club  | DoAfter : NONE |
3. [T][ ] quiz  | DoAfter : NONE |
```

### 6. `list month`

List tasks due within one month from the current date.

**Entering**

```markdown
list month
```

**would return something like:**
```markdown
Here are the tasks in your list that start/due within one month:
1. [T][X] read book  | DoAfter : NONE |
2. [D][ ] return book  | DoAfter : NONE |  (by: 23:59, 02 October 2023)
3. [T][X] join sports club  | DoAfter : NONE |
4. [T][ ] quiz  | DoAfter : NONE |
5. [D][ ] geo quiz  | DoAfter : NONE |  (by: 16:00, 04 October 2023)
6. [E][ ] team meeting  | DoAfter : NONE |  (from: 16:00, 04 October 2023 to: 17:00, 04 October 2023)
```

### 7. `delete`

Delete a task from your task list by specifying its index.

**Entering**

```markdown
delete 4
```

**would return something like:**
```markdown
Noted. I've removed this task:
[D][ ] return book  | DoAfter : NONE |  (by: 23:59, 02 October 2023)
```

### 8. `mark`

Mark a task as done by specifying its index. This helps you track completed tasks.

**Entering**

```markdown
mark 3
```

**would return something like:**
```markdown
Nice! I've marked this task as done: 
[D][X] submit quiz  | DoAfter : NONE |  (by: 23:59, 08 September 2023)
```

### 9. `unmark`

Unmark a task as done by specifying its index. Use this if you want to revisit/revise a completed task.

**Entering**

```markdown
unmark 3
```

**would return something like:**
```markdown
Ok, I've marked this task as not done yet:
[D][ ] submit quiz  | DoAfter : NONE |  (by: 23:59, 08 September 2023)
```

### 10. `find`

Find tasks containing specific keywords in their descriptions.

**Entering**

```markdown
find quiz
```

**would return something like:**
```markdown
Here are the matching tasks in your list:
1. [D][ ] submit quiz  | DoAfter : NONE |  (by: 23:59, 08 September 2023)
2. [T][ ] quiz  | DoAfter : NONE |
3. [D][ ] geo quiz  | DoAfter : NONE |  (by: 16:00, 04 October 2023)
```

### 11. `tag`

Add tags to a task by specifying its index and tags. Tags help you categorize and organize your tasks.

**Entering**

```markdown
tag 3 imp math
```

**would return something like:**
```markdown
Noted. I've added tags to this task:
[D][ ] submit quiz #imp #math | DoAfter : NONE |  (by: 23:59, 08 September 2023)
```

### 12. `doafter`

Set task dependencies by specifying the index of the dependent task and the index of the task it depends on. 
This helps you manage task order and prerequisites.

**Entering**

```markdown
doafter 3 5
```

**would return something like:**
```markdown
Noted. I've added a dependency to this task:
[D][ ] submit quiz | DoAfter : join sports club |  (by: 23:59, 08 September 2023)
```

### 13. `motivate`

Need motivation? Capt. Price has you covered! Use this command to get a motivational quote from Capt. Price.

**Entering**

```markdown
motivate
```

**would return something like:**
```markdown
Here's your dose of motivation, Sergeant!

`In the war against procrastination, we'll fight tooth and nail to victory, soldier!`
~ Capt. Price
```
## File Saving Info

If you have an input data file `./data/duke.txt`, Capt. Price Bot will read initial tasks from the file. Any changes will be written to an `outputDuke.txt` file in the same folder for your convenience. Sharing/exporting the file can help you share your task list between friends and computers.

## Additional Queries

For additional questions, feedback, or assistance, feel free to contact the creator at <u>eyelessrhyme7@gmail.com</u>. We're always open to feedback and ready to help you make the most of Capt. Price Bot.

_"Task Management, Simplified - On Your Command!"_
