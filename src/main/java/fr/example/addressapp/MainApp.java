package fr.example.addressapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personList = FXCollections.observableArrayList();


    /**
     * Constructeur initialisant la liste des personnes.
     */
    public MainApp() {
        personList.add(new Person("Hans", "Muster"));
        personList.add(new Person("Ruth", "Mueller"));
        personList.add(new Person("Heinz", "Kurz"));
        personList.add(new Person("Cornelia", "Meier"));
        personList.add(new Person("Werner", "Meyer"));
        personList.add(new Person("Lydia", "Kunz"));
        personList.add(new Person("Anna", "Best"));
        personList.add(new Person("Stefan", "Meier"));
        personList.add(new Person("Martin", "Mueller"));
    }


    /**
     * Accesseur en lecture de la valeur du stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Accesseur en lecture du champ liste des personnes.
     *
     * @return La liste des personnes.
     */
    public ObservableList<Person> getPersonList() {
        return personList;
    }


    /**
     * Définition de la scène principale de l'interface graphique.
     *
     * @param primaryStage scène de l'interface.
     * @throws IOException propagation de l'exception de la méthode shoPersonOverview.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My First JFX application");

        initRootLayout();
        showPersonOverview();
    }

    /**
     * Initialisation of the root layout.
     */
    public void initRootLayout() {
        try {
            // loading the RootLayout.fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            this.rootLayout = loader.load();

            // showing the scene that contains the root layout
            Scene scene = new Scene(this.rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chargement du fichier PersonOverview.fxml et ajout de ce dernier
     * au centre du composant root layout.
     *
     * @throws IOException thrown exception when the fxml file is not found
     */
    public void showPersonOverview() throws IOException {
        // loading PersonOverview.fxml file
        FXMLLoader pLoader = new FXMLLoader(MainApp.class.getResource("PersonOverview.fxml"));
        AnchorPane pOverview = pLoader.load();

        // adding the anchor pane to the center of the root layout
        this.rootLayout.setCenter(pOverview);

        // Connecting the MainApp to the PersonOverviewController
        PersonOverviewController personCtrller = pLoader.getController();
        personCtrller.setMainApp(this);
    }


    /**
     *
     */
    public boolean showEditDialog(Person p) {
        try {
            FXMLLoader dialogLoader = new FXMLLoader(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane dialogPane = dialogLoader.load();

            // creating the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit the details of a person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene dialogScene = new Scene(dialogPane);
            dialogStage.setScene(dialogScene);

            // set the stage and the person in the PersonEditDialogController
            PersonEditDialogController ctrer = dialogLoader.getController();
            ctrer.setDialogStage(dialogStage);
            ctrer.setPerson(p);

            dialogStage.showAndWait();

            return  ctrer.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Fonction principale
     *
     * @param args Paramètres
     */
    public static void main(String[] args) {
        launch(args);
    }
}
