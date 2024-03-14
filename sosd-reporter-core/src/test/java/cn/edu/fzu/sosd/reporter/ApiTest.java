package cn.edu.fzu.sosd.reporter;

import cn.edu.fzu.sosd.reporter.core.Reporter;
import cn.edu.fzu.sosd.reporter.core.ReporterCfg;
import cn.edu.fzu.sosd.reporter.core.ReporterHolder;

import java.util.concurrent.*;

public class ApiTest {

    public static void main(String[] args) {
        new ApiTest().testReporter();
    }

    void testReporter() {
        ReporterCfg cfg = new ReporterCfg("", 1000L, "");
        ReporterHolder.register(cfg);
    }

//    void testConcurrentConstructReporter() {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 600,
//                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//        ReporterCfg cfg = new ReporterCfg("", 0, "");
//
//        for (int i = 0; i < 1000; i++) {
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    ReporterHolder.register(cfg);
//                }
//            });
//        }
//    }
}
