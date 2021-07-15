package com.mta.diemdanhandroid.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FilterExecutorsUtils {
    private static final Executor BG_EXECUTOR = Executors.newSingleThreadExecutor();

    public static void runOnBGThread(Runnable runnable) {
        BG_EXECUTOR.execute(runnable);
    }
}
