package ua.demitt.homework.hellogwthibernate.server.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.entity.User;
import ua.demitt.homework.hellogwthibernate.server.util.HibernateUtil;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

import static ua.demitt.homework.hellogwthibernate.server.util.EntityDtoConverter.convert;

public class UserDaoImpl implements UserDao {

    private static SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public UserDto findUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("select u from User u where u.login = :login").
            setParameter("login", login).
            uniqueResult();
        UserDto userDto = convert(user);

        /*//Test
        long count = (long) session.createQuery("select count(u) from User u").
            uniqueResult();
        UserDto userDto = new UserDto();
        userDto.setLogin(String.valueOf(count));
        userDto.setPassword("pass");*/

        session.getTransaction().commit();
        return userDto;
    }
}
