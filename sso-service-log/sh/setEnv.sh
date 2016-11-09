# -----------------------------------------------------------------------------
#  Set CLASSPATH and Java options
#
# -----------------------------------------------------------------------------

#set JAVA_HOME & APP_HOME here
#JAVA_HOME=/usr/java/jdk1.6.0_33;export JAVA_HOME
. ../../env.sh
APP_HOME=..;export APP_HOME

# Make sure prerequisite environment variables are set
if [ -z "$JAVA_HOME" ]; then
  echo "The JAVA_HOME environment variable is not defined"
  echo "This environment variable is needed to run this program"
  exit 1
fi
if [ -z "$APP_HOME" ]; then
  echo "The APP_HOME environment variable is not defined"
  echo "This environment variable is needed to run this program"
  exit 1
fi
if [ ! -r "$APP_HOME"/bin/setEnv.sh ]; then
  echo "The APP_HOME environment variable is not defined correctly"
  echo "This environment variable is needed to run this program"
  exit 1
fi

# Set standard CLASSPATH
CLASSPATH="$JAVA_HOME"/jre/lib:"$APP_HOME"/bin:"$APP_HOME"/conf:"$APP_HOME"/lib

# Append jars to CLASSPATH
if [ -d "$APP_HOME"/lib ]; then
  for i in "$APP_HOME"/lib/*.jar; do
    CLASSPATH="$CLASSPATH":"$i"
  done
fi
