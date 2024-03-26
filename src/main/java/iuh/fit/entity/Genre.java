/*
 * @(#) Genre.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package iuh.fit.entity;

import lombok.*;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genre {

    private String genreID;
    private String name;
    private String description;


}
