package com.Laborex.Datawarehouse.Controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Laborex.Datawarehouse.Model.RotationStockDto;
import com.Laborex.Datawarehouse.Service.KpiService;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {

    private final KpiService kpiService;

    public KpiController(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @GetMapping("/rotation-stock")
    public RotationStockDto getRotationStock(
            @RequestParam String codeProduit,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fin
    ) {
        return kpiService.calculerRotationStock(codeProduit, debut, fin);
    }
}
