package com.Laborex.Datawarehouse.Service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.Laborex.Datawarehouse.Model.RotationStockDto;
import com.Laborex.Datawarehouse.Repository.StockRepository;
import com.Laborex.Datawarehouse.Repository.VenteRepository;

@Service
public class KpiService {

    private final VenteRepository venteRepository;
    private final StockRepository stockRepository;

    public KpiService(VenteRepository venteRepository, StockRepository stockRepository) {
        this.venteRepository = venteRepository;
        this.stockRepository = stockRepository;
    }

    public RotationStockDto calculerRotationStock(String codeProduit, Date debut, Date fin) {
        Integer quantiteVendue = venteRepository.sumQuantiteByProduitAndDateRange(codeProduit, debut, fin);
        Double stockMoyen = stockRepository.calculateStockAverage(codeProduit, debut, fin);

        int quantite = (quantiteVendue == null) ? 0 : quantiteVendue;
        double stock = (stockMoyen == null) ? 0.0 : stockMoyen;

        double rotation = (stock == 0) ? 0 : quantite / stock;

        return new RotationStockDto(codeProduit, debut, fin, quantite, stock, rotation);
    }
}
