# WeatherBot

to run the program, you need 

1 - create a file application.properties with path
src/main/resources/application.properties

and add the following data

bot.name = WeatherAlert
bot.token = //TelegramToken

#db related settings
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/tgbot
spring.datasource.username=root
spring.datasource.password=//your password 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

2 - create a database with params
![Image alt](https://github.com/NikitaKeith/WeatherBot/blob/main/Screenshot%202023-06-19%20at%2017.09.53.png)