package com.weatherbot.service;

import com.vdurmont.emoji.EmojiParser;
import com.weatherbot.config.BotConfig;
import com.weatherbot.model.User;
import com.weatherbot.model.UserRepository;
import com.weatherbot.weatherApiConnection.Request;
import com.weatherbot.weatherApiConnection.Respond;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;

import static org.json.JSONObject.doubleToString;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private UserRepository userRepository;
    final BotConfig config;
    Respond respond = new Respond();
    Request request = new Request();
    private String cityName;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatID;
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            chatID = update.getMessage().getChatId();

            switch (messageText) {
                case "/start": {
                    sendMassage(chatID, "Please enter the city");
                    startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                }
                break;
                case "/save":
                    registerUser(update.getMessage());
                    break;

                default: {
                    cityName = String.valueOf(update.getMessage());
                    double answerToUser = (respond.getAnswerFromApi(request.infoFromApi(cityName)));
                    sendMassage(chatID, "Weather in" + cityName + " tomorrow is " + answerToUser + " C");
                }
                break;
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    } //чтобы проверять чат получать сообщения от пользователя


    private void startCommandReceived(long chatID, String name) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you" + ":blush:");
        log.info("Replied to user " + name);
        sendMassage(chatID, answer);
    }

    @SneakyThrows
    private void sendMassage(long chatID, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(textToSend);
        execute(message);
    }

    private void registerUser(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {

            var chatId = message.getChatId();
            var chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setUsersCity(cityName);
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            log.info("user saved: " + user);
        }
    }

    //  @Scheduled(cron = "${cron.scheduler}")
    private void sendWeather() {
        var users = userRepository.findAll();
        for (User user : users) {
            sendMassage(user.getChatId(), doubleToString(respond.getAnswerFromApi(request.infoFromApi(cityName))));
        }
    }
}