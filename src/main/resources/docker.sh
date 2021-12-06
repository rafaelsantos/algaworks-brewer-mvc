#!/bin/bash
docker run --name brewer-db -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD -e MYSQL_DATABASE=brewer -e MYSQL_USER=$BREWER_USER -e MYSQL_PASSWORD=$BREWER_PASSWORD -p 3306:3306 -d mysql:latest