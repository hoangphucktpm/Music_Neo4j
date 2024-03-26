/*
 * @(#) AppUtils.java       1.0  25/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    private static final Gson GSON = new Gson();

    public static Driver initDriver() {
        URI uri = URI.create("neo4j://localhost:7687");
        String user = "neo4j";
        String pass = "12345678";
        return GraphDatabase.driver(uri, AuthTokens.basic(user, pass));
}

    public static <T> T convert(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
        try {
            String json = GSON.toJson(properties);
            return GSON.fromJson(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Map<String, Object> getPro(T obj) {
        String json = GSON.toJson(obj);
        return GSON.fromJson(json, new TypeToken<Map<String, Object>>(){});
    }
}




// Tạo lệnh đưa các file csv sau lên neo4j: Album.csv, Artist.csv, Song.csv, Genre.csv
// LOAD CSV WITH HEADERS FROM "file:///music/Genre.csv" AS row
//MERGE (genre:Genre {genreID: row.id})
//ON CREATE SET genre.name = row.name, genre.description = row.description
//RETURN genre

// LOAD CSV WITH HEADERS FROM "file:///music/Song.csv" AS row
//MERGE (song:Song {songID: row.id})
//ON CREATE SET song.name = row.name, song.runtime = row.runtime, song.lyric = row.lyric, song.fileLink = row.fileLink
//RETURN song

// LOAD CSV WITH HEADERS FROM "file:///music/Album.csv" AS row
//MERGE (album:Album {albumID: row.id})
//ON CREATE SET album.title = row.title, album.price = row.price, album.yearOfRelease = row.yearOfRelease, album.downloadLink = row.downloadLink, album.songID = row.songID, album.artistID = row.artistID, album.genreID = row.genreID
//RETURN album

// id	name	birthDate	url
// LOAD CSV WITH HEADERS FROM "file:///music/Artist.csv" AS row
//MERGE (artist:Artist {artistID: row.id})
//ON CREATE SET artist.name = row.name, artist.birthDate = row.birthDate, artist.url = row.url
//RETURN artist

// LOAD CSV WITH HEADERS FROM "file:///music/Album.csv" AS row
//MATCH (album:Album {albumID: row.id})
//MATCH (genre:Genre {genreID: row.genreID})
//MERGE (album) -[:BELONGS_TO]-> (genre)

// MATCH (album:Album), (song:Song)
//WHERE album.songID = song.songID
//CREATE (album) -[:HAVE]-> (song)

// MATCH (album:Album), (song:Song)
//WHERE album.songID = song.songID
//CREATE (album) -[:HAVE]-> (song)

