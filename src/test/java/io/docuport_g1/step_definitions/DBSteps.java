package io.docuport_g1.step_definitions;

import io.cucumber.java.en.*;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DB_Utility;
import org.junit.Assert;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DBSteps {

    List<String> actualUserId;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        String url = ConfigurationReader.getProperties("docuportDbUrl");
        String username = ConfigurationReader.getProperties("docuportDbUsername");
        String password = ConfigurationReader.getProperties("docuportDbPassword");

        DB_Utility.createConnection(url, username, password);
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String sql = "select id from document.users";
        DB_Utility.runQuery(sql);
        actualUserId = DB_Utility.getColumnDataAsList("id");
        System.out.println("actualUserId = " + actualUserId);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        Set<String> removingDup = new LinkedHashSet<>(actualUserId);
        Assert.assertEquals(actualUserId.size(), removingDup.size());

    }
}