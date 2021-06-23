package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

public class Experience_Manager {

    public static int getExperience(User user) {
        return Data_Lists.Player_Stats_Level.get(user.getUuid()).getExperience();
    }

    public static void setExperience(User user, int experience) {
        Data_Lists.Player_Stats_Level.get(user.getUuid()).setExperience(experience);
    }

    public static void addExperience(User user, int experience) {
        int amount = Data_Lists.Player_Stats_Level.get(user.getUuid()).getExperience();
        Data_Lists.Player_Stats_Level.get(user.getUuid()).setExperience(amount + experience);
    }

    public static void removeExperience(User user, int experience) {
        int amount = Data_Lists.Player_Stats_Level.get(user.getUuid()).getExperience();
        if (amount >= experience) {
            Data_Lists.Player_Stats_Level.get(user.getUuid()).setExperience(amount - experience);
        }
    }

}
