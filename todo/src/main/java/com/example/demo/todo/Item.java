package com.example.demo.todo;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

  @Id
  private Long id;
  private String name;
  private boolean completed;
  private Instant createdOn;
  private Instant updatedOn;
  @ManyToOne(fetch = FetchType.LAZY)
  private Todo todo;
}
