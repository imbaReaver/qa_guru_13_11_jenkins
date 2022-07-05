package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.components.Attachments;

public class TestBase {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        String browser = System.getProperty("browser","chrome");
        String version = System.getProperty("version","100");
        String size = System.getProperty("browserSize", "1920x1080");

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.browserSize = size;
        Configuration.remote = String.format("https://%s:%s@%s", config.login(), config.password(), config.urlRemoteSelenoid());
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }
}
