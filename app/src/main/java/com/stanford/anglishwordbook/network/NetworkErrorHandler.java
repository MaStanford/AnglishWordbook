package com.stanford.anglishwordbook.network;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by m.stanford on 3/3/15.
 */
class NetworkErrorHandler implements ErrorHandler {
    @Override public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401) {
            return new UnauthorizedException(cause);
        }
        return cause;
    }

    private class UnauthorizedException extends Throwable {
        public UnauthorizedException(RetrofitError cause) {
        }
    }
}
