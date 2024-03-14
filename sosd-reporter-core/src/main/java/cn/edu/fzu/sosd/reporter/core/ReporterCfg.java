package cn.edu.fzu.sosd.reporter.core;

public class ReporterCfg {
    private String serverUrl;
    private long keepAliveTime;
    private String strategy;

    public ReporterCfg(String serverUrl, long keepAliveTime, String strategy) {
        this.serverUrl = serverUrl;
        this.keepAliveTime = keepAliveTime;
        this.strategy = strategy;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public String getStrategy() {
        return strategy;
    }
}
