package solid.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserRepository class - Responsible only for data persistence operations
 * Single Responsibility: User data storage and retrieval
 */
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void save(User user) {
        users.add(user);
    }

    public Optional<User> findById(String id) {
        return users.stream()
                   .filter(user -> user.getId().equals(id))
                   .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public boolean deleteById(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return users.size();
    }
}