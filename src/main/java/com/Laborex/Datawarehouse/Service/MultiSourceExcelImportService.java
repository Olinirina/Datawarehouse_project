package com.Laborex.Datawarehouse.Service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultiSourceExcelImportService {

    @Autowired
    private List<ExcelImportation> importers;

    public void importerFichiers(List<MultipartFile> fichiers) throws IOException {
        for (MultipartFile file : fichiers) {
            String fileName = file.getOriginalFilename();
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // Trouver le bon importer
            ExcelImportation importer = importers.stream()
                .filter(i -> i.supports(fileName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Aucun importer ne supporte le fichier : " + fileName));

            importer.importDate(sheet);
            workbook.close();
        }
    }
}

