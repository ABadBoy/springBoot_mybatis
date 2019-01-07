package com.badboy.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class SimpleApolloConfigDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleApolloConfigDemo.class);

    public static final String DEFAULT_VALUE = "default";

    private Config config;

    public SimpleApolloConfigDemo() {
        ConfigChangeListener changeListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                logger.info("Changes for namespace {}", configChangeEvent.getNamespace());
                for (String key : configChangeEvent.changedKeys()) {
                    ConfigChange change = configChangeEvent.getChange(key);

                    logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                            change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                            change.getChangeType());
                }          
            }
        };
        config = ConfigService.getAppConfig();
        config.addChangeListener(changeListener);
    }

    public static void main(String[] args) throws IOException {

        SimpleApolloConfigDemo apolloConfigDemo = new SimpleApolloConfigDemo();
        System.out.println("Apollo Config Demo. Please input key to get the value. Input quit to exit.");
        while (true){
            System.out.println(">");
            String input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).readLine();

            if (input == null || input.length() == 0) {
                continue;
            }

            input = input.trim();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            apolloConfigDemo.getConfig(input);
        }
    }


    private String getConfig(String input) {

        String result = config.getProperty(input, DEFAULT_VALUE);
        logger.info(String.format("Loading key : %s with value: %s", input, result));
        return result;
    }
}
