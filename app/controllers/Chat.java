package controllers;

import java.util.List;

import models.ChatRoom;
import models.Message;
import models.User;
import play.mvc.Controller;

/**
 * 
 * チャット部屋
 *
 */
public class Chat extends Controller {

    /**
     * 
     * 入室
     */
    public static void index(String name) {

        User user = User.findById(name);
        List<Message> message = getMessages();
        ChatRoom.get().join(name);
        room(name);
    }

    public static void room(String user) {

        List events = ChatRoom.get().archive();
        render(user, events);
    }

    /**
     * 
     * 発言一覧.
     */
    public static List<Message> getMessages() {

        return Message.all().fetch(100);
    }

    /**
     * 
     * 入室しているユーザ一覧.
     */
    public static void getRoomUser() {

    }

    /**
     * 
     * 発言
     */
    public static void say(String name, String message) {

        if (message == null || message.trim().equals("")) {
            return;
        }
        User user = User.findById(name);

        new Message(user, message).save();

        room(name);
    }

    /**
     * 
     * 退出
     */
    public static void leave(String user) {

        ChatRoom.get().leave(user);
        Application.index();
    }

}
