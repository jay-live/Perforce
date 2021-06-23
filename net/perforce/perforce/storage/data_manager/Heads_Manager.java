package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

public class Heads_Manager {

    public static int getHeads(User user) {
        return Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).getHeads();
    }

    public static void setHeads(User user, int heads) {
        Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).setHeads(heads);
    }

    public static void addHeads(User user, int coins) {
        int balance = Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).getHeads();
        Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).setHeads(balance + coins);
    }

    public static void removeHeads(User user, int heads) {
        int balance = Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).getHeads();
        if (balance >= heads) {
            Data_Lists.Player_Stats_HeadsCollected.get(user.getUuid()).setHeads(balance - heads);
        }
    }

}
