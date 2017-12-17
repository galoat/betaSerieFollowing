package com.yaky.betaseriefollowing.data.classes;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity
public class Episode implements Parcelable {

  @Id
  private int id;
  private String title;
  private Long serieId;
  private int season;
  private int episode;
  private String description;
  private Date date;
  @ToOne
  private UserSerieInfo user;
  private String resource_url;
  @SerializedName("show")
  @ToOne
  private Show episodeInfo;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 494421999)
  private transient EpisodeDao myDao;
  @Generated(hash = 2122020312)
  private transient boolean user__refreshed;
  @Generated(hash = 1801746220)
  private transient boolean episodeInfo__refreshed;

  @Generated(hash = 1358053513)
  public Episode(int id, String title, Long serieId, int season, int episode,
          String description, Date date, String resource_url) {
      this.id = id;
      this.title = title;
      this.serieId = serieId;
      this.season = season;
      this.episode = episode;
      this.description = description;
      this.date = date;
      this.resource_url = resource_url;
  }

  @Generated(hash = 1336866052)
  public Episode() {
  }

  @Override
  public String toString() {
    return "Episode{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", serieId=" + serieId +
        ", season=" + season +
        ", episode=" + episode +
        ", description='" + description + '\'' +
        ", date=" + date +
        ", user=" + user +
        ", resource_url='" + resource_url + '\'' +
        ", episodeInfo=" + episodeInfo +
        ", daoSession=" + daoSession +
        ", myDao=" + myDao +
        ", user__refreshed=" + user__refreshed +
        ", episodeInfo__refreshed=" + episodeInfo__refreshed +
        '}';
  }

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getTitle() {
      return this.title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public Long getSerieId() {
      return this.serieId;
  }

  public void setSerieId(Long serieId) {
      this.serieId = serieId;
  }

  public int getSeason() {
      return this.season;
  }

  public void setSeason(int season) {
      this.season = season;
  }

  public int getEpisode() {
      return this.episode;
  }

  public void setEpisode(int episode) {
      this.episode = episode;
  }

  public String getDescription() {
      return this.description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public Date getDate() {
      return this.date;
  }

  public void setDate(Date date) {
      this.date = date;
  }

  public String getResource_url() {
      return this.resource_url;
  }

  public void setResource_url(String resource_url) {
      this.resource_url = resource_url;
  }

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 1947393394)
  public UserSerieInfo getUser() {
      if (user != null || !user__refreshed) {
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          UserSerieInfoDao targetDao = daoSession.getUserSerieInfoDao();
          targetDao.refresh(user);
          user__refreshed = true;
      }
      return user;
  }

  /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
  @Generated(hash = 920696363)
  public UserSerieInfo peakUser() {
      return user;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 870259318)
  public void setUser(UserSerieInfo user) {
      synchronized (this) {
          this.user = user;
          user__refreshed = true;
      }
  }

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 588311338)
  public Show getEpisodeInfo() {
      if (episodeInfo != null || !episodeInfo__refreshed) {
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          ShowDao targetDao = daoSession.getShowDao();
          targetDao.refresh(episodeInfo);
          episodeInfo__refreshed = true;
      }
      return episodeInfo;
  }

  /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
  @Generated(hash = 822679463)
  public Show peakEpisodeInfo() {
      return episodeInfo;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 2034647974)
  public void setEpisodeInfo(Show episodeInfo) {
      synchronized (this) {
          this.episodeInfo = episodeInfo;
          episodeInfo__refreshed = true;
      }
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.title);
    dest.writeValue(this.serieId);
    dest.writeInt(this.season);
    dest.writeInt(this.episode);
    dest.writeString(this.description);
    dest.writeLong(this.date != null ? this.date.getTime() : -1);
    dest.writeParcelable(this.user, flags);
    dest.writeString(this.resource_url);
    dest.writeParcelable(this.episodeInfo, flags);
  }

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 153634245)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getEpisodeDao() : null;
}

protected Episode(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    this.serieId = (Long) in.readValue(Long.class.getClassLoader());
    this.season = in.readInt();
    this.episode = in.readInt();
    this.description = in.readString();
    long tmpDate = in.readLong();
    this.date = tmpDate == -1 ? null : new Date(tmpDate);
    this.user = in.readParcelable(UserSerieInfo.class.getClassLoader());
    this.resource_url = in.readString();
    this.episodeInfo = in.readParcelable(Show.class.getClassLoader());
  }

  public static final Creator<Episode> CREATOR = new Creator<Episode>() {
    @Override
    public Episode createFromParcel(Parcel source) {
      return new Episode(source);
    }

    @Override
    public Episode[] newArray(int size) {
      return new Episode[size];
    }
  };
}
