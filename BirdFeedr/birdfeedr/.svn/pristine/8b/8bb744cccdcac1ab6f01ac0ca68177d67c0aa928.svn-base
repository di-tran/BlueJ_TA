@echo off

pushd +libs
set libsFolder=%CD%\
popd

for /R . %%a in (*.class *.jar *.ctxt) do (
    if NOT "%libsFolder%" == "%%~dpa" del "%%a"
)

pushd "%USERPROFILE%\bluej\extensions"
del *.jar ..\bluej-debuglog.txt
popd