package com.miniBankend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int createNewUser(String Name , String emil, String password){
        String sql = "INSERT INTO stock.user (first_name, email, password)" +
                "VALUES ('"+Name+"',  '"+emil+"', '"+password+"');";

        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.update(sql,params);

    }

    public int checkUserExist(String email){
        String sql = "select count(*) from stock.user where email = '"+email+"'";

        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql,params,Integer.class);
    }


    public int checkUserLogin(String email , String password){
        String sql = "select count(*) from stock.user where email = '"+email+"' and password = '"+password+"'";

        HashMap<String,Object> params = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql,params,Integer.class);
    }

}
