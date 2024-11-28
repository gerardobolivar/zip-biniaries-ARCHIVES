/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_armando_gerardo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import net.lingala.zip4j.core.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 *
 * @author Gerardo Vargas Fernández
 * @author Armando Sibaja Elizondo
 */
public class Zip extends SimpleFileVisitor<Path> {

    //This implementation was adapted from codejava.net
    //https://www.codejava.net/java-se/file-io/how-to-compress-files-in-zip-format-in-java
    //
    private static ZipOutputStream zos;

    private Path sourceDir;

    public Zip(Path sourceDir) {
        this.sourceDir = sourceDir;
    }

    public static void zipFile(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            String[] extractExtention = filePath.split("[.]");
            String zipFileName = extractExtention[0].concat(".zip");
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();
        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attributes) {
        try {
            Path targetFile = sourceDir.relativize(file);

            zos.putNextEntry(new ZipEntry(targetFile.toString()));

            byte[] bytes = Files.readAllBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return FileVisitResult.CONTINUE;
    }

    public static void zipp(String p) {
        String dirPath = p;
        Path sourceDir = Paths.get(dirPath);

        try {
            String zipFileName = dirPath.concat(".zip");
            zos = new ZipOutputStream(new FileOutputStream(zipFileName));

            Files.walkFileTree(sourceDir, new Zip(sourceDir));

            zos.close();
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
    }

    public static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void zipWithPassword(String fileName, String password) throws ZipException, IOException {
        try {
            String[] extractExtention = fileName.split("[.]");            
            ZipFile zipFile = new ZipFile(extractExtention[0].concat(".zip"));            
            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File(fileName));
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword(password);              
            zipFile.addFiles(filesToAdd, parameters);                                 
        } catch (net.lingala.zip4j.exception.ZipException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
