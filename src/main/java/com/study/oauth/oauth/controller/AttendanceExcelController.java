package com.study.oauth.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
public class AttendanceExcelController {

    private final ExcelService excelService;


    @GetMapping("/download/attendanceExcel")
    @ResponseBody
    public ResponseEntity<Resource> attendanceExcel() throws Exception {

        excelService.embedExcelFile();

        String filePath = "src/main/resources/templates/template.xlsx";
        File file = new File(filePath);
        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "template.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }


}
