
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project.database_1.models;

import com.rapleaf.jack.ModelIdWrapper;

public class PostId implements ModelIdWrapper<PostId>{
  private final long id;

  public PostId(Long id){
    this.id = id;
  }

  public Long getId(){
    return Long.valueOf(this.id);
  }

  public int compareTo(PostId other){
    return this.getId().compareTo(other.getId());
  }

  public boolean equals(Object other){
    if(other instanceof PostId){
      return this.getId().equals(((PostId) other).getId());
    }
    return false;
  }

  public int hashCode(){
    return this.getId().hashCode();
  }
}
