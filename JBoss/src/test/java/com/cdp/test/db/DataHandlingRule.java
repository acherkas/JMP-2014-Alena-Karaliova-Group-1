package com.cdp.test.db;

import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import com.cdp.test.configuration.Configuration;
import com.cdp.test.configuration.DatabaseConfiguration;

public class DataHandlingRule extends TestWatchman {

    @Override
    public void starting(FrameworkMethod method) {
        PrepareData pc = method.getAnnotation(PrepareData.class);
        if (null == pc) {
            return;
        }
        
        DataSeeder dataSeeder = createDataSeeder(pc.value());
        try {
            dataSeeder.prepare();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finished(FrameworkMethod method) {
        PrepareData pc = method.getAnnotation(PrepareData.class);
        if (null == pc) {
            return;
        }
        
        DataSeeder dataSeeder = createDataSeeder(pc.value());
        
        try {
            dataSeeder.cleanup();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private DataSeeder createDataSeeder(String fileName) {
        DatabaseConfiguration databaseConfiguration = Configuration.instance().getDatabaseConfiguration();
        DataSeeder dataSeeder = new XmlDatasetSeeder(fileName, databaseConfiguration);
        return dataSeeder;
    }
    
}
