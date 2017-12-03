package com.bupt.client.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.exception.MessageException;
import com.bupt.client.utils.AjaxObject;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;

@ControllerAdvice
public class BaseControllerAdvice {

	@ExceptionHandler(Exception.class)
	public @ResponseBody String handlerException(Exception e) {
		AjaxObject res;
		if (e instanceof MessageException) {
			res = AjaxObject.newError(e.getMessage());
		} else {
			res = AjaxObject.newError(ResponseEnum.ERROR_20.getMsg());
		}
		return res.setCallbackType("").toString();
	}
	
}
