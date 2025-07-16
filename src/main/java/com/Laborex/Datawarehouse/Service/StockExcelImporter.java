package com.Laborex.Datawarehouse.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Laborex.Datawarehouse.Model.Produit;
import com.Laborex.Datawarehouse.Model.Stock;
import com.Laborex.Datawarehouse.Model.Temps;
import com.Laborex.Datawarehouse.Repository.ProduitRepository;
import com.Laborex.Datawarehouse.Repository.StockRepository;
import com.Laborex.Datawarehouse.Repository.TempsRepository;

@Service
public class StockExcelImporter implements ExcelImportation{

	@Autowired
	public ProduitRepository produitRepository;
	@Autowired
	public TempsRepository tempsRepository;
	@Autowired
	public StockRepository stockRepository;
	
	@Override
	public boolean supports(String filename) {
		return filename.toLowerCase().contains("stock");
    }

	@Override
	public void importDate(Sheet sheet) throws IOException {
		Row headerRow= sheet.getRow(0);
		if(headerRow == null) return;
		
		// 🗂️ Remplir la map des noms de colonnes
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (Cell cell : headerRow) {
            columnIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }
		
		for(int i=1; i<=sheet.getLastRowNum();i++) {
			Row row =sheet.getRow(i);
			if(row == null) continue;
			//Lecture des valeurs
			//Stock
			String codeStock= getStringValue(row.getCell(columnIndexMap.get("codeStock")));
			int quantiteStocke= (int) getNumericValue(row.getCell(columnIndexMap.get("quantite")));
			
			//Produits
			String codeProduit = getStringValue(row.getCell(columnIndexMap.get("codeProduit")));
			
			//Date
			Cell dateCell = row.getCell(columnIndexMap.get("Date"));
			LocalDate localDate = null;

			// 1️⃣ Si la cellule est une vraie date (format Excel date)
			if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
			    Date excelDate = dateCell.getDateCellValue();
			    localDate = excelDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
			// 2️⃣ Si c’est une chaîne de texte comme "02/05/2022"
			else if (dateCell.getCellType() == CellType.STRING) {
			    String dateString = dateCell.getStringCellValue().trim();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    localDate = LocalDate.parse(dateString, formatter);
			} else {
			    System.err.println("⚠️ Format de date non pris en charge à la ligne " + i);
			    continue; // ou throw une exception
			}

			// Convertit la date en code_date et récupère/crée Temps
			Temps date = getOrCreateTemps(localDate);

			
			// Gestion des produits
			Optional<Produit> produitOpt = produitRepository.findById(codeProduit);
			if (!produitOpt.isPresent()) {
			    System.err.println("Produit inexistant : " + codeProduit + " à la ligne " + i);
			    continue; // Ignore la ligne
			}
			Produit produit = produitOpt.get();

			
			
			//Enregistrement ou mise a jour de la vente
			Optional<Stock> stockOpt=stockRepository.findById(codeStock);
			Stock stock=stockOpt.orElse(new Stock());
			stock.setCodeStock(codeStock);
			stock.setProduit(produit);
			stock.setQuantiteStocke(quantiteStocke);
			stock.setTemps(date);
			stockRepository.save(stock);
			
		}
	}
	// 🔧 Méthodes utilitaires
	public double getNumericValue(Cell cell) {
        if (cell == null) return 0;

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().replace(",", "."));
            } catch (NumberFormatException e) {
                return 0; // ou logger une erreur
            }
        }
        return 0;
    }

    public String getStringValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return "";
    }
    //Methode pour gerer le temps dynamiquements
    public Temps getOrCreateTemps(LocalDate localDate) {
        String codeDate = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return tempsRepository.findById(codeDate)
            .orElseGet(() -> {
                Temps temps = new Temps();
                temps.setCode_temps(codeDate);
                temps.setTemps(java.sql.Date.valueOf(localDate));
                temps.setJour(localDate.getDayOfMonth());
                temps.setMois(localDate.getMonthValue());
                temps.setAnnee(localDate.getYear());
                temps.setTrimestre((localDate.getMonthValue() - 1) / 3 + 1);

                return tempsRepository.save(temps);
            });
    }

}

