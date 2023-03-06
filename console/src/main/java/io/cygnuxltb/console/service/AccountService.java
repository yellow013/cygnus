package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.AccountDao;
import io.cygnuxltb.console.persistence.entity.AccountEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public class AccountService {

    @Resource
    private AccountDao accountDao;

    public List<AccountEntity> getAccount(int accountId) {
        return select(() -> accountDao.queryByAccountId(accountId), AccountEntity.class);
    }

}