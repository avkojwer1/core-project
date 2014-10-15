package com.labillusion.core.platform.runtime;

/**
 * Created by greg.chen on 14-10-15.
 */
public class RuntimeSettings {

    private static final RuntimeSettings INSTANCE = new RuntimeSettings();

    public static RuntimeSettings get() {
        return INSTANCE;
    }

    private RuntimeEnvironment environment = RuntimeEnvironment.DEV;

    public RuntimeEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(RuntimeEnvironment environment) {
        this.environment = environment;
    }
}
