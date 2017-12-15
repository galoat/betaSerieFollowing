package com.yaky.betaseriefollowing.data.classes;


import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.DaoException;

@Entity
public class Serie {
  @Id
  private Long id;
  private Long showsId;
  private Long thetvdb_id;
  private String imdb_id;
  private String title;
  private int remaining;
  @SerializedName("unseen")
  @ToMany(referencedJoinProperty = "serieId")
  private List<Episode> episode;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 566542788)
  private transient SerieDao myDao;

  public Episode first(){
    return episode.get(0);
  }

  @Generated(hash = 676502441)
  public Serie(Long id, Long showsId, Long thetvdb_id, String imdb_id,
          String title, int remaining) {
      this.id = id;
      this.showsId = showsId;
      this.thetvdb_id = thetvdb_id;
      this.imdb_id = imdb_id;
      this.title = title;
      this.remaining = remaining;
  }

  @Generated(hash = 1219348851)
  public Serie() {
  }

@Override
  public String toString() {
    return "Serie{" +
        "id=" + id +
        ", thetvdb_id=" + thetvdb_id +
        ", imdb_id='" + imdb_id + '\'' +
        ", title='" + title + '\'' +
        ", remaining=" + remaining +
        ", episode=" + episode +
        '}';
  }

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getShowsId() {
    return this.showsId;
}

public void setShowsId(Long showsId) {
    this.showsId = showsId;
}

public Long getThetvdb_id() {
    return this.thetvdb_id;
}

public void setThetvdb_id(Long thetvdb_id) {
    this.thetvdb_id = thetvdb_id;
}

public String getImdb_id() {
    return this.imdb_id;
}

public void setImdb_id(String imdb_id) {
    this.imdb_id = imdb_id;
}

public String getTitle() {
    return this.title;
}

public void setTitle(String title) {
    this.title = title;
}

public int getRemaining() {
    return this.remaining;
}

public void setRemaining(int remaining) {
    this.remaining = remaining;
}

/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 357605921)
public List<Episode> getEpisode() {
    if (episode == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        EpisodeDao targetDao = daoSession.getEpisodeDao();
        List<Episode> episodeNew = targetDao._querySerie_Episode(id);
        synchronized (this) {
            if (episode == null) {
                episode = episodeNew;
            }
        }
    }
    return episode;
}

/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 978067064)
public synchronized void resetEpisode() {
    episode = null;
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 962227575)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getSerieDao() : null;
}

}
