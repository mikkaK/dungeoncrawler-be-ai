package ch.noseryoung.dungeoncrawlerai.core.generic;


import java.util.Date;

/**
 * This entity is used to extend every other entity in this program.
 * As we'd like to see the date when an entity got created this will be a base-value.
 */
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