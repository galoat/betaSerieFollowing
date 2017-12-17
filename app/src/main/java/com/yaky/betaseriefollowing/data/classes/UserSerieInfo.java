package com.yaky.betaseriefollowing.data.classes;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.jar.Pack200.Packer;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserSerieInfo implements Parcelable{
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeByte(this.seen ? (byte) 1 : (byte) 0);
    dest.writeByte(this.downloaded ? (byte) 1 : (byte) 0);
  }

  protected UserSerieInfo(Parcel in) {
    this.id = in.readInt();
    this.seen = in.readByte() != 0;
    this.downloaded = in.readByte() != 0;
  }

  public static final Creator<UserSerieInfo> CREATOR = new Creator<UserSerieInfo>() {
    @Override
    public UserSerieInfo createFromParcel(Parcel source) {
      return new UserSerieInfo(source);
    }

    @Override
    public UserSerieInfo[] newArray(int size) {
      return new UserSerieInfo[size];
    }
  };
}
