package io.docuport_g1.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hook {

    private static final Logger LOG = LogManager.getLogger();
    public static WebDriver driver;



    @Before
    public void setUp(Scenario scenario){
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
        LOG.info(".......................START AUTOMATION......................Group_1");
    }

    @After
    public void tearDown(Scenario scenario){
        // only takes screenshot when a scenario is failed
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        LOG.info(".......................END AUTOMATION........................Group_1");
       Driver.closeDriver();
    }

   //@AfterStep
    public void takeScreenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }

    @Before("@docuportDb")
    public void setUpDB(){
        String url = ConfigurationReader.getProperties("docuportDbUrl");
        String username = ConfigurationReader.getProperties("docuportDbUsername");
        String password = ConfigurationReader.getProperties("docuportDbPassword");

        DB_Utility.createConnection(url, username, password);
    }

    @After("@docuportDb")
    public void closeDBConn (){
        DB_Utility.destroy();
    }

}

