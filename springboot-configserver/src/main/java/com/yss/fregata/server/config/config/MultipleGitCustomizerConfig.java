package com.yss.fregata.server.config.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.server.environment.MultipleJGitEnvironmentRepository;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Field;
import java.util.HashMap;

@Configuration
@ImportResource(locations = {"multicaster.xml"})
public class MultipleGitCustomizerConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String DefaultEnvironmentRepositoryBeanId = "defaultEnvironmentRepository";

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private ConfigurationPropertiesRebinder rebinder;

    @EventListener
    public void environmentChangeEventListener(EnvironmentChangeEvent event) {
        //git发生修改时才处理
        if(event.getKeys().size() <= 0) {
            return;
        }

        if (this.configurableApplicationContext != null) {
            MultipleJGitEnvironmentRepository bean = this.configurableApplicationContext.getBean(
                    MultipleJGitEnvironmentRepository.class);

            if(bean == null) {
                return;
            }

            try{
                // 清空repos
                Class ori = bean.getClass();
                Field field = ori.getDeclaredField("repos");
                field.setAccessible(true);
                field.set(bean, new HashMap<>());
            } catch (IllegalAccessException e) {
                log.error("", e);
            } catch (NoSuchFieldException e) {
                log.error("", e);
            }
            // 通过阻塞绑定multipleJGitEnvironmentProperties创建出新repo
            this.rebinder.rebind(DefaultEnvironmentRepositoryBeanId);
        }
    }
}
