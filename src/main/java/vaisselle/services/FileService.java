package vaisselle.services;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final String UPLOADFOLDER = "/uploads/";

    public String savePhoto(MultipartFile photoFile) {
        String result = "";

        if (photoFile != null && !photoFile.isEmpty()) {
            try {

                String uploadDir = System.getProperty("user.dir") + UPLOADFOLDER;

                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                String fileName = UUID.randomUUID() + "-" + photoFile.getOriginalFilename();
                File destination = new File(uploadPath, fileName);
                photoFile.transferTo(destination);

                result = UPLOADFOLDER + fileName;

            } catch (Exception e) {
            }
        }

        return result;
    }

    public boolean deleteFile(String relativePath) {
        if (relativePath == null || relativePath.isEmpty()) {
            return false;
        }
        try {
            String filePath = System.getProperty("user.dir") + relativePath;
            File file = new File(filePath);
            if (file.exists()) {
                return file.delete();
            }
        } catch (Exception e) {
        }
        return false;
    }

}
