package com.medilabo.sharedinterface;

import com.medilabo.shareddto.RiskProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "riskservice")
public interface RiskServiceClient {
    @RequestMapping(path = "/patients/risk/{id}", produces = "application/json")
    public RiskProfileDTO getRiskProfile(@PathVariable("id") Integer id);
}

