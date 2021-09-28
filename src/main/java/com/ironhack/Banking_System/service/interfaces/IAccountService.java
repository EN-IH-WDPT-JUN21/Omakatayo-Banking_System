package com.ironhack.Banking_System.service.interfaces;

import java.math.BigDecimal;

public interface IAccountService {

    void accountHolderTransferPrimaryOwnerName(Long id, BigDecimal transferAmount);

    void accountHolderTransferSecondaryOwnerName(Long id, BigDecimal transferAmount);
}
