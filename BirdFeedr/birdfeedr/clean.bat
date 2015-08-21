for /R . %%a in (*.class) do svn del --force "%%~dpnxa"
for /R . %%a in (*.ctxt) do svn del --force "%%~dpnxa"