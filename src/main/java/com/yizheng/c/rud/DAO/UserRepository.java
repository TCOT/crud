package com.yizheng.c.rud.DAO;

import com.yizheng.c.rud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**分层就是把一组同样功能的封装放一起。
 * 每个模块间的耦合性(Coupling)更小，代码独立度更高，方便代码的日常维护
 * DAO = Data Access Object = 数据存取对象
 * DAO并不需要和数据库有什么必然的联系，DAO只是数据存取对象的缩写.
 * 所以只要是把数据持久化包装成一个对象的访问（读写），这种对象都可以被称之为DAO
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    List<User> findByUsernameLike(String username);
}
