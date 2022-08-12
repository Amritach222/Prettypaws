package com.codewithamrit.myapplication.GetterSetter;

public class ModalVeterinary {
    public String vet_name;
    public String vet_address;
    public String vet_contact;

    public String getVet_name() {
        return vet_name;
    }

    public void setVet_name(String vet_name) {
        this.vet_name = vet_name;
    }

    public String getVet_address() {
        return vet_address;
    }

    public void setVet_address(String vet_address) {
        this.vet_address = vet_address;
    }

    public String getVet_contact() {
        return vet_contact;
    }

    public void setVet_contact(String vet_contact) {
        this.vet_contact = vet_contact;
    }

    public ModalVeterinary(String vet_name, String vet_address, String vet_contact) {
        this.vet_name = vet_name;
        this.vet_address = vet_address;
        this.vet_contact = vet_contact;
    }
}
