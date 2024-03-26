/*
 * @(#) SongDao.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.dao;

import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
public class SongDao {

    private Driver driver;
    private SessionConfig sessionConfig;

    public SongDao(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();

    }



    public void close() {
        driver.close();
    }


}
