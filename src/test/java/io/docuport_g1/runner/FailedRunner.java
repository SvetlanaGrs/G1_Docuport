package io.docuport_g1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/html-reports/cucumber-report.html",
                "json:target/cucumber.json"},
        features ="@target/rerun.txt",
        glue = "io/docuport_g1/step_definitions",
        monochrome = true,
        publish = true
)



public class FailedRunner {


}
