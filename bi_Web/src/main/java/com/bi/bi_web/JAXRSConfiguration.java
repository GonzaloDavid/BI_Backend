package com.bi.bi_web;

import com.web.service.ClienteFacadeREST;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
    
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(CorsApp.class);
         resources.add(ClienteFacadeREST.class);
        return resources;
    }
   
    
}
