/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db;

import com.mycompany.db.entities.DoctorImpl;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Tranquility
 */
@Local
public interface PersistentBean {

    public Map<String, String> getProperties();
    
    public void populateDefaultConfigValues();
    
    public DoctorImpl createDoctor(DoctorImpl doctor);
    
}
