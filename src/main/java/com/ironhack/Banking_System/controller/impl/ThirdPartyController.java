package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountHoldersDTO;
import com.ironhack.Banking_System.controller.dto.AllThirdPartyDTO;
import com.ironhack.Banking_System.dao.AccountHolder;
import com.ironhack.Banking_System.dao.ThirdParty;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AccountRepository accountRepository;


    // Mapping to create new ThirdParty user
    @PostMapping("/new/third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty newThirdParty(@RequestBody ThirdParty thirdParty) {
        thirdParty.setHashedKey(Objects.hash(thirdParty.getName(), thirdParty.getEmail()));
        return thirdPartyRepository.save(thirdParty);
    }

    // Mapping to show all ThirdParty's
    @GetMapping("/show/third_party")
    @ResponseStatus(HttpStatus.OK)
    public List<AllThirdPartyDTO> getAllThirdParty() {
        List<AllThirdPartyDTO> allThirdPartyDTOList = new ArrayList<>();
        for (ThirdParty thirdParty : thirdPartyRepository.findAll()) {
            AllThirdPartyDTO allThirdPartyDTO = new AllThirdPartyDTO(thirdParty.getId(),
                                                                     thirdParty.getName(),
                                                                     thirdParty.getEmail(),
                                                                     thirdParty.getHashedKey());
            allThirdPartyDTOList.add(allThirdPartyDTO);
        }
        return allThirdPartyDTOList;
    }


    /*@PatchMapping(
    "/third_party/transfer")
    @ResponseStatus(HttpStatus.OK)
    private String thirdPartyTransfer(@RequestHeader(value = "hashed-key") int hashedKey,
                                      @RequestParam Long userAccountId,
                                      @RequestParam Long transferAccountId,
                                      @RequestParam String secretKey,
                                      @RequestParam BigDecimal transferAmount) {
                    accountService.accountHolderTransferPrimaryOwnerName(userAccountId, transferAccountId, primaryOwnerId,
                                                                         transferAmount);
                    if (accountRepository.findById(userAccountId).get().getAccountType().equals(AccountType.CHECKING)) {
                        if (checkingRepository.findById(userAccountId).get().getBalance().getAmount().compareTo(checkingRepository.findById(userAccountId).get().getMinimumBalance().getAmount()) < 0) {
                            checkingRepository.findById(userAccountId).get().getBalance().decreaseAmount(transferAmount);
                        }
                    }
                } else {
                    return "Not enough founds on your account!!!";
                }
            }
            else {
                return "User account or transfer account not found!!!";
            }

        }
        else if (primaryOwnerId.isEmpty() && secondaryOwnerId.isPresent()) {
            if (accountRepository.findByIdAndSecondaryOwnerId(transferAccountId, secondaryOwnerId).isPresent()) {
                if (transferAmount.compareTo(accountRepository.findById(userAccountId).get().getBalance().getAmount()) <= 0) {
                    accountService.accountHolderTransferSecondaryOwnerName(userAccountId, transferAccountId, secondaryOwnerId,
                                                                           transferAmount);
                } else {
                    return "Not enough founds on your account!!!";
                }
            }
            else {
                return "User account or transfer account not found!!!";
            }
        }
        return "Jupiii :). Money have been transferred!";
    }*/
}
