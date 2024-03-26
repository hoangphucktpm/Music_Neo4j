/*
 * @(#) AppUtils.java       1.0  25/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.neo4j.driver.Driver;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.net.URI;
import java.util.Map;


/*
 * @description:
 * @author: Hoang Phuc
 * @date:   25/03/2024
 * @version:    1.0
 */
public class AppUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static Driver initDriver() {
        URI uri = URI.create("neo4j://localhost:7687");
        String user = "neo4j";
        String pass = "12345678";
        return GraphDatabase.driver(uri, AuthTokens.basic(user, pass));
    }

    public static <T> T convert(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
        try {
            String json = objectMapper.writeValueAsString(properties);
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


// Tạo lệnh đưa các file csv sau lên neo4j: Album.csv, Artist.csv, Song.csv, Genre.csv
// id	title	price	yearOfRelease	downloadLink	songID	artistID	genreID
// LOAD CSV WITH HEADERS FROM 'file:///music/Album.csv' AS row
// MERGE (a:Album {albumID: row.id})
// SET a.title = row.title, a.price = toFloat(row.price), a.yearOfRelease = toInteger(row.yearOfRelease), a.downloadLink = row.downloadLink
// RETURN a

// id:String	name:String	birthDate:LocalDate	url:String
// LOAD CSV WITH HEADERS FROM 'file:///music/Artist.csv' AS row
// MERGE (b:Artist {artistID: row.id})
// SET b.name = row.name, b.birthDate = datetime(row.birthDate), b.url = row.url
// RETURN b


// id	name	description
//LOAD CSV WITH HEADERS FROM 'file:///music/Genre.csv' AS row
//MERGE (g:Genre {genreID: row.id})
//SET g.name = row.name, g.description = row.description
//RETURN g

// id	name	runtime	lyric	fileLink
//LOAD CSV WITH HEADERS FROM 'file:///music/Song.csv' AS row
//MERGE (s:Song {songID: row.id})
//SET s.name = row.name, s.runtime = row.runtime, s.lyric = row.lyric, s.fileLink = row.fileLink
//RETURN s

// Tạo các unique index cho các label: Album, Artist, Genre, Song
//CREATE CONSTRAINT unique_ablum_id FOR (album:Album) REQUIRE album.albumID IS UNIQUE;
//CREATE CONSTRAINT unique_artist_id FOR (artist:Artist) REQUIRE artist.artistID IS UNIQUE;
//CREATE CONSTRAINT unique_genre_id FOR (genre:Genre) REQUIRE genre.genreID IS UNIQUE;
//CREATE CONSTRAINT unique_song_id FOR (song:Song) REQUIRE song.songID IS UNIQUE;

// Thiết lập các relationship giữa các label: Album, Artist, Genre, Song
// LOAD CSV WITH HEADERS FROM 'file:///music/Album.csv' AS row
// MATCH (a:Album {albumID: row.id})
// MATCH (s:Song {songID: row.songID})
// MERGE (a)-[:HAVE]->(s)

// LOAD CSV WITH HEADERS FROM 'file:///music/Album.csv' AS row
// MATCH (a:Album {albumID: row.id})
// MATCH (b:Artist {artistID: row.artistID})
// MERGE (a)-[:BE_PERFORMED]->(b)

// LOAD CSV WITH HEADERS FROM 'file:///music/Album.csv' AS row
// MATCH (a:Album {albumID: row.id})
// MATCH (g:Genre {genreID: row.genreID})
// MERGE (a)-[:BELONGS_TO]->(g)

