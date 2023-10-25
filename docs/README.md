# ButlerBot

ButlerBot is a **to-do list manager, optimised for Command Line Interface
(CLI)** while having the benefit of a Graphical User Interface (GUI). 

## Contents
[Features](#features)

## Features 

### Adding Tasks: `todo`, `deadline`, `event`

Allows you to add tasks of various types (todo, deadline, event) to the list.

**Format:**\
`todo TASK [/tag TAGS]`\
`deadline TASK /by DEADLINE [/tag TAGS]`\
`event TASK /from START /to END [/tag TAGS]`

**Example:** \
`todo CS2103T iP increment Level-1`\
`deadline CS2103 UserGuide /by 22/9/2023 2359 /tag priority.HIGH`\
`event JB Trip /from 23/9/2023 0730 /to 24/9/2023 2230 /tag fun malaysia`

**Note:**\
`DEADLINE`, `START`, `END` are all fields with a specific format (d/M/yyyy hhMM).\
`[/tag TAGS]` are optional.

### Marking Tasks: `mark`, `unmark`

Allows you to mark tasks in the list as complete/incomplete.

**Format:**\
`mark INDEX`\
`unmark INDEX`

**Example:** \
`mark 1`\
`unmark 2`

**Note:**\
`INDEX` must be within the range of 1 and the number of taskings in the list (inclusive).

### Delete Tasks: `delete`

Allows you to remove tasks in the list.

**Format:**\
`delete INDEX`

**Example:** \
`delete 2`

**Note:**\
`INDEX` must be within the range of 1 and the number of taskings in the list (inclusive).

### List Tasks: `list`

Allows you to view all your tasks in the list.

**Example:** \
`list`

### Tagging Tasks: `tag`, `untag`


Allows you to assign and remove tags to various tasks.

**Format:**\
`tag INDEX TAGS`
`untag INDEX TAGS`

**Example:** \
`tag 1 test CS2100`
`untag 1 CS2100`

**Note:**\
`INDEX` must be within the range of 1 and the number of taskings in the list (inclusive).\
`TAGS` are one word tags, seperated by spaces.

### Finding Tasks: `find`

Allows you to find tasks containing a specific keyword.

**Format:**\
`find KEYWORD`

**Example:** \
`find Midterms`

**Note:**\
`KEYWORD` is case-sensitive.
