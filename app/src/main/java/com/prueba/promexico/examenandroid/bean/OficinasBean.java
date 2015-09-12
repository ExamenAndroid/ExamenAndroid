package com.prueba.promexico.examenandroid.bean;

import com.prueba.promexico.examenandroid.annotation.EntityFieldAnnotation;

/**
 * Created by BEST BUY on 12/09/2015.
 */
public class OficinasBean {

    @EntityFieldAnnotation(name = "rowid")
    private String rowId;

    @EntityFieldAnnotation(name = "idOfficce")
    private String idOfficce;

    @EntityFieldAnnotation(name = "Office")
    private String office;

    @EntityFieldAnnotation(name = "Street_number")
    private String streetNumber;

    @EntityFieldAnnotation(name = "Location")
    private String location;

    @EntityFieldAnnotation(name = "zip_code")
    private String zipCode;

    @EntityFieldAnnotation(name = "State")
    private String state;

    @EntityFieldAnnotation(name = "Country")
    private String country;

    @EntityFieldAnnotation(name = "Agent")
    private String agent;

    @EntityFieldAnnotation(name = "Position")
    private String position;

    @EntityFieldAnnotation(name = "Email")
    private String email;

    @EntityFieldAnnotation(name = "Phone")
    private String phone;

    @EntityFieldAnnotation(name = "Coordinates")
    private String coordinates;

    @EntityFieldAnnotation(name = "COffice")
    private String cOffice;


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getIdOfficce() {
        return idOfficce;
    }

    public void setIdOfficce(String idOfficce) {
        this.idOfficce = idOfficce;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getcOffice() {
        return cOffice;
    }

    public void setcOffice(String cOffice) {
        this.cOffice = cOffice;
    }

    @Override
    public String toString() {
        return getcOffice();
    }
}
