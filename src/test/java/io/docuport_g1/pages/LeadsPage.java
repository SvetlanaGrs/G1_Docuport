package io.docuport_g1.pages;

import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeadsPage {
    public LeadsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private static ResultSet rs;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> rows;


    public List<Map<String, String>> returnDataFromUI(String fullName, String emailAddress, String phoneNumber) {
        List<Map<String, String>> leadsListUI = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Assuming column indexes for Full Name, Email, and Phone Number
            String name = cells.get(1).getText().trim();  // Adjust index if needed
            String email = cells.get(2).getText().trim();
            String phone = cells.get(3).getText().trim();

            // Store values in a map
            Map<String, String> leadData = new HashMap<>();
            leadData.put(fullName, name);
            leadData.put(emailAddress, email);
            leadData.put(phoneNumber, phone);

            leadsListUI.add(leadData);
        }

        // Print the collected leads data
        for (Map<String, String> lead : leadsListUI) {
            System.out.println("Lead Info: " + lead);
        }

        return leadsListUI;
    }

    public List<Map<String, String>> returnDataFromDB() throws SQLException {

        List<Map<String, String>> leadsListDB = new ArrayList<>();

        DB_Utility.createConnection();
        rs = DB_Utility.runQuery("SELECT owner_first_name, contact_email_address, contact_phone_number FROM document.leads");

        while (rs.next()) {
            Map<String, String> leadData = new HashMap<>();
            leadData.put("Full Name", rs.getString("owner_first_name"));
            leadData.put("Email Address", rs.getString("contact_email_address"));
            leadData.put("Phone Number", rs.getString("contact_phone_number"));

            leadsListDB.add(leadData);
        }

        System.out.println("DB Leads Data: " + leadsListDB);

        return leadsListDB;

    }

    public void verifyLeads(List<Map<String, String>> leadsListUI, List<Map<String, String>> leadsListDB) {
        for (Map<String, String> uiLead : leadsListUI) {
            if (leadsListDB.contains(uiLead)) {
                System.out.println("✅ Lead Matched: " + uiLead);
            } else {
                System.out.println("❌ Lead MISMATCH: " + uiLead);
            }
        }
    }
}
