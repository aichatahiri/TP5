package test;

import entities.Machine;
import entities.Salle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.MachineService;
import services.SalleService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MachineServiceTest {

    private MachineService machineService;
    private Machine machine;
    private Salle salle;
    private SalleService salleService;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();

        // Créer une salle avant de créer la machine
        salle = new Salle();
        salle.setCode("A101");
        salleService.create(salle);
        assertNotNull("Salle should have been created", salle.getId());

        // Créer la machine liée à la salle
        machine = new Machine();
        machine.setRef("MACH-001");
        machine.setDateAchat(new Date());
        machine.setSalle(salle);
        machineService.create(machine);
        assertNotNull("Machine should have been created", machine.getId());
    }

    @After
    public void tearDown() {
        // Supprimer la machine si elle existe encore
        Machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }

        // Supprimer la salle si elle existe encore
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertTrue("Machine should have a valid ID after creation", machine.getId() > 0);
    }

    @Test
    public void testFindById() {
        Machine found = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", found);
        assertEquals("Machine ref should match", machine.getRef(), found.getRef());
    }

    @Test
    public void testUpdate() {
        machine.setRef("MACH-002");
        boolean updated = machineService.update(machine);
        assertTrue("Machine should be updated successfully", updated);

        Machine refreshed = machineService.findById(machine.getId());
        assertEquals("Updated ref should match", "MACH-002", refreshed.getRef());
    }

    @Test
    public void testDelete() {
        boolean deleted = machineService.delete(machine);
        assertTrue("Machine should be deleted", deleted);

        Machine found = machineService.findById(machine.getId());
        assertNull("Machine should not exist after deletion", found);
    }

    @Test
    public void testFindBetweenDate() {
        Date yesterday = new Date(System.currentTimeMillis() - 86400000L); // hier
        Date today = new Date();
        List<Machine> machines = machineService.findBetweenDate(yesterday, today);

        assertNotNull("List of machines should not be null", machines);
        assertFalse("List of machines should not be empty", machines.isEmpty());
    }
}
