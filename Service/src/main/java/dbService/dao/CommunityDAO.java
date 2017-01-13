package dbService.dao;

import dbService.dataSets.CommentDataSet;
import dbService.dataSets.CommunityDataSet;
import dbService.dataSets.FriendDataSet;
import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CommunityDAO {
    private Session session;

    public CommunityDAO(Session session) {
        this.session = session;
    }

    public List<CommunityDataSet> getUsers(String community_name) throws HibernateException {
        Criteria criteria = session.createCriteria(CommunityDataSet.class);
        List<CommunityDataSet> res = new ArrayList<>();
        res.add((CommunityDataSet) criteria.add(Restrictions.eq("community_name", community_name)).list().get(0));
        res.add((CommunityDataSet) criteria.add(Restrictions.eq("community_name", community_name)).list().get(1));
        return res;
    }

    public Long addUser(UsersDataSet user, String communityName) throws HibernateException {
        return (Long) session.save(new CommunityDataSet(user, communityName));
    }

    public Long insertCommunity(String name) throws HibernateException {
        return (Long) session.save(new CommunityDataSet(name));
    }
}