package com.stanford.anglishwordbook.network.requests;

import java.util.List;
import java.util.Map;

/**
 * Interface for IGinRequest body
 *
 * Created by m.stanford on 3/4/15.
 */
public interface IRequest {

    /**
     * Params for the request
     */
    void setQueryParams(Map<String, String> params);
    Map<String, String> getQueryParams();

    /**
     * Check if mParams are valid.
     * I.E. if we need a time stamp param, is it in the mParams map
     * @return
     */
    boolean isQueryParamsValid();

    /**
     * Return the list of required mParams.  This is needed for isQueryParamsValid
     * @return
     */
    List<String> getRequiredQueryParams();

    /**
     * Params for the request
     */
    void setBodyParams(Map<String, String> params);
    Map<String, String> getBodyParams();

    /**
     * Check if mParams are valid.
     * I.E. if we need a time stamp param, is it in the mParams map
     * @return
     */
    boolean isBodyParamsValid();

    /**
     * Return the list of required mParams.  This is needed for isQueryParamsValid
     * @return
     */
    List<String> getRequiredBodyParams();

    void generateRequiredParams();
}
