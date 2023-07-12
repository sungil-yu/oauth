package com.study.oauth.oauth.controller;

import com.study.oauth.oauth.domain.Worker;
import com.study.oauth.oauth.exception.NotFoundWorkerByDate;
import com.study.oauth.oauth.service.ExcelService;
import com.study.oauth.oauth.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendanceExcelController {

    private final ExcelService excelService;

    private final WorkerService workerService;

    @GetMapping("/download/attendanceExcel")
    @ResponseBody
    public ResponseEntity<Resource> attendanceExcel(@RequestParam(value = "start", required = false) LocalDateTime start,
                                                    @RequestParam(value = "end", required = false) LocalDateTime end) {
        List<Worker> workers = workerService.getWorkersByDate(start, end);

        if (workers.isEmpty()) {
            workers = workerService.getWorkers();
        }

        excelService.embedExcelFile(workers);

        ClassPathResource resource = new ClassPathResource("templates/template.xlsx");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "template.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


}
