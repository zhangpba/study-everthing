package com.study.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一键多值
 */
public class MultiValueMap {

    public static void main(String[] args) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("days","30");
        paramMap.put("size","9");
        paramMap.put("top","10");

        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM SITUATION_DATA where url = (:url) ");

        String[] params = {"days", "page", "size", "show", "ruleCategoryId", "assetType", "top", "stime", "etime", "type", "exclude", "gid", "sortBy", "segId", "label", "businessType", "datasrcInfo", "datasrcType", "limit", "pId", "includeTerminal", "areaType"};
        Arrays.asList(params).forEach(param -> {
            String value = paramMap.get(param);
            if (value != null) {
                sqlBuilder.append("and ");
                sqlBuilder.append(param);
                sqlBuilder.append("=");
                sqlBuilder.append(" (: ");
                sqlBuilder.append(param);
                sqlBuilder.append(") ");
            }
        });
        sqlBuilder.append("ORDER BY  timestamp limit 1");

        System.out.println(sqlBuilder.toString());

    }
}
