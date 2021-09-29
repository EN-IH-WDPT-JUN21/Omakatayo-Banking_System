package com.ironhack.Banking_System.service.interfaces;

import java.math.BigDecimal;
import java.util.Optional;

public interface IAccountService {

    void accountHolderTransferPrimaryOwnerName(Long userAccountId,
                                               Long transferAccountId,
                                               Optional<Long> primaryOwnerId,
                                               BigDecimal transferAmount);

    void accountHolderTransferSecondaryOwnerName(Long userAccountId,
                                                 Long transferAccountId,
                                                 Optional<Long> secondaryOwnerId,
                                                 BigDecimal transferAmount);
}
