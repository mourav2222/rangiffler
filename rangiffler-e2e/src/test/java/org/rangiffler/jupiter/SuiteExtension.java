package org.rangiffler.jupiter;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Level;

public interface SuiteExtension extends BeforeAllCallback {

  default void beforeSuite(ExtensionContext context) {
  }

  default void afterSuite() {
  }

  @Override
  default void beforeAll(ExtensionContext context) {

      SelenideLogger.addListener("AllureSelenide",
              new AllureSelenide()
                      .screenshots(true)        // attach screenshot on failure
                      .savePageSource(true)     // attach HTML page source
                      .enableLogs(LogType.BROWSER, Level.ALL));       // optionally attach console logs


      context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).
        getOrComputeIfAbsent(this.getClass(),
            k -> {
              beforeSuite(context);
              return (ExtensionContext.Store.CloseableResource) this::afterSuite;
            }
        );

  }
}
