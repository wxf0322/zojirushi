package org.clover.zojirushi.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {

    private Logger logger = LoggerFactory.getLogger(TimerTask.class);

    @Scheduled(cron="0 0/2 * * * ?")
    public void run() {
        logger.info("TimerTask Testing...");
    }

}
