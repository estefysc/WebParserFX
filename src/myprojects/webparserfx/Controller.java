package myprojects.webparserfx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Controller {
    String optionSelected;
    String urlResult;

    @FXML private Button startButton;
    @FXML private TextArea resultArea;
    @FXML private ChoiceBox jobTypeMenu;

    final String cssQuery = "tbody#search-results-content";
    final String element = "td";
    final String FACULTY_URL = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=faculty";
    final String STAFF_URL = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=usps%20(staff)";
    final String ADMIN_URL = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=administrative%20%26%20professional";
    final String FIRST_OPTION = "Faculty";
    final String SECOND_OPTION = "Staff";
    final String THIRD_OPTION = "Administrative and Professional";

    ObservableList<String> jobTypeList = FXCollections.observableArrayList(FIRST_OPTION, SECOND_OPTION, THIRD_OPTION);

    @FXML // Why @FXML is needed?
    private void initialize() {
        jobTypeMenu.setItems(jobTypeList);
        jobTypeMenu.setValue(jobTypeList.get(0));
        urlResult = FACULTY_URL;

        optionSelected = (String) jobTypeMenu.getValue();
        System.out.println(optionSelected + " default");

        jobTypeMenu.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
                    optionSelected = jobTypeList.get((Integer) newVal);
                    System.out.println(optionSelected);
                    switch (optionSelected) {
                        case FIRST_OPTION:
                            urlResult = FACULTY_URL;
                            break;
                        case SECOND_OPTION:
                            urlResult = STAFF_URL;
                            break;
                        case THIRD_OPTION:
                            urlResult = ADMIN_URL;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + optionSelected);
                    }
                    System.out.println(urlResult);
                }
        );
    } // End initialize()

    public void printJobs() {
        try {
            // IMPORTANT!! Why does IDE states  " = new ArrayList<>() " is redundant?
            List<String> listJobs;

            Document doc = Jsoup.connect(urlResult).get(); // The text from "Open until filled is not being captured here".

            // Find elements with id = "search-results-content" --- el#id: elements with ID, e.g. div#logo
            Elements table = doc.select(cssQuery); // The text from "Open until filled is not being captured here".

            // Find each td within the table
            Elements tableData = table.select(element);

            // Extract only the text from each td
            listJobs = tableData.eachText();

            resultArea.setText(listJobs.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /*
    TEMPLATES - printJobs() works fine.
    // Any access modifier besides public will throw exceptions. ??
    public void printJobs() {
        try {
            String url = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=usps%20(staff)";
            // IMPORTANT!! Why does IDE states  " = new ArrayList<>() " is redundant?
            List<String> listJobs;

            Document doc = Jsoup.connect(url).get(); // The text from "Open until filled is not being captured here".

            // Find elements with id = "search-results-content" --- el#id: elements with ID, e.g. div#logo
            Elements table = doc.select("tbody#search-results-content"); // The text from "Open until filled is not being captured here".

            // Find each td within the table
            Elements tableData = table.select("td");

            // Extract only the text from each td
            listJobs = tableData.eachText();

            // ToDo Iterate to print each element on a new line.
//            for (String e : listJobs) {
//                resultArea.appendText(e);
//            }

            resultArea.setText(listJobs.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    } // End printJobs()

    public void printFacultyJobs() { // IDE add throw exception clause here -- why?
        try {
            String facultyUrl = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=faculty";
            List<String> facultyListJobs;

            Document facultyDoc = Jsoup.connect(facultyUrl).get();
            Elements facultyTable = facultyDoc.select("tbody#search-results-content");
            Elements facultyTableData = facultyTable.select("td");
            facultyListJobs = facultyTableData.eachText();
            resultArea.setText(facultyListJobs.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    } // End printFacultyJobs

    public void printAdminJobs() {
        try {
            String adminUrl = "https://jobs.ucf.edu/en-us/filter/?search-keyword=&job-mail-subscribe-privacy=agree&work-type=administrative%20%26%20professional";
            List<String> adminListJobs;

            Document adminDoc = Jsoup.connect(adminUrl).get();
            Elements adminTable = adminDoc.select("tbody#search-results-content");
            Elements adminTableData = adminTable.select("td");
            adminListJobs = adminTableData.eachText();
            resultArea.setText(adminListJobs.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    } // End printAdminJobs
    */

} // end Controller
