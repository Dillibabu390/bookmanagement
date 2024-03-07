
package com.bookin.bookmanagement.response;
import com.bookin.bookmanagement.constant.APIConstant;
import com.bookin.bookmanagement.constant.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class APIResponseUtil {
    private APIResponseUtil() {
    }

    public static Map<String, List<String>> getCORSHeaderMap() {
        Map<String, List<String>> map = new HashMap<>();

        map.put("Access-Control-Allow-Methods", Arrays.asList("GET", "PUT", "HEAD", "POST", "OPTIONS", "DELETE"));
        map.put("Access-Control-Allow-Credentials", List.of("true"));
        map.put("Access-Control-Allow-Headers", Arrays.asList("content-type", "authorization", "x-http-method-override", "origin,x-requested-with",
                "accept,withCredentials"));
        map.put("Cache-Control", List.of("no-cache"));
        map.put("Strict-Transport-Security", List.of("max-age=86400; includeSubDomains"));

        return map;
    }

    public static HttpHeaders getCorsHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(getCORSHeaderMap());

        return headers;
    }

    /**
     * Return the ResponseEntity by wrapping up the Data into APIOutput
     * @param data
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> getResponseWithData(Object data) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(data);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_OK);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    /**
     * This function is to avoid double wrapping if data is already in type APIOutput
     * @param data
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> getResponseWithApiOutputData(APIOutput data) {
        return new ResponseEntity<>(data, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithActualData(Object data) {
        return new ResponseEntity<>(data, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithMessage(String msg) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setMsg(msg);
        apiOutput.setData(null);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_OK);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithErrorMessage(String msg) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setMsg(msg);
        apiOutput.setData(null);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_ERROR);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithErrorMessageAndErrorCode(String msg) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setMsg(msg);
        apiOutput.setData(null);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_ERROR);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> getResponseForEmptyList() {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(null);
        apiOutput.setMsg(ResponseMessage.AUD_NO_RECORDS_FOUND.toString());
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_OK);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithDataAndMessage(Object data, String message) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(data);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_OK);
        apiOutput.setMsg(message);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseWithDataAndErrorMessage(Object data, String message) {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(data);
        apiOutput.setStatus(APIConstant.API_OUTPUT_STATUS_ERROR);
        apiOutput.setMsg(message);
        return new ResponseEntity<>(apiOutput, getCorsHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseByStatus(APIOutput data) {
        if (data.getStatus()) {
            return new ResponseEntity<>(data, getCorsHeader(), HttpStatus.OK);
        } else {
            return APIResponseUtil.getResponseWithErrorMessageAndErrorCode(data.getMsg());
        }
    }

}

