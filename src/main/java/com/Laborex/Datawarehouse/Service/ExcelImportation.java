package com.Laborex.Datawarehouse.Service;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelImportation {
	//Reconaitre le fichier
	boolean supports(String filename);
	void importDate(Sheet sheet)throws IOException;

}
