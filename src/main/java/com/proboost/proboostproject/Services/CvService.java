package com.proboost.proboostproject.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.proboost.proboostproject.Modules.cvFile;
import com.proboost.proboostproject.Respositories.CvRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;

import javax.print.Doc;


import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class CvService {
    private Logger logger = LoggerFactory.getLogger(CvService.class);

    @Autowired
    private CvRepo cvrepo;
    private OffreService offreService;


    public CvService(CvRepo cvrepo ,OffreService offreService) {
        this.cvrepo = cvrepo;
        this.offreService=offreService;
    }




    public cvFile saveFile(MultipartFile file , int id) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            cvFile attachment
                    = new cvFile(fileName,
                    file.getContentType(),
                    file.getBytes(),
                    offreService.getOffre(id)

            );
            return cvrepo.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    public cvFile getFile(String fileId) throws Exception {
        return cvrepo
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

    public Stream<cvFile> getAllFiles(){
        return cvrepo.findAll().stream();
    }


    public void downloadZipFile(HttpServletResponse response, List<String> listOfFileNames) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for(String fileName : listOfFileNames) {
                FileSystemResource fileSystemResource = new FileSystemResource(fileName);
                ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
                zipEntry.setSize(fileSystemResource.contentLength());
                zipEntry.setTime(System.currentTimeMillis());

                zipOutputStream.putNextEntry(zipEntry);

                StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


public List<String> getFileNames(){
        return cvrepo.findAll().stream().map(cv -> cv.getFileName()).collect(Collectors.toList());
}

}