package com.fkealy.glofox.services;

import com.fkealy.glofox.exceptions.BookingServiceException;
import com.fkealy.glofox.model.Booking;

public interface BookingService {


    void bookClass(Booking request) throws BookingServiceException;

}
