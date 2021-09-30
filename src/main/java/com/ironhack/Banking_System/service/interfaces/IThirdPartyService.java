package com.ironhack.Banking_System.service.interfaces;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface IThirdPartyService {

    void thirdPartyTransfer(Long userAccountId, Long transferAccountId,
                       String secretKey,
                       BigDecimal transferAmount);
}
