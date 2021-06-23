package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

import java.util.List;

public class Friends_Manager {

    public static boolean hasFriends(User user) {
        return Data_Lists.Player_Friends.get(user.getUuid()).getFriends().size() > 0;
    }

    public static boolean isFriends(User user, User target) {
        return Data_Lists.Player_Friends.get(user.getUuid()).getFriends().contains(target);
    }

    public static List<User> getFriends(User user) {
        return Data_Lists.Player_Friends.get(user.getUuid()).getFriends();
    }

    public static void addFriend(User user, User target) {
        if (!isFriends(user, target)) {
            // User friends
            List<User> userFriends = getFriends(user);
            userFriends.add(target);
            Data_Lists.Player_Friends.get(user.getUuid()).setFriends(userFriends);

            // targets friends
            List<User> targetFriends = getFriends(target);
            targetFriends.add(user);
            Data_Lists.Player_Friends.get(target.getUuid()).setFriends(targetFriends);
        }
    }

    public static void removeFriend(User user, User target) {
        if (isFriends(user, target)) {
            // User friends
            List<User> userFriends = getFriends(user);
            userFriends.remove(target);
            Data_Lists.Player_Friends.get(user.getUuid()).setFriends(userFriends);

            // targets friends
            List<User> targetFriends = getFriends(target);
            targetFriends.remove(user);
            Data_Lists.Player_Friends.get(target.getUuid()).setFriends(targetFriends);
        }
    }

}
