package fr.example.addressapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postCodeLabel;
    @FXML
    private Label birthdayLabel;
    // reference to the MainApp
    private MainApp mainApp;


    /**
     * initialise les colonnes de la TableView.
     */
    @FXML
    private void initialize() {
        // initialize the person table with content inside both column.
        this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // clear Person Details
        showPersonDetails(null);

        // Listener for selection change in the tableview.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue)
        );
    }


    /**
     * This method is called the delete button is pressed.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing have been selected by the user.
            warnAlert();
        }
    }


    /**
     *
     */
    @FXML
    private void handleNewPerson() {
        Person newPers = new Person();
        boolean okClicked = mainApp.showEditDialog(newPers);

        if (okClicked) {
            mainApp.getPersonList().add(newPers);
        }
    }


    /**
     *
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPers = personTable.getSelectionModel().getSelectedItem();

        if (selectedPers != null) {
            boolean okClicked = mainApp.showEditDialog(selectedPers);

            if (okClicked) {
                showPersonDetails(selectedPers);
            }
        } else {
            warnAlert();
        }
    }


    /**
     *
     */
    private void warnAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No selection");
        alert.setHeaderText("No person selected!");
        alert.setContentText("Please select a person in the table when deleting!");

        alert.showAndWait();
    }


    /**
     * Modificateur des cellules de la TableView par les valeurs dans la personList.
     *
     * @param mainApp Application MainApp.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        this.personTable.setItems(mainApp.getPersonList());
    }


    /**
     * This method shows the details of a person in the TableView
     *
     * @param p an instance of the Person class.
     */
    private void showPersonDetails(Person p) {
        if (p != null) {
            this.firstNameLabel.setText(p.getFirstName());
            this.lastNameLabel.setText(p.getLastName());
            this.streetLabel.setText(p.getStreet());
            this.cityLabel.setText(p.getCity());
            this.postCodeLabel.setText(p.getPostalCode());
            this.birthdayLabel.setText(DateUtil.formatDate(p.getBirthDay()));
        } else {
            this.firstNameLabel.setText("");
            this.lastNameLabel.setText("");
            this.streetLabel.setText("");
            this.cityLabel.setText("");
            this.postCodeLabel.setText("");
            this.birthdayLabel.setText("");
        }
    }
}
