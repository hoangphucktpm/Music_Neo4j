/*
 * @(#) Album.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(value = {"artistID", "genreID", "songID"})
public class Album {
    @NonNull
    private String albumID;
    @NonNull
    private String title;
    @NonNull
    private Integer yearOfRelease;
    @NonNull
    private String downloadLink;
    @NonNull
    private Double price;

    @ToString.Exclude
    private Artist artist;
    @ToString.Exclude
    private Genre genre;
    @ToString.Exclude
    private Song song;

}
