package org.mapleir.dot4j.event.impl;

import org.mapleir.dot4j.event.Event;
import net.minecraft.util.Hand;

public class HandSwingEvent extends Event {

    private Hand hand;

    public HandSwingEvent(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
