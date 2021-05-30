package com.dogankamer.repository;

import com.dogankamer.controller.model.ParkedVehicle;
import com.dogankamer.controller.model.StatusResponse;
import com.dogankamer.exception.e.NoEmptySlotException;
import com.dogankamer.exception.e.NoTicketException;
import com.dogankamer.repository.model.Vehicle;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Scope("singleton")
public class Garage {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    @Getter
    private ArrayList<Vehicle> slots;
    private Map<Integer, Integer> ticketSlotMap;
    private int ticketSequence = 0;

    @Value("${garage.capacity:10}")
    private int capacity;

    @PostConstruct
    public void initialize() {
        slots = new ArrayList<>(Arrays.asList(new Vehicle[capacity]));
        ticketSlotMap = new HashMap<>();
    }

    public Integer placeCar(Vehicle vehicle) {

        final int width = vehicle.getType().getWidth();
        int emptySlot = 0;
        int indexStart = -1;
        int index = 0;
        try {
            readWriteLock.writeLock().lock();

            Iterator<Vehicle> iterator = slots.iterator();
            while (iterator.hasNext()) {
                Vehicle next = iterator.next();
                if (next == null) {
                    if (indexStart == -1) {
                        indexStart = index;
                    }
                    emptySlot++;
                } else {
                    indexStart = -1;
                    emptySlot = 0;
                }
                if (emptySlot == width) {
                    vehicle.setIndex(indexStart);
                    for (int i = indexStart; i <= index; i++) {
                        slots.set(i, vehicle);
                    }
                    Integer ticket = ++ticketSequence;
                    vehicle.setTicket(ticket);
                    ticketSlotMap.put(ticket, indexStart);
                    return ticket;
                }
                index++;
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        throw new NoEmptySlotException("There is no slot for [" + vehicle + "]");
    }

    public Vehicle leaveCar(Integer ticket) {
        try {
            readWriteLock.writeLock().lock();

            Integer index = ticketSlotMap.remove(ticket);
            if (index == null) {
                throw new NoTicketException("Ticket [" + ticket + "] not found.") ;
            } else {
                Vehicle vehicle = slots.get(index);
                for (int i = index; i < index + vehicle.getType().getWidth(); i++) {
                    slots.set(i, null);
                }
                return vehicle;
            }

        } finally {
            readWriteLock.writeLock().unlock();

        }
    }

    public StatusResponse getStatus() {
        try {
            readWriteLock.readLock().lock();

            ArrayList<ParkedVehicle> list = new ArrayList<>();
            for (int i = 0 ; i < slots.size(); ) {
                Vehicle vehicle = slots.get(i);
                if (vehicle != null) {
                    ParkedVehicle parkedVehicle = ParkedVehicle.builder()
                            .colour(vehicle.getColour())
                            .plate(vehicle.getPlate())
                            .ticket(vehicle.getTicket())
                            .slots(IntStream.range(vehicle.getIndex() + 1, vehicle.getIndex() + vehicle.getType().getWidth() + 1).toArray())
                            .build();
                    list.add(parkedVehicle);
                    i += vehicle.getType().getWidth();
                } else {
                    i++;
                }
            }
            return StatusResponse.builder().list(list).build();

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
