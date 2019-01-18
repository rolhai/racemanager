/*
 * Copyright 2018 rolhai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.racemanager.batch;

import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author rolhai
 */
@Singleton
public class CounterBatchTimer {

    private static final Logger logger = Logger.getLogger(CounterBatchTimer.class.getName());

    @PostConstruct
    public void init() {
        logger.info("CounterBatchTimer.init");
    }

    //@Schedule(hour = "17", minute = "20", second = "0")
    public void start() {
        logger.info("CounterBatchTimer.counterJob");
        BatchRuntime.getJobOperator().start("counterJob", new Properties());
    }

    @Schedule(hour = "*", minute = "*", second = "2")
    public void everySeconds() {
        logger.info("CounterBatchTimer.everySeconds");
    }

}
