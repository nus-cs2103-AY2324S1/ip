@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

java -classpath ..\bin Duke < .\input\mark-as-done.txt > .\actual\mark-as-done.txt
FC .\actual\mark-as-done.txt .\expected\mark-as-done.txt

java -classpath ..\bin Duke < .\input\task-type.txt > .\actual\task-type.txt
FC .\actual\task-type.txt .\expected\task-type.txt

java -classpath ..\bin Duke < .\input\add-task-error.txt > .\actual\add-task-error.txt
FC .\actual\add-task-error.txt .\expected\add-task-error.txt

java -classpath ..\bin Duke < .\input\delete-task.txt > .\actual\delete-task.txt
FC .\actual\delete-task.txt .\expected\delete-task.txt