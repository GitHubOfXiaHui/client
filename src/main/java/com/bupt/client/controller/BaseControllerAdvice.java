package com.bupt.client.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.exception.MessageException;
import com.bupt.client.utils.AjaxObject;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;

@ControllerAdvice
public class BaseControllerAdvice {

	@ExceptionHandler(MessageException.class)
	public @ResponseBody String handlerMessageException(MessageException e) {
		return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody String handlerException() {
		return AjaxObject.newError(ResponseEnum.ERROR_20.getMsg()).setCallbackType("").toString();
	}
	
}
