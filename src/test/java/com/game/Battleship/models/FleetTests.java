package com.game.Battleship.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTests {

    @Test
    public void testThatFleetContainsFiveShips(){
        Fleet fleet = new Fleet();
        List<Ship> allShips = fleet.getFleet();
        assertEquals(allShips.size(), 5);
    }
    @Test
    public void testThatFleetContainsSubmarine(){
        Fleet fleet = new Fleet();
        List<Ship> allShips = fleet.getFleet();
        allShips.get(3).getType();
        assertEquals(allShips.get(3).getType(), Cell.Type.SUBMARINE);
    }

    @Test
    public void testThatFleetContainsDstroyer(){
        Fleet fleet = new Fleet();
        List<Ship> allShips = fleet.getFleet();
        allShips.get(4).getType();
        assertEquals(allShips.get(4).getType(), Cell.Type.DESTROYER);
    }
}
