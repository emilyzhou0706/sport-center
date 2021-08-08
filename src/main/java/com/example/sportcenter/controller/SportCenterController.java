package com.example.sportcenter.controller;

import com.example.sportcenter.entity.Court;
import com.example.sportcenter.entity.SportCenter;
import com.example.sportcenter.exception.ResourceNotFoundException;
import com.example.sportcenter.repository.CourtRepository;
import com.example.sportcenter.repository.SportCenterRepository;
import com.example.sportcenter.service.GetCenterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SportCenterController {
    @Autowired
    private GetCenterServiceImpl getCourtServiceImpl;

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private SportCenterRepository sportCenterRepository;

    public SportCenterController(GetCenterServiceImpl getCourtServiceImpl, CourtRepository courtRepository, SportCenterRepository sportCenterRepository) {
        this.getCourtServiceImpl = getCourtServiceImpl;
        this.courtRepository = courtRepository;
        this.sportCenterRepository = sportCenterRepository;
    }

    @PostMapping("/center/create")
    public ResponseEntity<Object> createRole(@RequestBody SportCenter sportCenter) {
        return  getCourtServiceImpl.addCenter(sportCenter);
    }

    @GetMapping("/center/details/{id}")
    public SportCenter getCenter(@PathVariable Integer id) {
        if(sportCenterRepository.findById(id).isPresent())
        return sportCenterRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/center/all")
    public List<SportCenter> getCenters() {
        return sportCenterRepository.findAll();
    }

    @PutMapping("/sportcenters/{centerid}/playerAdd/{playerAdd}")
    public List<Court> updateComment(@PathVariable (value = "centerid") Integer centerid,
                               @PathVariable (value = "playerAdd") Integer playerAdd) {
        if(!sportCenterRepository.existsById(centerid)) {
            System.out.println("center not found*************");
            throw new ResourceNotFoundException("Centerid " + centerid + " not found!!");
        }
        getCourtServiceImpl.addPlayer(centerid, playerAdd);
        return courtRepository.findAll();
    }
}


