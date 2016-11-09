#!/bin/sh
# -----------------------------------------------------------------------------
# Start/Stop Script for the Ultra NMS Probe
#

. ./setEnv.sh
$JAVA_HOME/bin/java -Xms1g -Xmx1g -Xmn256m -Xss256k -XX:PermSize=80m -XX:MaxPermSize=80m -XX:+ForceTimeHighResolution -Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSFullGCsBeforeCompaction=1 -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:+DisableExplicitGC -verbose:gc -Xloggc:sgmprobegc.txt -XX:+PrintGCDetails -XX:+PrintGCTimeStamps  -Djava.security.policy=java.policy -Djava.awt.headless=true -classpath $CLASSPATH com.hisign.sso.service.main.LogServiceProvider
