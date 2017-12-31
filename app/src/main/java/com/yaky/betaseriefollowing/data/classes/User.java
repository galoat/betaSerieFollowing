package com.yaky.betaseriefollowing.data.classes;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class User implements Parcelable{
  @Id
  private Long id;
  private String login;
  private String password;
  @Transient
  private String token;
  @ToOne
  private Shows shows;

  @Generated(hash = 895792858)
  public User(Long id, String login, String password) {
      this.id = id;
      this.login = login;
      this.password = password;
  }

  public User(String login, String password) {
    this.login = login;
    this.password = convertPassMd5(password);
  }

  public User(String login) {
    this.login = login;
  }

  @Generated(hash = 586692638)
  public User() {
  }
  public Long getId() {
      return this.id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getLogin() {
      return this.login;
  }
  public void setLogin(String login) {
      this.login = login;
  }
  public String getPassword() {
      return this.password;
  }
  public void setPassword(String password) {
      this.password = convertPassMd5(password);
  }

  public static String convertPassMd5(String pass) {
    String password = null;
    MessageDigest mdEnc;
    try {
      mdEnc = MessageDigest.getInstance("MD5");
      mdEnc.update(pass.getBytes(), 0, pass.length());
      pass = new BigInteger(1, mdEnc.digest()).toString(16);
      while (pass.length() < 32) {
        pass = "0" + pass;
      }
      password = pass;
    } catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    }
    return password;
  }


  /** To-one relationship, resolved on first access. */
  @Generated(hash = 229653216)
  public Shows getShows() {
      if (shows != null || !shows__refreshed) {
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          ShowsDao targetDao = daoSession.getShowsDao();
          targetDao.refresh(shows);
          shows__refreshed = true;
      }
      return shows;
  }
  /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
  @Generated(hash = 901089533)
  public Shows peakShows() {
      return shows;
  }
  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 828183882)
  public void setShows(Shows shows) {
      synchronized (this) {
          this.shows = shows;
          shows__refreshed = true;
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

  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 1507654846)
  private transient UserDao myDao;
  @Generated(hash = 756019410)
  private transient boolean shows__refreshed;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

public void setEncryptedPassword(String password){
  this.password = password;
}

@Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.login);
    dest.writeString(this.password);
    dest.writeString(this.token);
    dest.writeParcelable(this.shows, flags);
  }

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2059241980)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getUserDao() : null;
}

protected User(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.login = in.readString();
    this.password = in.readString();
    this.token = in.readString();
    this.shows = in.readParcelable(Shows.class.getClassLoader());
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override
    public User createFromParcel(Parcel source) {
      return new User(source);
    }

    @Override
    public User[] newArray(int size) {
      return new User[size];
    }
  };

}
