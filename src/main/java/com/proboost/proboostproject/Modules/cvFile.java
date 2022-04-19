package com.proboost.proboostproject.Modules;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="CV")
public class cvFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne
    private Offre_Emploi offre;


    @OneToOne(mappedBy = "cvfile")
    private User user;

    public cvFile() {
    }

    public cvFile( String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public cvFile(String fileName, String fileType, byte[] data, Offre_Emploi offre) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.offre = offre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}