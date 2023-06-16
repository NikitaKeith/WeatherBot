package com.weatherbot.service;

import com.vdurmont.emoji.EmojiParser;
import com.weatherbot.config.BotConfig;
import com.weatherbot.weatherApiConnection.Request;
import com.weatherbot.weatherApiConnection.Respond;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;
    Respond respond = new Respond();
    Request request = new Request();

    public TelegramBot(BotConfig config) {
        this.config = config;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();


            switch (messageText) {
                case "/start":
                    startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                    break;
                case "/city Lichtenfels":
                    String cityName = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                    double answerToUser = (respond.getAntwortFromApi(request.infoFromApi(cityName)));
                    sendMassage(chatID,"Weather in" + cityName+ " tomorrow is "+answerToUser + " C");
                    break;
                default:sendMassage(chatID, "Sorry, the command was not recognized");
            }
        }
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    } //чтобы проверять чат получать сообщения от пользователя

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    private void startCommandReceived(long chatID, String name) {

        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you"+":blush:");
        log.info("Replied to user " + name);

        sendMassage(chatID,answer);
    }

    private void sendMassage (long chatID, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}