/*
 * @(#) ArtistDao.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.dao;

import iuh.fit.entity.Artist;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;

import java.util.Map;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
public class ArtistDao {
    private Driver driver;
    private SessionConfig sessionConfig;

    public ArtistDao(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();

    }

    // a. Thêm 1 nghệ sĩ mới: addArtist(artist: Artist): boolean
    public boolean addArtist(Artist artist) {
        String query = "CREATE (a:Artist {artistID: $artistID, name: $name, birthDate: $birthDate, url: $url})";
        Map<String, Object> params = Map.of("artistID", artist.getArtistID(), "name", artist.getName(), "birthDate", artist.getBirthDate(), "url", artist.getUrl());
        try (Session session = driver.session(sessionConfig)) {
            return session.writeTransaction(tx -> {
                Result result = tx.run(query, params);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
    }



    public void close() {
        driver.close();
    }
}
