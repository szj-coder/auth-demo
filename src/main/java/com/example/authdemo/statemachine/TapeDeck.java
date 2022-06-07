package com.example.authdemo.statemachine;

/**
 * @author szj
 * @date 2022/05/06 22:21
 */
public interface TapeDeck {
    void load(String nameOfTape);

    void eject();

    void start();

    void pause();

    void stop();

    void play();
}
