package cn.edu.fzu.sosd.reporter.core.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpReportTask implements Delayed{
    private final long executionTime;

    public HttpReportTask(long delay) {
        this.executionTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = executionTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long diff = this.executionTime - ((HttpReportTask) o).executionTime;
        return Long.compare(diff, 0);
    }

    public void run() {
        System.out.println("take reporting!" + System.currentTimeMillis());
    }
}
