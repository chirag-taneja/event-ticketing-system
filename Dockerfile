FROM ubuntu:latest
LABEL authors="chirag"

ENTRYPOINT ["top", "-b"]