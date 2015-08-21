for PROJECT in ../../Projects/*; do
	find "$PROJECT"  \
         -ipath "*Possible Templates*" -o \
	 -iname "*.java" -print0 \
	 | xargs -0 wc -l > "Report-$(basename $PROJECT)-Lines-Of-Code.txt";
done
