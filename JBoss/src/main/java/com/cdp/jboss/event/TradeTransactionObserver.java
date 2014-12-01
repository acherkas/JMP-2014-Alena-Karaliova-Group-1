package com.cdp.jboss.event;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.cdp.jboss.dao.TradeTransactionDao;
import com.cdp.jboss.domain.TradeTransaction;
import com.cdp.jboss.domain.TransactionType;

@Singleton
public class TradeTransactionObserver implements Serializable {

    private static final long serialVersionUID = -7956754109902696136L;

    @Inject
    TradeTransactionDao tradeTransactionDao;
    
    public void shareSold(@Observes @Sell ShareEvent event) {
        TradeTransaction tradeTransaction = new TradeTransaction(event.getUser(), event.getShare(), event.getAmount(), TransactionType.BUY);
        tradeTransactionDao.save(tradeTransaction);
    }
    
    public void shareBought(@Observes @Buy ShareEvent event) {
        TradeTransaction tradeTransaction = new TradeTransaction(event.getUser(), event.getShare(), event.getAmount(), TransactionType.BUY);
        tradeTransactionDao.save(tradeTransaction);
    }
    
}
