package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {

        render();
    }

    public static void enter(@Required String user) {

        if (validation.hasErrors()) {
            flash.error("Please choose a nick name…");
            index();
        }

        Chat.index(user);
    }
}