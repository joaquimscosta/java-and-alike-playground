package com.example.demo.todo;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends PersistableIdentifier<Long> {

  private String name;
  private boolean completed;
  private Instant createdOn;
  private Instant updatedOn;
  @ManyToOne(fetch = FetchType.LAZY)
  private Todo todo;
}
