import java.util.*;

public class AuthService {

    public static boolean login(List<User> users, String username, String password) {
        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}