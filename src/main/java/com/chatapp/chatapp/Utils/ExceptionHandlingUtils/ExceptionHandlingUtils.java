package com.chatapp.chatapp.Utils.ExceptionHandlingUtils;

import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import com.chatapp.chatapp.Dataloader.vo.RootResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingUtils {

    public static final String PROJECT_ERROR_CODE= "CAP_001";
    private static final String EXCEPTION_OCCURRED = "Exception Occurred";

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGenericException(Throwable e) {
        CaLogger.logs.error(EXCEPTION_OCCURRED, e);
        RootResponse response = new RootResponse()  ;
        response.setSysErrorMessage(e.getMessage());
        response.setSysErrorCode(PROJECT_ERROR_CODE);
        String cause = e.getCause() != null ? e.getCause().toString() : "No cause available in Throwable";
        response.setRetStatus(cause);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ChatAppException.class)
    public ResponseEntity<?> handleGenericException(ChatAppException chatAppException) {
        RootResponse rootResponse = new RootResponse();
        String cause = chatAppException.getCause() != null ? chatAppException.getCause().toString() : "No cause available in ChatAppException";
        rootResponse.setRetStatus(cause);
        rootResponse.setSysErrorCode(PROJECT_ERROR_CODE);
        rootResponse.setSysErrorMessage(chatAppException.getMessage());
        return new ResponseEntity<>(rootResponse, HttpStatus.NOT_FOUND);
    }
}
