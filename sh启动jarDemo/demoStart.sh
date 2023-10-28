#!/bin/bash

# 指定 Java 可执行文件路径
JAVA_EXECUTABLE="/home/zy_/Downloads/jdk1.8.0_192/bin/java"

# 指定 JAR 文件的路径
JAR_FILE="/home/zy_/Desktop/demo1-0.0.1-SNAPSHOT.jar"

#设置 JAVA 虚拟机参数
JAVA_OPTS="-Xmx512m -Xms256m"

#启动
$JAVA_EXECUTABLE $JAVA_OPTS -jar $JAR_FILE
