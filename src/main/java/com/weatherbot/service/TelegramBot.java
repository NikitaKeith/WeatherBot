package com.weatherbot.service;

import com.weatherbot.config.BotConfig;
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
        String answer = "Hi, " + name + ", nice to meet you";
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