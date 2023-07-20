package com.example.authdemo.statemachine;


import com.example.authdemo.learn.statemachine.TapeDeck;
import com.example.authdemo.learn.statemachine.TapeDeckHandler;
import org.apache.mina.statemachine.StateMachine;
import org.apache.mina.statemachine.StateMachineFactory;
import org.apache.mina.statemachine.StateMachineProxyBuilder;
import org.apache.mina.statemachine.annotation.Transition;
import org.apache.mina.statemachine.event.UnhandledEventException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author szj
 * @date 2022/06/23 17:03
 */
public class TapeDeckHandlerTest {

    @Test
    public void stateException() {
        try {
            TapeDeckHandler handler = new TapeDeckHandler();
            StateMachine sm = StateMachineFactory.getInstance(Transition.class).create(TapeDeckHandler.LOADED, handler);
            TapeDeck deck1 = new StateMachineProxyBuilder().create(TapeDeck.class, sm);
            deck1.load("The Knife -- Silent Shout");
            System.out.println(sm.getStates());
            deck1.eject();
        } catch (UnhandledEventException e) {
            assertTrue(true);
            return;
        }
        fail();
    }
}