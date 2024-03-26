/*
 * @(#) ArtistDaoTest.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;

import iuh.fit.dao.ArtistDao;
import iuh.fit.entity.Artist;
import iuh.fit.utils.AppUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArtistDaoTest {
    public static String DB_NAME = "phuc21036541";
    private ArtistDao artistDao;

    @BeforeAll
    public void setUp() {
        artistDao = new ArtistDao(AppUtils.initDriver(), DB_NAME);
    }

    // a. Them nghệ sĩ mới
    @Test
    public void testAddNewArtist() {
        LocalDate birthDate = LocalDate.of(2003, 3, 21);
        Artist artist = new Artist("Phuc005", "Phuc nè", birthDate, "phuc.com");
        artistDao.addArtist(artist);
        System.out.println("Thêm thành công");
    }


    @AfterAll
    public void tearDown() {
        artistDao.close();
    }


}
