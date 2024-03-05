package com.example.demo03.service.ProductService;

import com.example.demo03.model.entity.Product;
import com.example.demo03.responsitoty.ProductRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    String line = "";
    public void saveCustomerData (String fileName) {
        try {
            String pathFile = "/Users/son/Desktop/OJT/Demo03/src/main/resources/uploads/" + fileName;
            Path pathToFile = Paths.get(pathFile);
            BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
            while ((line = br.readLine()) != null) {
                String [] data = line.split(";");
                Product product = new Product();
                product.setProductName(data[0]);
                String[] stringDate = data[1].split("/");
                String date = stringDate[2]+"-"+stringDate[1]+"-"+stringDate[0];
                product.setCreateDate(Date.valueOf(date));
                product.setPrice(Double.valueOf(data[2]));
                product.setQuanity(Integer.valueOf(data[3]));

                Product pro = productRepository.findProductByProductName(product.getProductName());

                switch (data[4]) {
                    case "create":
                        if (pro == null) {
                            productRepository.save(product);
                        }
                        break;
                    case "delete":
                        if (pro != null) {
                            productRepository.delete(pro);
                        }
                        break;
                    case "update":
                        if (pro != null) {
                            product.setProductId(pro.getProductId());
                            productRepository.save(product);
                        }
                        break;
                }
            }
            File file = new File(pathFile);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("chan");

        }
    }
    public void generateExcel (HttpServletResponse response) throws IOException {
        List<Product> products = productRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Customer Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Time");
        row.createCell(3).setCellValue("Price");
        row.createCell(4).setCellValue("Quantity");

        int dataRowIndex = 1;

        for (Product product : products) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(product.getProductId());
            dataRow.createCell(1).setCellValue(product.getProductName());
            dataRow.createCell(2).setCellValue(product.getCreateDate());
            dataRow.createCell(3).setCellValue(product.getPrice());
            dataRow.createCell(4).setCellValue(product.getQuanity());


            dataRowIndex++;
        }
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    public List<Product> findAll () {
        return productRepository.findAll();
    }


}
