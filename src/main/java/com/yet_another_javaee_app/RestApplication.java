/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yet_another_javaee_app;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jev Prentice
 */
@ApplicationPath("api")
public class RestApplication extends Application implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        final String disableMoxy = CommonProperties.MOXY_JSON_FEATURE_DISABLE + '.'
                + context.getConfiguration().getRuntimeType().name().toLowerCase();
        context.property(disableMoxy, true);
        context.register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        return true;
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        myAddRestResourceClasses(classes);
        classes.add(this.getClass());
        return classes;
    }

    private void myAddRestResourceClasses(final Set<Class<?>> resources) {
        resources.add(JacksonFeature.class);
        resources.add(com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper.class);
        resources.add(com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper.class);
        resources.add(com.yet_another_javaee_app.RestService.class);
    }
}
