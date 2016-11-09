title sso-service
call setEnv.bat
java -Xms512m -Xmx512m -Xss256k -XX:PermSize=128m -XX:MaxPermSize=128m  -XX:+ForceTimeHighResolution -Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSFullGCsBeforeCompaction=1 -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:+DisableExplicitGC -verbose:gc -Xloggc:sgmprobegc.txt -XX:+PrintGCDetails -XX:+PrintGCTimeStamps  com.hisign.sso.service.main.ServiceProvider

