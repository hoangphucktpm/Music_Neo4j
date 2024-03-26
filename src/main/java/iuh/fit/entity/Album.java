/*
 * @(#) Album.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.entity;

import lombok.*;

import java.time.LocalDateTime;

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
public class Album {

    private String albumID;
    private String title;
    private int yearOfRelease;
    private String downloadLink;
    private double price;
    private Artist artist;
    private Genre genre;
    private Song song;

}
