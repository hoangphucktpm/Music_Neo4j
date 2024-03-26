/*
 * @(#) AlbumDaoTest.java       1.0  26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;

import iuh.fit.dao.AlbumDao;
import iuh.fit.utils.AppUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/*
 * @description:
 * @author: Hoang Phuc
 * @date:   26/03/2024
 * @version:    1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumDaoTest {

    public static String DB_NAME = "phuc21036541";
    private AlbumDao albumDao;

    @BeforeAll
    public void setUp() {
        albumDao = new AlbumDao(AppUtils.initDriver(), DB_NAME);
    }

    //b. Cập nhật đơn giá cho 1 album nào đó khi biết mã album
    @Test
    public void testUpdateAlbumPrice() {
        albumDao.updatePriceOfAlbum("A003", 23333333);
        System.out.println("Cập nhật thành công");
    }

    // c. Tìm các album thuộc về thể loại nào đó khi biết tên thể loại
    @Test
    public void testListAlbumsByGenre() {
        albumDao.listAlbumsByGenre("Pop").forEach(System.out::println);
    }

    // d. Thoonsg ke số album theo từng the loại,kết quả sap xep theo tên thể loại tăng dần:
    @Test
    public void testCountAlbumsByGenre() {
        albumDao.getNumberOfAlbumsByGenre().forEach((k, v) -> System.out.println(k + " - " + v));
    }

    @AfterAll
    public void tearDown() {
        albumDao.close();
    }
}
