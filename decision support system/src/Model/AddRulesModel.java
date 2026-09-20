/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author User
 */
public class AddRulesModel {
     private String patient;
     private String dCategery;
     private String dName;
     private String signs;
     private String ruleno;
     private String rule;

    public AddRulesModel(String patient, String dCategery, String dName, String signs, String ruleno, String rule) {
        this.patient = patient;
        this.dCategery = dCategery;
        this.dName = dName;
        this.signs = signs;
        this.ruleno = ruleno;
        this.rule = rule;
    }

    
    public String getdCategery() {
        return dCategery;
    }

    public void setdCategery(String dCategery) {
        this.dCategery = dCategery;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getRuleno() {
        return ruleno;
    }

    public void setRuleno(String ruleno) {
        this.ruleno = ruleno;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }





    
}
