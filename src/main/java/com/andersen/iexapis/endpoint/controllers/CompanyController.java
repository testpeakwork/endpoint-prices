package com.andersen.iexapis.endpoint.controllers;

import com.andersen.iexapis.endpoint.dto.json.CompanyData;
import com.andersen.iexapis.endpoint.services.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "stocks", description = "Company endpoint", basePath = "/stock")
@RequestMapping(path = "/${service.version}/stocks")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get stock data by filter")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyData> getPrice(@ApiParam(name = "List of companySymbols") @RequestParam List<String> companies,
                                      @ApiParam(format = "yyyy-MM-dd HH:mm", example = "2019-03-10 00:00", name = "Datetime from") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date from,
                                      @ApiParam(format = "yyyy-MM-dd HH:mm", example = "2019-03-10 00:00", name = "Datetime to") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date to) {
        return companyService.getCompaniesStocks(companies, from, to);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleInvalidRequest(ConstraintViolationException e) {
        return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}