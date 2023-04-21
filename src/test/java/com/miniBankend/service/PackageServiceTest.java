package com.miniBankend.service;

import com.miniBankend.model.Packages;
import com.miniBankend.repository.PackageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PackageServiceTest {

    @Autowired
    PackageService packageService;

    @MockBean
    PackageRepository packageRepository;

    @Test
    public void shouldBeReturnTrueWhenUpdate(){
        Packages packages = new Packages();
        packages.setPackage_id("test");
        packages.setPackage_name("test");
        packages.setCreate_date("test");
        packages.setPrice_per_unit(1);
        when(packageRepository.findById(packages.getPackage_id())).thenReturn(packages);
        when(packageRepository.updatePackage(packages)).thenReturn(1);
        assertEquals(1,packageService.updatePackage(packages.getPackage_id(),packages));
    }


    @Test
    public void bubbleSort() {

        int[] arr = new int[5];
        arr[0] = 10;
        arr[1] = 8;
        arr[2] = 9;
        arr[3] = 12;
        arr[4] = 1;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <  n - i; j++) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for(int d = 0; d < n ; d ++){
            System.out.println(arr[d]);
        }

    }
}
