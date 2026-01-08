package vaisselle.services;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String saveFile(MultipartFile photoFile) {
        String result = "";
        if (!photoFile.isEmpty()) {
            try {

                String uploadDir = System.getProperty("user.dir") + "/uploads/";

                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                String fileName = UUID.randomUUID() + "-" + photoFile.getOriginalFilename();
                File destination = new File(uploadPath, fileName);
                photoFile.transferTo(destination);

                result = "/uploads/" + fileName;

            } catch (Exception e) {
            }
        }

        return result;
    }

}
