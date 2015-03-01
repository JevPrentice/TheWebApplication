/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.entities;

import java.io.Serializable;

/**
 *
 * @author jprentice
 */
public interface ConfigProperties extends Serializable {

    String getPropKey();

    String getPropValue();
    
    void setPropKey(String key);
    
    void setPropValue(String value);

}
