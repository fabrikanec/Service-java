package dbService.dao;

import dbService.dataSets.ArticleDataSet;
import dbService.dataSets.CommentDataSet;
import dbService.dataSets.PhotoDataSet;
import dbService.dataSets.PlayListDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Blob;
import java.util.Date;

//PASSED
public class PlayListDAO {
    private Session session;

    public PlayListDAO(Session session) {
        this.session = session;
    }

    public PlayListDataSet getTrack(Long track_id) throws HibernateException {
        Criteria criteria = session.createCriteria(PlayListDataSet.class);
        return ((PlayListDataSet) criteria.add(Restrictions.eq("track_id", track_id)).uniqueResult());
    }

    public Long insertTrack(Long id, Blob track) throws HibernateException {
        return (Long) session.save(new PlayListDataSet(id, track));
    }
}