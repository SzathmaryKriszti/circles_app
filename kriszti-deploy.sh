#!/bin/bash
#UPDATE THESE LINES ACCORDING TO YOUR PROJECT STRUCTURE
PRIVATE_KEY_PATH=/Users/kriszti/Documents/keys/krisztina.pem
SITE_DOMAIN=circles.kriszti.app
SERVER_USER=ubuntu
FRONTEND_DIR="./frontend"
BACKEND_BUILD_DIR="./target"
FRONTEND_BUILD_DIR="$FRONTEND_DIR/dist/frontend"
REMOTE_FRONTEND_DIR="~/frontend"
REMOTE_BACKEND_DIR="~"
REMOTE_STARTUP_SCRIPT="~/startup.sh"
REMOTE_SHUTDOWN_SCRIPT="~/shutdown.sh"
#Print all commands to console during execution
set -x
#Same but needed... set verbose mode...
set -v
#All or nothing, if anything fails, stop execution
set -e
cd ${FRONTEND_DIR}
export NODE_OPTIONS=--openssl-legacy-provider
ng build
cd ..
mvn clean package -DskipTests=true
## Get backend filename
JAR_FILE_NAME='circles-app-1.0-SNAPSHOT.jar'
# Kill running process on server
ssh -i ${PRIVATE_KEY_PATH} ${SERVER_USER}@${SITE_DOMAIN} "$REMOTE_SHUTDOWN_SCRIPT $REMOTE_BACKEND_DIR/$JAR_FILE_NAME"
# Delete all existing frontend files
ssh -i ${PRIVATE_KEY_PATH} ${SERVER_USER}@${SITE_DOMAIN} "sudo rm -rf $REMOTE_FRONTEND_DIR/*"
# Copy frontend files
scp -i ${PRIVATE_KEY_PATH} -r ${FRONTEND_BUILD_DIR}/* ${SERVER_USER}@${SITE_DOMAIN}:$REMOTE_FRONTEND_DIR
# Copy backend files
scp -i ${PRIVATE_KEY_PATH} -r ${BACKEND_BUILD_DIR}/$JAR_FILE_NAME ${SERVER_USER}@${SITE_DOMAIN}:$REMOTE_BACKEND_DIR
# Start backend server
ssh -i ${PRIVATE_KEY_PATH} ${SERVER_USER}@${SITE_DOMAIN} "$REMOTE_STARTUP_SCRIPT $REMOTE_BACKEND_DIR/$JAR_FILE_NAME"