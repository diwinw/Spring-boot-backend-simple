package com.miniBankend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniBankend.comfig.JwtUtil;
import com.miniBankend.model.Packages;
import com.miniBankend.model.RequestSearchModel;
import com.miniBankend.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/packages")
public class PackagesController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private PackageService packageService;

    @PostMapping
    public ResponseEntity<?> createPackage(@RequestHeader("token") String token ,@RequestBody Packages packages) {
        if(token== null || !jwtUtil.getToken().contains(token)){
            return new ResponseEntity("auth fail",
                    HttpStatus.BAD_REQUEST  );
        }
         if(packages.getPackage_name()!= null && !packages.getPackage_name().isEmpty() && packages.getPrice_per_unit() >0 ){
             if(packageService.createPackage(packages)){
                 return new ResponseEntity<>("create Success", HttpStatus.CREATED);
             }
         }
        return new ResponseEntity<>("create fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{packageId}")
    public ResponseEntity<?> updatePackage(@RequestHeader("token") String token,@PathVariable String packageId, @RequestBody Packages newPackage) {
        if(token== null || !jwtUtil.getToken().contains(token)){
            return new ResponseEntity("auth fail",
                    HttpStatus.BAD_REQUEST  );
        }
        int updatedPackage = packageService.updatePackage(packageId, newPackage);
        if (updatedPackage >0 ) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<?> deletePackage(@RequestHeader("token") String token,@PathVariable String packageId) {
        if(token== null || !jwtUtil.getToken().contains(token)){
            return new ResponseEntity("auth fail",
                    HttpStatus.BAD_REQUEST  );
        }
        int result = packageService.deletePackage(packageId);
        if(result>0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("remove fail", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Packages>> getAllPackages(@RequestHeader("token") String token) throws JsonProcessingException {
        if(token== null || !jwtUtil.getToken().contains(token)){
            return new ResponseEntity("auth fail",
                    HttpStatus.BAD_REQUEST  );
        }
        List<Packages> packagesList = packageService.getAllPackages();
        return new ResponseEntity<>(packagesList, HttpStatus.OK);
    }
    @PostMapping("/search/{sortFlag}")
    public ResponseEntity<?>  findPackagesByFilters(@RequestHeader("token") String token,@RequestBody RequestSearchModel searchModel ,@PathVariable Boolean sortFlag) {

        if(token== null || !jwtUtil.getToken().contains(token)){
            return new ResponseEntity("auth fail",
                    HttpStatus.BAD_REQUEST  );
        }
        String packageId = searchModel.getId();
        String packageName = searchModel.getName();
        Integer minPrice = searchModel.getMin_price();
        Integer maxPrice = searchModel.getMax_price();
        String startDate = searchModel.getStart_date();
        String endDate = searchModel.getEnd_date();
        return new ResponseEntity<>(packageService.search(packageId, packageName, minPrice, maxPrice, startDate, endDate,sortFlag), HttpStatus.OK);
    }
}
