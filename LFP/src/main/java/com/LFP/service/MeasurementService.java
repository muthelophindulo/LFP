package com.LFP.service;

import com.LFP.model.Measurements;
import com.LFP.repository.MeasurementsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementsRepository measurementsRepository;

    public MeasurementService(CustomerService customerService, MeasurementsRepository measurementsRepository) {

        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurements> getMeasurements(){
        return measurementsRepository.findAll();
    }

    public void delete(Long id){

        Measurements x = measurementsRepository.getReferenceById(id);
        if(x != null){
            measurementsRepository.deleteById(id);
        }
        else{
            System.out.println("no measurement of that id has been found");
        }

    }

    public Measurements save(Measurements measurement){
        return measurementsRepository.save(measurement);
    }

    public Measurements getByName(String name){
        return measurementsRepository.getMeasurementByName(name);
    }

    public Measurements getById(Long id){
        return measurementsRepository.getReferenceById(id);
    }
}
