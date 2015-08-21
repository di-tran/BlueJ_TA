#!/bin/bash
gdriveFolder=$1
echo Script is disabled. Please edit and delete "--dry-run"
echo
echo
rsync -itarv --dry-run --exclude=*.ini --size-only --stats "$gdriveFolder"/ .