package com.oswi.inventory.inventarios.responses;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    
    //Aqui vamos a crear las respuestas al momento de tratar de consumir la api.
    //Por ejemplo, si hay una respuesta existosa se devuelve un 200, si no un 404
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();


    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(String type, String code, String date) {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("type", type);
        map.put("code", code);
        map.put("date", date);

        metadata.add(map);
    }
    

}
