package com.miniBankend.repository;

import com.miniBankend.model.Packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PackageRepository{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public int save(Packages packages) {
        String sql = "INSERT INTO packages (package_name, price_per_unit)" +
                "VALUES('"+packages.getPackage_name()+"',"+packages.getPrice_per_unit()+");";
        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public String getAll() {
        String sql = "select array_to_json(array_agg(LIST.*)) as product from (select * from stock.packages ) LIST ";
        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql,params,String.class);
    }

    public int removePackage(String id) {
        String sql = "delete from stock.packages where package_id = "+id+";";
        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public int updatePackage(Packages packages) {
        String sql = "update stock.packages \n" +
                "set package_name = '"+packages.getPackage_name()+"' , price_per_unit = "+ packages.getPrice_per_unit()+" " +
                "where package_id = "+ packages.getPackage_id()+" ";
        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public Packages findById(String id) {
        String sql = "SELECT * FROM stock.packages WHERE package_id = "+id+"";
        Map<String, Object> parameters = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> {
            Packages packages = new Packages();
            packages.setPackage_id(rs.getString("package_id"));
            packages.setPackage_name(rs.getString("package_name"));
            packages.setPrice_per_unit(rs.getInt("price_per_unit"));
            packages.setCreate_date(rs.getString("create_date"));
            return packages;
        });
    }
    public List<Packages> findPackagesByFilters(String packageId, String packageName, Integer minPrice, Integer maxPrice, String startDate, String endDate) {
        String sql = "SELECT * FROM stock.packages WHERE 1=1";
        if(packageId!= null){
            sql += "AND package_id = '"+packageId+"' ";
        }
        if(packageName!= null){
            sql +="AND package_name LIKE '"+packageName+"' ";
        }
        if(minPrice>0 && minPrice < maxPrice){
            sql +="AND price_per_unit > "+minPrice+" ";
        }
        if(maxPrice>0 && minPrice < maxPrice){
            sql +="AND price_per_unit < "+maxPrice+" " ;
        }
        if(startDate!= null){
            sql +="AND create_date >= '"+startDate+"' " ;
        }
        if(endDate != null){
            sql += "AND create_date < '"+endDate+"' ";
        }
        Map<String, Object> parameters = new HashMap<>();
        return namedParameterJdbcTemplate.query(sql, parameters, (rs, rowNum) -> {
            Packages packages = new Packages();
            packages.setPackage_id(rs.getString("package_id"));
            packages.setPackage_name(rs.getString("package_name"));
            packages.setPrice_per_unit(rs.getInt("price_per_unit"));
            packages.setCreate_date(rs.getString("create_date"));
            return packages;
        });
    }



}