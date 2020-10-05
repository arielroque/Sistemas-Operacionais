package br.edu.ufcg.so.jantar.solutions.semaphore;

import br.edu.ufcg.so.jantar.enums.LifeStates;
import br.edu.ufcg.so.jantar.models.Dinner;

import java.util.ArrayList;

public class SemaphoreSolution extends Dinner {

    public SemaphoreSolution(int totalPhilosophers) {
        super();
        this.totalPhilosophers = totalPhilosophers;
        this.states = new ArrayList<>(this.totalPhilosophers);
        this.philosophers = new Object[this.totalPhilosophers];

        for (int i = 0; i < this.totalPhilosophers; i++) {
            this.states.add(LifeStates.THINKING);
            this.philosophers[i] = new Object();
        }

        System.out.println(states);
    }

    @Override
    public void takeCutlery(int philosopherId) {
        states.set(philosopherId, LifeStates.HUNGRY);
        synchronized (philosophers[philosopherId]) {
            if (canEat(philosopherId)) {
                states.set(philosopherId, LifeStates.EATING);
            } else {
                try {
                    this.philosophers[philosopherId].wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                }
            }
            System.out.println(states);
        }
    }

    @Override
    public void returnCutlery(int philosopherId) {
        states.set(philosopherId, LifeStates.THINKING);
        //System.out.println("Philosopher " + philosopherId + " returned the cutlery.");
        System.out.println(states);

        if (getRightState(philosopherId) == LifeStates.HUNGRY &&
                getRightState(getRight(philosopherId)) != LifeStates.EATING) {
            states.set(getRight(philosopherId), LifeStates.EATING);
            synchronized (philosophers[getRight(philosopherId)]) {
                philosophers[getRight(philosopherId)].notify();
            }
        }
        if (getLeftState(philosopherId) == LifeStates.HUNGRY &&
                getLeftState(getLeft(philosopherId)) != LifeStates.EATING) {
            states.set(getLeft(philosopherId), LifeStates.EATING);
            synchronized (philosophers[getLeft(philosopherId)]) {
                philosophers[getLeft(philosopherId)].notify();
            }
        }
    }

}
