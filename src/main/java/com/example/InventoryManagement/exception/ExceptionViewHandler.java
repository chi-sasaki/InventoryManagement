package com.example.InventoryManagement.exception;

import com.example.InventoryManagement.controller.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {CompanyViewController.class, PartViewController.class, ProductViewController.class,
        InventoryViewController.class, StockViewController.class})

public class ExceptionViewHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(
            ResourceNotFoundException e,
            Model model) {

        model.addAttribute(
                "errorMessage",
                e.getMessage());

        return "error/not-found";
    }

    @ExceptionHandler(CannotDeleteException.class)
    public String handleDeleteError(
            CannotDeleteException e,
            Model model) {

        model.addAttribute(
                "errorMessage",
                e.getMessage());

        return "error/delete-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(
            Exception e,
            Model model) {

        model.addAttribute(
                "errorMessage",
                "システムエラーが発生しました");

        return "error/system-error";
    }
}
