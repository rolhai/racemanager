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
package at.racemanager.api.entity;

import java.util.ArrayList;
import java.util.List;

public class TestdataBuilder {

    /**
     * create a list of drivers
     *
     * @return list of drivers
     */
    public List<Driver> getDrivers() {
        List<Driver> drivers = new ArrayList<>();

        Driver d1 = new Driver();
        d1.setId(1l);
        d1.setFirstname("Michael");
        d1.setLastname("Schumacher");
        drivers.add(d1);

        Driver d2 = new Driver();
        d1.setId(2l);
        d1.setFirstname("Mika");
        d1.setLastname("Hakkinen");
        drivers.add(d2);

        Driver d3 = new Driver();
        d3.setId(3l);
        d3.setFirstname("Damon");
        d3.setLastname("Hill");
        drivers.add(d3);

        return drivers;
    }
}
