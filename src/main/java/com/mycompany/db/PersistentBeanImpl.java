/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db;

import com.mycompany.db.entities.ConfigProperties;
import com.mycompany.db.entities.ConfigPropertiesImpl;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author jprentice
 */
@RequestScoped
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PersistentBeanImpl implements PersistentBean {

    @Resource(name = "jdbc/ConnectionPool", lookup = "jdbc/ConnectionPool", mappedName = "jdbc/ConnectionPool", authenticationType = Resource.AuthenticationType.CONTAINER)
    DataSource ds;

    @PersistenceContext(unitName = "WEB_APP_PERSISTENCE_UNIT")
    EntityManager em;

    @Override
    public Map<String, String> getProperties() {
        return ConfigPropertiesImpl.getProperties(em);
    }

    @Override
    public void populateDefaultConfigValues() {

        ConfigProperties prop1 = new ConfigPropertiesImpl();
        prop1.setPropKey("key1");
        prop1.setPropValue("val1");

//        em.merge(prop1);

//        ConfigProperties prop2 = new ConfigPropertiesImpl();
//        prop1.setPropKey("key2");
//        prop1.setPropValue("val2");
//
//        em.persist(prop2);
    }

}
