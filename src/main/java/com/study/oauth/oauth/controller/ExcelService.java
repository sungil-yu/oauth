package com.study.oauth.oauth.controller;

import com.study.oauth.oauth.domain.Worker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private static List<Worker> workers;

    public ExcelService() {
        workers = new ArrayList<>();
        createWorker();
    }

    private void createWorker() {
        for (int i =0 ; i < 30; i++){
            workers.add(new Worker("name" + i, "email" + i, "static/img/logo.jpg", "/static/video/1.mp4", LocalDateTime.now()));
        }
    }

    private void createHeader(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("email");
        row.createCell(2).setCellValue("image");
        row.createCell(3).setCellValue("video");

        sheet.setColumnWidth(0, 256 * 20);
        sheet.setColumnWidth(1, 256 * 20);
        sheet.setColumnWidth(2, 256 * 20);
        sheet.setColumnWidth(3, 256 * 20);
    }

    public void embedExcelFile() {
        long startTime = System.currentTimeMillis();

        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("Workers");
            createHeader(sheet);

            Drawing<?> pat = sheet.createDrawingPatriarch();
            String objectName = "video";
            String fileExtension = ".mp4";

            int imageColNo = 2;
            int videoColNo = 3;

            for (int i = 0; i < workers.size(); i++) {
                int rowNo = i + 1;
                XSSFRow row = sheet.createRow(rowNo);

                row.setHeight((short) 2000);

                row.createCell(0).setCellValue(workers.get(i).getName());
                row.createCell(1).setCellValue(workers.get(i).getEmail());

                try (FileInputStream imageFile = new FileInputStream(new ClassPathResource(workers.get(i).getImageUrl()).getFile());
                     FileInputStream videoFile = new FileInputStream(new ClassPathResource(workers.get(i).getVideoUrl()).getFile())) {

                    int iconId = wb.addPicture(IOUtils.toByteArray(imageFile), Workbook.PICTURE_TYPE_PNG);
                    int oleIdx = wb.addOlePackage(IOUtils.toByteArray(videoFile), objectName, objectName + fileExtension, objectName + fileExtension);

                    ClientAnchor imageAnchor = pat.createAnchor(0, 0, 0, 0, imageColNo, rowNo, imageColNo + 1, rowNo + 1);
                    imageAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
                    Picture picture = pat.createPicture(imageAnchor, iconId);
                    picture.resize(1, 1);

                    ClientAnchor videoAnchor = pat.createAnchor(0, 0, 0, 0, videoColNo, rowNo, videoColNo + 1, rowNo + 1);
                    videoAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

                    XSSFObjectData objectData = (XSSFObjectData) pat.createObjectData(videoAnchor, oleIdx, iconId);
                    objectData.getCTShape().getNvSpPr().getCNvPr().setName(objectName);
                    objectData.getCTShape().getNvSpPr().getCNvPr().setHidden(false);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/templates/template.xlsx")) {
                wb.write(fileOut);
            }

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
    }
}
