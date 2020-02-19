package com.fkealy.glofox.presentation;

import com.fkealy.glofox.model.GymClassSeries;
import com.fkealy.glofox.services.GymClassServiceImpl;
import com.fkealy.glofox.exceptions.GymClassServiceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GymClassController {

    @Autowired
    GymClassServiceImpl gymClassService;

    @RequestMapping(value = "/classes", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<GymClassSeries> addClass(@RequestBody GymClassSeries request) throws GymClassServiceValidationException {
            gymClassService.saveGymClass(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
