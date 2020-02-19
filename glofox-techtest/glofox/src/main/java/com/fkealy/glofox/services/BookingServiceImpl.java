package com.fkealy.glofox.services;

import com.fkealy.glofox.exceptions.BookingServiceException;
import com.fkealy.glofox.model.Booking;
import com.fkealy.glofox.model.GymClass;
import com.fkealy.glofox.persistence.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookingServiceImpl implements BookingService {

    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    GymClassRepository gymClassRepository;


    @Override
    public void bookClass(Booking request) throws BookingServiceException {
        String targetDate = formatDayValue(request.getDate());
        Optional<GymClass> gymClass = searchRepositoryForClassesOnDate(targetDate);
        if(gymClass.isPresent()){
            gymClassRepository.save(reduceCapacityByOne(gymClass.get()));
        } else {
            throw new BookingServiceException("no class found on that day");
        }
    }

    private String formatDayValue(LocalDateTime date) {
        return fmt.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private Optional<GymClass> searchRepositoryForClassesOnDate(String date){
        Iterable<GymClass> gymClasses = gymClassRepository.findAll();
        return StreamSupport
                .stream( gymClasses.spliterator(),false)
                .filter(g ->
                        formatDayValue(g.getDate())
                                .equals(date)).findFirst();
    }

    private GymClass reduceCapacityByOne(GymClass gymClass){
        gymClass.setCapacity(gymClass.getCapacity() - 1);
        return gymClass;
    }
}
