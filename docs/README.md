# Oreo

Oreo is your trusty canine friend that can help you track your task!

## Features 

1. Add task (todo, event, deadline)
2. Delete task
3. Modify task

### Adding task

**THE "todo" COMMAND:**

Creates a non-time sensitive task.

HOW TO USE:
"todo <description>" - creates a todo task with the specified description.

EXAMPLE USE:
"todo play with oreo" (creates a todo task called "play with oreo").

**THE "deadline" COMMAND:**

Creates a time sensitive deadline task with a specified end date time.

HOW TO USE:
"deadline <description> /by <datetime>" - creates a deadline task with description and "/by" a date time specified.

<datetime> - can either be date only, time only or both. (e.g. "1 Jan, 8am" - is allowed together or in its elements: "1 Jan", "8am").

For accepted date time formats, input "help datetime".

EXAMPLE USE:
"deadline feed oreo /by 01/01/2023, 1800" (creates a deadline task to feed oreo by 1st Jan 2023 6:00 PM)
"deadline buy oreo toy /by 1 Jan" (creates a deadline task to get oreo a toy by next occurrence of 1st Jan)
"deadline bathe oreo /by 6:30pm" (creates a deadline task to bathe oreo by next occurence of 6:30 PM)


**THE "event" COMMAND:**

Creates a time sensitive event with a specified
start and end datetime.

HOW TO USE:
"event <description> /from <datetime> /to <datetime>" - creates a deadline task with description specified by a from datetime and a to datetime

<datetime> - can either be date only, time only or both. (e.g. "1 Jan, 8am" - is allowed together or in its elements: "1 Jan", "8am"). "/to" date and time must be after "/from" date and time. Some combinations of "/from" and "/to" date times are not accepted, check user guide for more info.

For accepted date time formats, type "help datetime" to see all accepted date time formats

EXAMPLE USE:
"event playtime /from 01/01/2023, 1200 /to 6:00pm" (creates an event task for playtime on Jan 1 2023 from 12:00 PM - 6:00 PM)
"event staycation /from 1 Jan, 1200 /to Jan 3, 12:00 (creates an event for staycation from Jan 1 12:00 PM to Jan 3 2023 12:00 PM for next occurence of the of date range)
"event walk oreo /from 17:00 /to 1800" (creates an event task to walk oreo for the next occurence of 5:00 PM to 6:00 PM)

### Modifying Task

**THE "edit" COMMAND:**

same as "modify" and "update"

Opens editing mode for the task specified by the index. Task specified will appear in the text field for user to modify the task. Modified task must follow the correct syntax when modifying.

User can type "C" or "c" to exit editing mode for the task and resume normal input.

HOW TO USE:
"edit <index>" - opens editing mode on task specified in index and puts task in the text field for user to edit. User can make the necessary amends and input it to modify the specified task.
*note: index refers to the position of task in list

EXAMPLE USE:
"edit 1" (task 1: TODO: play with oreo - command will enter editing mode on task 1 and display "todo play with oreo" in the textfield for user to edit)

"c" exits editing mode without editing the task.

## How to Use

### `datetime` - Date time formating

Seems like you need help with some date time formats! Oreo knows the time well so don't worry!

ACCEPTED DATE FORMATS:
d/M/yyyy (e.g. 1/1/2023, 01-12-2023) *dashes are accepted too
d/M/yy (e.g. 1/1/23, 01-12-23) *dashes are accepted too
MMM d yyyy (e.g. Jan 1 2023) *not case sensitive
MMM d yy (e.g. Jan 1 23) *not case sensitive
d MMM yyyy (e.g. 1 Jan 2023) *not case sensitive
d MMM yy (e.g. 1 Jan 24) *not case sensitive

ACCEPTED TIME FORMATS:
HHmm (e.g. 1800)
h a (e.g. 6 pm, 6 PM)
ha (e.g. 6pm, 6PM)
HH:mm (e.g. 18:00, 18.00) *periods are accepted too
h:mm a (e.g. 6:00 pm, 6:00 PM, 6.00 PM) *periods are accepted too
h:mma (e.g. 6:00pm, 6:00PM, 6.00PM) *periods are accepted too

✓ A GOOD EXAMPLE (BOTH DATE AND TIME):
`"1 Jan 2023, 6pm"`

✗ BAD EXAMPLES:
`"1 /0 1/2023"` - unnecessary blank space
`"1 Jan 2023 6pm"` - missing comma

ADDITIONAL INFO:
1. Any combination of date time inputs. Entering date only, time only or both are valid.
2. Time after date must be separated with a ", " (e.g. 1 Jan 2023, 6pm).
3. If no date is specified, date will be set next occurance of that time.
4. For events with no date specified, date will be set based on point 3 with respect to the end time.
5. Date specified without a given year will be assigned the next instance of this date.
6. For event datetime inputs, "/to" date must be after "/from" date.
7. For event datetime inputs, an unspecified "/to" date will set "/to" date to the specified "/from" date. (this is valid: "event playtime /from 1 jan, 2pm /to 5pm")
