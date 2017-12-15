package com.yaky.betaseriefollowing.data.classes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Show {
  @Id
  private Long id;
  private Long thevdb_Id;
  private String title;

  @Generated(hash = 884686251)
  public Show(Long id, Long thevdb_Id, String title) {
      this.id = id;
      this.thevdb_Id = thevdb_Id;
      this.title = title;
  }

  @Generated(hash = 2080215184)
  public Show() {
  }

  @Override
  public String toString() {
    return "Show{" +
        "id=" + id +
        ", thevdb_Id=" + thevdb_Id +
        ", title='" + title + '\'' +
        '}';
  }

  public Long getId() {
      return this.id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public Long getThevdb_Id() {
      return this.thevdb_Id;
  }

  public void setThevdb_Id(Long thevdb_Id) {
      this.thevdb_Id = thevdb_Id;
  }

  public String getTitle() {
      return this.title;
  }

  public void setTitle(String title) {
      this.title = title;
  }
}
