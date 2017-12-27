package com.yaky.betaseriefollowing.data.classes;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Shows implements Parcelable{
  @Id
  private Long id;
  @SerializedName("shows")
  @ToMany(referencedJoinProperty = "showsId")
  private List<Serie> listShow;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 1574328248)
  private transient ShowsDao myDao;


  @Generated(hash = 922051266)
  public Shows(Long id) {
      this.id = id;
  }

  @Generated(hash = 614552610)
  public Shows() {
  }


  @Override
  public String toString() {
    return "Shows{" +
        "listShow=" + listShow +
        '}';
  }

  public int size(){
    return listShow.size();
  }

  public Long getId() {
      return this.id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 887775757)
  public List<Serie> getListShow() {
      if (listShow == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          SerieDao targetDao = daoSession.getSerieDao();
          List<Serie> listShowNew = targetDao._queryShows_ListShow(id);
          synchronized (this) {
              if (listShow == null) {
                  listShow = listShowNew;
              }
          }
      }
      return listShow;
  }

  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 838886583)
  public synchronized void resetListShow() {
      listShow = null;
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
    dest.writeValue(this.id);
    dest.writeList(this.listShow);
  }

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 993163379)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getShowsDao() : null;
}

protected Shows(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.listShow = new ArrayList<Serie>();
    in.readList(this.listShow, Serie.class.getClassLoader());
  }

  public static final Creator<Shows> CREATOR = new Creator<Shows>() {
    @Override
    public Shows createFromParcel(Parcel source) {
      return new Shows(source);
    }

    @Override
    public Shows[] newArray(int size) {
      return new Shows[size];
    }
  };
}
