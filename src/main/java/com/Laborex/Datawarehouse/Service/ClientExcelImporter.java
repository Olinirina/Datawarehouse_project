package com.Laborex.Datawarehouse.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Laborex.Datawarehouse.Model.Categorie_Client;
import com.Laborex.Datawarehouse.Model.Client;
import com.Laborex.Datawarehouse.Model.Pays;
import com.Laborex.Datawarehouse.Repository.Categorie_ClientRepository;
import com.Laborex.Datawarehouse.Repository.ClientRepository;
import com.Laborex.Datawarehouse.Repository.PaysRepository;

@Service
public class ClientExcelImporter implements ExcelImportation {
	@Autowired
	public ClientRepository clientRepository;
	@Autowired
	public PaysRepository paysRepository;
	@Autowired
	public Categorie_ClientRepository categorieCliRepository;

	@Override
	public boolean supports(String filename) {
		return filename.toLowerCase().contains("client");
	}

	@Override
	public void importDate(Sheet sheet) throws IOException {
		Row headerRow=sheet.getRow(0);
		if(headerRow == null) return;
		
		//Remplir le map des noms de colonnes
		Map<String,Integer> columnIndexMap =new HashMap<>();
		for(Cell cell : headerRow) {
			columnIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
			
		}
		for(int i=1; i<=sheet.getLastRowNum();i++) {
			Row row=sheet.getRow(i);
			if(row == null) continue;
			
			//Lecture des valeurs 
			//Clients
			String codeCli= getStringValue(row.getCell(columnIndexMap.get("codeCli")));
			String nomCli= getStringValue(row.getCell(columnIndexMap.get("nomCli")));
			
			//Categories des clients
			String codeCatCli= getStringValue(row.getCell(columnIndexMap.get("codeCatCli")));
			String nomCatCli= getStringValue(row.getCell(columnIndexMap.get("nomCatCli")));
			
			//Pays
			String codePays= getStringValue(row.getCell(columnIndexMap.get("codePays")));
			String nomPays= getStringValue(row.getCell(columnIndexMap.get("nomPays")));
			
			//GEstion du pays
			Pays localisation;
			Optional<Pays> paysOpt= paysRepository.findById(codePays);
			if(paysOpt.isPresent()) {
				localisation =paysOpt.get();
				//Mise a jour
				localisation.setCode_pays(codePays);
				localisation.setNom_pays(nomPays);
				paysRepository.save(localisation);
			}else {
				localisation= new Pays();
				localisation.setCode_pays(codePays);
				localisation.setNom_pays(nomPays);
				localisation= paysRepository.save(localisation);
			}
			
			//Gestion des categories cli
			Categorie_Client catCli;
			Optional<Categorie_Client> categorieOpt=categorieCliRepository.findById(codeCatCli);
			if(categorieOpt.isPresent()) {
				catCli=categorieOpt.get();
				//Mis a jour
				catCli.setCode_catCli(codeCatCli);
				catCli.setNom_catCli(nomCatCli);
				catCli.setLocalisation(localisation);
				categorieCliRepository.save(catCli);
				
			}else {
				catCli=new Categorie_Client();
				catCli.setCode_catCli(codeCatCli);
				catCli.setNom_catCli(nomCatCli);
				catCli.setLocalisation(localisation);
				catCli= categorieCliRepository.save(catCli);
			}
			
			//Enregistrement des clients
			Optional<Client> clientOpt= clientRepository.findById(codeCli);
			Client client= clientOpt.orElse(new Client());
			
			client.setCode_client(codeCli);
			client.setNom_client(nomCli);
			client.setCategorieCli(catCli);
			clientRepository.save(client);
			
		}
		
	}
	
	// Méthodes utilitaires
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
	            return String.valueOf(cell.getNumericCellValue());
	        }
	        return "";
	    }

}
