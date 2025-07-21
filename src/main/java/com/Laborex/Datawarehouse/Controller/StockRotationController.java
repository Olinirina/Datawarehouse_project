package com.Laborex.Datawarehouse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Laborex.Datawarehouse.Model.RotationStockDTO;
import com.Laborex.Datawarehouse.Service.RotationStockService;

@RestController
@RequestMapping("/api/rotation")
public class StockRotationController {

    @Autowired
    private RotationStockService rotationStockService;

    @GetMapping
    public List<RotationStockDTO> getRotation(@RequestParam int mois, @RequestParam int annee) {
        return rotationStockService.calculerRotationParMoisPourTousLesProduits(mois, annee);
    }
}


