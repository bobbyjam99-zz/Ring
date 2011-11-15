package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

/**
 * 
 * ユーザクラス
 *
 */
@Entity
public class User extends Model {

    @Id
    public String name;
}
