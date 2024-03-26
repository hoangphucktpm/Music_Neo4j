/*
 * @(#) AblumDao.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.dao;

import iuh.fit.entity.Album;
import iuh.fit.utils.AppUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
public class AlbumDao {
    private Driver driver;
    private SessionConfig sessionConfig;

    public AlbumDao(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();

    }

    //b. Update đơn gía cho 1 ablum nào đó khi biet mã số: updatePriceOfAblum(ablumID: String, price: double): boolean
    public boolean updatePriceOfAlbum(String albumID, double price) {
        String query = "MATCH (a:Album {albumID: $albumID}) SET a.price = $price";
        Map<String, Object> params = Map.of("albumID", albumID, "price", price);
        try (Session session = driver.session(sessionConfig)) {
            return session.writeTransaction(tx -> {
                Result result = tx.run(query, params);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
    }


    //// Tìm kiếm các album thuộc về thể loại nào đó khi biiết tên thể loại: listAlbumsByGenre(genreName: String): List<Album>
    public List<Album> listAlbumsByGenre(String name) {
        String query = "MATCH (a:Album)-[:BELONGS_TO]->(g:Genre {name: $name}) RETURN a";
        Map<String, Object> params = Map.of("name", name);
        try (Session session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query, params);
                if (!result.hasNext()) {
                    return null;
                }
                return result.stream()
                        .map(record -> {
                            Node node = record.get("a").asNode();
                            return AppUtils.convert(node, Album.class);
                        })
                        .toList();
            });
        }
    }

    // d. Thoonsg ke số album theo từng the loại,kết quả sap xep theo tên thể loại tăng dần:
    // getNumberOfAlbumsByGenre(): Map<String, Long> // key: tên thể loại; Value: số album

    public Map<String, Long> getNumberOfAlbumsByGenre() {
        String query = "MATCH (a:Album)-[:BELONGS_TO]->(g:Genre) RETURN g.name, count(a) as count ORDER BY g.name";
        try (Session session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                return result.stream()
                        .collect(Collectors.toMap(
                                record -> record.get("g.name").asString(),
                                record -> record.get("count").asLong(),
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));
            });
        }
    }


    public void close() {
        driver.close();
    }

}
