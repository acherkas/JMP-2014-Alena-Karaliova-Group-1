package com.cdp.jboss.service;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.asset.ByteArrayAsset;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cdp.jboss.dao.ShareDao;
import com.cdp.jboss.dao.TradeTransactionDao;
import com.cdp.jboss.domain.Share;
import com.cdp.jboss.domain.TradeTransaction;
import com.cdp.jboss.domain.User;
import com.cdp.jboss.event.ShareEvent;
import com.cdp.jboss.service.TradeService;
import com.cdp.test.db.DataHandlingRule;
import com.cdp.test.db.PrepareData;


@RunWith(Arquillian.class)
public class TradeServiceTest {

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create("test.jar", JavaArchive.class)
                         .addPackages(false, Share.class.getPackage(), 
                                      ShareEvent.class.getPackage(),
                                      TradeTransactionDao.class.getPackage())
                         .addClass(TradeService.class)
                         .addManifestResource(new ByteArrayAsset("<beans />".getBytes()), ArchivePaths.create("beans.xml"))
                         .addManifestResource("inmemory-test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }

    @Rule
    public DataHandlingRule dataHandlingRule = new DataHandlingRule();
    
    @PersistenceContext
    EntityManager em;

    @Inject
    ShareDao shareDao;
    
    @Inject
    TradeTransactionDao tradeTransactionDao;
    
    @Inject
    TradeService tradeService;
    
    @Test
    @PrepareData("datasets/shares.xml")
    public void shouldAddShareToTheUserPortfolio() {
        // given
        User user = em.find(User.class, 1L);
        Share share = shareDao.getByKey("CTP");
        Integer amount = Integer.valueOf(1);
        
        // when
        tradeService.buy(user, share, amount);
        
        // then
        assertThat(user.getSharesAmount(share)).isEqualTo(3);
    }
    
    @Test
    @PrepareData("datasets/shares.xml")
    public void shouldRemoveShareFromTheUserPortfolio() {
        // given
        User user = em.find(User.class, 1L);
        Share share = shareDao.getByKey("CTP");
        Integer amount = Integer.valueOf(1);
        
        // when
        tradeService.sell(user, share, amount);
        
        // then
        assertThat(user.getSharesAmount(share)).isEqualTo(1);
    }
    
    @Test
    @PrepareData("datasets/shares.xml")
    public void shouldLogTradeTransactionAfterBuyingShare() {
        // given
        User user = em.find(User.class, 1L);
        Share share = shareDao.getByKey("CTP");
        Integer amount = Integer.valueOf(1);
        
        // when
        tradeService.buy(user, share, amount);
        
        // then
        List<TradeTransaction> transactions = tradeTransactionDao.getTransactions(user);
        assertThat(transactions).hasSize(1);
    }

}
