package com.example.stock_fifo_lifo;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        classes.add(HelloResource.class);
        classes.add(OutResource.class);
        classes.add(StockResource.class);
        classes.add(ItemResource.class);
        classes.add(StoreResource.class);

        return classes;
    }
}