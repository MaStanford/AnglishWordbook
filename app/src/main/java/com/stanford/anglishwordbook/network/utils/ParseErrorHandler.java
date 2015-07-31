package com.stanford.anglishwordbook.network.utils;

import com.parse.ParseException;

/**
 * Created by m.stanford on 7/31/15.
 */
public class ParseErrorHandler {

    public static void handleParseError(ParseException e) {
        switch (e.getCode()) {
            case ParseException.INVALID_SESSION_TOKEN:
                handleInvalidSessionToken();
                break;
            case ParseException.OBJECT_NOT_FOUND:
                handleObjectNotFound();
                break;

        }
    }

    private static void handleObjectNotFound() {

    }

    private static void handleInvalidSessionToken() {

    }
}
