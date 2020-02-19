package com.fkealy.glofox.presentation;

import com.fkealy.glofox.exceptions.BookingServiceException;
import com.fkealy.glofox.model.Booking;
import com.fkealy.glofox.model.GymClassSeries;
import com.fkealy.glofox.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GymClassBookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/bookings", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<GymClassSeries> bookClass(@RequestBody Booking request) throws BookingServiceException {
        bookingService.bookClass(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
