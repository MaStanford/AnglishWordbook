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
    void setParams(Map<String, Object> params);
    Map<String, Object> getParams();

    /**
     * Check if mParams are valid.
     * I.E. if we need a time stamp param, is it in the mParams map
     * @return
     */
    boolean isParamsValid();

    /**
     * Return the list of required mParams.  This is needed for isParamsValid
     * @return
     */
    List<String> getRequiredParams();

    /**
     * Returns if the queryParams are valid
     * @return
     */
    boolean isQueryParamsValid();

    /**
     * Returns the list of required query params.
     * @return
     */
    List<String> gettRequiredQueryParams();
}
