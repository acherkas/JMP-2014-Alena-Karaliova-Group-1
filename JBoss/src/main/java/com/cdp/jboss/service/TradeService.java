package com.cdp.jboss.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.cdp.jboss.domain.Share;
import com.cdp.jboss.domain.User;
import com.cdp.jboss.event.Buy;
import com.cdp.jboss.event.Sell;
import com.cdp.jboss.event.ShareEvent;

@Stateless
public class TradeService {

    @Inject @Buy
    private Event<ShareEvent> buyEvent;
    
    @Inject @Sell
    private Event<ShareEvent> sellEvent;
    
    public void buy(User user, Share share, Integer amount) {
        user.addShares(share, amount);
        ShareEvent shareEvent = new ShareEvent(share, user, amount);
        buyEvent.fire(shareEvent);
    }
    
    public void sell(User user, Share share, Integer amount) {
        user.removeShares(share, amount);
        ShareEvent shareEvent = new ShareEvent(share, user, amount);
        sellEvent.fire(shareEvent);
    }
    
}
