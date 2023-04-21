package com.miniBankend.service;

import com.miniBankend.model.Packages;
import com.miniBankend.repository.PackageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PackageServiceTest {

    @Autowired
    PackageService packageService;

    @MockBean
    PackageRepository packageRepository;

    @Test
    public void shouldBeReturnTrueWhenCreatePackage(){
        Packages packages = new Packages();
        packages.setPackage_id("test");
        packages.setPackage_name("test");
        packages.setCreate_date("test");
        packages.setPrice_per_unit(1);
        when(packageRepository.save(packages)).thenReturn(1);
        assertTrue(packageService.createPackage(packages));
    }

    @Test
    public void shouldBeReturnFalseWhenCreatePackageNotSuccess(){
        Packages packages = new Packages();
        packages.setPackage_id("test");
        packages.setPackage_name("test");
        packages.setCreate_date("test");
        packages.setPrice_per_unit(1);
        when(packageRepository.save(packages)).thenReturn(0);
        assertFalse(packageService.createPackage(packages));
    }


    @Test
    public void shouldBeReturnRowWhenUpdate(){
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
    public void shouldBeNotSuccessWhenUpdate(){
        Packages packages = new Packages();
        packages.setPackage_id("test");
        packages.setPackage_name("test");
        packages.setCreate_date("test");
        packages.setPrice_per_unit(1);
        when(packageRepository.findById(packages.getPackage_id())).thenReturn(null);
        when(packageRepository.updatePackage(packages)).thenReturn(1);
        assertEquals(-1,packageService.updatePackage(packages.getPackage_id(),packages));
    }


    @Test
    public void shouldBeRemovePackageSuccess(){

        when(packageRepository.removePackage("test")).thenReturn(1);
        assertEquals(1,packageService.deletePackage("test"));
    }

    @Test
    public void shouldBeReturnAllPackage(){

        when(packageRepository.getAll()).thenReturn("[{\"package_id\":3,\"package_name\":\"Package 3\",\"price_per_unit\":10,\"create_date\":\"2023-04-21T16:41:32.372088\"},{\"package_id\":4,\"package_name\":\"Package 4\",\"price_per_unit\":8,\"create_date\":\"2023-04-21T16:41:32.372088\"},{\"package_id\":5,\"package_name\":\"Package 5\",\"price_per_unit\":9,\"create_date\":\"2023-04-21T16:41:32.372088\"},{\"package_id\":6,\"package_name\":\"Package 6\",\"price_per_unit\":15,\"create_date\":\"2023-04-21T16:41:32.372088\"},{\"package_id\":7,\"package_name\":\"Package 7\",\"price_per_unit\":1,\"create_date\":\"2023-04-21T16:41:32.372088\"}]");
        assertNotNull(packageService.getAllPackages());
    }

    @Test
    public void shouldBeNotReturnAllPackage(){

        when(packageRepository.getAll()).thenReturn(null);
        assertNull(packageService.getAllPackages());
    }


    @Test
    public void shouldBReturnListPackage(){
        List<Packages> packageList = new ArrayList<>();

        Packages packages = new Packages();
        packages.setPackage_id("test");
        packages.setPackage_name("test");
        packages.setCreate_date("test");
        packages.setPrice_per_unit(2);
        packageList.add(packages);
        packages = new Packages();
        packages.setPackage_id("test2");
        packages.setPackage_name("test2");
        packages.setCreate_date("test2");
        packages.setPrice_per_unit(1);
        packageList.add(packages);
        when(packageRepository.findPackagesByFilters("test","test",0,0,"test","test")).thenReturn(packageList);
        assertNotNull(packageService.search("test","test",0,0,"test","test",true));
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
