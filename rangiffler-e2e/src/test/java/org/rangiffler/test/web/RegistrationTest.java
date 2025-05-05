package org.rangiffler.test.web;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.rangiffler.api.AllureDockerApiClient;
import org.rangiffler.config.Config;
import org.rangiffler.page.WelcomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationTest {

  private static final Config CFG = Config.getInstance();
  private static final Faker faker = new Faker();
  private final Logger LOG = LoggerFactory.getLogger(RegistrationTest.class);

  @Test
  void shouldRegisterNewUser() {
    String newUsername = faker.name().username();
    String password = "12345";
    LOG.info("CFG.frontUrl: {}, CFG Class: {}", CFG.frontUrl(), CFG.getClass());
    Selenide.open(CFG.frontUrl(), WelcomePage.class)
        .register()
        .fillRegisterPage(newUsername, password, password)
        .submit()
        .login(newUsername, password)
        .checkThatPageLoaded();
  }
}
