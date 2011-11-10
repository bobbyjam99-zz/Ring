package controllers;

import java.util.List;

import models.ChatRoom;
import play.mvc.Controller;

public class Chat extends Controller {

    public static void index(String user) {

        ChatRoom.get().join(user);
        room(user);
    }

    public static void room(String user) {

        List events = ChatRoom.get().archive();
        render(user, events);
    }

    public static void say(String user, String message) {

        ChatRoom.get().say(user, message);
        room(user);
    }

    public static void leave(String user) {

        ChatRoom.get().leave(user);
        Application.index();
    }

}
