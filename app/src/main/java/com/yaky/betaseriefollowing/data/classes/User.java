package com.yaky.betaseriefollowing.data.classes;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class User implements Parcelable{
  @Id
  private Long id;
  private String login;
  private String password;
  @Generated(hash = 895792858)
  public User(Long id, String login, String password) {
      this.id = id;
      this.login = login;
      this.password = password;
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


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.login);
    dest.writeString(this.password);
  }

  protected User(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.login = in.readString();
    this.password = in.readString();
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
