package ua.demitt.homework.hellogwthibernate.server.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.entity.User;
import ua.demitt.homework.hellogwthibernate.server.util.HibernateUtil;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

import static ua.demitt.homework.hellogwthibernate.server.util.EntityDtoConverter.convert;

public class UserDaoImpl implements UserDao {

    private static final Logger serverLogger = LogManager.getLogger(UserDaoImpl.class);
    private static SessionFactory sessionFactory;

    public UserDaoImpl() {
        serverLogger.info("Was loaded");
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public UserDto findUser(String login) {
        serverLogger.info("Starts. Find login \"" + login + "\"");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("select u from User u where u.login = :login").
            setParameter("login", login).
            uniqueResult();
        UserDto userDto = convert(user);

        serverLogger.info("Search result: " + (user== null ? "no user" : "user present") );

        session.getTransaction().commit();
        return userDto;
    }
}
