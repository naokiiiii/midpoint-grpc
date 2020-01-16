package com.evolveum.midpoint.web.boot;

import com.evolveum.midpoint.gui.api.util.MidPointApplicationConfiguration;
import com.evolveum.midpoint.model.impl.security.SecurityHelper;
import com.evolveum.midpoint.util.logging.Trace;
import com.evolveum.midpoint.util.logging.TraceManager;
import com.evolveum.midpoint.web.security.MidPointApplication;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"jp.openstandia.midpoint.grpc"})
public class GrpcServerConfiguration implements MidPointApplicationConfiguration, ApplicationContextAware {
    static {
        System.out.println("GrpcServerConfiguration loaded");
    }

    private static final Trace LOGGER = TraceManager.getTrace(MidPointApplication.class);

    private static ApplicationContext applicationContext;
    private static MidPointApplication application;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    @Override
    public void init(MidPointApplication app) {
        LOGGER.info("GrpcServerConfiguration start");

        application = app;

        LOGGER.info("GrpcServerConfiguration end");
    }

    public static MidPointApplication getApplication() {
        if (application == null) {
            throw new IllegalStateException("MidPointApplication is null");
        }
        return application;
    }

    public static SecurityHelper getSecurityHelper() {
        return applicationContext.getBean(SecurityHelper.class);
    }
}