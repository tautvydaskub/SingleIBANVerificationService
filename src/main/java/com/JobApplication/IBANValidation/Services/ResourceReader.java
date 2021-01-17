package com.JobApplication.IBANValidation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResourceReader {

    @Autowired
    private ResourceLoader resourceLoader;

    public Map<String, Integer> ibanCodeLengthByCountryCodeReader(){
        String data = getFileData("classpath:CodeLengths.csv");
        Map<String,Integer> map = new HashMap<String, Integer>();
        for(String line : data.split(System.lineSeparator()))
        {
            String[] lineData = line.split(",");
            map.put(lineData[0], Integer.parseInt(lineData[1]));
        }
        return map;
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
