/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author jevprentice
 */
@JsonTypeName("Doctor")
@JsonSubTypes({
    @JsonSubTypes.Type(DoctorImpl.class)
})
@JsonDeserialize(as = DoctorImpl.class)
interface Doctor extends Serializable{

    @JsonProperty("medID")
    public String getMediId();

    @JsonProperty("name")
    public String getName();

    @JsonProperty("surname")
    public String getSurname();

    @JsonProperty("id")
    public UUID getId();
}
