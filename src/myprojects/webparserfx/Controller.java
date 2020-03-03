package myprojects.webparserfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML private Button startButton;
    @FXML private TextArea resultArea;

    // Any access modifier besides public will throw exceptions. ??
    /*
    public void testPrintText() {
        resultArea.setText("Button pressed.");
    }
    */

    public void printJobs() {
        try {
            String url = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=usps%20(staff)";
            Document doc = Jsoup.connect(url).get(); // The text from "Open until filled is not being captured here".

            // IMPORTANT!! Why does IDE states  " = new ArrayList<>() " is redundant?
            List<String> listJobs;

            // Find elements with id = "search-results-content" --- el#id: elements with ID, e.g. div#logo
            Elements table = doc.select("tbody#search-results-content"); // The text from "Open until filled is not being captured here".

            // Find each td within the table
            Elements tableData = table.select("td");

            // Extract only the text from each td
            listJobs = tableData.eachText();

            // TODO Iterate to print each element on a new line.
//            for (String e : listJobs) {
//                resultArea.setText(e);
//            }

            resultArea.setText(listJobs.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
} // end Controller
