package dbService.dao;

import dbService.dataSets.ArticleDataSet;
import dbService.dataSets.CommentDataSet;
import dbService.dataSets.PhotoDataSet;
import dbService.dataSets.VideoDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Blob;
import java.util.Date;

//PASSED
public class VideoDAO {
    private Session session;

    public VideoDAO(Session session) {
        this.session = session;
    }

    public VideoDataSet getVideo(Long video_id) throws HibernateException {
        Criteria criteria = session.createCriteria(PhotoDataSet.class);
        return ((VideoDataSet) criteria.add(Restrictions.eq("video_id", video_id)).uniqueResult());
    }

    public Long insertVideo(Long id, Blob video) throws HibernateException {
        return (Long) session.save(new VideoDataSet(id, video));
    }
}