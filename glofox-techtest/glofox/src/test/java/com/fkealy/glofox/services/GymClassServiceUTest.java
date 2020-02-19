package com.fkealy.glofox.services;


import com.fkealy.glofox.exceptions.GymClassServiceValidationException;
import com.fkealy.glofox.model.GymClassSeries;
import com.fkealy.glofox.persistence.GymClassRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymClassServiceUTest {


    @Mock
    GymClassRepository gymClassRepository;

    @InjectMocks
    GymClassService testObj = new GymClassServiceImpl();

    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = startDate.plusDays(10);

    GymClassSeries goodGymClassSeries = new GymClassSeries("yoga", startDate,endDate,20);
    GymClassSeries badDatesGymClassSeries = new GymClassSeries("yoga",startDate,startDate.minusDays(1),10);
    GymClassSeries badCapacityGymClassSeries = new GymClassSeries("yoga",startDate,endDate,-10);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_correct_data_service_saveAll_to_repository() throws GymClassServiceValidationException {
        testObj.saveGymClass(goodGymClassSeries);
        verify(gymClassRepository,times(1)).saveAll(anyIterable());
    }

    @Test(expected = GymClassServiceValidationException.class)
    public void when_bad_dates_service_throws_exception() throws GymClassServiceValidationException {
        testObj.saveGymClass(badDatesGymClassSeries);
    }

    @Test(expected = GymClassServiceValidationException.class)
    public void when_bad_capacity_service_throws_exception() throws GymClassServiceValidationException {
        testObj.saveGymClass(badCapacityGymClassSeries);
    }
}
