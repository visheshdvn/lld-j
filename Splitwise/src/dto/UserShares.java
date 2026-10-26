package dto;

import users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserShares {
    private float total = 0;
    private Map<User, Float> shares;

    public UserShares() {
    }

    /*
    operations
     */
    public void addUserShare(User user, Float share) {
        if (share == null) {
            shares = new HashMap<>();
        }

        total += share;
        shares.put(user, share);
    }

    public Float getShareOfUser(User user) {
        return shares.getOrDefault(user, 0F);
    }

    public Set<User> getAllMembers() {
        return shares.keySet();
    }

    public float getTotalShares() {
        return total;
    }
}
