package br.edu.ufcg.so.jantar.models;

import br.edu.ufcg.so.jantar.enums.LifeStates;

import java.util.ArrayList;

public abstract class Dinner {

    protected ArrayList<LifeStates> states;
    protected Object[] philosophers;
    protected int totalPhilosophers;

    public void takeCutlery(int philosopherId) {};

    public void returnCutlery(int philosopherId) {};

    protected boolean canEat (int philosopherId) {
        return (getRightState(philosopherId) != LifeStates.EATING &&
                getLeftState(philosopherId) != LifeStates.EATING);
    }

    protected LifeStates getRightState (int philosopherId) {
        return states.get(getRight(philosopherId));
    }

    protected int getRight (int position) {
        return (position + 1) % totalPhilosophers;
    }

    protected LifeStates getLeftState (int philosopherId) {
        return states.get(getLeft((philosopherId)));
    }

    protected int getLeft (int position) {
        return (position + totalPhilosophers - 1) % totalPhilosophers;
    }

}
