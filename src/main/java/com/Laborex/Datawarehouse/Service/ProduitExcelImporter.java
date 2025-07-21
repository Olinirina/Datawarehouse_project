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

import com.Laborex.Datawarehouse.Model.Categorie_Produit;
import com.Laborex.Datawarehouse.Model.Fournisseur;
import com.Laborex.Datawarehouse.Model.Pays;
import com.Laborex.Datawarehouse.Model.Produit;
import com.Laborex.Datawarehouse.Repository.Categorie_ProduitRepository;
import com.Laborex.Datawarehouse.Repository.FournisseurRepository;
import com.Laborex.Datawarehouse.Repository.PaysRepository;
import com.Laborex.Datawarehouse.Repository.ProduitRepository;
@Service
public class ProduitExcelImporter implements ExcelImportation {

    @Autowired
    public ProduitRepository produitRepository;

    @Autowired
    public Categorie_ProduitRepository categorieRepository;

    @Autowired
    public FournisseurRepository fournisseurRepository;

    @Autowired
    public PaysRepository paysRepository;

    @Override
    public boolean supports(String filename) {
        return filename.toLowerCase().contains("produit");
    }

    @Override
    public void importDate(Sheet sheet) throws IOException {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return;

        // 🗂️ Remplir la map des noms de colonnes
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (Cell cell : headerRow) {
            columnIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // Lecture des valeurs
            //Produits
            String codeProduit = getStringValue(row.getCell(columnIndexMap.get("codeProduit")));
            String nom = getStringValue(row.getCell(columnIndexMap.get("nomProduit")));
            double prixachat = getNumericValue(row.getCell(columnIndexMap.get("prixAchat")));
            int unite = (int) getNumericValue(row.getCell(columnIndexMap.get("unite")));
            int seuilMinimal=(int) getNumericValue(row.getCell(columnIndexMap.get("seuil")));

            //Categories
            String codeCat = getStringValue(row.getCell(columnIndexMap.get("codeCat")));
            String nomCat = getStringValue(row.getCell(columnIndexMap.get("nomCat")));

            //Fournisseurs
            String codeFrns = getStringValue(row.getCell(columnIndexMap.get("codeFrns")));
            String nomFrns = getStringValue(row.getCell(columnIndexMap.get("nomFrns")));

            //Pays
            String codePays = getStringValue(row.getCell(columnIndexMap.get("codePays")));
            String nomPays = getStringValue(row.getCell(columnIndexMap.get("nomPays")));

            // Gestion du pays
            Pays localisation;
            Optional<Pays> paysOpt = paysRepository.findById(codePays);
            if (paysOpt.isPresent()) {
                localisation = paysOpt.get();
                ////mise a jour
                localisation.setCode_pays(codePays);
                localisation.setNom_pays(nomPays);
                paysRepository.save(localisation);
            } else {
                localisation = new Pays();
                localisation.setCode_pays(codePays);
                localisation.setNom_pays(nomPays);
                localisation = paysRepository.save(localisation);
            }

            // Gestion de la catégorie
            Categorie_Produit cat;
            Optional<Categorie_Produit> categorieOpt = categorieRepository.findById(codeCat);
            if (categorieOpt.isPresent()) {
                cat = categorieOpt.get();
                //mise a jour
                cat.setCodeCatPro(codeCat);
                cat.setNom_catPro(nomCat);
                categorieRepository.save(cat);
            } else {
                cat = new Categorie_Produit();
                cat.setCodeCatPro(codeCat);
                cat.setNom_catPro(nomCat);
                cat = categorieRepository.save(cat);
            }

            // Gestion du fournisseur
            Fournisseur frns;
            Optional<Fournisseur> fournisseurOpt = fournisseurRepository.findById(codeFrns);
            if (fournisseurOpt.isPresent()) {
                frns = fournisseurOpt.get();
                ////mise a jour
                frns.setCode_fournisseur(codeFrns);
                frns.setNom_Fournisseur(nomFrns);
                frns.setPays(localisation);
                fournisseurRepository.save(frns);
            } else {
                frns = new Fournisseur();
                frns.setCode_fournisseur(codeFrns);
                frns.setNom_Fournisseur(nomFrns);
                frns.setPays(localisation);
                frns = fournisseurRepository.save(frns);
            }

            // Enregistrement ou mise à jour du produit
            Optional<Produit> produitOpt = produitRepository.findById(codeProduit);
            Produit produit = produitOpt.orElse(new Produit());

            produit.setCodeProduit(codeProduit);
            produit.setNomProduit(nom);
            produit.setPrixAchat(prixachat);
            produit.setUnite(unite);
            produit.setCategorie(cat);
            produit.setFournisseur(frns);
            produit.setSeuilStockMinimal(seuilMinimal);

            produitRepository.save(produit);
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
}
