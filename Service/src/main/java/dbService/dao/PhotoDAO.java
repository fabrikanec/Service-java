package dbService.dao;

import dbService.dataSets.ArticleDataSet;
import dbService.dataSets.CommentDataSet;
import dbService.dataSets.PhotoDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Blob;
import java.util.Date;

//PASSED
public class PhotoDAO {
    private Session session;

    public PhotoDAO(Session session) {
        this.session = session;
    }

    public PhotoDataSet getPhoto(Long photo_id) throws HibernateException {
        Criteria criteria = session.createCriteria(PhotoDataSet.class);
        return ((PhotoDataSet) criteria.add(Restrictions.eq("photo_id", photo_id)).uniqueResult());
    }

    public Long insertPhoto(Long id, Blob photo) throws HibernateException {
        return (Long) session.save(new PhotoDataSet(id, photo));
    }
}