package com.yaky.betaseriefollowing.data.classes;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserSerieInfo {
  @Id
  private int id;
  private boolean seen;
  private boolean downloaded;

  @Generated(hash = 1020520657)
  public UserSerieInfo(int id, boolean seen, boolean downloaded) {
      this.id = id;
      this.seen = seen;
      this.downloaded = downloaded;
  }

  @Generated(hash = 742822471)
  public UserSerieInfo() {
  }

  @Override
  public String toString() {
    return "UserSerieInfo{" +
        "id=" + id +
        ", seen=" + seen +
        ", downloaded=" + downloaded +
        '}';
  }

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public boolean getSeen() {
      return this.seen;
  }

  public void setSeen(boolean seen) {
      this.seen = seen;
  }

  public boolean getDownloaded() {
      return this.downloaded;
  }

  public void setDownloaded(boolean downloaded) {
      this.downloaded = downloaded;
  }
}
