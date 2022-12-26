import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class rekrute {
    private static String[] columns = {"id","sitename", "Postname",
            "description", "DateDePublication", "Entreprise","EntrepriseDes", "region", "Competence","Type_de_contrat","secteur","experience","niveau_etude","langue","nombre_de_poste","Postuler"};
    private static List<data_V2> data_V2 = new ArrayList<data_V2>();

    public static void main(String[] args) throws IOException {
        try {
            int j;
            int k = 1;
            int nombre_de_poste=1;

            for (int i = 0; i < 36; i++) {

                Document doc = Jsoup.connect("https://www.rekrute.com/offres.html?s=3&p="+i+"&o=1").get();
                Elements newsHeadlines = doc.select(".post-id");
                for (Element hicham : newsHeadlines) {

                    Elements Postname = hicham.select(".titreJob");
                    Elements DateDePublication = hicham.select(".date span");
                    Elements lien = hicham.select(".section h2 a");
                    Document emploi = Jsoup.connect("https://www.rekrute.com"+lien.attr("href")).get();
                    Elements Type_de_contrat = emploi.select("span[title=Type de contrat]");
                    Elements experience = emploi.select("li[title=Expérience requise]");
                    Elements region = emploi.select("li[title=Région]");
                    Elements Competence = emploi.select(".tagSkills");
                    Elements Entreprise = emploi.select("#recruiterDescription p");
                    Elements secteur = emploi.select(".h2italic");
                    String[] niveau_etude = {"Bac+5 et plus", "Bac+2", "Bac+4", "Bac+3","Bac+2"};
                    Random random = new Random();
                    int index = random.nextInt(niveau_etude.length);
                    Elements description = emploi.select("#recruiterDescription p");
//
                    String langue = "anglais›bon niveau français›courant";
                    String sitename="rekrute.com";
//
                    data_V2.add(new data_V2(k,sitename, Postname.text(),description.text(), DateDePublication.first().text(),
                            Entreprise.text(),description.text(), region.text(), Competence.text(),
                            Type_de_contrat.text(),secteur.text(),experience.text(),niveau_etude[index],langue,nombre_de_poste,"https://www.rekrute.com"+lien.attr("href")));

                    System.out.println("Emploi " + k+ " succefully collected !!!!!!!!!!");





                    k++;

                }
                j = i + 1;
                System.out.println("page " + j + " succefully collected!!!!!");



            }

            SAVE();
        }
        catch (Exception e){
            System.out.println("Erreur de la connexion");
            SAVE();

        }


    }
    public static void SAVE() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("rekrute");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);


        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        int rowNum = 1;

        for (data_V2 data : data_V2) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.id);
            row.createCell(1).setCellValue(data.sitename);
            row.createCell(2).setCellValue(data.Postname);
            row.createCell(3).setCellValue(data.description);
            row.createCell(4).setCellValue(data.DateDePublication);
            row.createCell(5).setCellValue(data.Entreprise);
            row.createCell(6).setCellValue(data.EntrepriseDes);
            row.createCell(7).setCellValue(data.region);
            row.createCell(8).setCellValue(data.Competence);
            row.createCell(9).setCellValue(data.Type_de_contrat);
            row.createCell(10).setCellValue(data.secteur);
            row.createCell(11).setCellValue(data.experience);
            row.createCell(12).setCellValue(data.niveau_etude);
            row.createCell(13).setCellValue(data.langue);
            row.createCell(14).setCellValue(data.nombre_de_poste);
            row.createCell(15).setCellValue(data.Postuler);
        }


        FileOutputStream fileOut = new FileOutputStream("rekrute.xlsx");
        workbook.write(fileOut);
        fileOut.close();

    }
}
