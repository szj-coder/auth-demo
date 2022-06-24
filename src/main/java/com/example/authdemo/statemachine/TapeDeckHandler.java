package com.example.authdemo.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.statemachine.StateMachine;
import org.apache.mina.statemachine.StateMachineFactory;
import org.apache.mina.statemachine.StateMachineProxyBuilder;
import org.apache.mina.statemachine.annotation.State;
import org.apache.mina.statemachine.annotation.Transition;
import org.apache.mina.statemachine.annotation.Transitions;

/**
 * @author szj
 * @date 2022/05/06 22:21
 */
@Slf4j
public class TapeDeckHandler {
    @State
    public static final String EMPTY = "Empty";
    @State
    public static final String LOADED = "Loaded";
    @State
    public static final String PLAYING = "Playing";
    @State
    public static final String PAUSED = "Paused";

    @Transition(on = "load", in = EMPTY, next = LOADED)
    public void loadTape(String nameOfTape) {
        log.info("Tape '" + nameOfTape + "' loaded");
    }

    @Transitions({
            @Transition(on = "play", in = LOADED, next = PLAYING),
            @Transition(on = "play", in = PAUSED, next = PLAYING)
    })
    public void playTape() {
        log.info("Playing tape");
    }

    @Transition(on = "pause", in = PLAYING, next = PAUSED)
    public void pauseTape() {
        log.info("Tape paused");
    }

    @Transition(on = "stop", in = PLAYING, next = LOADED)
    public void stopTape() {
        log.info("Tape stopped");
    }

    @Transition(on = "eject", in = LOADED, next = EMPTY)
    public void ejectTape() {
        log.info(">> Tape ejected");
    }

    public static void main(String[] args) {
        TapeDeckHandler handler = new TapeDeckHandler();
        StateMachine sm = StateMachineFactory.getInstance(Transition.class).create(TapeDeckHandler.EMPTY, handler);
        TapeDeck deck = new StateMachineProxyBuilder().create(TapeDeck.class, sm);

        deck.load("The Knife - Silent Shout");
        deck.play();
        deck.pause();
        deck.play();
        deck.stop();
        deck.eject();

        log.info("-------------------------");
        handler = new TapeDeckHandler();
        StateMachine sm1 = StateMachineFactory.getInstance(Transition.class).create(TapeDeckHandler.LOADED, handler);
        TapeDeck deck1 = new StateMachineProxyBuilder().create(TapeDeck.class, sm1);
        deck1.eject();
        log.info(sm1.getStates().toString());
    }
}
