package main.Dao;

public class UserDao extends CrudRepository<User,Long> {
    User findUsername(String username);
}
