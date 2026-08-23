#!/bin/sh
set -e

CONNECT_URL="http://connect:8083"
CONNECTOR_FILE="/connectors/mariadb-connector.json"

echo "Waiting for Kafka Connect to be ready at ${CONNECT_URL}..."

until curl -s "${CONNECT_URL}/connector-plugins" | grep -q "MySqlConnector"; do
    sleep 2
done

echo "Kafka Connect is ready."
echo "Registering Debezium MariaDB connector..."

curl -i -X POST \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    --data "@${CONNECTOR_FILE}" \
    "${CONNECT_URL}/connectors"

echo
echo "Current connectors:"

curl -s "${CONNECT_URL}/connectors"

echo