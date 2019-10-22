package com.haipiao.registration.application;

import com.google.common.base.Preconditions;
import com.haipiao.registration.handler.VendSCHandler;
import com.haipiao.registration.handler.VerifySCHandler;
import com.haipiao.registration.req.VendSCRequest;
import com.haipiao.registration.req.VerifySCRequest;
import com.haipiao.registration.resp.VendSCResponse;
import com.haipiao.registration.resp.VerifySCResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private VendSCHandler vendSCHandler;
    @Autowired
    private VerifySCHandler verifySCHandler;

    @RequestMapping(value = "/security_code", method = RequestMethod.POST)
    public ResponseEntity<VendSCResponse> vendSecurityCode(@RequestParam("cell") String cell,
                                                           @RequestParam("country_code") String countryCode,
                                                           @RequestParam("type") String type) {
        logger.info("cell={}, country_code={}, type={}", cell, countryCode, type);
        Preconditions.checkArgument(StringUtils.isNotEmpty(cell));
        Preconditions.checkArgument(StringUtils.isNotEmpty(countryCode));
        Preconditions.checkArgument(StringUtils.isNotEmpty(type));
        VendSCRequest request = new VendSCRequest()
            .setCell(cell)
            .setCountryCode(countryCode)
            .setType(type);
        return vendSCHandler.handle(request);
    }

    @RequestMapping(value = "/verification", method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
    public ResponseEntity<VerifySCResponse> verifySecurityCode(@RequestBody VerifySCRequest request) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getCell()));
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getSecurityCode()));
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getType()));
        return verifySCHandler.handle(request);
    }

    @GetMapping("/healthz")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getHealthz() {
        return "ok";
    }

    @GetMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getRoot() {
        return "ok";
    }

    // TODO: are these generated? are they really useful?
    public void setVendSCHandler(VendSCHandler vendSCHandler) {
        this.vendSCHandler = vendSCHandler;
    }

    public void setVerifySCHandler(VerifySCHandler verifySCHandler) {
        this.verifySCHandler = verifySCHandler;
    }
}
