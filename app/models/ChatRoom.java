package models;

import java.util.Date;
import java.util.List;

import play.db.jpa.Model;
import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;

/**
 * 
 * チャット部屋
 *
 */
public class ChatRoom {

    final ArchivedEventStream<ChatRoom.Event> chatEvents = new ArchivedEventStream<ChatRoom.Event>(
        100);

    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<ChatRoom.Event> join(String user) {

        chatEvents.publish(new Join(user));
        return chatEvents.eventStream();
    }

    /**
     * A user leave the room
     */
    public void leave(String user) {

        chatEvents.publish(new Leave(user));
    }

    /**
     * For active refresh, we need to retrieve the whole message archive at
     * each refresh
     */
    public List<ChatRoom.Event> archive() {

        return chatEvents.archive();
    }

    public abstract class Event extends Model {

        final public String type;

        final public Date date;

        public Event(String type) {

            this.type = type;
            this.date = new Date();
        }

    }

    public class Join extends Event {

        final public String user;

        public Join(String user) {

            super("join");
            this.user = user;
        }

    }

    public class Leave extends Event {

        final public String user;

        public Leave(String user) {

            super("leave");
            this.user = user;
        }

    }

    static ChatRoom instance = null;

    public static ChatRoom get() {

        if (instance == null) {
            instance = new ChatRoom();
        }
        return instance;
    }

}
