package ex04;

class UserNotFoundException extends RuntimeException {}

public class UsersArrayList implements UsersList {

    private User[] array;

    public UsersArrayList() {
        array = new User[10];
    }

    @Override
    public void add(User user) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                array[i] = user;
                return;
            }
        }
        User[] newArray = new User[(int)(array.length * 1.5)];
        arrayCopy(newArray, array, array.length);
        newArray[array.length] = user;
        array = newArray;
    }

    @Override
    public User getById(Integer id) {
        for (User user : array) {
            if (user != null && user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(Integer index) {
        int i = 0;
        for (User user : array) {
            if (user != null) {
                if (index == i)
                    return user;
                ++i;
            }
        }
        return null;
    }

    @Override
    public Integer size() {
        Integer result = 0;
        for (User user : array) {
            if (user != null)
                ++result;
        }
        return result;
    }

    private void arrayCopy(User[] dst, User[] src, int size) {
        for (int i = 0; i < size; ++i) {
            dst[i] = src[i];
        }
    }
}
