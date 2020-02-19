package com.fkealy.glofox.services;

import com.fkealy.glofox.exceptions.BookingServiceException;
import com.fkealy.glofox.model.Booking;
import com.fkealy.glofox.model.GymClass;
import com.fkealy.glofox.persistence.GymClassRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceUTest {

    @Mock
    GymClassRepository gymClassRepository;

    @InjectMocks
    BookingService bookingService = new BookingServiceImpl();


    ArgumentCaptor<GymClass> acGymClass = ArgumentCaptor.forClass(GymClass.class);

    private static final int capacity = 10;
    private static final String className = "yoga";

    private static final LocalDateTime now = LocalDateTime.now();
    private Booking goodBooking = new Booking("freddie",now);
    private Booking badBooking = new Booking("freddie",now.minusDays(1));
    private GymClass gymClass;


    @Before
    public void init(){
        gymClass = new GymClass(className, capacity, now);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_good_booking_minus_one_capacity_and_save_reduced_capacity() throws BookingServiceException {
        when(gymClassRepository.findAll()).thenReturn(Collections.singletonList(gymClass));
        bookingService.bookClass(goodBooking);
        verify(gymClassRepository, times(1)).save(acGymClass.capture());
        assertEquals(capacity - 1,acGymClass.getValue().getCapacity());
    }

    @Test(expected = BookingServiceException.class)
    public void when_no_class_found_for_booking_throw_exception() throws BookingServiceException {
        when(gymClassRepository.findAll()).thenReturn(Collections.singletonList(gymClass));
        bookingService.bookClass(badBooking);
    }





}
