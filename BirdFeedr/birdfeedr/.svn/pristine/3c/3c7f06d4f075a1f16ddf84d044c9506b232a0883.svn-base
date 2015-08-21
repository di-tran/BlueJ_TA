@echo off

rem STOP! Please use BlueJ to compile the project.
rem This script serves only as a backup and should not be used.

rem This file will compile *.java files into *.class files.
rem Construct class path with the JARs.

pushd C:\Program Files\Java\jdk1.7*
set JDKBIN=%CD%\bin
set PATH=%JDKBIN%;%PATH%
popd
pushd +libs
SET LIBS=%CD%
popd
set BASE=%CD%
set BlueJDir=C:\Program Files (x86)\BlueJ

rem javac -cp %cp% *.java Runner\*.java
for /R . %%a in (*.java) do (
    javac -cp "%BASE%";"%LIBS%\*";"%BlueJDir%\lib\*" "%%a"
)
IF %ERRORLEVEL% NEQ 0 GOTO :EOF

