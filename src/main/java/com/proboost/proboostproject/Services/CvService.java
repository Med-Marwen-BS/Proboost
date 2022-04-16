package com.proboost.proboostproject.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.proboost.proboostproject.Modules.cvFile;
import com.proboost.proboostproject.Respositories.CvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import javax.print.Doc;


@Service
public class CvService {

    @Autowired
    private CvRepo cvrepo;

    public CvService(CvRepo cvrepo) {
        this.cvrepo = cvrepo;
    }




    public cvFile saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            cvFile attachment
                    = new cvFile(fileName,
                    file.getContentType(),
                    file.getBytes()
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

}
