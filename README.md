# WeatherBot

to run the program, you need 

1 - create a file application.properties with path
src/main/resources/application.properties

and add the following data

bot.name = WeatherAlert<br />
bot.token = //TelegramToken<br />

#db related settings<br />
spring.jpa.hibernate.ddl-auto=update<br />
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/tgbot<br />
spring.datasource.username=root<br />
spring.datasource.password=//your password <br />
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver<br />
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect<br />
spring.jpa.show-sql=true<br />

2 - create a database with params<br />
![Image alt](https://github.com/NikitaKeith/WeatherBot/blob/main/Screenshot%202023-06-19%20at%2017.09.53.png)
