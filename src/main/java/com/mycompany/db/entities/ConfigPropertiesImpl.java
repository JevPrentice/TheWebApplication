/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author jprentice
 */
@Entity
@Table(schema = "public", name = "properties")
@NamedQueries({
    @NamedQuery(name = "ConfigProperties.getAll", query = "select p from ConfigPropertiesImpl p")
})
public class ConfigPropertiesImpl implements ConfigProperties {

    @Id
    String propKey;

    String propValue;

    @Override
    public String getPropKey() {
        return propKey;
    }

    @Override
    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getPropValue() {
        return propValue;
    }

    @Override
    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public static Map<String, String> getProperties(EntityManager em) {
        Map<String, String> propMap = new HashMap<>();
        Query query = em.createNamedQuery("ConfigProperties.getAll");
        List<ConfigProperties> list = query.getResultList();
        for (ConfigProperties prop : list) {
            propMap.put(prop.getPropKey(), prop.getPropValue());
        }
        return propMap;
    }
}
