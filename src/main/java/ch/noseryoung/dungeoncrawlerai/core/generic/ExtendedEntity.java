package ch.noseryoung.dungeoncrawlerai.core.generic;


import java.util.Date;

public abstract class ExtendedEntity {


  private Date createdAt;

  protected ExtendedEntity() {
    this.createdAt = new Date();
  }
  public Date getCreatedAt() {
    return createdAt;
  }

  public ExtendedEntity setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }
}