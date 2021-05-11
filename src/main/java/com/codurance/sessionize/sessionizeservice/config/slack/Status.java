package com.codurance.sessionize.sessionizeservice.config.slack;

public class Status {

  private String status;

  public String getStatus() {
    return status;
  }

  public boolean isDown() {
    return !this.status.equals("ok");
  }
}
