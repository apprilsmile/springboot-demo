package com.example.demo.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 调度器.
 */
public enum Scheduler {
    SINGLETON;

    final ScheduledExecutorService scheduledExecutorService;

    Scheduler() {
        scheduledExecutorService = Executors.newScheduledThreadPool(32);
    }

    /**
     * getter for ScheduledExecutorService .
     *
     * @return value of ScheduledExecutorService
     */
    public static ScheduledExecutorService getInstance() {
        return SINGLETON.scheduledExecutorService;
    }
}
