package com.cdp.jboss.controller;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import javax.enterprise.inject.Produces;
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

import com.cdp.jboss.controller.PortfolioController;
import com.cdp.jboss.dao.ShareDao;
import com.cdp.jboss.dao.TradeTransactionDao;
import com.cdp.jboss.domain.Share;
import com.cdp.jboss.domain.TradeTransaction;
import com.cdp.jboss.domain.User;
import com.cdp.jboss.domain.User.LoggedIn;
import com.cdp.jboss.event.ShareEvent;
import com.cdp.jboss.service.TradeService;
import com.cdp.test.cdi.scope.RequiredScope;
import com.cdp.test.cdi.scope.ScopeHandlingRule;
import com.cdp.test.cdi.scope.ScopeType;
import com.cdp.test.db.DataHandlingRule;
import com.cdp.test.db.PrepareData;

@RunWith(Arquillian.class)
public class PortfolioControllerTest {

    @Rule
    public DataHandlingRule dataHandlingRule = new DataHandlingRule();
    
    @Rule
    public ScopeHandlingRule scopeHandlingRule = new ScopeHandlingRule();
    
    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create("test.jar", JavaArchive.class)
                         .addPackages(false, Share.class.getPackage(), 
                                             ShareEvent.class.getPackage())
                         .addClasses(TradeTransactionDao.class, 
                                     ShareDao.class, 
                                     TradeService.class,
                                     PortfolioController.class)
                         .addManifestResource(new ByteArrayAsset("<beans />".getBytes()), ArchivePaths.create("beans.xml"))
                         .addManifestResource("inmemory-test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Inject 
    ShareDao shareDao;
    
    @Inject
    TradeTransactionDao tradeTransactionDao;
    
    @Inject
    PortfolioController portfolioController;
    
    @Test
    @PrepareData("datasets/shares.xml")
    @RequiredScope(ScopeType.CONVERSATION)
    public void shouldAddCtpShareToUserPortfolio() {
        // given
        User user = portfolioController.getUser();
        Share ctpShare = shareDao.getByKey("CTP");
        
        // when
        portfolioController.buy(ctpShare, 1);
        portfolioController.confirm();
        
        // then
        assertThat(user.getSharesAmount(ctpShare)).isEqualTo(3);
    }
    
    @Test
    @PrepareData("datasets/shares.xml")
    @RequiredScope(ScopeType.CONVERSATION)
    public void shouldNotModifyUserPortfolioWhenCancelProcess() {
        // given
        User user = portfolioController.getUser();
        Share ctpShare = shareDao.getByKey("CTP");
        
        // when
        portfolioController.buy(ctpShare, 1);
        portfolioController.cancel();
        
        // then
        assertThat(user.getSharesAmount(ctpShare)).isEqualTo(2);
    }
    
    @Test
    @RequiredScope(ScopeType.CONVERSATION)
    @PrepareData("datasets/shares.xml")
    public void shouldRecordTransactionWhenUserBuysAShare() {
        // given
        User user = portfolioController.getUser();
        Share ctpShare = shareDao.getByKey("CTP");
        
        // when
        portfolioController.buy(ctpShare, 1);
        portfolioController.confirm();

        // then
        List<TradeTransaction> transactions = tradeTransactionDao.getTransactions(user);
        assertThat(transactions).hasSize(1);
    }
    
    @Produces @LoggedIn User loggedInUser() {
        return entityManager.find(User.class, 1L);
    }
    
}
