package com.assignment.transport;

import com.assignment.utils.JsonUtil;
import io.restassured.response.Response;

import static com.assignment.config.Config.BASE_URL_API;

public abstract class BaseTransport {

    /**
     * Return service url
     * @param serviceName   -   service name
     * @return  -   url
     */
    protected static String getServiceUrl(String serviceName) {
        return BASE_URL_API + serviceName + "/";
    }

    /**
     * Conver response body to object
     * @param response  -   response
     * @param clazz -   model class
     * @return  -   instance of a model class
     * @param <T>
     */
    public static <T> T jsonToObject(Response response, Class<T> clazz) {
        return JsonUtil.jsonToObject(response.asPrettyString(), clazz);
    }

}
