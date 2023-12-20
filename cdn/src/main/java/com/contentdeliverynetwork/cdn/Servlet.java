package com.contentdeliverynetwork.cdn;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class Servlet {

    @PostMapping("api/videos/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        Path filePath = Path.of("C:\\Users\\Mohammed Sohail\\OneDrive\\Desktop\\Server1", fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Received video file: " + fileName);
            return new ResponseEntity<>("Video uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Video upload failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/videos/{fileName}")
    public ResponseEntity<StreamingResponseBody> streamVideo(@PathVariable(value = "fileName") String fileName)
            throws IOException {
        String videosDirectory = "C:\\Users\\Mohammed Sohail\\OneDrive\\Desktop\\Server1";

        Path filePath = Paths.get(videosDirectory, fileName);
        File videoFile = filePath.toFile();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        StreamingResponseBody responseBody = outputStream -> {
            try (FileInputStream fileInputStream = new FileInputStream(videoFile)) {
                byte[] data = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(data)) != -1) {
                    outputStream.write(data, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println("Error");
            }
        };

        return ResponseEntity.ok().headers(headers).body(responseBody);
    }
}
