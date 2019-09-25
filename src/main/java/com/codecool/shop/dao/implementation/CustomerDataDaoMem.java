package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CustomerDataDao;

import java.util.HashMap;
import java.util.Map;

public class CustomerDataDaoMem implements CustomerDataDao {

    public Map<String, String> CustomerData = new HashMap<String, String>();
    private static CustomerDataDaoMem instance = null;

    public static CustomerDataDaoMem getInstance() {
        if (instance == null) {
            instance = new CustomerDataDaoMem();
        }
        return instance;
    }

    @Override
    public void put(String key, String value) {
        CustomerData.put(key, value);
    }

    @Override
    public void remove(String key) {
        CustomerData.remove(key);
    }

    @Override
    public void replace(String key, String value) {
        CustomerData.replace(key, value);
    }

    @Override
    public Object get(Object key) {
        return CustomerData.get(key);
    }

    @Override
    public void clear(){
        CustomerData.clear();
    }
}
