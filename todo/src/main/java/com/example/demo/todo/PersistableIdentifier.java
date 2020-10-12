package com.example.demo.todo;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class PersistableIdentifier<T> {

  @Id
  protected T id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PersistableIdentifier)) {
      return false;
    }
    PersistableIdentifier<?> that = (PersistableIdentifier<?>) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
