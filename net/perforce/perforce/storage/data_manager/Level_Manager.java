package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

public class Level_Manager {

    public static int getLevel(User user) {
        return Data_Lists.Player_Stats_Level.get(user.getUuid()).getLevel();
    }

    public static void setLevel(User user, int level) {
        Data_Lists.Player_Stats_Level.get(user.getUuid()).setLevel(level);
    }

    public static void addLevel(User user, int level) {
        int amount = Data_Lists.Player_Stats_Level.get(user.getUuid()).getLevel();
        Data_Lists.Player_Stats_Level.get(user.getUuid()).setLevel(amount + level);
    }

    public static void removeLevel(User user, int level) {
        int amount = Data_Lists.Player_Stats_Level.get(user.getUuid()).getLevel();
        if (amount >= level) {
            Data_Lists.Player_Stats_Level.get(user.getUuid()).setLevel(amount - level);
        }
    }

}
