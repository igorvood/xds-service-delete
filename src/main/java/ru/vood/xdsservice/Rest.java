package ru.vood.xdsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Rest {

    @GetMapping("/qqq")
    public Map<String, String> dfsdf() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key1", "val2");
        stringStringHashMap.put("key2", "val2");
        return stringStringHashMap;
    }

    @GetMapping("/qqq1")
    public List<Map<String, String>> dfsdfdsd() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key1", "val2");
        stringStringHashMap.put("key2", "val2");
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        maps.add(stringStringHashMap);
        maps.add(stringStringHashMap);
        return maps;
    }

}
