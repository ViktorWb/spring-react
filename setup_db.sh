set -x

sudo docker kill mysql-spring-react
sudo docker rm mysql-spring-react

set -xe

# Run MySQL in Docker
sudo docker run --name mysql-spring-react -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql:latest

sleep 20

SQLCOMMAND=$(cat <<END

create database spring_react_db;
create user 'springuser'@'%' identified by 'my-spring-password';
grant all on spring_react_db.* to 'springuser'@'%';

END
)

# Create the database "spring-react-db"
sudo docker exec \
    -it mysql-spring-react mysql \
    --host=localhost \
    --port=3306 \
    --password=my-secret-pw \
    -e "$SQLCOMMAND"
