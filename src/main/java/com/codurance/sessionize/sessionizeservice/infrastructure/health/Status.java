package com.codurance.sessionize.sessionizeservice.infrastructure.health;

public class Status {

  private String status;

  public String getStatus() {
    return status;
  }

  public boolean isDown() {
    return !this.status.equals("ok");
  }
}
