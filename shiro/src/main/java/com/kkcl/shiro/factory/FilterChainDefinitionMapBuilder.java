package com.kkcl.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefintionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/**","authc");
        return map;
    }
}
