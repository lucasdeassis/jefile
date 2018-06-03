package com.dotsub.lucas.jefile.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "data")
public class Data {

  @Id
  private Long metadataId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Metadata metadata;
  
  private String content;

  public Data() {
    // default constructor for Jackson
  }

  public Data(Metadata metadata, String content) {
    super();
    this.metadata = metadata;
    this.content = content;
  }

  public Long getId() {
    return metadataId;
  }

  public void setId(Long id) {
    this.metadataId = id;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((metadataId == null) ? 0 : metadataId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Data other = (Data) obj;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (metadataId == null) {
      if (other.metadataId != null)
        return false;
    } else if (!metadataId.equals(other.metadataId))
      return false;
    return true;
  }
}
