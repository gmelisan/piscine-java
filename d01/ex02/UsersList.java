package ex02;

public interface UsersList {
    public void add(User user);
    public User getById(Integer id);
    public User getByIndex(Integer index);
    public Integer size();
}
