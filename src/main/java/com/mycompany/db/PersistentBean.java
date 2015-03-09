/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db;

import com.mycompany.db.entities.Doctor;
import java.util.Map;

/**
 *
 * @author Tranquility
 */
public interface PersistentBean {

    public Map<String, String> getProperties();
    
    public void populateDefaultConfigValues();
    
    public Doctor createDoctor(Doctor doctor);
    
}
