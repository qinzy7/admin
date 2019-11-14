package com.qinzy.admin.server.utils;

import com.qinzy.admin.server.config.BaseResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
public class CheckUtils {

    public static BaseResult check(BindingResult br){
        if(br.getErrorCount()>0){
            br.hasErrors();
            List<ObjectError> allErrors = br.getAllErrors();
            FieldError fError = (FieldError) allErrors.get(0);
            return  BaseResult.error(fError.getDefaultMessage());
        }
        return null;
    }
}