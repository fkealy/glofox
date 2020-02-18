package com.fkealy.glofox.services;

import com.fkealy.glofox.exceptions.GymClassServiceValidationException;
import com.fkealy.glofox.model.GymClass;
import com.fkealy.glofox.model.GymClassSeries;
import com.fkealy.glofox.persistence.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

@Service
public class GymClassServiceImpl implements GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Override
    public void saveGymClass(GymClassSeries gymClassSeries) throws GymClassServiceValidationException {
        if(gymClassSeries.getEndDate().isBefore(gymClassSeries.getStartDate())){
            throw new GymClassServiceValidationException("startDate must be before endDate");
        }

        saveGymClassForEachDate(
                gymClassSeries.getName()
                ,gymClassSeries.getCapacity()
                ,gymClassSeries.getStartDate()
                ,gymClassSeries.getEndDate()
        );
    }

    private void saveGymClassForEachDate(String name, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate,endDate.plusDays(1)))
                .forEach(date ->  gymClassRepository.save(new GymClass(name,capacity,date)));
    }
}

