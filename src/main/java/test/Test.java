package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

import java.text.MessageFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        Salle salle1 = new Salle("A101");
        Salle salle2 = new Salle("B202");
        salleService.create(salle1);
        salleService.create(salle2);

        Machine machine1 = new Machine("M123", new Date(), salleService.findById(1));
        Machine machine2 = new Machine("M124", new Date(), salleService.findById(2));
        machineService.create(machine1);
        machineService.create(machine2);

        for (Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            for (Machine machine : salle.getMachines()) {
                System.out.println("  Machine: " + machine.getRef());
            }
        }

        Date d1 = new Date(110, 0, 1);
        Date d2 = new Date();
        System.out.println("Machines achetées entre " + d1 + " et " + d2 + ":");
        for (Machine m : machineService.findBetweenDate(d1, d2)) {
            System.out.println(MessageFormat.format("{0} achetée le {1}", m.getRef(), m.getDateAchat()));
        }
    }
}
