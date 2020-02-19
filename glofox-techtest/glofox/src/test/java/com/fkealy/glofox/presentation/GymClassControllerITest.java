package com.fkealy.glofox.presentation;

import com.fkealy.glofox.model.GymClassSeries;
import com.fkealy.glofox.persistence.GymClassRepository;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class GymClassControllerITest extends FunctionalTest {

    @Autowired
    GymClassRepository gymClassRepository;

    @Test
    public void testSaveClass201(){
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(10);
        GymClassSeries goodClass = new GymClassSeries("yoga",startDate,endDate,10);
        System.out.println(startDate.toString());

        given()
            .contentType(ContentType.JSON)
            .with()
            .body(goodClass)
        .when()
            .post("/classes")
        .then()
            .assertThat()
            .statusCode(201);
    }

    @Test
    public void testInvalidDate(){
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(-10);
        GymClassSeries badClass = new GymClassSeries("yoga",startDate,endDate,10);

        given()
            .contentType(ContentType.JSON)
            .with()
            .body(badClass)
        .when()
            .post("/classes")
            .then()
            .assertThat()
            .statusCode(400);
    }

}
