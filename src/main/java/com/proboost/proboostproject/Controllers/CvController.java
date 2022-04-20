package com.proboost.proboostproject.Controllers;


import java.util.List;
import java.util.Optional;


import com.proboost.proboostproject.Modules.cvFile;
import com.proboost.proboostproject.ResponseData;
import com.proboost.proboostproject.Services.CvService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.stream.Collectors;


@RestController
public class CvController {

    @Autowired
    private CvService cvservice;

    public CvController(CvService cvservice) {
        this.cvservice = cvservice;
    }


    @PostMapping("/upload/{id}")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file , @PathVariable("id") int id ) throws Exception {
        cvFile attachment = null;
        String downloadURl = "";
        attachment = cvservice.saveFile(file,id);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }




    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        cvFile attachment = null;
        attachment = cvservice.getFile(fileId);

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }


    @GetMapping("/showFile/{fileId}")
    public ResponseEntity<Resource> showFile(@PathVariable String fileId) throws Exception {
        cvFile attachment = null;
        attachment = cvservice.getFile(fileId);

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseData>> getListFiles() {
        List<ResponseData> files = cvservice.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/download/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseData(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getFileType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }



@GetMapping("/fileName")
    private List<String> getListOfFileNames() {
        return  cvservice.getFileNames();
    }

    @GetMapping("/downloadZipFile")
    public void downloadZipFile(HttpServletResponse response) {
        List<String> listOfFileNames = getListOfFileNames();
        cvservice.downloadZipFile(response, listOfFileNames);
    }

}