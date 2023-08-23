package com.md.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                HibernateConfig.class,
                TilesConfigs.class,
                SpringSecurityConfig.class


        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                WebAppContextConfig.class,
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/",
        };
    }
}
