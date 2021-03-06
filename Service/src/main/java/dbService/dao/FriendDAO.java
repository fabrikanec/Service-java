package dbService.dao;

import dbService.dataSets.FriendDataSet;
import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class FriendDAO {
    private Session session;

    public FriendDAO(Session session) {
        this.session = session;
    }

    public FriendDataSet getFriend(Long id) throws HibernateException {
        Criteria criteria = session.createCriteria(FriendDataSet.class);
        return ((FriendDataSet) criteria.add(Restrictions.eq("id", id)).uniqueResult());
    }

    public Long addFriend(UsersDataSet user, UsersDataSet friend) throws HibernateException {
        return (Long) session.save(new FriendDataSet(user, friend));
    }
}