package org.rangiffler.config;

public class LocalConfig implements Config {

  static final LocalConfig INSTANCE = new LocalConfig();

  private LocalConfig() {
  }

  @Override
  public String frontUrl() {

    return "http://127.0.0.1:8082";
    //return "http://127.0.0.1:3002";
  }

  @Override
  public String apiUrl() {
    return "http://127.0.0.1:8080";
  }

  @Override
  public String authUrl() {
    return "http://127.0.0.1:9000";
  }

  @Override
  public String apiJdbcUrl() {
    return "jdbc:mysql://127.0.0.1:3306/rangiffler-api?serverTimezone=UTC";
  }

  @Override
  public String allureDockerUrl() {

    return "http://130.162.218.209:5050";
    //return "http://allure-service.mxytin.ip-ddns.com";
    //return null;
  }
}
