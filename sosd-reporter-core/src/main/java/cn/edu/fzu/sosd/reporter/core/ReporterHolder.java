package cn.edu.fzu.sosd.reporter.core;

public class ReporterHolder {

    private static volatile Reporter instance;

    public static synchronized void register(ReporterCfg cfg) {
        if (instance == null) {
            synchronized (ReporterHolder.class) {
                if (instance == null) {
                    instance = new Reporter(cfg);
                }
            }
        }
    }

    public static synchronized Reporter getInstance() {
        return instance;
    }
}
