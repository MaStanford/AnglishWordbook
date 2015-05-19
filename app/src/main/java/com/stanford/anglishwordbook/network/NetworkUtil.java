package com.stanford.anglishwordbook.network;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by m.stanford on 3/3/15.
 */
public class NetworkUtil {

    /**
     * Convert a bundle to a Map.
     *
     * Retro accepts map headers and we pass around Bundles.
     *
     * @param bundle
     * @return
     */
    public static Map<String, Object> bundleToMap(Bundle bundle) {
        Map<String, Object> map =  new HashMap<>();
        for(String key : bundle.keySet()){
            map.put(key, bundle.get(key));
        }
        return map;
    }
}
