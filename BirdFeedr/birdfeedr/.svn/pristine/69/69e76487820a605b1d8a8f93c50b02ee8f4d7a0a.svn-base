@echo off

rem This file will install the JAR in Windows.
rem Assuming the classes have been compiled in BlueJ.

rem Set local variables.
set outJar=%CD%\CodingPractice.jar
set tempMFFile=Manifest.mf
set mainClass=Main.class
set libPath=+libs
set extPath=%USERPROFILE%\bluej\extensions

rem Create manifest.
echo Main-Class: Main> %tempMFFile%
set CP=
for /R ./+libs %%a in (*.jar) do call :buildJARList %%~na
echo Class-Path: %CP%>> %tempMFFile%

rem Make jar
set exFileList=exercises.lst
del "%exFileList%"
for /R ..\Exercises\ %%a in (*.xml) do echo /Exercises/%%~nxa>> "%exFileList%"
jar -cfm  "%outJar%" %tempMFFile% *.class *.fxml Runner\*.class "%exFileList%"
pushd ..\
jar -uf  "%outJar%" Exercises\*
popd

rem Remove manifest.
del %tempMFFile%
del "%exFileList%"

rem Copy the jars into the extensions folder.
if not exist "%extPath%" mkdir "%extPath%"
copy /Y "%outJar%" "%extPath%"
rem XXX don't copy bluejext.jar
for /R ./+libs %%a in (*.jar) do copy "%%a" "%extPath%"


"C:\Program Files (x86)\BlueJ\BlueJ.exe"

rem Exit.
goto :EOF

rem Concatenates the path.
:buildJARList
set CP=%CP% %*.jar
goto :EOF