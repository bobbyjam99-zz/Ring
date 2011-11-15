package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.Model;

/**
 * 
 * 発言クラス.
 *
 */
@Entity
public class Message extends Model {

    @Id
    @GeneratedValue
    public String id;

    /* ユーザ */
    public User user;

    /* 発言内容 */
    public String text;

    /* 発言日付 */
    public Date date;

    public Message(User user, String text) {

        this.user = user;
        this.text = text;
        this.date = new Date();
    }

}
