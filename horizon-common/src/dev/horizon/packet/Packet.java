package dev.horizon.packet;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Packet implements Serializable {

  private UUID uniqueId;

  protected Packet(final UUID uniqueId) {
    this.uniqueId = uniqueId;
  }

  protected Packet() {
    this(randomUUID());
  }

  public UUID getUniqueId() {
    return uniqueId;
  }

  public <T extends Packet> T pointAt(final UUID requestUniqueId) {
    this.uniqueId = requestUniqueId;
    //noinspection unchecked
    return (T) this;
  }

  @Override
  public boolean equals(final Object comparedObject) {
    if (this == comparedObject) {
      return true;
    }

    if (comparedObject == null || getClass() != comparedObject.getClass()) {
      return false;
    }

    final Packet packet = (Packet) comparedObject;
    return Objects.equals(uniqueId, packet.uniqueId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uniqueId);
  }
}
