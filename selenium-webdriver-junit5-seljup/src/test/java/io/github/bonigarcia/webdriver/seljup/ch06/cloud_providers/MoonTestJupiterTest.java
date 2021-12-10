/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.webdriver.seljup.ch06.cloud_providers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class MoonTestJupiterTest {

    @DriverUrl
    URL remoteUrl;

    @DriverCapabilities
    DesiredCapabilities capabilities;

    @BeforeEach
    void setup() throws MalformedURLException {
        String username = System.getProperty("moonUsername");
        String password = System.getProperty("moonPassword");
        String company = System.getProperty("moonCompany");

        // An alternative way to read username and password is using envs:
        // String username = System.getenv("MOON_USERNAME");
        // String password = System.getenv("MOON_PASSWORD");
        // String company = System.getenv("MOON_COMPANY");

        assumeThat(username).isNotEmpty();
        assumeThat(password).isNotEmpty();
        assumeThat(company).isNotEmpty();

        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "70.0");

        remoteUrl = new URL(
                String.format("https://%s:%s@%s.cloud.aerokube.com/wd/hub",
                        username, password, company));
    }

    @Test
    void testMoon(RemoteWebDriver driver) {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
