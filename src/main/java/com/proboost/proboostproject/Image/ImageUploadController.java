package com.proboost.proboostproject.Image;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Respositories.UserRepo;
import com.proboost.proboostproject.Services.UserService;
import com.proboost.proboostproject.filter.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
@AllArgsConstructor
public class ImageUploadController {

    @Autowired
    ImageRepository imageRepository;

    private final UserService userService ;

    @PostMapping("/upload")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file,@RequestHeader("Authorization") String token) throws IOException {

        try {
            if(!token.equals("") && token!=null){
               User user = userService.getCurrentUser(token);
//         user.setImage(img);
//         userrepo.saveAndFlush(user);
                System.out.println();
                System.out.println();
                System.out.println("User t3ada hamdullah -----------  "+user);
                System.out.println();
                System.out.println();


                System.out.println("Original Image Byte Size - " + file.getBytes().length);
                ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                        compressBytes(file.getBytes()));
                //img.setUser(user);
                //img.setUser(userrepo.findById(1).get());



                img =imageRepository.save(img);

                user.setImage(img.getId());

                userService.update(user);
            }

        }catch (Exception e){
            System.out.println();
            System.out.println();
            System.out.println("user mochkla kbira -----------  "+e.getMessage());
            System.out.println();
            System.out.println();
        }




        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{id}" })
    public ImageModel getImage(@PathVariable("id") Long id) throws IOException {

        final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[4000000];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
