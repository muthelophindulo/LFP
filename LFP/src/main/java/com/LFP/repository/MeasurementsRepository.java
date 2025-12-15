package com.LFP.repository;

import com.LFP.model.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurements,Long> {
    Measurements getMeasurementByName(String name);
}
