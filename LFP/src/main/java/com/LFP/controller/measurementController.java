package com.LFP.controller;

import com.LFP.model.Measurements;
import com.LFP.service.MeasurementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/measurements")
public class measurementController {
    private final MeasurementService measurementService;

    public measurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("measurements", new Measurements());
        return "measurements/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Measurements x){
        x.setMeasurementDate(LocalDateTime.now());

        measurementService.save(x);

        return "redirect:/dashboard";
    }


    @GetMapping("/list")
    public String showMeasurements(Model model){
        List<Measurements> measurements = measurementService.getMeasurements();
        model.addAttribute("measurements", measurements);

        // Add statistics
        model.addAttribute("totalmeasurements", measurementService.getMeasurements().size());



        return "measurements/measurements";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        measurementService.delete(id);
        return "redirect:/measurements/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id,Model model){
        Measurements measurements = measurementService.getById(id);
        model.addAttribute("measurement",measurements);

        return "measurements/view";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Measurements measurements = measurementService.getById(id);

        model.addAttribute("Measurements", measurements);


        model.addAttribute("pageTitle", "update measurement");

        return "measurements/update";
    }

    @PostMapping("/update")
    public String saveUpdate(
            @ModelAttribute Measurements measurements
    ){
        measurements.setName( measurementService.getById(measurements.getId()).getName() );
        measurementService.save(measurements);
        return "redirect:/measurements/list";
    }
}
