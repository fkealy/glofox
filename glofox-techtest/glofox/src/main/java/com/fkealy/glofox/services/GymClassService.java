package com.fkealy.glofox.services;

import com.fkealy.glofox.exceptions.GymClassServiceValidationException;
import com.fkealy.glofox.model.GymClassSeries;

public interface GymClassService {

    void saveGymClass(GymClassSeries gymClassSeries) throws GymClassServiceValidationException;
}
