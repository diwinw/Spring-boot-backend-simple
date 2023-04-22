package com.miniBankend.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniBankend.model.Packages;
import com.miniBankend.repository.PackageRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ObjectMapper objectMapper ;
    public boolean createPackage(Packages packages) {
        try{
            if(packageRepository.save(packages)==1){
                log.info("PackageService : create package success");
                return true;
            }
            log.info("PackageService : create package fail");
            return false;
        }catch (Exception e){
            log.error("PackageService : create package error = "+e);
            return false;
        }
    }
    public int updatePackage(String packageId, Packages newPackage) {
        Packages packageResult = packageRepository.findById(packageId);
        if (packageResult!= null ) {
            packageResult.setPackage_name(newPackage.getPackage_name());
            packageResult.setPrice_per_unit(newPackage.getPrice_per_unit());
            return packageRepository.updatePackage(packageResult);
        }
        return -1;
    }
    public int deletePackage(String id) {
        try {
          return  packageRepository.removePackage(id);
        }catch (Exception ex){
            log.error("PackageService : remove error = " + ex);
            return -1;
        }
    }
    public List<Packages> getAllPackages() {
        try{
            String packageList = packageRepository.getAll();
            log.info("PackageService : get list package success");
            if(packageList!= null && !packageList.isEmpty()){
                return objectMapper.readValue(packageList, new TypeReference<List<Packages>>() {});
            }else{
                log.info("PackageService : not found list package ");
                return null;
            }
        }catch (Exception ex){
            log.error("PackageService : get list package error = " + ex);
            return null;
        }
    }

    public List<Packages> search (String packageId, String packageName, Integer minPrice, Integer maxPrice, String startDate, String endDate , Boolean sorting){
        try{
            List<Packages> resultPackageList = packageRepository.findPackagesByFilters(packageId,packageName,minPrice,maxPrice,startDate,endDate);
            if(resultPackageList.size()>1 && sorting){
                return bubbleSort(resultPackageList);
            }
            return resultPackageList;
        }catch (Exception ex){
            log.error("PackageService : search error = "+ ex);
            return null;
        }
    }


    public List<Packages> bubbleSort(List<Packages> packagesList) {
        Packages temp = null;
        for(int i=0; i < packagesList.size(); i++) {
            for(int j=1; j < (packagesList.size()-i); j++) {
                if(packagesList.get(j-1).getPrice_per_unit() > packagesList.get(j).getPrice_per_unit()) {
                    temp = packagesList.get(j-1);
                    packagesList.set(j-1, packagesList.get(j));
                    packagesList.set(j, temp);
                }
            }
        }
        return packagesList;
    }


}
