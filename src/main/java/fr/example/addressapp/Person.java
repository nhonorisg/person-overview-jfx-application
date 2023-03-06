package fr.example.addressapp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final StringProperty postalCode;
    private final ObjectProperty<LocalDate> birthDay;


    /**
     * Constructeur par défaut initialisant à null les champs nom et prénom.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructeur d'un objet personne avec les valeurs noms et prénom.
     *
     * @param name nom de la personne.
     * @param surname prénom de la personne.
     */
    public Person(String name, String surname) {
        this.firstName = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(surname);

        // Dummy data for test
        this.street = new SimpleStringProperty("Street name");
        this.city = new SimpleStringProperty("City name");
        this.postalCode = new SimpleStringProperty("0000");
        this.birthDay = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
    }

    // Définition des accesseurs
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public LocalDate getBirthDay() {
        return birthDay.get();
    }

    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay.set(birthDay);
    }
}
