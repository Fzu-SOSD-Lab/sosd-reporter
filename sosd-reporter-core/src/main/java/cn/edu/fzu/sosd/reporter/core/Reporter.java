package cn.edu.fzu.sosd.reporter.core;

import cn.edu.fzu.sosd.reporter.core.task.HttpReportTask;

import java.util.concurrent.*;

public class Reporter {

    private final ReporterCfg cfg;
    BlockingQueue<HttpReportTask> queue;
    ThreadPoolExecutor executor;

    public Reporter(ReporterCfg cfg) {
        this.cfg = cfg;
        this.queue = new DelayQueue<>();
        queue.add(new HttpReportTask(this.cfg.getKeepAliveTime()));
        this.executor = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        setTimer();
    }

    public ReporterCfg getCfg() {
        return this.cfg;
    }

    private void setTimer() {
        executor.execute(this::report);
    }

    private void report() {
        while (true) {
            try {
                HttpReportTask task = queue.take();
                task.run();
                queue.add(new HttpReportTask(this.cfg.getKeepAliveTime()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
