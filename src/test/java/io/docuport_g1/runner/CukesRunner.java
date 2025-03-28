package io.docuport_g1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {//"pretty",
                  "html:target/html-reports/cucumber-report.html",
                  "json:target/json-reports/json-report.json",
                  "rerun:target/rerun.txt",
                  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/features",
        glue = "io/docuport_g1/step_definitions",
        dryRun = false,
        tags = "@smoke",
        monochrome = true
)

public class CukesRunner {

}
