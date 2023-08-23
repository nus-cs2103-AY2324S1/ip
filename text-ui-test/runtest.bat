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

REM run the tests and output to the actual folder
java -classpath ..\bin Duke < .\input\mark-as-done.txt > .\actual\mark-as-done.txt
java -classpath ..\bin Duke < .\input\task-type.txt > .\actual\task-type.txt

REM compare the actual outputs with the expected ones
FC .\actual\mark-as-done.txt .\expected\mark-as-done.txt
FC .\actual\task-type.txt .\expected\task-type.txt
