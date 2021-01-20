package com.JobApplication.ibanvalidation.services;

import com.JobApplication.ibanvalidation.models.RecognizedBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceReader {

    @Autowired
    private ResourceLoader resourceLoader;

    public Map<String, Integer> ibanCodeLengthByCountryCodeReader(){
        String data = getFileData("classpath:CodeLengths.csv");
        Map<String,Integer> codeLengths = new HashMap<>();
        for(String line : data.split(System.lineSeparator()))
        {
            String[] lineData = line.split(",");
            codeLengths.put(lineData[0], Integer.parseInt(lineData[1]));
        }
        return codeLengths;
    }

    public List<RecognizedBank> recognizedBankReader() {
        String data = getFileData("classpath:RecognizedBanks.csv");
        List<RecognizedBank> recognizedBanks = new ArrayList<>();
        for(String line : data.split(System.lineSeparator()))
        {
            String[] lineData = line.split(",");
            recognizedBanks.add(new RecognizedBank(lineData[0], lineData[1], lineData[2]));
        }
        return recognizedBanks;
    }


    private Resource getResource(String path)
    {
        return resourceLoader.getResource(path);
    }

    private String getFileData(String path)
    {
        Resource resource = getResource(path);
        String data = "";
        try {
            InputStream inputStream = resource.getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
