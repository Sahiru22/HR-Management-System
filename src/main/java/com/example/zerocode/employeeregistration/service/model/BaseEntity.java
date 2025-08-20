package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<K> implements Persistable<K> {

  @CreatedDate
  protected Long createdAt;

  @CreatedBy
  protected String createdBy;

  @LastModifiedDate
  protected Long modifiedAt;

  @LastModifiedBy
  protected String modifiedBy;

  protected Long deletedAt;

  protected String deletedBy;

  @Version
  protected Integer version;

  @Transient
  public boolean isNew() {
    return version == null;
  }
}

