package cn.edu.fzu.sosd.reporter.core;

import java.util.concurrent.*;

public class Reporter {

    private final ReporterCfg cfg;
    BlockingQueue<DelayedReportTask> queue;
    ThreadPoolExecutor executor;

    public Reporter(ReporterCfg cfg) {
        this.cfg = cfg;
        this.queue = new DelayQueue<>();
        queue.add(new DelayedReportTask(this.cfg.getKeepAliveTime()));
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
                DelayedReportTask task = queue.take();
                task.run();
                queue.add(new DelayedReportTask(this.cfg.getKeepAliveTime()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class DelayedReportTask implements Delayed {
        private final long executionTime;

        DelayedReportTask(long delay) {
            this.executionTime = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = executionTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long diff = this.executionTime - ((DelayedReportTask) o).executionTime;
            return Long.compare(diff, 0);
        }

        public void run() {
            System.out.println("take reporting!" + System.currentTimeMillis());
        }
    }

}
